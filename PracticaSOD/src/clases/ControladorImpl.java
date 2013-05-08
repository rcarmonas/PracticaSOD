package clases;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

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
	
	ControladorImpl(){
		queue=new LinkedBlockingQueue<Division>();
		trabajos=new ArrayList<Trabajo>();
	}
	
	public void finTrabajo(int id, String clave) 
	{
		int maxProgress = trabajos.get(id).max_progress;
		try{
			if(!clave.equals(""))//Acabado!
			{
				trabajos.get(id).progress = maxProgress;
				//Procesar contraseña obtenida
				trabajos.get(id).resultado=clave;
			}
			else if(trabajos.get(id).progress<maxProgress)//Aumento el progreso...
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
			for(int i=1; i<=MAX_PROGRESS_dic; i++)
				setDivision(new Division(t, (char)i));
	}
	
	public synchronized Trabajo crearMD5(String cadena, int tam_maximo, int dic)
	{
		int id = trabajos.size();
		Trabajo aux = new Trabajo(false,id, MD5, dic, cadena, 0, "", tam_maximo, 0,MAX_PROGRESS,"");
		if(dic!=0)
			aux.max_progress=MAX_PROGRESS_dic;
		trabajos.add(aux);
		dividirTrabajo(aux);
		return aux;
	}

	
	public synchronized Trabajo crearSHA(String cadena, int tam_maximo, int dic)
	{
		int id = trabajos.size();
		Trabajo aux = new Trabajo(false,id, SHA,dic,  cadena, 0, "", tam_maximo, 0,MAX_PROGRESS,"");
		if(dic!=0)
			aux.max_progress=MAX_PROGRESS_dic;
		trabajos.add(aux);
		dividirTrabajo(aux);
		return aux;
	}

	
	public synchronized Trabajo crearRed(String cadena, int puerto, String usuario, int tam_maximo, int dic) 
	{
		Trabajo aux = new Trabajo();
		int id = trabajos.size();
		aux.id = id;
		aux.tipo = RED;
		aux.progress = 0;
		if(dic!=0)
			aux.max_progress=MAX_PROGRESS_dic;
		else
			aux.max_progress=MAX_PROGRESS;
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

	public synchronized Trabajo crearRSA(String cadena1, String cadena2) 
	{
		Trabajo aux = new Trabajo();
		int id = trabajos.size();
		aux.id = id;
		aux.tipo = RSA;
		aux.progress = 0;
		aux.max_progress=MAX_PROGRESS_rsa;
		aux.resultado="";
		aux.cadena = cadena1;
		aux.usuario = cadena2;
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
	    trabajos.get(id).borrado=true;
	}

	@Override
	public Trabajo getTrabajo(int id) {
		try{
		return trabajos.get(id);
		}catch(Exception e){return null;}
	}
}
