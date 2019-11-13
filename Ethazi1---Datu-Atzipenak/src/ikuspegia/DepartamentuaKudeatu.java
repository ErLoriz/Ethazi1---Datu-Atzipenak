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

import eredua.Delete;
import eredua.Insertak;
import eredua.Update;
import kontroladorea.Departamentua;
import kontroladorea.Enplegatua;
import kontroladorea.Metodoak;
import kontroladorea.MetodoakIkuspegia;
import logger.LoggerKudeatu;

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

	private int lerroAukeratu;
	private JButton btnEzeztatu;
	

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
        			int id = Integer.parseInt(dtm.getValueAt(dtm.getRowCount() - 1, 0).toString());
	        		String izena = txtIzena.getText();
	        		String kokapena = txtKokapena.getText();
	        		Metodoak.sartuDepartamentua(id + 1, izena, kokapena);
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
					LoggerKudeatu.idatziLog("Lerroa aukeratu behar da.");
				} else {
					lerroAukeratu = tabla.convertRowIndexToModel(tabla.getSelectedRow());
					Metodoak.ezabatuDepartamentua(Integer.parseInt(dtm.getValueAt(lerroAukeratu, 0).toString()));
					((DefaultTableModel)tabla.getModel()).removeRow(lerroAukeratu);
					
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
					
					lerroAukeratu = tabla.convertRowIndexToModel(tabla.getSelectedRow());
					
					System.out.println(lerroAukeratu);
					
					txtIzena.setText((String) dtm.getValueAt(lerroAukeratu, 1));
					txtKokapena.setText((String) dtm.getValueAt(lerroAukeratu, 2));
					
					
					btnBaieztatu.setEnabled(true);
					btnEzeztatu.setEnabled(true);
					btnAdd.setEnabled(false);
					btnDel.setEnabled(false);
					
					txtIzena.setText((String) dtm.getValueAt(lerroAukeratu, 1));
					txtKokapena.setText((String) dtm.getValueAt(lerroAukeratu, 2));
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
		sp.putConstraint(SpringLayout.NORTH, btnBaieztatu, 23, SpringLayout.NORTH, contenedor);
		sp.putConstraint(SpringLayout.WEST, btnBaieztatu, 129, SpringLayout.EAST, txtIzena);
		sp.putConstraint(SpringLayout.EAST, btnBaieztatu, -106, SpringLayout.EAST, contenedor);
		btnBaieztatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
        		if(txtIzena.getText().equals("") || txtKokapena.getText().equals("")) {
        			JOptionPane.showMessageDialog(null, "Ez utzi informazioa utzik, mesedez.");
        		} else {
        			String idDeptString;
    				int idDept;

        			dtm.setValueAt(txtIzena.getText(), lerroAukeratu, 1);
        			dtm.setValueAt(txtKokapena.getText(), lerroAukeratu, 2);
        			
    				btnBaieztatu.setEnabled(false);
    				btnEzeztatu.setEnabled(false);
    				btnAdd.setEnabled(true);
    				btnDel.setEnabled(true);
    				textBilatuID.setEnabled(true);
    				
    				idDeptString = (String)dtm.getValueAt(lerroAukeratu, 0);
    				System.out.println(idDeptString);
    				idDept = Integer.parseInt(idDeptString);
    				System.out.println(idDept);
    				Metodoak.aldatuDepartamentua(idDept, txtIzena.getText(), txtKokapena.getText());
    				
    				txtIzena.setText("");
    				txtKokapena.setText("");
        		}
				
			}
		});
		btnBaieztatu.setEnabled(false);
		contenedor.add(btnBaieztatu);
		
		lblKokapena = new JLabel("Kokapena:");
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
		 
		 btnEzeztatu = new JButton("Ezeztatu");
		 btnEzeztatu.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		
		 		txtIzena.setText("");
				txtKokapena.setText("");
				
				btnAdd.setEnabled(true);
				btnDel.setEnabled(true);
				btnBaieztatu.setEnabled(false);
				btnEzeztatu.setEnabled(false);
		 		
		 	}
		 });
		 sp.putConstraint(SpringLayout.SOUTH, btnBaieztatu, -19, SpringLayout.NORTH, btnEzeztatu);
		 sp.putConstraint(SpringLayout.WEST, btnEzeztatu, 129, SpringLayout.EAST, txtKokapena);
		 sp.putConstraint(SpringLayout.SOUTH, btnEzeztatu, 8, SpringLayout.SOUTH, lblKokapena);
		 sp.putConstraint(SpringLayout.EAST, btnEzeztatu, -106, SpringLayout.EAST, contenedor);
		 sp.putConstraint(SpringLayout.NORTH, btnEzeztatu, -40, SpringLayout.SOUTH, lblKokapena);
		 btnEzeztatu.setEnabled(false);
		 contenedor.add(btnEzeztatu);
		 
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

    
	

}