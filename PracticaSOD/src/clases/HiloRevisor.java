package clases;

import interfaz.Inicio;
import java.util.ArrayList;
/**
 * Hilo de ejecución que se encarga de revisar si los
 * trabajos que ha generado el propio cliente han terminado y eliminarlos de
 * su tabla en caso de que así sea.
 * @author Rafael Carmona Sánchez
 * @author José Manuel Herruzo Ruiz
 */
public class HiloRevisor extends Thread {

	ArrayList<Integer> trabajosPropios;
	ArrayList<HiloAtacante> hilos;
	Controlador controlador;
	Inicio inicio;
	
	public HiloRevisor(Inicio i,ArrayList<HiloAtacante> h,ArrayList<Integer> t,Controlador c){
		trabajosPropios=t;
		controlador=c;
		hilos=h;
		inicio=i;
	}
	public void run(){
		while(true)
		{
			//actualiza la tabla con los trabajos
			Trabajo [] vectorTrabajos=controlador.trabajos();
			inicio.actualizarTabla(vectorTrabajos);
			
			//comprueba si los trabajos que ha creado el cliente han terminado o han sido borrados
			for(int i=0;i<vectorTrabajos.length;i++)
			{
				if(trabajosPropios.contains((Integer)vectorTrabajos[i].id))//si lo ha creado este cliente
				{
					int aux=vectorTrabajos[i].id;
					if(vectorTrabajos[i].borrado==true)
					{
						trabajosPropios.remove((Integer)aux);
						HiloMensaje hm=new HiloMensaje("Trabajo con ID "+aux+" ha sido borrado");
						hm.start();
					}
					if(vectorTrabajos[i].progress==ControladorImpl.MAX_PROGRESS)
					{
						trabajosPropios.remove((Integer)aux);
						controlador.borrarTrabajo(aux);
						HiloMensaje hm=new HiloMensaje("Trabajo con ID "+aux+" finalizado\nResultado: "+vectorTrabajos[i].resultado);
						hm.start();
					}
				}
			}
			//comprueba si el trabajo que ejecuta cada hilo se ha borrado. en ese caso interrumpe los hilos
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

