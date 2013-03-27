package clases;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class ControladorImpl implements ControladorOperations{
	public static int MAX_PROGRESS = StrManager.FIN - StrManager.INICIO;
	public static int MD5 = 0;
	public static int SHA = 1;
	public static int RED = 2;
	LinkedBlockingQueue<Division> queue;
	ArrayList<Trabajo> trabajos;
	

	@Override
	public void finTrabajo(int id, String trabajo) 
	{
		if(trabajo!=null)//Acabado!
		{
			trabajos.get(id).progress = MAX_PROGRESS;
			//Procesar contraseña obtenida
		}
		else if(trabajos.get(id).progress<MAX_PROGRESS)//Aumento el progreso...
			trabajos.get(id).progress++;
	}

	@Override
	public Division getTrabajo()
	{
		try {
			Division aux;
			do{
				aux = this.queue.take();
			}while(aux.trabajo.progress < MAX_PROGRESS);
			return aux;
		} catch (InterruptedException e) {
			return null;
		}
	}

	@Override
	public boolean setTrabajo(Division t) 
	{
		try {
			this.queue.put(t);
			return true;
		} catch (InterruptedException e) 
		{
			return false;
		}
	}
	private void dividirTrabajo(int id) throws InterruptedException
	{
		for(int i=StrManager.INICIO; i<=StrManager.FIN; i++)
			queue.put(new Division(trabajos.get(id), (char)i));
	}
	@Override
	public boolean crearMD5(String cadena, int tam_maximo) {
		Trabajo aux = new Trabajo();
		int id = trabajos.size();
		aux.id = id;
		aux.tipo = MD5;
		aux.progress = 0;
		aux.puerto = 0;
		aux.tam_maximo = tam_maximo;
		aux.usuario = null;
		aux.cadena = cadena;
		trabajos.add(aux);
		try {
			dividirTrabajo(id);
			return true;
		} catch (InterruptedException e) {
			return false;
		}
	}

	@Override
	public boolean crearSHA(String cadena, int tam_maximo) {
		Trabajo aux = new Trabajo();
		int id = trabajos.size();
		aux.id = id;
		aux.tipo = SHA;
		aux.progress = 0;
		aux.puerto = 0;
		aux.tam_maximo = tam_maximo;
		aux.usuario = null;
		aux.cadena = cadena;
		trabajos.add(aux);
		try {
			dividirTrabajo(id);
			return true;
		} catch (InterruptedException e) {
			return false;
		}
	}

	@Override
	public boolean crearRed(String cadena, int puerto, String usuario, int tam_maximo) 
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
		trabajos.add(aux);
		try {
			dividirTrabajo(id);
			return true;
		} catch (InterruptedException e) {
			return false;
		}
	}

}
