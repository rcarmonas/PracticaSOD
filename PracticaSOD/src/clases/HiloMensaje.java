package clases;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
