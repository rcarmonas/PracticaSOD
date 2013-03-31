package clases;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class HiloRevisor extends Thread {

	ArrayList<Integer> trabajos;
	Controlador controlador;
	public HiloRevisor(ArrayList<Integer> t,Controlador c){
		trabajos=t;
		controlador=c;
	}
	public void run(){
		while(true)
		{
			for(int i=0;i<trabajos.size();i++)
			{
				Trabajo trab=controlador.getTrabajo(trabajos.get(i));
				if(trab.progress==ControladorImpl.MAX_PROGRESS)
				{
					int aux=trab.id;
					String res=trab.resultado;
					trabajos.remove(i);
					controlador.borrarTrabajo(aux);
					JOptionPane.showMessageDialog(null, "Trabajo con ID "+aux+" finalizado\nResultado: "+res);
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
