package ikuspegia;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import kontroladorea.Departamentua;
import kontroladorea.Enplegatua;
import kontroladorea.Metodoak;
import kontroladorea.MetodoakIkuspegia;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;

public class DepartamentuaKudeatu extends JFrame {

	/**************** ATRIBUTOS ***************************/
	// CONTENEDOR PRINCIPAL
	private JPanel contenedor;

	// DEFINICIÓN DE LAS ETIQUETAS
	private JLabel lblIzena;

	// DEFINICIÓN DE LOS CUADROS DE TEXTO
	protected JTextField txtIzena;
	protected JTextField txtKokapena;
	
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
	private JLabel lblKokapena;
	private JTextField textBilatuID;
	private JLabel lblBilatuID;

	private int aukeratutakoLerroa;

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
		lblIzena = new JLabel("Izena:");
		contenedor.add(lblIzena);
		/**************** EOF ETIQUETAS ^^^^^^^^^^^^^^^^ **/

		/**************** BOF CUADROS DE TEXTO vvvvvvvvv **/
		// CUADRO DE TEXTO PARA EL NOMBRE
		txtIzena = new JTextField();
		sp.putConstraint(SpringLayout.WEST, txtIzena, 28, SpringLayout.EAST, lblIzena);
		sp.putConstraint(SpringLayout.EAST, txtIzena, -335, SpringLayout.EAST, contenedor);
		sp.putConstraint(SpringLayout.NORTH, lblIzena, 3, SpringLayout.NORTH, txtIzena);
		sp.putConstraint(SpringLayout.NORTH, txtIzena, 67, SpringLayout.NORTH, contenedor);
		contenedor.add(txtIzena);
		/**************** EOF CUADROS DE TEXTO ^^^^^^^^^ **/

		/**************** BOF TABLA vvvvvvvvvvvvvvvvvvvv **/
		scroll = new JScrollPane();
		sp.putConstraint(SpringLayout.NORTH, scroll, 156, SpringLayout.NORTH, contenedor);
		cabecera = new String[] { "ID", "IZENA", "KOKAPENA" };
		dtm = new DefaultTableModel(kontroladorea.Metodoak.departamentuaIkusi(eredua.Kontsultak.DepartamentuakIkusi()), cabecera);
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
		sp.putConstraint(SpringLayout.WEST, scroll, 10, SpringLayout.WEST, contenedor);
		sp.putConstraint(SpringLayout.EAST, scroll, -10, SpringLayout.EAST, contenedor);
		sp.putConstraint(SpringLayout.SOUTH, scroll, -50, SpringLayout.SOUTH, contenedor);
		/**************** EOF TABLA ^^^^^^^^^^^^^^^^^^^^ **/

