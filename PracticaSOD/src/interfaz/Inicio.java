package interfaz;
import java.awt.CardLayout;
import java.awt.Dimension;
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

public class Inicio {

	private JFrame frmSistemaDistribudoDe;
	private JTextField textField_2;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private DefaultTableModel dtmModelo;
	private JTable jtTabla;
	private JTextField textField_4;

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
		frmSistemaDistribudoDe.setBounds(100, 100, 926, 575);
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
		
		JPanel NuevoAtaque = new JPanel();
		panel.add(NuevoAtaque, "name_5999225250373");
		NuevoAtaque.setLayout(null);
		
		JLabel lblLongitudMximaDe = new JLabel("Longitud máxima de la clave");
		lblLongitudMximaDe.setBounds(215, 251, 199, 15);
		NuevoAtaque.add(lblLongitudMximaDe);
		
		textField_2 = new JTextField();
		textField_2.setBounds(422, 249, 37, 19);
		NuevoAtaque.add(textField_2);
		textField_2.setColumns(3);
		
		JLabel lblNuevoAtaque = new JLabel("Nuevo Ataque");
		lblNuevoAtaque.setFont(new Font("Dialog", Font.BOLD, 17));
		lblNuevoAtaque.setBounds(311, 64, 148, 27);
		NuevoAtaque.add(lblNuevoAtaque);
		
		final JPanel Cambiar = new JPanel();
		Cambiar.setBounds(182, 152, 361, 56);
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
		comboBox.setBounds(245, 103, 70, 24);
		NuevoAtaque.add(comboBox);
		
		JButton btnIniciarAtaque = new JButton("Añadir ataque");
		btnIniciarAtaque.setBounds(349, 304, 132, 25);
		btnIniciarAtaque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					 Object aoNuevo[]= {comboBox.getSelectedItem(),textField.getText(),textField_1.getText(),textField_3.getText(),textField_4.getText(),textField_2.getText()};
					 dtmModelo.addRow(aoNuevo);
				}catch(NumberFormatException t){
					JOptionPane.showMessageDialog(null, "Formato incorrecto");
				}
			}
		});
		
		NuevoAtaque.add(btnIniciarAtaque);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(201, 108, 45, 15);
		NuevoAtaque.add(lblTipo);
		
		JPanel VerAtaques = new JPanel();
		panel.add(VerAtaques, "name_3410875170670");
		
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
				CardLayout cl = (CardLayout)(panel.getLayout());
			    cl.show(panel, "name_3410875170670");
			}
		});
		mnCliente.add(mntmNewMenuItem);
		
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
		TableColumn tcColumna = jtTabla.getColumn("Dirección");
		tcColumna.setPreferredWidth(100);
		tcColumna=jtTabla.getColumn("Tipo");
		tcColumna.setPreferredWidth(20);
		tcColumna=jtTabla.getColumn("Puerto");
		tcColumna.setPreferredWidth(40);
		tcColumna=jtTabla.getColumn("Usuario");
		tcColumna.setPreferredWidth(100);
		tcColumna=jtTabla.getColumn("Cadena");
		tcColumna.setPreferredWidth(200);
		tcColumna=jtTabla.getColumn("Longitud");
		tcColumna.setPreferredWidth(40);
		
		//creo un panel con barra de desplazamiento que contiene a table
		JScrollPane jspScrollpane = new JScrollPane(jtTabla);
		Dimension d = jtTabla.getPreferredSize();
		jspScrollpane.setPreferredSize(new Dimension(d.width,jtTabla.getRowHeight()*20+1));
		//añado el panel a la ventana
		VerAtaques.add(jspScrollpane);
		
		JButton btnBorrarSelecciondos = new JButton("Borrar Selecciondos");
		btnBorrarSelecciondos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int [] aiSeleccionadas=jtTabla.getSelectedRows();
				int iLongitud=aiSeleccionadas.length;
				for(int i=iLongitud-1;i>=0;i--)
					dtmModelo.removeRow(aiSeleccionadas[i]);
			}
		});
		VerAtaques.add(btnBorrarSelecciondos);
	}
}
