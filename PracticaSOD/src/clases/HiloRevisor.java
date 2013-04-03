package clases;

import java.util.ArrayList;
/**
 * Hilo de ejecución que se encarga de revisar si los
 * trabajos que ha generado el propio cliente han terminado y eliminarlos de
 * su tabla en caso de que así sea.
 * @author Rafael Carmona Sánchez
 * @author José Manuel Herruzo Ruiz
 */
public class HiloRevisor extends Thread {

	ArrayList<Integer> trabajos;
	ArrayList<HiloAtacante> hilos;
	Controlador controlador;
	public HiloRevisor(ArrayList<HiloAtacante> h,ArrayList<Integer> t,Controlador c){
		trabajos=t;
		controlador=c;
		hilos=h;
	}
	public void run(){
		while(true)
		{
			//comprueba si los trabajos que ha creado el cliente se han borrado o han finalizado
			for(int i=0;i<trabajos.size();i++)
			{
				Trabajo trab=controlador.getTrabajo(trabajos.get(i));
				int aux=trab.id;
				if(trab.borrado==true)
				{
					trabajos.remove(i);
					HiloMensaje hm=new HiloMensaje("Trabajo con ID "+aux+" ha sido borrado");
					hm.start();
				}
				if(trab.progress==ControladorImpl.MAX_PROGRESS)
				{
					String res=trab.resultado;
					trabajos.remove(i);
					controlador.borrarTrabajo(aux);
					HiloMensaje hm=new HiloMensaje("Trabajo con ID "+aux+" finalizado\nResultado: "+res);
					hm.start();
				}
			}
			//comprueba si el trabajo que ejecuta cada hilo se ha borrado
			for(int i=0;i<hilos.size();i++)
			{
				Division t = hilos.get(i).trabajo;
				if(t!=null)
				{
					int aux = t.trabajo.id;
					Trabajo trab=controlador.getTrabajo(aux);
					if(trab.borrado==true)
					{
						hilos.get(i).interrumpir();
					}
				}
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

