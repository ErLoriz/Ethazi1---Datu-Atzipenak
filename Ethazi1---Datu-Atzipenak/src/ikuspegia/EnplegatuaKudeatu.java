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
    
    private String zuzendaria = "false";
    
    private String[] ardurak;
    private String[] departamentuak;
    private int lerroAukeratu;
    private Boolean zuzendariBalorea;
 
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
        contenedor.setLayout(sp);
 
        //Vamos al lío
        /**************** BOF ETIQUETAS  vvvvvvvvvvvvvvvv **/
        //ETIQUETA NOMBRE
        lblIzena = new JLabel("Izena:");  //Crear el objeto
        sp.putConstraint(SpringLayout.NORTH, lblIzena, 53, SpringLayout.NORTH, contenedor);
        contenedor.add(lblIzena);
       
        //ETIQUETA NIF
        lblKokapena = new JLabel("Soldata:");
        sp.putConstraint(SpringLayout.WEST, lblKokapena, 10, SpringLayout.WEST, contenedor);
        sp.putConstraint(SpringLayout.WEST, lblIzena, 0, SpringLayout.WEST, lblKokapena);
        contenedor.add(lblKokapena);
        /**************** EOF ETIQUETAS  ^^^^^^^^^^^^^^^^ **/
 
        /**************** BOF CUADROS DE  TEXTO vvvvvvvvv **/
        //CUADRO DE TEXTO PARA EL NOMBRE
        txtIzena = new JTextField();
        sp.putConstraint(SpringLayout.NORTH, txtIzena, -3, SpringLayout.NORTH, lblIzena);
        sp.putConstraint(SpringLayout.WEST, txtIzena, 59, SpringLayout.EAST, lblIzena);
        sp.putConstraint(SpringLayout.EAST, txtIzena, -380, SpringLayout.EAST, contenedor);
        contenedor.add(txtIzena);
        //CUADRO DE TEXTO PARA EL NIF
        txtSoldata = new JTextField();
        sp.putConstraint(SpringLayout.WEST, txtSoldata, 50, SpringLayout.EAST, lblKokapena);
        sp.putConstraint(SpringLayout.EAST, txtSoldata, -380, SpringLayout.EAST, contenedor);
        sp.putConstraint(SpringLayout.NORTH, txtSoldata, -3, SpringLayout.NORTH, lblKokapena);
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
        cboxZuzendaria.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		if(zuzendaria == "false") {
        			zuzendaria = "true";
        		} else {
        			zuzendaria = "false";
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
        		
        		int id = dtm.getRowCount()+1;
        		String izena = txtIzena.getText();
        		String soldata = txtSoldata.getText();
        		Date altaData = new Date();
        		Date altaOrdua = new Date();
        		idDept = Metodoak.departamentuIdLortu(cbDept.getSelectedItem().toString());
        		idArdura = Metodoak.arduraIdLortu(cbArdura.getSelectedItem().toString());
        		
        		dtm.addRow(new Object[] {id, izena, soldata, data.format(altaData), ordua.format(altaOrdua), zuzendaria, idDept, idArdura});
        		Metodoak.sartuEnplegatua(id, izena, Double.parseDouble(soldata), data.format(altaData), ordua.format(altaOrdua), zuzendaria, idDept, idArdura);
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
        			
					lerroAukeratu = tabla.convertRowIndexToModel(tabla.getSelectedRow());
					
					System.out.println(lerroAukeratu);
        			
        			txtIzena.setText((String) dtm.getValueAt(lerroAukeratu, 1));
        			txtSoldata.setText((String) dtm.getValueAt(lerroAukeratu, 2));
        			
        			zuzendariString = (String) dtm.getValueAt(lerroAukeratu, 5);
        			if(zuzendariString.equalsIgnoreCase("true"))
        				zuzendariBoolean = true;
        			else
        				zuzendariBoolean = false;
        			
        			if(zuzendariBoolean==true)
        				cboxZuzendaria.setSelected(true);
        			else 
        				cboxZuzendaria.setSelected(false);
        			
        			departamentuIzena = Kontsultak.DepartamentuIzenaLortu(Integer.parseInt(dtm.getValueAt(lerroAukeratu, 6).toString()));
        			cbDept.setSelectedItem(departamentuIzena);
        			
        			arduraIzena = Kontsultak.ArduraIzenaLortu(Integer.parseInt(dtm.getValueAt(lerroAukeratu, 7).toString()));
        			cbArdura.setSelectedItem(arduraIzena);
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
        sp.putConstraint(SpringLayout.WEST, btnBaieztatu, 13, SpringLayout.EAST, cbDept);
        sp.putConstraint(SpringLayout.EAST, btnBaieztatu, 0, SpringLayout.EAST, scroll);
        btnBaieztatu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		if(txtIzena.getText().equals("") || txtSoldata.getText().equals("")) {
        			JOptionPane.showMessageDialog(null, "Ez utzi informazioa utzik, mesedez.");
        		}else {
        			
        			dtm.setValueAt(txtIzena.getText(), lerroAukeratu, 1);
        			dtm.setValueAt(txtSoldata.getText(), lerroAukeratu, 2);
        			
        			if(cboxZuzendaria.isSelected()) {
        				dtm.setValueAt("Bai", lerroAukeratu, 5);
        				zuzendariBalorea = true;
        			} else {
        				dtm.setValueAt("Ez", lerroAukeratu, 5);
        				zuzendariBalorea = false;
        			}
        			
        			dtm.setValueAt(Kontsultak.DepartamentuIdLortu(cbDept.getSelectedItem().toString()), lerroAukeratu, 6);
        			dtm.setValueAt(Kontsultak.ArduraIdLortu(cbArdura.getSelectedItem().toString()), lerroAukeratu, 7);
        			
        			Update.EnplegatuaAldatu(Integer.parseInt(dtm.getValueAt(lerroAukeratu, 0).toString()), txtIzena.getText(), txtSoldata.getText(), zuzendariBalorea.toString(), Integer.parseInt(dtm.getValueAt(tabla.getSelectedRow(), 6).toString()), Integer.parseInt(dtm.getValueAt(lerroAukeratu, 7).toString()));
        			
        			txtIzena.setText("");
    				txtSoldata.setText("");
    				
    				btnAdd.setEnabled(true);
    				btnDel.setEnabled(true);
        		}
        	}
        });
        sp.putConstraint(SpringLayout.NORTH, btnBaieztatu, 58, SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.SOUTH, btnBaieztatu, -73, SpringLayout.NORTH, scroll);
        btnBaieztatu.setEnabled(false);
        contenedor.add(btnBaieztatu);
        
        sp.putConstraint(SpringLayout.NORTH, cboxZuzendaria, 14, SpringLayout.SOUTH, txtSoldata);
        sp.putConstraint(SpringLayout.WEST, cboxZuzendaria, 99, SpringLayout.WEST, contenedor);
        contenedor.add(cboxZuzendaria);
        
        
        sp.putConstraint(SpringLayout.NORTH, cbDept, -3, SpringLayout.NORTH, lblIzena);
        sp.putConstraint(SpringLayout.EAST, cbDept, -128, SpringLayout.EAST, contenedor);
        contenedor.add(cbDept);
        departamentuak = Metodoak.departamentuIzenakIkusi();
        for(int i=0;i<departamentuak.length;i++) {
        	cbDept.addItem(departamentuak[i]);
        }
        
        JLabel lblDept = new JLabel("Departamentua:");
        sp.putConstraint(SpringLayout.NORTH, lblDept, 0, SpringLayout.NORTH, lblIzena);
        sp.putConstraint(SpringLayout.WEST, lblDept, 21, SpringLayout.EAST, txtIzena);
        contenedor.add(lblDept);
        
        JLabel lblArdura = new JLabel("Ardura:");
        sp.putConstraint(SpringLayout.NORTH, lblArdura, 0, SpringLayout.NORTH, lblKokapena);
        sp.putConstraint(SpringLayout.WEST, lblArdura, 0, SpringLayout.WEST, btnUpd);
        contenedor.add(lblArdura);
        
        
        sp.putConstraint(SpringLayout.WEST, cbArdura, 15, SpringLayout.EAST, lblArdura);
        sp.putConstraint(SpringLayout.EAST, cbArdura, -128, SpringLayout.EAST, contenedor);
        sp.putConstraint(SpringLayout.WEST, cbDept, 0, SpringLayout.WEST, cbArdura);
        sp.putConstraint(SpringLayout.NORTH, cbArdura, -3, SpringLayout.NORTH, lblKokapena);
        contenedor.add(cbArdura);
        ardurak = Metodoak.arduraIzenakIkusi();
        for(int i=0;i<ardurak.length;i++) {
        	cbArdura.addItem(ardurak[i]);
        }
        
        
        lblBilatu = new JLabel("Bilatu ID:");
        sp.putConstraint(SpringLayout.NORTH, lblBilatu, 10, SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, lblBilatu, 0, SpringLayout.WEST, lblIzena);
        contenedor.add(lblBilatu);
        
        textBilatuID = new JTextField();
        sp.putConstraint(SpringLayout.NORTH, textBilatuID, -3, SpringLayout.NORTH, lblBilatu);
        sp.putConstraint(SpringLayout.WEST, textBilatuID, 0, SpringLayout.WEST, txtIzena);
        sp.putConstraint(SpringLayout.EAST, textBilatuID, 28, SpringLayout.EAST, lblDept);
        contenedor.add(textBilatuID);
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
        
        //Se hace visible la ventana
        setVisible(true);
    }
	
}