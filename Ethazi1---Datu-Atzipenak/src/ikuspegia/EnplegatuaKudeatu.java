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
    private JButton button;
    private JButton button_1;
    private JLabel lblBilatu;
    private JTextField textBilatu;
    private JCheckBox cboxZuzendaria;
    
    static String zuzendaria = "false";
 
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
        dtm         = new DefaultTableModel(kontroladorea.Metodoak.enplegatuakIkusi(eredua.Kontsultak.EnplegatuakIkusi()),cabecera);
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
        		
        		System.out.println(zuzendaria);
        		
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
        		
        		SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
        		
        		int id = dtm.getRowCount();
        		String izena = txtIzena.getText();
        		String soldata = txtSoldata.getText();
        		Date altaData = new Date();
        		String altaOrdua = LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute();
        		String dept = "Informatika";
        		String ardura = "Irakaslea";
        		
        		dtm.addRow(new Object[] {id, izena, soldata, data.format(altaData), altaOrdua, zuzendaria, dept, ardura});
        		
        	}
        });
        contenedor.add(btnAdd);
        sp.putConstraint(SpringLayout.SOUTH, btnAdd, -10,
                        SpringLayout.SOUTH, contenedor);//colocarlo
        sp.putConstraint(SpringLayout.WEST, btnAdd,   60,
                        SpringLayout.WEST, contenedor);
        //BOTÓN BORRAR
        btnDel          = new JButton("Ezabatu");
        contenedor.add(btnDel);
        sp.putConstraint(SpringLayout.SOUTH, btnDel, -10,
                        SpringLayout.SOUTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, btnDel,  190,
                        SpringLayout.WEST, contenedor);
        //BOTÓN MODIFICAR
        btnUpd          = new JButton("Editatu");
        btnUpd.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		int i = tabla.getSelectedRow();
        		
        		if(i == -1) {
        			JOptionPane.showMessageDialog(null, "Mesedez, aukeratu lerro bat."); 
        		} else {
        			
        		}
        		
        	}
        });
        contenedor.add(btnUpd);
        sp.putConstraint(SpringLayout.SOUTH, btnUpd, -10,
                        SpringLayout.SOUTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, btnUpd,  310,
                        SpringLayout.WEST, contenedor);
        
        button = new JButton("Irten");
        sp.putConstraint(SpringLayout.NORTH, button, 6, SpringLayout.SOUTH, scroll);
        sp.putConstraint(SpringLayout.WEST, button, 143, SpringLayout.EAST, btnUpd);
        sp.putConstraint(SpringLayout.SOUTH, button, -10, SpringLayout.SOUTH, contenedor);
        sp.putConstraint(SpringLayout.EAST, button, -25, SpringLayout.EAST, contenedor);
        button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		MetodoakIkuspegia.pasatuMenura();
        		dispose();
        	}
        });
        contenedor.add(button);
        
        button_1 = new JButton("Baieztatu");
        sp.putConstraint(SpringLayout.NORTH, button_1, 58, SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, button_1, 0, SpringLayout.WEST, button);
        sp.putConstraint(SpringLayout.SOUTH, button_1, -73, SpringLayout.NORTH, scroll);
        sp.putConstraint(SpringLayout.EAST, button_1, -27, SpringLayout.EAST, contenedor);
        button_1.setEnabled(false);
        contenedor.add(button_1);
        
        sp.putConstraint(SpringLayout.NORTH, cboxZuzendaria, 14, SpringLayout.SOUTH, txtSoldata);
        sp.putConstraint(SpringLayout.WEST, cboxZuzendaria, 99, SpringLayout.WEST, contenedor);
        contenedor.add(cboxZuzendaria);
        
        JComboBox cbDept = new JComboBox();
        sp.putConstraint(SpringLayout.NORTH, cbDept, -3, SpringLayout.NORTH, lblIzena);
        sp.putConstraint(SpringLayout.EAST, cbDept, -128, SpringLayout.EAST, contenedor);
        contenedor.add(cbDept);
        
        JLabel lblDept = new JLabel("Departamentua:");
        sp.putConstraint(SpringLayout.NORTH, lblDept, 0, SpringLayout.NORTH, lblIzena);
        sp.putConstraint(SpringLayout.WEST, lblDept, 21, SpringLayout.EAST, txtIzena);
        contenedor.add(lblDept);
        
        JLabel lblArdura = new JLabel("Ardura:");
        sp.putConstraint(SpringLayout.NORTH, lblArdura, 0, SpringLayout.NORTH, lblKokapena);
        sp.putConstraint(SpringLayout.WEST, lblArdura, 0, SpringLayout.WEST, btnUpd);
        contenedor.add(lblArdura);
        
        JComboBox cbArdura = new JComboBox();
        sp.putConstraint(SpringLayout.WEST, cbArdura, 15, SpringLayout.EAST, lblArdura);
        sp.putConstraint(SpringLayout.EAST, cbArdura, -128, SpringLayout.EAST, contenedor);
        sp.putConstraint(SpringLayout.WEST, cbDept, 0, SpringLayout.WEST, cbArdura);
        sp.putConstraint(SpringLayout.NORTH, cbArdura, -3, SpringLayout.NORTH, lblKokapena);
        contenedor.add(cbArdura);
        
        lblBilatu = new JLabel("Bilatu ID:");
        sp.putConstraint(SpringLayout.NORTH, lblBilatu, 10, SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, lblBilatu, 0, SpringLayout.WEST, lblIzena);
        contenedor.add(lblBilatu);
        
        textBilatu = new JTextField();
        sp.putConstraint(SpringLayout.NORTH, textBilatu, -3, SpringLayout.NORTH, lblBilatu);
        sp.putConstraint(SpringLayout.WEST, textBilatu, 0, SpringLayout.WEST, txtIzena);
        sp.putConstraint(SpringLayout.EAST, textBilatu, 28, SpringLayout.EAST, lblDept);
        contenedor.add(textBilatu);
        /**************** EOF BOTONES ^^^^^^^^^^^^^^^^^^^^ **/
 
        //Se hace visible la ventana
        setVisible(true);
    }
	
}
