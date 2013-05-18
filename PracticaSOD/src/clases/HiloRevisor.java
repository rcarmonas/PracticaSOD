package clases;

import interfaz.Inicio;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
/**
 * Hilo de ejecución que se encarga de revisar si los
 * trabajos que ha generado el propio cliente han terminado y eliminarlos de
 * su tabla en caso de que así sea.
 * @author Rafael Carmona Sánchez
 * @author José Manuel Herruzo Ruiz
 */
public class HiloRevisor extends SwingWorker<Void,Object[]> {

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
	protected Void doInBackground()
	{
		while(true){
			//obtengo los trabajos y los publico
			Trabajo [] vectorTrabajos=controlador.trabajos();
			publicarTrabajos(vectorTrabajos);
			
			//comprueba si los trabajos que ha creado el cliente han terminado o han sido borrados
			for(int i=0;i<vectorTrabajos.length;i++)
			{				
				if(trabajosPropios.contains((Integer)vectorTrabajos[i].id))//si lo ha creado este cliente
				{
					int aux=vectorTrabajos[i].id;
					if(vectorTrabajos[i].borrado==true)
					{
						trabajosPropios.remove((Integer)aux);
						HiloMensaje hm=new HiloMensaje("Trabajo con ID "+aux+" ha sido borrado",inicio.ventana);
						hm.start();
					}
					if(vectorTrabajos[i].progress>=vectorTrabajos[i].max_progress)
					{
						trabajosPropios.remove((Integer)aux);
						controlador.borrarTrabajo(aux);
						String res=vectorTrabajos[i].resultado;
						if(res.equals(""))
							res="No se ha encontrado la solución";
						else
							res="Resultado: "+res;
						HiloMensaje hm=new HiloMensaje("Trabajo con ID "+aux+" finalizado\n"+res,inicio.ventana);
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

    protected void process(List<Object[]> rows) {
    	//guardo la fila que estuviera seleccionada
		int seleccionada=inicio.jtTabla.getSelectedRow();
		int id=-1;
		if(seleccionada!=-1)
			id=Integer.parseInt(inicio.jtTabla.getValueAt(seleccionada, 0).toString());
		
		//cargo la tabla
        for (Object[] row : rows) 
        {
        	if(row!=null)
        	{
        		inicio.dtmModelo.addRow(row);
        		if((Integer)row[0]==id)
                	inicio.jtTabla.setRowSelectionInterval(inicio.jtTabla.convertRowIndexToView(inicio.dtmModelo.getRowCount()-1),inicio.jtTabla.convertRowIndexToView(inicio.dtmModelo.getRowCount()-1));
        	}
        	else{
        	//borro todas las filas
    		int nrows=inicio.dtmModelo.getRowCount();
    		for(int i=nrows-1;i>=0;i--)
    			inicio.dtmModelo.removeRow(i);
        	}
        }
    }
    private void publicarTrabajos(Trabajo[] trabajos)
    {
    	publish((Object[])null);//fuerza a actualizar siempre la tabla
		for(int i=0;i<trabajos.length;i++)
		{
			if(trabajos[i].borrado==false)
			{
				int progreso=(int)(trabajos[i].progress*100.0/trabajos[i].max_progress);
				JProgressBar p=new JProgressBar();
				p.setValue(progreso);
				p.setStringPainted(true);
				String diccionario="";
				switch(trabajos[i].diccionario)
				{
					case 0: diccionario="No"; break;
					case 1: diccionario="Diccionario"; break;
					case 2: diccionario="Palabras"; break;
					case 3: diccionario="Nombres"; break;
				}
				if(trabajos[i].tipo==ControladorImpl.MD5)
				{
					Object aoNuevo[]= {trabajos[i].id,"MD5",diccionario,"","",trabajos[i].cadena,"",trabajos[i].tam_maximo,p};
					publish(aoNuevo);
				}
				else if(trabajos[i].tipo==ControladorImpl.SHA)
				{
					Object aoNuevo[]= {trabajos[i].id,"SHA",diccionario,"","",trabajos[i].cadena,"",trabajos[i].tam_maximo,p};
					publish(aoNuevo);
				}
				else if(trabajos[i].tipo==ControladorImpl.RED)
				{
					Object aoNuevo[]= {trabajos[i].id,"Red",diccionario,trabajos[i].cadena,trabajos[i].puerto,trabajos[i].usuario,"",trabajos[i].tam_maximo,p};
					publish(aoNuevo);
				}
				else if(trabajos[i].tipo==ControladorImpl.RSA)
				{
					Object aoNuevo[]= {trabajos[i].id,"RSA",diccionario,"","",trabajos[i].cadena,trabajos[i].usuario,trabajos[i].tam_maximo,p};
					publish(aoNuevo);
				}
			}
		}
    }
}

