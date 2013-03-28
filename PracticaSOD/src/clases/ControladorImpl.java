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
	public void finTrabajo(int id, String clave) 
	{
		if(clave!=null)//Acabado!
		{
			trabajos.get(id).progress = MAX_PROGRESS;
			//Procesar contraseña obtenida
		}
		else if(trabajos.get(id).progress<MAX_PROGRESS)//Aumento el progreso...
			trabajos.get(id).progress++;
	}

	@Override
	public Division getDivision()
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
		dividirTrabajo(aux);
		return true;

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
		dividirTrabajo(aux);
		return true;
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
		dividirTrabajo(aux);
		return true;
	}

}
