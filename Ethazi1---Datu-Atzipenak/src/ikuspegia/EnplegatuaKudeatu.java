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
import eredua.Kontsultak;
import eredua.Update;
import kontroladorea.Departamentua;
import kontroladorea.Enplegatua;
import kontroladorea.Metodoak;
import kontroladorea.MetodoakIkuspegia;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;

public class EnplegatuaKudeatu extends JFrame {

	 /**************** ATRIBUTOS ***************************/
    //CONTENEDOR PRINCIPAL
    private JPanel contenedor;
 
    //DEFINICIÓN DE LAS ETIQUETAS
    private JLabel lblIzena;
    private JLabel lblKokapena;

    //DEFINICIÓN DE LOS CUADROS DE TEXTO
    protected JTextField txtIzena;
    protected JTextField txtSoldata;

    //DEFINICIÓN DE LOS BOTONES
    protected JButton btnAdd;
    protected JButton btnDel;
    protected JButton btnUpd;
 
    //DEFINICIÓN DE LOS OBJETOS PARA LA TABLA
    private JScrollPane scroll; //Panel de scroll que contiene la tabla
    protected Object[][] datos; //Cuerpo de la tabla
    protected String[] cabecera;    //Cabecera de la tabla
    protected DefaultTableModel dtm;//Unión de la cabecera y la tabla
    protected JTable tabla; //Tabla propiamente dicha
    private JButton btnIrten;
    private JButton btnBaieztatu;
    private JLabel lblBilatu;
    private JTextField textBilatuID;
    private JCheckBox cboxZuzendaria;
    
    private JComboBox cbDept = new JComboBox();
    JComboBox cbArdura = new JComboBox();
    
    private String zuzendaria = "Ez";
    
    private String[] ardurak;
    private String[] departamentuak;
    private int lerroAukeratu;
    private Boolean zuzendariBalorea;
    private JButton btnEzeztatu;
 