		/**************** BOF BOTONES vvvvvvvvvvvvvvvvvv **/
		// BOTÓN AÑADIR
		btnAdd = new JButton("Gehitu");
		btnAdd.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		if(txtIzena.getText().equals("") || txtKokapena.getText().equals("")) {
        			JOptionPane.showMessageDialog(null, "Sartu informazio guztia, mesedez.");
        		} else {
        			int id = dtm.getRowCount() + 101;
	        		String izena = txtIzena.getText();
	        		String kokapena = txtKokapena.getText();
        		
	        		dtm.addRow(new Object[] {id + 1, izena, kokapena});
        		}
        		

        		
        	}
        });
		contenedor.add(btnAdd);
		sp.putConstraint(SpringLayout.SOUTH, btnAdd, -10, SpringLayout.SOUTH, contenedor);// colocarlo
		sp.putConstraint(SpringLayout.WEST, btnAdd, 60, SpringLayout.WEST, contenedor);
		// BOTÓN BORRAR
		btnDel = new JButton("Ezabatu");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tabla.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Aukeratu lerro bat, mesedez.");
				} else {
					dtm.removeRow(tabla.getSelectedRow());
				}
				
			}
		});
		sp.putConstraint(SpringLayout.NORTH, btnDel, 0, SpringLayout.NORTH, btnAdd);
		sp.putConstraint(SpringLayout.WEST, btnDel, 59, SpringLayout.EAST, btnAdd);
		contenedor.add(btnDel);
		// BOTÓN MODIFICAR
		btnUpd = new JButton("Editatu");
		btnUpd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(tabla.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Aukeratu lerro bat, mesedez.");
				} else {
					btnBaieztatu.setEnabled(true);
					btnAdd.setEnabled(false);
					btnDel.setEnabled(false);
					textBilatuID.setEnabled(false);
					
					txtIzena.setText((String) dtm.getValueAt(tabla.getSelectedRow(), 1));
					txtKokapena.setText((String) dtm.getValueAt(tabla.getSelectedRow(), 2));
					
					aukeratutakoLerroa = tabla.getSelectedRow();
					
					btnBaieztatu.setEnabled(true);
					btnAdd.setEnabled(false);
					btnDel.setEnabled(false);
					
					txtIzena.setText((String) dtm.getValueAt(tabla.getSelectedRow(), 1));
					txtKokapena.setText((String) dtm.getValueAt(tabla.getSelectedRow(), 2));
					
					aukeratutakoLerroa = tabla.getSelectedRow();
				}
				
			}
		});
		sp.putConstraint(SpringLayout.NORTH, btnUpd, 0, SpringLayout.NORTH, btnAdd);
		sp.putConstraint(SpringLayout.WEST, btnUpd, 61, SpringLayout.EAST, btnDel);
		contenedor.add(btnUpd);

		JButton btnIrten = new JButton("Irten");
		sp.putConstraint(SpringLayout.NORTH, btnIrten, 6, SpringLayout.SOUTH, scroll);
		sp.putConstraint(SpringLayout.WEST, btnIrten, -114, SpringLayout.EAST, contenedor);
		sp.putConstraint(SpringLayout.SOUTH, btnIrten, -4, SpringLayout.SOUTH, contenedor);
		sp.putConstraint(SpringLayout.EAST, btnIrten, -28, SpringLayout.EAST, contenedor);
		btnIrten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MetodoakIkuspegia.pasatuMenura();
				dispose();
			}
		});
		contenedor.add(btnIrten);

		btnBaieztatu = new JButton("Baieztatu");
		btnBaieztatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
        		if(txtIzena.getText().equals("") || txtKokapena.getText().equals("")) {
        			JOptionPane.showMessageDialog(null, "Ez utzi informazioa utzik, mesedez.");
        		} else {

        			dtm.setValueAt(txtIzena.getText(), aukeratutakoLerroa, 1);
        			dtm.setValueAt(txtKokapena.getText(), aukeratutakoLerroa, 2);
        			
    				btnBaieztatu.setEnabled(false);
    				btnAdd.setEnabled(true);
    				btnDel.setEnabled(true);
    				textBilatuID.setEnabled(true);
    				
    				txtIzena.setText("");
    				txtKokapena.setText("");
        			
        		}
				
			}
		});
		sp.putConstraint(SpringLayout.NORTH, btnBaieztatu, 82, SpringLayout.NORTH, contenedor);
		sp.putConstraint(SpringLayout.WEST, btnBaieztatu, 83, SpringLayout.EAST, txtIzena);
		sp.putConstraint(SpringLayout.EAST, btnBaieztatu, -152, SpringLayout.EAST, contenedor);
		btnBaieztatu.setEnabled(false);
		contenedor.add(btnBaieztatu);
		
		lblKokapena = new JLabel("Kokapena:");
		sp.putConstraint(SpringLayout.SOUTH, btnBaieztatu, 0, SpringLayout.SOUTH, lblKokapena);
		sp.putConstraint(SpringLayout.SOUTH, lblKokapena, -26, SpringLayout.NORTH, scroll);
		sp.putConstraint(SpringLayout.EAST, lblIzena, 0, SpringLayout.EAST, lblKokapena);
		sp.putConstraint(SpringLayout.WEST, lblKokapena, 10, SpringLayout.WEST, contenedor);
		contenedor.add(lblKokapena);
		
		txtKokapena = new JTextField();
		sp.putConstraint(SpringLayout.WEST, txtKokapena, 0, SpringLayout.WEST, txtIzena);
		sp.putConstraint(SpringLayout.EAST, txtKokapena, -335, SpringLayout.EAST, contenedor);
		sp.putConstraint(SpringLayout.NORTH, txtKokapena, -3, SpringLayout.NORTH, lblKokapena);
		contenedor.add(txtKokapena);
		
		textBilatuID = new JTextField();
		sp.putConstraint(SpringLayout.NORTH, textBilatuID, 10, SpringLayout.NORTH, contenedor);
		sp.putConstraint(SpringLayout.WEST, textBilatuID, 0, SpringLayout.WEST, txtIzena);
		sp.putConstraint(SpringLayout.EAST, textBilatuID, 236, SpringLayout.WEST, contenedor);
		contenedor.add(textBilatuID);
		
		lblBilatuID = new JLabel("Bilatu:");
		sp.putConstraint(SpringLayout.NORTH, lblBilatuID, 3, SpringLayout.NORTH, textBilatuID);
		sp.putConstraint(SpringLayout.EAST, lblBilatuID, 0, SpringLayout.EAST, lblIzena);
		contenedor.add(lblBilatuID);
		/**************** EOF BOTONES ^^^^^^^^^^^^^^^^^^^^ **/
		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
		 tabla.setRowSorter(rowSorter);
		 
		 textBilatuID.getDocument().addDocumentListener(new DocumentListener(){

	            @Override
	            public void insertUpdate(DocumentEvent e) {
	                String text = textBilatuID.getText();

	                if (text.trim().length() == 0) {
	                    rowSorter.setRowFilter(null);
	                } else {
	                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
	                }
	            }

	            @Override
	            public void removeUpdate(DocumentEvent e) {
	                String text = textBilatuID.getText();

	                if (text.trim().length() == 0) {
	                    rowSorter.setRowFilter(null);
	                } else {
	                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
	                }
	            }

	            @Override
	            public void changedUpdate(DocumentEvent e) {
	                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	            }
		 });

		
		
		// Se hace visible la ventana
		setVisible(true);
	}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
               JFrame frame = new JFrame("Row Filter");
               frame.getContentPane().add(new DepartamentuaKudeatu());
               frame.pack();
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.setLocationRelativeTo(null);
               frame.setVisible(true);
            }

        });
    }
	

}