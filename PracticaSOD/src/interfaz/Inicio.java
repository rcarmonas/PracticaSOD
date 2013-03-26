package interfaz;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clases.Atacante;

public class Inicio {

	private JFrame frmSistemaDistribudoDe;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio window = new Inicio();
					window.frmSistemaDistribudoDe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Inicio() {
		initialize();
	}

	/*
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSistemaDistribudoDe = new JFrame();
		frmSistemaDistribudoDe.setResizable(false);
		frmSistemaDistribudoDe.setTitle("Sistema distribuído de ataques a la seguridad");
		frmSistemaDistribudoDe.setBounds(100, 100, 552, 400);
		frmSistemaDistribudoDe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//barra de menus
		JMenuBar menuBar = new JMenuBar();
		frmSistemaDistribudoDe.setJMenuBar(menuBar);
		
		JMenu mnControlador = new JMenu("Controlador");
		menuBar.add(mnControlador);
		frmSistemaDistribudoDe.getContentPane().setLayout(new CardLayout(0, 0));
		
		//panel principal
		final JPanel panel = new JPanel();
		frmSistemaDistribudoDe.getContentPane().add(panel, "name_7972469409594");
		panel.setLayout(new CardLayout(0, 0));
		
		//panel de bienvenida
		JPanel Bienvenida = new JPanel();
		panel.add(Bienvenida, "name_7992351480176");
		Bienvenida.setLayout(null);
		
		JLabel lblBienvenido = new JLabel("Bienvenido");
		lblBienvenido.setFont(new Font("Dialog", Font.BOLD, 20));
		lblBienvenido.setBounds(230, 87, 139, 63);
		Bienvenida.add(lblBienvenido);
		
		//panel del controlador
		JPanel Controlador = new JPanel();
		panel.add(Controlador, "name_7992336414471");
		Controlador.setLayout(null);
		JLabel lblControladorIniciado = new JLabel("Controlador iniciado");
		lblControladorIniciado.setBounds(195, 49, 156, 51);
		Controlador.add(lblControladorIniciado);
		
		JButton btnParar = new JButton("Parar");
		btnParar.setBounds(211, 132, 124, 30);
		btnParar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout)(panel.getLayout());
				cl.show(panel, "name_7992351480176");
			}
		});
		Controlador.add(btnParar);
		
		JPanel Ataques = new JPanel();
		panel.add(Ataques, "name_5999225250373");
		Ataques.setLayout(null);
		
		JLabel lblDireccin = new JLabel("Dirección");
		lblDireccin.setBounds(74, 74, 70, 15);
		Ataques.add(lblDireccin);
		
		textField = new JTextField();
		textField.setBounds(159, 72, 114, 19);
		Ataques.add(textField);
		textField.setColumns(10);
		
		JLabel lblPuerto = new JLabel("Puerto");
		lblPuerto.setBounds(308, 74, 48, 15);
		Ataques.add(lblPuerto);
		
		JLabel label = new JLabel("");
		label.setBounds(261, 14, 0, 0);
		Ataques.add(label);
		
		textField_1 = new JTextField();
		textField_1.setBounds(362, 72, 59, 19);
		Ataques.add(textField_1);
		textField_1.setColumns(5);
		
		JButton btnIniciarAtaque = new JButton("Iniciar Ataque");
		btnIniciarAtaque.setBounds(221, 195, 132, 25);
		btnIniciarAtaque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().length()==0||textField_1.getText().length()==0||textField_2.getText().length()==0||textField_3.getText().length()==0)
				{
					JOptionPane.showMessageDialog(null, "Debe completar todos los campos");
				}
				else
				{
					try{
						Atacante a=new Atacante(textField.getText(),Integer.parseInt(textField_1.getText()));
						String clave=a.atacar(textField_3.getText(),Integer.parseInt(textField_2.getText()));
						if(clave!=null)
							JOptionPane.showMessageDialog(null, "Clave encontrada: "+clave);
						else
							JOptionPane.showMessageDialog(null, "No se ha encontrado la clave");
					}catch(NumberFormatException t){
						JOptionPane.showMessageDialog(null, "Formato incorrecto");
					}
					
				}
			}
		});
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(330, 14, 0, 0);
		Ataques.add(label_1);
		
		JLabel lblLongitudMximaDe = new JLabel("Longitud máxima de la clave");
		lblLongitudMximaDe.setBounds(87, 142, 199, 15);
		Ataques.add(lblLongitudMximaDe);
		
		textField_2 = new JTextField();
		textField_2.setBounds(294, 140, 37, 19);
		Ataques.add(textField_2);
		textField_2.setColumns(3);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(221, 41, 0, 0);
		Ataques.add(label_2);
		
		
		Ataques.add(btnIniciarAtaque);
		
		JLabel label_3 = new JLabel("");
		label_3.setBounds(363, 41, 0, 0);
		Ataques.add(label_3);
		
		JLabel label_4 = new JLabel("");
		label_4.setBounds(368, 41, 0, 0);
		Ataques.add(label_4);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(87, 113, 70, 15);
		Ataques.add(lblUsuario);
		
		textField_3 = new JTextField();
		textField_3.setBounds(159, 111, 114, 19);
		Ataques.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNuevoAtaque = new JLabel("Nuevo Ataque");
		lblNuevoAtaque.setFont(new Font("Dialog", Font.BOLD, 17));
		lblNuevoAtaque.setBounds(208, 14, 148, 27);
		Ataques.add(lblNuevoAtaque);
		
		JMenuItem mntmIniciarControlador = new JMenuItem("Iniciar controlador");
		mntmIniciarControlador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout)(panel.getLayout());
			    cl.show(panel, "name_7992336414471");
			}
		});
		mnControlador.add(mntmIniciarControlador);
		
		JMenu mnCliente = new JMenu("Cliente");
		menuBar.add(mnCliente);
		
		JMenuItem mntmAtacar = new JMenuItem("Atacar");
		mntmAtacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout)(panel.getLayout());
			    cl.show(panel, "name_5999225250373");
			}
		});
		mnCliente.add(mntmAtacar);
	}
}