    /**************** MÉTODOS ***************************/
    //CONSTRUCTOR
    @SuppressWarnings("serial")
	public EnplegatuaKudeatu(){
    	this.setResizable(false);
        //Métodos de la JFrame
        setBounds(100, 100, 630, 450);//Definir las dimensiones de la ventana
        setTitle("ENPLEGATUEN KUDEAPENA");    //Barra de título
        setDefaultCloseOperation(EXIT_ON_CLOSE);    //Acción al pulsar salir
 
        //CREAR EL CONTENEDOR PRINCIPAL Y AÑADIRLO A LA VENTANA
        contenedor = new JPanel();
        getContentPane().add(contenedor);
 
        //INDICAR QUE SE QUIERE USAR SPRINGLAYOUT
        SpringLayout sp = new SpringLayout();
        sp.putConstraint(SpringLayout.WEST, cbDept, 0, SpringLayout.WEST, cbArdura);
        sp.putConstraint(SpringLayout.EAST, cbDept, -148, SpringLayout.EAST, contenedor);
        sp.putConstraint(SpringLayout.EAST, cbArdura, -148, SpringLayout.EAST, contenedor);
        contenedor.setLayout(sp);
 
        //Vamos al lío
        /**************** BOF ETIQUETAS  vvvvvvvvvvvvvvvv **/
        //ETIQUETA NOMBRE
        lblIzena = new JLabel("Izena:");  //Crear el objeto
        sp.putConstraint(SpringLayout.NORTH, cbDept, -3, SpringLayout.NORTH, lblIzena);
        sp.putConstraint(SpringLayout.NORTH, lblIzena, 53, SpringLayout.NORTH, contenedor);
        contenedor.add(lblIzena);
       
        //ETIQUETA NIF
        lblKokapena = new JLabel("Soldata:");
        sp.putConstraint(SpringLayout.NORTH, cbArdura, -3, SpringLayout.NORTH, lblKokapena);
        sp.putConstraint(SpringLayout.WEST, lblKokapena, 10, SpringLayout.WEST, contenedor);
        sp.putConstraint(SpringLayout.WEST, lblIzena, 0, SpringLayout.WEST, lblKokapena);
        contenedor.add(lblKokapena);
        /**************** EOF ETIQUETAS  ^^^^^^^^^^^^^^^^ **/
 
        /**************** BOF CUADROS DE  TEXTO vvvvvvvvv **/
        //CUADRO DE TEXTO PARA EL NOMBRE
        txtIzena = new JTextField();
        sp.putConstraint(SpringLayout.NORTH, txtIzena, -3, SpringLayout.NORTH, lblIzena);
        sp.putConstraint(SpringLayout.WEST, txtIzena, 32, SpringLayout.EAST, lblIzena);
        sp.putConstraint(SpringLayout.EAST, txtIzena, -407, SpringLayout.EAST, contenedor);
        contenedor.add(txtIzena);
        //CUADRO DE TEXTO PARA EL NIF
        txtSoldata = new JTextField();
        sp.putConstraint(SpringLayout.NORTH, txtSoldata, -3, SpringLayout.NORTH, lblKokapena);
        sp.putConstraint(SpringLayout.WEST, txtSoldata, 0, SpringLayout.WEST, txtIzena);
        sp.putConstraint(SpringLayout.EAST, txtSoldata, -407, SpringLayout.EAST, contenedor);
        contenedor.add(txtSoldata);
        /**************** EOF CUADROS DE  TEXTO ^^^^^^^^^ **/
 
        /**************** BOF TABLA  vvvvvvvvvvvvvvvvvvvv **/
        scroll      = 
        		new JScrollPane();
        sp.putConstraint(SpringLayout.SOUTH, lblKokapena, -58, SpringLayout.NORTH, scroll);
        sp.putConstraint(SpringLayout.NORTH, scroll, 176,
                        SpringLayout.NORTH, contenedor);
        cabecera    = new String[] {"ID","IZENA","SOLDATA","ALTA DATA","ALTA ORDUA","ZUZENDARIA","DEPT.","ARDURA"};
        dtm         = new DefaultTableModel(Metodoak.enplegatuakIkusi(), cabecera);
        tabla       = new JTable(dtm) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
        JCheckBox cboxZuzendaria = new JCheckBox("Zuzendaria");
        sp.putConstraint(SpringLayout.NORTH, cboxZuzendaria, 14, SpringLayout.SOUTH, txtSoldata);
        cboxZuzendaria.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		if(zuzendaria == "Ez") {
        			zuzendaria = "Bai";
        		} else {
        			zuzendaria = "Ez";
        		}
        		
        		
        	}
        });
		
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scroll.setViewportView(tabla);
        //y ahora se coloca el scrollpane...
        contenedor.add(scroll); //añadir al contenedor
        sp.putConstraint(SpringLayout.WEST, scroll,   10,
                        SpringLayout.WEST, contenedor);
        sp.putConstraint(SpringLayout.EAST, scroll,  -10,
                        SpringLayout.EAST, contenedor);
        sp.putConstraint(SpringLayout.SOUTH, scroll, -50,
                        SpringLayout.SOUTH, contenedor);
        /**************** EOF TABLA ^^^^^^^^^^^^^^^^^^^^ **/
 
        /**************** BOF BOTONES vvvvvvvvvvvvvvvvvv **/
        //BOTÓN AÑADIR
        btnAdd          = new JButton("Gehitu");
        btnAdd.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		boolean beteta = true;
        		int idDept = 0;
        		int idArdura = 0;
        		
        		if(txtIzena.getText().equals("")) {
        			beteta = false;
        			JOptionPane.showMessageDialog(null,"Izena jarri", "Txarto", JOptionPane.INFORMATION_MESSAGE);
        		}
        		else if(txtSoldata.getText().equals("")) {
        			beteta = false;
        			JOptionPane.showMessageDialog(null,"Soldata jarri", "Txarto", JOptionPane.INFORMATION_MESSAGE);
        		}
        		
        		if(beteta == true) {
	        		SimpleDateFormat data = new SimpleDateFormat("yyyy/MM/dd");
	        		SimpleDateFormat ordua = new SimpleDateFormat("HH:mm");
	        		int id = Integer.parseInt(dtm.getValueAt(dtm.getRowCount() - 1, 0).toString());
	        		System.out.println(id);
	        		String izena = txtIzena.getText();
	        		String soldata = txtSoldata.getText();
	        		Date altaData = new Date();
	        		Date altaOrdua = new Date();
	        		idDept = Metodoak.departamentuIdLortu(cbDept.getSelectedItem().toString());
	        		idArdura = Metodoak.arduraIdLortu(cbArdura.getSelectedItem().toString());
	        		
	        		System.out.println(zuzendaria + "hola");
	        		
	        		dtm.addRow(new Object[] {id + 1, izena, soldata, data.format(altaData), ordua.format(altaOrdua), zuzendaria, cbDept.getSelectedItem().toString(), cbArdura.getSelectedItem().toString()});
	        		Metodoak.sartuEnplegatua(id + 1, izena, Double.parseDouble(soldata), data.format(altaData), ordua.format(altaOrdua), zuzendaria, idDept, idArdura);
	        	}
        	}
        });
        
        contenedor.add(btnAdd);
        sp.putConstraint(SpringLayout.SOUTH, btnAdd, -10,
                        SpringLayout.SOUTH, contenedor);//colocarlo
        sp.putConstraint(SpringLayout.WEST, btnAdd,   60,
                        SpringLayout.WEST, contenedor);
        //BOTÓN BORRAR
        btnDel          = new JButton("Ezabatu");
        btnDel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {

				if(tabla.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Aukeratu lerro bat, mesedez.");
				} else {
					lerroAukeratu = tabla.convertRowIndexToModel(tabla.getSelectedRow());
					Delete.EnplegatuaEzabatu(Integer.parseInt(dtm.getValueAt(lerroAukeratu, 0).toString()));
					((DefaultTableModel)tabla.getModel()).removeRow(lerroAukeratu);
					
				}
        	}
        });
        contenedor.add(btnDel);
        sp.putConstraint(SpringLayout.SOUTH, btnDel, -10,
                        SpringLayout.SOUTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, btnDel,  190,
                        SpringLayout.WEST, contenedor);
        //BOTÓN MODIFICAR
        btnUpd          = new JButton("Editatu");
        btnUpd.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String zuzendariString="";
        		boolean zuzendariBoolean=false;
        		String departamentuIzena="";
        		String arduraIzena="";
        		int i = tabla.getSelectedRow();
        		
        		if(i == -1) {
        			JOptionPane.showMessageDialog(null, "Mesedez, aukeratu lerro bat."); 
        		} else {
        			btnAdd.setEnabled(false);
        			btnDel.setEnabled(false);
        			lblBilatu.setEnabled(false);
        			btnBaieztatu.setEnabled(true);
        			btnEzeztatu.setEnabled(true);
        			
					lerroAukeratu = tabla.convertRowIndexToModel(tabla.getSelectedRow());
					
					System.out.println(lerroAukeratu);
        			
        			txtIzena.setText((String) dtm.getValueAt(lerroAukeratu, 1));
        			txtSoldata.setText((String) dtm.getValueAt(lerroAukeratu, 2));
        			
        			zuzendariString = (String) dtm.getValueAt(lerroAukeratu, 5);
        			if(zuzendariString.equalsIgnoreCase("Bai"))
        				zuzendariBoolean = true;
        			else
        				zuzendariBoolean = false;
        			
        			if(zuzendariBoolean==true)
        				cboxZuzendaria.setSelected(true);
        			else 
        				cboxZuzendaria.setSelected(false);
        			
        			//departamentuIzena = Kontsultak.DepartamentuIzenaLortu(Integer.parseInt(dtm.getValueAt(lerroAukeratu, 6).toString()));
        			cbDept.setSelectedItem(dtm.getValueAt(lerroAukeratu, 6));
        			
        			//arduraIzena = Kontsultak.ArduraIzenaLortu(Integer.parseInt(dtm.getValueAt(lerroAukeratu, 7).toString()));
        			cbArdura.setSelectedItem(dtm.getValueAt(lerroAukeratu, 7));
        		}
        		
        	}
        });
        contenedor.add(btnUpd);
        sp.putConstraint(SpringLayout.SOUTH, btnUpd, -10,
                        SpringLayout.SOUTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, btnUpd,  310,
                        SpringLayout.WEST, contenedor);
        
        btnIrten = new JButton("Irten");
        sp.putConstraint(SpringLayout.NORTH, btnIrten, 6, SpringLayout.SOUTH, scroll);
        sp.putConstraint(SpringLayout.WEST, btnIrten, 143, SpringLayout.EAST, btnUpd);
        sp.putConstraint(SpringLayout.SOUTH, btnIrten, -10, SpringLayout.SOUTH, contenedor);
        sp.putConstraint(SpringLayout.EAST, btnIrten, -25, SpringLayout.EAST, contenedor);
        btnIrten.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		MetodoakIkuspegia.pasatuMenura();
        		dispose();
        	}
        });
        contenedor.add(btnIrten);
        
        btnBaieztatu = new JButton("Baieztatu");
        sp.putConstraint(SpringLayout.NORTH, btnBaieztatu, -15, SpringLayout.NORTH, lblIzena);
        sp.putConstraint(SpringLayout.WEST, btnBaieztatu, 18, SpringLayout.EAST, cbDept);
        sp.putConstraint(SpringLayout.SOUTH, btnBaieztatu, -93, SpringLayout.NORTH, scroll);
        sp.putConstraint(SpringLayout.EAST, btnBaieztatu, 0, SpringLayout.EAST, btnIrten);
        btnBaieztatu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		if(txtIzena.getText().equals("") || txtSoldata.getText().equals("")) {
        			JOptionPane.showMessageDialog(null, "Ez utzi informazioa utzik, mesedez.");
        		}else {
        			
        			dtm.setValueAt(txtIzena.getText(), lerroAukeratu, 1);
        			dtm.setValueAt(txtSoldata.getText(), lerroAukeratu, 2);
        			
        			if(cboxZuzendaria.isSelected()) {
        				dtm.setValueAt("Bai", lerroAukeratu, 5);
        			} else {
        				dtm.setValueAt("Ez", lerroAukeratu, 5);
        			}
        			
        			dtm.setValueAt(cbDept.getSelectedItem().toString(), lerroAukeratu, 6);
        			dtm.setValueAt(cbArdura.getSelectedItem().toString(), lerroAukeratu, 7);
        			
        			Update.EnplegatuaAldatu(Integer.parseInt(dtm.getValueAt(lerroAukeratu, 0).toString()), txtIzena.getText(), txtSoldata.getText(), dtm.getValueAt(lerroAukeratu, 5).toString(), dtm.getValueAt(lerroAukeratu, 6).toString(), dtm.getValueAt(lerroAukeratu, 7).toString());
        			
        			txtIzena.setText("");
    				txtSoldata.setText("");
    				
    				btnAdd.setEnabled(true);
    				btnDel.setEnabled(true);
    				btnBaieztatu.setEnabled(false);
    				btnEzeztatu.setEnabled(false);
        		}
        	}
        });
        btnBaieztatu.setEnabled(false);
        contenedor.add(btnBaieztatu);
        sp.putConstraint(SpringLayout.WEST, cboxZuzendaria, 99, SpringLayout.WEST, contenedor);
        contenedor.add(cboxZuzendaria);
        contenedor.add(cbDept);
        departamentuak = Metodoak.departamentuIzenakIkusi();
        for(int i=0;i<departamentuak.length;i++) {
        	cbDept.addItem(departamentuak[i]);
        }
        
        JLabel lblDept = new JLabel("Departamentua:");
        sp.putConstraint(SpringLayout.NORTH, lblDept, 0, SpringLayout.NORTH, lblIzena);
        sp.putConstraint(SpringLayout.WEST, lblDept, 28, SpringLayout.EAST, txtIzena);
        contenedor.add(lblDept);
        
        JLabel lblArdura = new JLabel("Ardura:");
        sp.putConstraint(SpringLayout.WEST, cbArdura, 18, SpringLayout.EAST, lblArdura);
        sp.putConstraint(SpringLayout.NORTH, lblArdura, 0, SpringLayout.NORTH, lblKokapena);
        sp.putConstraint(SpringLayout.EAST, lblArdura, 0, SpringLayout.EAST, lblDept);
        contenedor.add(lblArdura);
        contenedor.add(cbArdura);
        ardurak = Metodoak.arduraIzenakIkusi();
        
        for(int i=0;i<ardurak.length;i++) {
        	cbArdura.addItem(ardurak[i]);
        }
        
        
        lblBilatu = new JLabel("Bilatu:");
        sp.putConstraint(SpringLayout.NORTH, lblBilatu, 10, SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, lblBilatu, 0, SpringLayout.WEST, lblIzena);
        contenedor.add(lblBilatu);
        
        textBilatuID = new JTextField();
        sp.putConstraint(SpringLayout.NORTH, textBilatuID, -3, SpringLayout.NORTH, lblBilatu);
        sp.putConstraint(SpringLayout.WEST, textBilatuID, 6, SpringLayout.EAST, lblBilatu);
        sp.putConstraint(SpringLayout.EAST, textBilatuID, -306, SpringLayout.EAST, contenedor);
        contenedor.add(textBilatuID);
        /**************** EOF BOTONES ^^^^^^^^^^^^^^^^^^^^ **/
 
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabla.getModel());
		 tabla.setRowSorter(rowSorter);
		 
		 btnEzeztatu = new JButton("Ezeztatu");
		 btnEzeztatu.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		
		 		txtIzena.setText("");
				txtSoldata.setText("");
				
				btnAdd.setEnabled(true);
				btnDel.setEnabled(true);
				btnBaieztatu.setEnabled(false);
				btnEzeztatu.setEnabled(false);
		 		
		 	}
		 });
		 sp.putConstraint(SpringLayout.NORTH, btnEzeztatu, 10, SpringLayout.SOUTH, btnBaieztatu);
		 sp.putConstraint(SpringLayout.WEST, btnEzeztatu, 0, SpringLayout.WEST, btnBaieztatu);
		 sp.putConstraint(SpringLayout.SOUTH, btnEzeztatu, 55, SpringLayout.SOUTH, btnBaieztatu);
		 sp.putConstraint(SpringLayout.EAST, btnEzeztatu, 0, SpringLayout.EAST, btnIrten);
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
		 
        setVisible(true);
    }
	
}