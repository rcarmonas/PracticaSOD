package clases;

import javax.swing.JOptionPane;
/**
 * Hilo encargado de mostrar por pantalla un mensaje al usuario cuando 
 * un trabajo ha sido finalizado o borrado
 * @author Rafael Carmona Sánchez
 * @author José Manuel Herruzo Ruiz
 */
public class HiloMensaje extends Thread {
	private String mensaje;
	HiloMensaje(String m){
		mensaje=m;
	}
	public void run(){
		JOptionPane.showMessageDialog(null, mensaje);
	}
}
