package interfaz;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import clases.*;

public class Inicio {
	private JFrame ventana;
	private JTextField textField_2;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private DefaultTableModel dtmModelo;
	private JTable jtTabla;
	private JTextField textField_4;
	private JPanel panel;
	private Controlador control;
	private HiloAtacante[] hilos=null;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio window = new Inicio();
					window.ventana.setVisible(true);
					window.control=buscarControlador(args);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static Controlador buscarControlador(String[] args)
	{
		try{
			// Crear e inicializar el ORB
			ORB orb = ORB.init(args, null);
	        // Obtener la referencia CORBA al servidor de nombres
	        org.omg.CORBA.Object objRef = 
		    orb.resolve_initial_references("NameService");
	
	        // Traducir su referencia
	        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
	 
	        // Resolver el nombre
	        String name = "Controlador";
	        Controlador c=ControladorHelper.narrow(ncRef.resolve_str(name));
	        return c;
		}catch(Exception e)
		{
			System.err.println("Error CORBA");
			return null;
		}
		
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
		/**
		 * CREACION DE LA VENTANA
		 */
		
		ventana = new JFrame();
		ventana.setResizable(false);
		ventana.setTitle("Sistema distribuído de ataques a la seguridad");
		ventana.setBounds(100, 100, 926, 575);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/**
		 * CREACION DE PANELES
		 */
		
		//panel principal
		panel = new JPanel();
		ventana.getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));		
		
		//panel de bienvenida
		JPanel Bienvenida = new JPanel();
		panel.add(Bienvenida, "name_7992351480176");
		Bienvenida.setLayout(null);
		
		JLabel lblBienvenido = new JLabel("Bienvenido");
		lblBienvenido.setFont(new Font("Dialog", Font.BOLD, 20));
		lblBienvenido.setBounds(394, 193, 139, 63);
		Bienvenida.add(lblBienvenido);
		
		//panel del controlador
		final JPanel Controlador = new JPanel();
		panel.add(Controlador, "name_7992336414471");
		Controlador.setLayout(new CardLayout(0, 0));
		
		JPanel Unirse = new JPanel();
		Controlador.add(Unirse, "name_4528748878770");
		
		JLabel lblNmeroDeHilos = new JLabel("Número de hilos");
		Unirse.add(lblNmeroDeHilos);
		
		textField_5 = new JTextField();
		Unirse.add(textField_5);
		textField_5.setColumns(4);
		
		JButton btnUnirse = new JButton("Unirse");
		btnUnirse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hilos=new HiloAtacante[Integer.parseInt(textField_5.getText())];
				for(int i=0;i<Integer.parseInt(textField_5.getText());i++)
				{
					hilos[i]=new HiloAtacante(control);
					hilos[i].start();
				}
				CardLayout cl = (CardLayout)(Controlador.getLayout());
			    cl.show(Controlador, "name_4475524850557");
			}
		});
		Unirse.add(btnUnirse);
		
		JPanel Unido = new JPanel();
		Controlador.add(Unido, "name_4475524850557");
		JLabel lblControladorIniciado = new JLabel("Unido");
		Unido.add(lblControladorIniciado);
		
		JButton btnParar = new JButton("Salir");
		btnParar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(int i=0;i<hilos.length;i++)
				{
					hilos[i].disactive();
				}
				hilos=null;
				CardLayout cl = (CardLayout)(Controlador.getLayout());
			    cl.show(Controlador, "name_4528748878770");
			}
		});
		Unido.add(btnParar);
		
		
		//panel de nuevo ataque
		JPanel NuevoAtaque = new JPanel();
		panel.add(NuevoAtaque, "name_5999225250373");
		NuevoAtaque.setLayout(null);
		
		JLabel lblLongitudMximaDe = new JLabel("Longitud máxima de la clave");
		lblLongitudMximaDe.setBounds(293, 250, 199, 15);
		NuevoAtaque.add(lblLongitudMximaDe);
		
		textField_2 = new JTextField();
		textField_2.setBounds(500, 248, 37, 19);
		NuevoAtaque.add(textField_2);
		textField_2.setColumns(3);
		
		JLabel lblNuevoAtaque = new JLabel("Nuevo Ataque");
		lblNuevoAtaque.setFont(new Font("Dialog", Font.BOLD, 17));
		lblNuevoAtaque.setBounds(389, 50, 148, 27);
		NuevoAtaque.add(lblNuevoAtaque);
		
			//subpanel nuevo ataque
			final JPanel Cambiar = new JPanel();
			Cambiar.setBounds(260, 151, 361, 56);
			NuevoAtaque.add(Cambiar);
			Cambiar.setLayout(new CardLayout(0, 0));
			
			JPanel Red = new JPanel();
			Cambiar.add(Red, "name_3509705215789");
			
			JLabel label_5 = new JLabel("Dirección");
			Red.add(label_5);
			
			textField = new JTextField();
			textField.setColumns(10);
			Red.add(textField);
			
			JLabel label_6 = new JLabel("Puerto");
			Red.add(label_6);
			
			textField_1 = new JTextField();
			textField_1.setColumns(5);
			Red.add(textField_1);
			
			JLabel label_7 = new JLabel("Usuario");
			Red.add(label_7);
			
			textField_3 = new JTextField();
			textField_3.setColumns(10);
			Red.add(textField_3);
			
			JPanel MD5 = new JPanel();
			Cambiar.add(MD5, "name_3509728720973");
			
			JLabel lblCadena = new JLabel("Cadena");
			MD5.add(lblCadena);
			
			textField_4 = new JTextField();
			MD5.add(textField_4);
			textField_4.setColumns(20);
		
			
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(279, 107, 45, 15);
		NuevoAtaque.add(lblTipo);

		final JComboBox<Object> comboBox = new JComboBox<Object>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox.getSelectedItem()=="Red")
				{
					textField_4.setText("");
					CardLayout cl = (CardLayout)(Cambiar.getLayout());
				    cl.show(Cambiar, "name_3509705215789");
				}
				else
				{
					textField.setText("");
					textField_1.setText("");
					textField_3.setText("");
					CardLayout cl = (CardLayout)(Cambiar.getLayout());
				    cl.show(Cambiar, "name_3509728720973");
				}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"Red", "MD5", "SHA"}));
		comboBox.setBounds(323, 102, 70, 24);
		NuevoAtaque.add(comboBox);
		
		JButton btnIniciarAtaque = new JButton("Añadir ataque");
		btnIniciarAtaque.setBounds(389, 306, 158, 25);
		btnIniciarAtaque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					// Object aoNuevo[]= {comboBox.getSelectedItem(),textField.getText(),textField_1.getText(),textField_3.getText(),textField_4.getText(),textField_2.getText()};
					// dtmModelo.addRow(aoNuevo);
					 if(comboBox.getSelectedItem().equals("MD5"))
					 {
						 control.crearMD5(textField_4.getText(),Integer.parseInt(textField_2.getText()));
					 }
					 else if(comboBox.getSelectedItem().equals("SHA"))
					 {
						 control.crearSHA(textField_4.getText(), Integer.parseInt(textField_2.getText()));
					 }
					 else if(comboBox.getSelectedItem().equals("Red"))
					 {
						 control.crearRed(textField.getText(), Integer.parseInt(textField_1.getText()), textField_3.getText(), Integer.parseInt(textField_2.getText()));
					 }
					 
				}catch(NumberFormatException t){
					JOptionPane.showMessageDialog(null, "Formato incorrecto");
				}
			}
		});
		
		NuevoAtaque.add(btnIniciarAtaque);
		
		//panel ver ataques
		JPanel VerAtaques = new JPanel();
		VerAtaques.setLayout(new BorderLayout(0, 0));
		panel.add(VerAtaques, "name_3410875170670");
		
		//creacion de la tabla
		//defino un modelo de tabla con las columnas necesarias
		dtmModelo = new DefaultTableModel();
		dtmModelo.addColumn("Tipo");
		dtmModelo.addColumn("Dirección");
		dtmModelo.addColumn("Puerto");
		dtmModelo.addColumn("Usuario");
		dtmModelo.addColumn("Cadena");
		dtmModelo.addColumn("Longitud");
		
		//reservo la tabla
		jtTabla = new JTable(dtmModelo);
		
		//Defino el ancho de las columnas
		TableColumn tcColumna;
		tcColumna=jtTabla.getColumn("Cadena");
		tcColumna.setPreferredWidth(300);
		tcColumna=jtTabla.getColumn("Tipo");
		tcColumna.setPreferredWidth(40);
		tcColumna=jtTabla.getColumn("Puerto");
		tcColumna.setPreferredWidth(70);
		tcColumna=jtTabla.getColumn("Longitud");
		tcColumna.setPreferredWidth(60);
		
		//creo panel con barra de desplazamiento que contiene a table
		JScrollPane jspScrollpane = new JScrollPane(jtTabla);
		//añado el panel a la ventana
		VerAtaques.add(jspScrollpane,BorderLayout.CENTER);
		
		JButton btnBorrarSelecciondos = new JButton("Borrar Selecciondos");
		btnBorrarSelecciondos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int [] aiSeleccionadas=jtTabla.getSelectedRows();
				int iLongitud=aiSeleccionadas.length;
				for(int i=iLongitud-1;i>=0;i--)
					dtmModelo.removeRow(aiSeleccionadas[i]);
			}
		});
		VerAtaques.add(btnBorrarSelecciondos, BorderLayout.SOUTH);
		
		/**
		 * BARRA DE MENUS
		 */
		JMenuBar menuBar = new JMenuBar();
		ventana.setJMenuBar(menuBar);
		
		JMenu mnControlador = new JMenu("Cliente");
		menuBar.add(mnControlador);
		
		JMenuItem mntmIniciarControlador = new JMenuItem("Unirse");
		mntmIniciarControlador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout)(panel.getLayout());
			    cl.show(panel, "name_7992336414471");
			    if(hilos!=null)
			    {
			    	cl = (CardLayout)(Controlador.getLayout());
				    cl.show(Controlador, "name_4475524850557");
			    }
			    else
			    {
			    	cl = (CardLayout)(Controlador.getLayout());
				    cl.show(Controlador, "name_4528748878770");
			    }
			    
			}
		});
		mnControlador.add(mntmIniciarControlador);
		
		JMenu mnCliente = new JMenu("Ataques");
		menuBar.add(mnCliente);
		
		JMenuItem mntmAtacar = new JMenuItem("Nuevo Ataque");
		mntmAtacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout)(panel.getLayout());
			    cl.show(panel, "name_5999225250373");
			}
		});
		mnCliente.add(mntmAtacar);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Ver Ataques");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(int i=0;i<dtmModelo.getRowCount();i++)
					dtmModelo.removeRow(i);
				Trabajo[] trabajos=control.trabajos();
				for(int i=0;i<trabajos.length;i++)
				{
					if(trabajos[i].tipo==ControladorImpl.MD5)
					{
						Object aoNuevo[]= {"MD5","","","",trabajos[i].cadena,trabajos[i].tam_maximo};
						dtmModelo.addRow(aoNuevo);
					}
					else if(trabajos[i].tipo==ControladorImpl.SHA)
					{
						Object aoNuevo[]= {"SHA","","","",trabajos[i].cadena,trabajos[i].tam_maximo};
						dtmModelo.addRow(aoNuevo);
					}
					else if(trabajos[i].tipo==ControladorImpl.RED)
					{
						Object aoNuevo[]= {"Red",trabajos[i].cadena,trabajos[i].puerto,trabajos[i].usuario,"",trabajos[i].tam_maximo};
						dtmModelo.addRow(aoNuevo);
					}
				}
				
				CardLayout cl = (CardLayout)(panel.getLayout());
			    cl.show(panel, "name_3410875170670");
			}
		});
		mnCliente.add(mntmNewMenuItem);
	}
}
