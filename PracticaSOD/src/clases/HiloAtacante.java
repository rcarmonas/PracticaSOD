package clases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.Socket;
/**
 * Hilo encargado de realizar los ataques. Para ello obtiene los trabajos
 * de una cola y los procesa para luego informar al controlador de que el 
 * trabajo ha sido finalizado.
 * @author Rafael Carmona Sánchez
 * @author José Manuel Herruzo Ruiz
 */
public class HiloAtacante extends Thread {
	private boolean activo;//indica que se pare y se elimine (utilizado cuando se quitan hilos)
	private Controlador ctrl;
	public Division trabajo;
	private boolean encontrado;//indica que se pare y coja un nuevo trabajo de la cola (utilizado cuando se borran trabajos o se encuentra la solucion)
	private String resultado;
	
	public HiloAtacante(Controlador ctrl)
	{
		activo = true;
		this.ctrl = ctrl;
	}
	
	public void run()
	{
		while(activo)
		{
			//Obtengo un trabajo:
			trabajo = ctrl.getDivision();
			if(!activo)
			{
				ctrl.setDivision(trabajo);//si se desactivo mientras esperaba en la cola, devuelve el trabajo
				break;
			}
			//Inicialización de datos
			this.encontrado = false;
			this.resultado = "";
			StringBuffer cad = new StringBuffer();
			//Añade la primera letra:
			cad.append(trabajo.c);
			//Comprueba todas las cadenas de forma recursiva:
			try{
				probarCadenas(cad);
			}catch (Exception e)
			{
				ctrl.setDivision(trabajo);
			}
			if(activo)//si esta activo
				ctrl.finTrabajo(trabajo.trabajo.id, resultado);//comunica el fin del trabajo
			else//si se ha desactivado
				ctrl.setDivision(trabajo);//devuelve la division a la cola
			trabajo=null;
		}
	}
	
	/**
	 * Para el hilo y devuelve la división que estaba procesando a la cola
	 */
	public void disactive()
	{
		this.activo = false;
	}
	public void interrumpir()
	{
		this.encontrado=true;
	}
	/**
	 * Prueba de forma recursiva todas las cadenas posibles hasta encontrar
	 * un resultado o terminar
	 * @param str Cadena que llevamos por ahora
	 * @throws IOException 
	 */
	private void probarCadenas(StringBuffer str) throws IOException
	{
		if(trabajo.trabajo.tipo == ControladorImpl.RSA)
		{
			//Datos necesarios:
			BigInteger n = new BigInteger(trabajo.trabajo.cadena);
			BigInteger sqrt = BigMath.bigIntSqRootCeil(n);
			BigInteger nPart = new BigInteger((int)trabajo.c+"");
			BigInteger nParts = new BigInteger(ControladorImpl.MAX_PROGRESS_rsa+"");
			BigInteger[] part_size = sqrt.divideAndRemainder(nParts);
			BigInteger i = part_size[0].multiply(nPart);
			BigInteger fin;
			BigInteger p = null;
			//Se establece el límite superior
			fin = i.add(part_size[0]);
			if(nPart.compareTo(nParts.subtract(BigInteger.ONE))==0)
				fin = sqrt;
			if(i.compareTo(BigInteger.ZERO)==0)
				i = i.add(new BigInteger("2"));
			//Se ponen a punto variables para el bucle
			boolean resultadoEncontrado = false;
			sqrt = null;
			nPart = null;
			nParts = null;
			part_size = null;			
			//Bucle principal: Busca los factores primos
	        for(; i.compareTo(fin)<=0 && !encontrado && activo; i=i.nextProbablePrime())
	        {
	        	if(n.mod(i).equals(BigInteger.ZERO))
	        	{
	        		this.encontrado=true;
	        		resultadoEncontrado = true;
	        		p = i.add(BigInteger.ZERO);
	        	}
	        }
	        if(resultadoEncontrado)
	        {
	        	BigInteger q = n.divide(p);
	        	BigInteger phi = q.subtract(BigInteger.ONE).multiply(p.subtract(BigInteger.ONE));
	        	BigInteger e = new BigInteger(trabajo.trabajo.usuario);
		 		System.out.println(n + " " + i);
	        	BigInteger d = e.modInverse(phi);
	        	this.resultado =  "d=" + d.toString() + ", " +  "n=" + n.toString();
	        }
		}
		else if(trabajo.trabajo.diccionario==0)
		{
			probarCombinacion(str.toString());
			
			if(str.length()<=trabajo.trabajo.tam_maximo-1 && activo && !encontrado)
				for(char j=(char)StrManager.INICIO;j<=StrManager.FIN; j++)
				{
					str.append(j);
					probarCadenas(str);
					str.deleteCharAt(str.length()-1);
				}
		}
		else
		{
			String[] dics = {"dic", "lemario", "nombres"};
			String aux = dics[trabajo.trabajo.diccionario-1];
			FileInputStream fstream = new FileInputStream(aux +"/"+(int)trabajo.c+".txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			String strLine;
			
			while ((strLine = br.readLine()) != null && activo && !encontrado)
				probarCombinacion(strLine.trim().toLowerCase());
			
			in.close();
		}
	}
	/**
	 * Prueba la combinación actual para averiguar si es la que buscamos
	 * @param actual combinación a probar
	 */
	private void probarCombinacion(String actual)
	{
		if(activo&&!encontrado)
		{
			String aux;
			switch(trabajo.trabajo.tipo)
			{
				case ControladorImpl.MD5:
					//En caso de tratarse de un trabajo MD5:
					aux = StrManager.MD5(actual);
					if(aux!=null)
					if(aux.equals(trabajo.trabajo.cadena))
					{
						this.encontrado = true;
						this.resultado = actual;
					}
				break;
				case ControladorImpl.SHA:
					//En caso de tratarse de un trabajo SHA1:
					aux = StrManager.SHA(actual.toString());
					if(aux!=null)
					if(aux.equals(trabajo.trabajo.cadena))
					{
						this.encontrado = true;
						this.resultado = actual;
					}
				break;
				case ControladorImpl.RED:
					//En caso de tratarse de un trabajo de red:
					try
					{
						String cadena="USER "+trabajo.trabajo.usuario+" PASSWORD ";
						Socket socket=new Socket(trabajo.trabajo.cadena,trabajo.trabajo.puerto);
	
						InputStreamReader ir = new InputStreamReader(socket.getInputStream());
						BufferedReader entrada=new BufferedReader(ir);
						OutputStreamWriter or = new OutputStreamWriter(socket.getOutputStream());
						BufferedWriter salida=new BufferedWriter(or);
						salida.write(cadena+actual+"\0");
						salida.flush();
	
						String a=entrada.readLine();
						if(a.equals("true"))
						{
							this.encontrado = true;
							this.resultado = actual;
							socket.close();
						}
						socket.close();

					} catch (IOException e) {
						
						System.out.println("Error al intentar conectar.");
					}
				break;
			}
		}
	}
}
