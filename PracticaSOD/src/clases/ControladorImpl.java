package clases;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

public class ControladorImpl extends ControladorPOA{
	public static int MAX_PROGRESS = StrManager.FIN - StrManager.INICIO;
	public static final int MD5 = 0;
	public static final int SHA = 1;
	public static final int RED = 2;
	public LinkedBlockingQueue<Division> queue;
	public ArrayList<Trabajo> trabajos;
	
	ControladorImpl(){
		queue=new LinkedBlockingQueue<Division>();
		trabajos=new ArrayList<Trabajo>();
	}
	
	public void finTrabajo(int id, String clave) 
	{
		try{
		if(!clave.equals(""))//Acabado!
		{
			System.out.println("Trabajo: "+id+" Resultado: "+clave);
			trabajos.get(id).progress = MAX_PROGRESS;
			//Procesar contraseña obtenida
			trabajos.get(id).resultado=clave;
		}
		else if(trabajos.get(id).progress<MAX_PROGRESS)//Aumento el progreso...
			trabajos.get(id).progress++;
		}catch(Exception e){}
	}

	
	public Division getDivision()
	{
		try {
			return this.queue.take();
		} catch (InterruptedException e) {
			return null;
		}
	}

	
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
	
	public void dividirTrabajo(Trabajo t)
	{
		//inserta las divisiones
		for(int i=StrManager.INICIO; i<=StrManager.FIN; i++)
			setDivision(new Division(t, (char)i));
	}
	
	public synchronized Trabajo crearMD5(String cadena, int tam_maximo)
	{
		int id = trabajos.size();
		Trabajo aux = new Trabajo(id, MD5, cadena, 0, "", tam_maximo, 0,"");
		trabajos.add(aux);
		dividirTrabajo(aux);
		return aux;
	}

	
	public synchronized Trabajo crearSHA(String cadena, int tam_maximo)
	{
		int id = trabajos.size();
		Trabajo aux = new Trabajo(id, SHA, cadena, 0, "", tam_maximo, 0,"");
		trabajos.add(aux);
		dividirTrabajo(aux);
		return aux;
	}

	
	public synchronized Trabajo crearRed(String cadena, int puerto, String usuario, int tam_maximo) 
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
		trabajos.add(aux);
		dividirTrabajo(aux);
		return aux;
	}

	@Override
	public Trabajo[] trabajos() {
		return trabajos.toArray(new Trabajo[trabajos.size()]);
	}

	@Override
	public void trabajos(Trabajo[] newTrabajos) {
		
	}

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
		  trabajos.remove(id);
	}

	@Override
	public Trabajo getTrabajo(int id) {
		try{
		return trabajos.get(id);
		}catch(Exception e){return null;}
	}
}
