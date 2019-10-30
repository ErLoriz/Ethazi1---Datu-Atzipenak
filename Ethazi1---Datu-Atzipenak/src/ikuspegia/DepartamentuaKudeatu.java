package ikuspegia;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import kontroladorea.Departamentua;
import kontroladorea.Enplegatua;
import kontroladorea.Metodoak;
import kontroladorea.MetodoakIkuspegia;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class DepartamentuaKudeatu extends JFrame {

	/**************** ATRIBUTOS ***************************/
	// CONTENEDOR PRINCIPAL
	private JPanel contenedor;

	// DEFINICIÓN DE LAS ETIQUETAS
	private JLabel lblIzena;

	// DEFINICIÓN DE LOS CUADROS DE TEXTO
	protected JTextField txtIzena;

	// DEFINICIÓN DE LOS BOTONES
	protected JButton btnAdd;
	protected JButton btnDel;
	protected JButton btnUpd;

	// DEFINICIÓN DE LOS OBJETOS PARA LA TABLA
	private JScrollPane scroll; // Panel de scroll que contiene la tabla
	protected Object[][] datos; // Cuerpo de la tabla
	protected String[] cabecera; // Cabecera de la tabla
	protected DefaultTableModel dtm;// Unión de la cabecera y la tabla
	protected JTable tabla; // Tabla propiamente dicha
	private JButton btnBaieztatu;

	/**************** MÉTODOS ***************************/
	// CONSTRUCTOR
	@SuppressWarnings("serial")
	public DepartamentuaKudeatu() {
		this.setResizable(false);
		// Métodos de la JFrame
		setBounds(100, 100, 630, 450);// Definir las dimensiones de la ventana
		setTitle("DEPARTAMENTUEN KUDEAPENA"); // Barra de título
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Acción al pulsar salir

		// CREAR EL CONTENEDOR PRINCIPAL Y AÑADIRLO A LA VENTANA
		contenedor = new JPanel();
		getContentPane().add(contenedor);

		// INDICAR QUE SE QUIERE USAR SPRINGLAYOUT
		SpringLayout sp = new SpringLayout();
		contenedor.setLayout(sp);

		// Vamos al lío
		/**************** BOF ETIQUETAS vvvvvvvvvvvvvvvv **/
		// ETIQUETA NOMBRE
		lblIzena = new JLabel("Izena:"); // Crear el objeto
		lblIzena.setEnabled(false);
		contenedor.add(lblIzena);
		/**************** EOF ETIQUETAS ^^^^^^^^^^^^^^^^ **/

		/**************** BOF CUADROS DE TEXTO vvvvvvvvv **/
		// CUADRO DE TEXTO PARA EL NOMBRE
		txtIzena = new JTextField();
		sp.putConstraint(SpringLayout.NORTH, txtIzena, -3, SpringLayout.NORTH, lblIzena);
		sp.putConstraint(SpringLayout.WEST, txtIzena, 62, SpringLayout.EAST, lblIzena);
		sp.putConstraint(SpringLayout.EAST, txtIzena, -321, SpringLayout.EAST, contenedor);
		txtIzena.setEnabled(false);
		contenedor.add(txtIzena);
		/**************** EOF CUADROS DE TEXTO ^^^^^^^^^ **/

		/**************** BOF TABLA vvvvvvvvvvvvvvvvvvvv **/
		scroll = new JScrollPane();
		sp.putConstraint(SpringLayout.WEST, lblIzena, 0, SpringLayout.WEST, scroll);
		sp.putConstraint(SpringLayout.SOUTH, lblIzena, -53, SpringLayout.NORTH, scroll);
		cabecera = new String[] { "ID", "IZENA", "KOKAPENA" };
		dtm = new DefaultTableModel(departamentuaIkusi(eredua.Kontsultak.DepartamentuakIkusi()), cabecera);
		tabla = new JTable(dtm) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scroll.setViewportView(tabla);
		Metodoak.departamentuakIkusi(scroll);
		// y ahora se coloca el scrollpane...
		contenedor.add(scroll); // añadir al contenedor
		sp.putConstraint(SpringLayout.NORTH, scroll, 120, SpringLayout.NORTH, contenedor);
		sp.putConstraint(SpringLayout.WEST, scroll, 10, SpringLayout.WEST, contenedor);
		sp.putConstraint(SpringLayout.EAST, scroll, -10, SpringLayout.EAST, contenedor);
		sp.putConstraint(SpringLayout.SOUTH, scroll, -50, SpringLayout.SOUTH, contenedor);
		/**************** EOF TABLA ^^^^^^^^^^^^^^^^^^^^ **/

		/**************** BOF BOTONES vvvvvvvvvvvvvvvvvv **/
		// BOTÓN AÑADIR
		btnAdd = new JButton("Gehitu");
		contenedor.add(btnAdd);
		sp.putConstraint(SpringLayout.SOUTH, btnAdd, -10, SpringLayout.SOUTH, contenedor);// colocarlo
		sp.putConstraint(SpringLayout.WEST, btnAdd, 60, SpringLayout.WEST, contenedor);
		// BOTÓN BORRAR
		btnDel = new JButton("Ezabatu");
		sp.putConstraint(SpringLayout.NORTH, btnDel, 0, SpringLayout.NORTH, btnAdd);
		sp.putConstraint(SpringLayout.WEST, btnDel, 59, SpringLayout.EAST, btnAdd);
		contenedor.add(btnDel);
		// BOTÓN MODIFICAR
		btnUpd = new JButton("Editatu");
		btnUpd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblIzena.setEnabled(true);
				txtIzena.setEnabled(true);
				btnBaieztatu.setEnabled(true);
				btnAdd.setEnabled(false);
				btnDel.setEnabled(false);
			}
		});
		sp.putConstraint(SpringLayout.NORTH, btnUpd, 0, SpringLayout.NORTH, btnAdd);
		sp.putConstraint(SpringLayout.WEST, btnUpd, 61, SpringLayout.EAST, btnDel);
		contenedor.add(btnUpd);

		JButton btnIrten = new JButton("Irten");
		sp.putConstraint(SpringLayout.NORTH, btnIrten, 6, SpringLayout.SOUTH, scroll);
		sp.putConstraint(SpringLayout.WEST, btnIrten, -114, SpringLayout.EAST, contenedor);
		sp.putConstraint(SpringLayout.SOUTH, btnIrten, 6, SpringLayout.SOUTH, btnAdd);
		sp.putConstraint(SpringLayout.EAST, btnIrten, -28, SpringLayout.EAST, contenedor);
		btnIrten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MetodoakIkuspegia.pasatuMenura();
				dispose();
			}
		});
		contenedor.add(btnIrten);

		btnBaieztatu = new JButton("Baieztatu");
		sp.putConstraint(SpringLayout.NORTH, btnBaieztatu, -17, SpringLayout.NORTH, lblIzena);
		sp.putConstraint(SpringLayout.WEST, btnBaieztatu, 49, SpringLayout.EAST, txtIzena);
		sp.putConstraint(SpringLayout.SOUTH, btnBaieztatu, 31, SpringLayout.NORTH, lblIzena);
		sp.putConstraint(SpringLayout.EAST, btnBaieztatu, -172, SpringLayout.EAST, contenedor);
		btnBaieztatu.setEnabled(false);
		contenedor.add(btnBaieztatu);
		/**************** EOF BOTONES ^^^^^^^^^^^^^^^^^^^^ **/

		// Se hace visible la ventana
		setVisible(true);
	}

	public static String[][] departamentuaIkusi(ArrayList<Departamentua> d1) {
		String[][] data = new String[d1.size()][3];

		for (int i = 0; i <= d1.size() - 1; i++) {

			data[i][0] = Integer.toString(d1.get(i).getIdDepartamentua());
			data[i][1] = d1.get(i).getIzena();
			data[i][2] = d1.get(i).getKokapena();

		}

		return data;
	}

	public boolean isCellEditable(int rowIndex, int colIndex) {
		return false;
	}

}
