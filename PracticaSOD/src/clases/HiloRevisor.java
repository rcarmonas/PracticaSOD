package clases;

import javax.swing.JOptionPane;

public class HiloRevisor extends Thread {

	Trabajo trabajo;
	Controlador controlador;
	public HiloRevisor(Trabajo t,Controlador c){
		trabajo=t;
		controlador=c;
	}
	public void run(){
		boolean continuar=true;
		while(continuar){
			if(trabajo.progress==ControladorImpl.MAX_PROGRESS)
			{
				JOptionPane.showMessageDialog(null, "Trabajo finalizado\nResultado: "+trabajo.resultado);
				controlador.borrarTrabajo(trabajo);
				continuar=false;
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
