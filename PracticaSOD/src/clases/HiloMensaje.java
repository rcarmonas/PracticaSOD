package clases;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * Hilo encargado de mostrar por pantalla un mensaje al usuario cuando 
 * un trabajo ha sido finalizado o borrado
 * @author Rafael Carmona Sánchez
 * @author José Manuel Herruzo Ruiz
 */
public class HiloMensaje extends Thread {
	private String mensaje;
	private JFrame ventana;
	HiloMensaje(String m,JFrame v){
		mensaje=m;
		ventana=v;
	}
	public void run(){
		JOptionPane.showMessageDialog(ventana, mensaje);
	}
}
