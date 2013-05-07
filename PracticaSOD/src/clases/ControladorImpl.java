package clases;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Clase encargada de implementar el controlador, el cual se encargará de manejar
 * los trabajos y divisiones para su correcto funcionamiento
 * @author Rafael Carmona Sánchez
 * @author José Manuel Herruzo Ruiz
 */

public class ControladorImpl extends ControladorPOA{
	public static int MAX_PROGRESS = StrManager.FIN - StrManager.INICIO;
	public static int MAX_PROGRESS_dic = 10;
	public static int MAX_PROGRESS_rsa = 256;
	public static final int MD5 = 0;
	public static final int SHA = 1;
	public static final int RED = 2;
	public static final int RSA = 3;
	public LinkedBlockingQueue<Division> queue;
	public ArrayList<Trabajo> trabajos;
	
	/**
	 * Constructor vacío. Crea una cola de divisiones y una lista de trabajos.
	 */
	ControladorImpl(){
		queue=new LinkedBlockingQueue<Division>();
		trabajos=new ArrayList<Trabajo>();
	}
	
	/**
	 * Comunica al servidor el fin de un trabajo por parte de un cliente
	 * @param id Identificador del trabajo terminado
	 * @param clave Resultado obtenido, si no se ha encontrado, será una cadena vacía
	 */
	@Override
	public void finTrabajo(int id, String clave) 
	{
		int maxProgress = ControladorImpl.MAX_PROGRESS;
		if(trabajos.get(id).tipo == RSA)
			maxProgress = this.MAX_PROGRESS_rsa;
		else if(trabajos.get(id).diccionario !=0)
			maxProgress = this.MAX_PROGRESS_dic;
		try{
		if(!clave.equals(""))//Acabado!
		{
			System.out.println("Trabajo: "+id+" Resultado: "+clave);
			trabajos.get(id).progress = maxProgress;
			//Procesar contraseña obtenida
			trabajos.get(id).resultado=clave;
		}
		else if(trabajos.get(id).progress<maxProgress)//Aumento el progreso...
			trabajos.get(id).progress++;
		}catch(Exception e){}
	}

	/**
	 * @return Devuelve la próxima división en la cola de trabajos.
	 */
	@Override
	public Division getDivision()
	{
		try {
			return this.queue.take();
		} catch (InterruptedException e) {
			return null;
		}
	}

	/**
	 * Añade divisiones de trabajos a la cola
	 * @param t División que será añadida a la cola
	 * @return Devuelve true si se ha añadido correctamente y false en caso contrario
	 */
	@Override
	public boolean setDivision(Division t) 
	{
		try {
			this.queue.put(t);
			return true;
		} catch (InterruptedException e) 
		{
			return false;
		}
	}
	
	/**
	 * Divide un trabajo en un número determinado de divisiones y las almacena en la cola
	 * @param t Trabajo a dividir
	 */
	@Override
	public void dividirTrabajo(Trabajo t)
	{
		//inserta las divisiones
		if(t.tipo == RSA)
		{
			BigInteger aux = new BigInteger(t.cadena);
			BigInteger limite = new BigInteger("256");
			if(aux.compareTo(limite)<0)
				setDivision(new Division(t, (char)255));
			else
				for(int i=0; i<ControladorImpl.MAX_PROGRESS_rsa; i++)
					setDivision(new Division(t, (char)i));
		}
		else if(t.diccionario==0)
			for(int i=StrManager.INICIO; i<=StrManager.FIN; i++)
				setDivision(new Division(t, (char)i));
		else
			for(int i=1; i<=10; i++)
				setDivision(new Division(t, (char)i));
	}
	
	/**
	 * Crea un trabajo de tipo MD5
	 * @param cadena Cadena resultado
	 * @param tam_maximo Tamaño máximo de la clave que será evaluado
	 * @return Trabajo creado
	 * @param Valor que determina si se usará un diccionario y cual se usaría.
	 */
	@Override
	public synchronized Trabajo crearMD5(String cadena, int tam_maximo, int dic)
	{
		int id = trabajos.size();
		Trabajo aux = new Trabajo(false,id, MD5, dic, cadena, 0, "", tam_maximo, 0,"");
		trabajos.add(aux);
		dividirTrabajo(aux);
		return aux;
	}

	/**
	 * Crea un trabajo de tipo SHA1
	 * @param cadena Cadena resultado
	 * @param tam_maximo Tamaño máximo de la clave que será evaluado
	 * @param Valor que determina si se usará un diccionario y cual se usaría.
	 * @return Trabajo creado
	 */
	@Override
	public synchronized Trabajo crearSHA(String cadena, int tam_maximo, int dic)
	{
		int id = trabajos.size();
		Trabajo aux = new Trabajo(false,id, SHA,dic,  cadena, 0, "", tam_maximo, 0,"");
		trabajos.add(aux);
		dividirTrabajo(aux);
		return aux;
	}

	/**
	 * Crea un trabajo de tipo de red
	 * @param cadena Dirección del servidor de red que será atacado
	 * @param puerto Puerto del servidor de red que será atacado
	 * @param Usuario con el que se atacará el servidor
	 * @param tam_maximo Tamaño máximo de la clave que será evaluado
	 * @return Trabajo creado
	 * @param Valor que determina si se usará un diccionario y cual se usaría.
	 */
	@Override
	public synchronized Trabajo crearRed(String cadena, int puerto, String usuario, int tam_maximo, int dic) 
	{
		Trabajo aux = new Trabajo();
		int id = trabajos.size();
		aux.id = id;
		aux.tipo = RED;
		aux.progress = 0;
		aux.puerto = puerto;
		aux.tam_maximo = tam_maximo;
		aux.usuario = usuario;
		aux.cadena = cadena;
		aux.resultado="";
		aux.diccionario = dic;
		trabajos.add(aux);
		dividirTrabajo(aux);
		return aux;
	}
	/**
	 * Crea un trabajo de tipo RSA
	 * @param cadena1 Parámetro n de la clave pública RSA
	 * @param cadena2 Parámetro e de la clave pública RSA
	 * @return Trabajo creado
	 */
	@Override
	public synchronized Trabajo crearRSA(String cadena1, String cadena2) 
	{
		Trabajo aux = new Trabajo();
		int id = trabajos.size();
		aux.id = id;
		aux.tipo = RSA;
		aux.progress = 0;
		aux.resultado="";
		aux.cadena = cadena1;
		aux.usuario = cadena2;
		trabajos.add(aux);
		dividirTrabajo(aux);
		return aux;
	}
	
	/**
	 * @return Array con la lista de trabajos
	 */
	@Override
	public Trabajo[] trabajos() {
		return trabajos.toArray(new Trabajo[trabajos.size()]);
	}

	@Override
	public void trabajos(Trabajo[] newTrabajos) {
		
	}

	/**
	 * Elimina un trabajo de la lista de trabajos y de la cola de divisiones.
	 * @param id Id del trabajo que se eliminará
	 */
	@Override
	public void borrarTrabajo(int id) {
		 Iterator<Division> it = queue.iterator();
		    while(it.hasNext())
		    {
		        Division em = it.next();
		        if(em.trabajo.id==id)
		        {
		            queue.remove(em);
		        }   
		    }
		    trabajos.get(id).borrado=true;
	}

	/**
	 * Devuelve un objeto trabajo de la lista de trabajos
	 * @param id Identificador único del trabajo
	 */
	@Override
	public Trabajo getTrabajo(int id) {
		try{
		return trabajos.get(id);
		}catch(Exception e){return null;}
	}
}
