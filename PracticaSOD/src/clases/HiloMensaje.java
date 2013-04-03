package clases;

import javax.swing.JOptionPane;

public class HiloMensaje extends Thread {
	private String mensaje;
	HiloMensaje(String m){
		mensaje=m;
	}
	public void run(){
		JOptionPane.showMessageDialog(null, mensaje);
	}
}
