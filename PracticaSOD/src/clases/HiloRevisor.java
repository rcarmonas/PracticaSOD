package clases;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class HiloRevisor extends Thread {

	ArrayList<Trabajo> trabajos;
	Controlador controlador;
	public HiloRevisor(ArrayList<Trabajo> t,Controlador c){
		trabajos=t;
		controlador=c;
	}
	public void run(){
		while(true)
		{
			for(int i=0;i<trabajos.size();i++)
			{
				if(trabajos.get(i).progress==ControladorImpl.MAX_PROGRESS)
				{
					JOptionPane.showMessageDialog(null, "Trabajo con ID "+trabajos.get(i).id+" finalizado\nResultado: "+trabajos.get(i).resultado);
					controlador.borrarTrabajo(trabajos.get(i));
					trabajos.remove(i);
				}
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
