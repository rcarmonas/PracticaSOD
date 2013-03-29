package clases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class HiloAtacante extends Thread {
	private boolean activo;
	private Controlador ctrl;
	private Division trabajo;
	private boolean encontrado;
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
			//Inicialización de datos
			this.encontrado = false;
			this.resultado = null;
			StringBuffer cad = new StringBuffer();
			//Obtengo un trabajo:
			trabajo = ctrl.getDivision();
			//Añade la primera letra:
			cad.append(trabajo.c);
			//Comprueba todas las cadenas de forma recursiva:
			probarCadenas(cad);
			//Comunica el fin de un trabajo:
			ctrl.finTrabajo(trabajo.trabajo.id, resultado);
		}
	}
	
	/**
	 * Para el hilo e informa al controlador de que el trabajo no se ha terminado
	 */
	public void disactive()
	{
		if(this.trabajo!=null && !this.encontrado)
			ctrl.setDivision(trabajo);
		this.activo = false;
	}
	/**
	 * Prueba de forma recursiva todas las cadenas posibles hasta encontrar
	 * un resultado o terminar
	 * @param str Cadena que llevamos por ahora
	 */
	private void probarCadenas(StringBuffer str)
	{
		String aux;
		switch(trabajo.trabajo.tipo)
		{
			case ControladorImpl.MD5:
				//En caso de tratarse de un trabajo MD5:
				aux = StrManager.MD5(str.toString());
				if(aux!=null)
				if(aux.equals(trabajo.trabajo.cadena))
				{
					this.encontrado = true;
					this.resultado = aux;
				}
			break;
			case ControladorImpl.SHA:
				//En caso de tratarse de un trabajo SHA1:
				aux = StrManager.SHA(str.toString());
				if(aux!=null)
				if(aux.equals(trabajo.trabajo.cadena))
				{
					this.encontrado = true;
					this.resultado = aux;
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

					salida.write(cadena+str.toString()+"\0");
					salida.flush();

					String a=entrada.readLine();
					if(a.equals("true"))
					{
						this.encontrado = true;
						this.resultado = str.toString();
					}
					socket.close();

				} catch (IOException e) {}
			break;
		}
		
		if(str.length()<=trabajo.trabajo.tam_maximo && activo && !encontrado)
			for(char j=(char)StrManager.INICIO;j<=StrManager.FIN; j++)
			{
				str.append(j);
				probarCadenas(str);
				str.deleteCharAt(str.length()-1);
			}
	}
}
