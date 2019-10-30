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

import kontroladorea.Enplegatua;
import kontroladorea.MetodoakIkuspegia;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
    protected JTextField txtAbizena;
 
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
 
    /**************** MÉTODOS ***************************/
    //CONSTRUCTOR
    @SuppressWarnings("serial")
	public EnplegatuaKudeatu(){
    	this.setResizable(false);
        //Métodos de la JFrame
        setBounds(100, 100, 630, 450);//Definir las dimensiones de la ventana
        setTitle("DEPARTAMENTUEN KUDEAPENA");    //Barra de título
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
        contenedor.add(lblIzena);      //Añadirlo al contenedor
        sp.putConstraint(SpringLayout.NORTH, lblIzena, 10,
                        SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, lblIzena,  10,
                        SpringLayout.WEST, contenedor);
       
        //ETIQUETA NIF
        lblKokapena = new JLabel("Soldata:");
        sp.putConstraint(SpringLayout.NORTH, lblKokapena, 29, SpringLayout.SOUTH, lblIzena);
        sp.putConstraint(SpringLayout.WEST, lblKokapena, 0, SpringLayout.WEST, lblIzena);
        contenedor.add(lblKokapena);
        /**************** EOF ETIQUETAS  ^^^^^^^^^^^^^^^^ **/
 
        /**************** BOF CUADROS DE  TEXTO vvvvvvvvv **/
        //CUADRO DE TEXTO PARA EL NOMBRE
        txtIzena = new JTextField();
        sp.putConstraint(SpringLayout.NORTH, txtIzena, 10, SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, txtIzena, 59, SpringLayout.EAST, lblIzena);
        sp.putConstraint(SpringLayout.EAST, txtIzena, -380, SpringLayout.EAST, contenedor);
        contenedor.add(txtIzena);
        //CUADRO DE TEXTO PARA EL NIF
        txtAbizena = new JTextField();
        sp.putConstraint(SpringLayout.NORTH, txtAbizena, 20, SpringLayout.SOUTH, txtIzena);
        sp.putConstraint(SpringLayout.WEST, txtAbizena, 50, SpringLayout.EAST, lblKokapena);
        sp.putConstraint(SpringLayout.EAST, txtAbizena, 244,
                        SpringLayout.WEST, contenedor);
        contenedor.add(txtAbizena);
        /**************** EOF CUADROS DE  TEXTO ^^^^^^^^^ **/
 
        /**************** BOF TABLA  vvvvvvvvvvvvvvvvvvvv **/
        scroll      = 
        		new JScrollPane();
        cabecera    = new String[] {"ID","IZENA","SOLDATA","ALTA DATA","ALTA ORDUA","ZUZENDARIA","DEPT.","ARDURA"};
        dtm         = new DefaultTableModel(enplegatuakIkusi(eredua.Kontsultak.EnplegatuakIkusi()),cabecera);
        tabla       = new JTable(dtm) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scroll.setViewportView(tabla);
        //y ahora se coloca el scrollpane...
        contenedor.add(scroll); //añadir al contenedor
        sp.putConstraint(SpringLayout.NORTH, scroll, 120,
                        SpringLayout.NORTH, contenedor);
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
        	}
        });
        contenedor.add(btnUpd);
        sp.putConstraint(SpringLayout.SOUTH, btnUpd, -10,
                        SpringLayout.SOUTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, btnUpd,  310,
                        SpringLayout.WEST, contenedor);
        
        button = new JButton("Irten");
        button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		MetodoakIkuspegia.pasatuMenura();
        		dispose();
        	}
        });
        sp.putConstraint(SpringLayout.NORTH, button, 6, SpringLayout.SOUTH, scroll);
        sp.putConstraint(SpringLayout.WEST, button, -94, SpringLayout.EAST, scroll);
        sp.putConstraint(SpringLayout.SOUTH, button, 0, SpringLayout.SOUTH, btnAdd);
        sp.putConstraint(SpringLayout.EAST, button, -25, SpringLayout.EAST, contenedor);
        contenedor.add(button);
        
        button_1 = new JButton("Baieztatu");
        sp.putConstraint(SpringLayout.NORTH, button_1, -15, SpringLayout.NORTH, lblKokapena);
        sp.putConstraint(SpringLayout.WEST, button_1, 0, SpringLayout.WEST, button);
        sp.putConstraint(SpringLayout.SOUTH, button_1, -37, SpringLayout.NORTH, scroll);
        sp.putConstraint(SpringLayout.EAST, button_1, -27, SpringLayout.EAST, contenedor);
        button_1.setEnabled(false);
        contenedor.add(button_1);
        
        JCheckBox cboxZuzendaria = new JCheckBox("Zuzendaria");
        sp.putConstraint(SpringLayout.WEST, cboxZuzendaria, 100, SpringLayout.WEST, contenedor);
        sp.putConstraint(SpringLayout.SOUTH, cboxZuzendaria, -6, SpringLayout.NORTH, scroll);
        contenedor.add(cboxZuzendaria);
        
        JComboBox cbDept = new JComboBox();
        sp.putConstraint(SpringLayout.NORTH, cbDept, 7, SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.EAST, cbDept, -128, SpringLayout.EAST, contenedor);
        contenedor.add(cbDept);
        
        JLabel lblDept = new JLabel("Departamentua:");
        sp.putConstraint(SpringLayout.WEST, cbDept, 16, SpringLayout.EAST, lblDept);
        sp.putConstraint(SpringLayout.NORTH, lblDept, 0, SpringLayout.NORTH, lblIzena);
        sp.putConstraint(SpringLayout.WEST, lblDept, 23, SpringLayout.EAST, txtIzena);
        contenedor.add(lblDept);
        
        JLabel lblArdura = new JLabel("Ardura:");
        sp.putConstraint(SpringLayout.NORTH, lblArdura, 0, SpringLayout.NORTH, lblKokapena);
        sp.putConstraint(SpringLayout.EAST, lblArdura, 0, SpringLayout.EAST, lblDept);
        contenedor.add(lblArdura);
        
        JComboBox cbArdura = new JComboBox();
        sp.putConstraint(SpringLayout.NORTH, cbArdura, 26, SpringLayout.SOUTH, cbDept);
        sp.putConstraint(SpringLayout.WEST, cbArdura, 0, SpringLayout.WEST, cbDept);
        sp.putConstraint(SpringLayout.EAST, cbArdura, 0, SpringLayout.EAST, cbDept);
        contenedor.add(cbArdura);
        /**************** EOF BOTONES ^^^^^^^^^^^^^^^^^^^^ **/
 
        //Se hace visible la ventana
        setVisible(true);
    }
    
    public static String[][] enplegatuakIkusi(ArrayList <Enplegatua> e1){
    	String[][] data = new String[e1.size()][8];
    	
    	for(int i = 0; i <= e1.size() - 1; i++) {
	    		
	    	data[i][0] = Integer.toString(e1.get(i).getIdEnplegatua());
	    	data[i][1] = e1.get(i).getIzena();
	    	data[i][2] = Double.toString(e1.get(i).getSoldata());
	    	data[i][3] = e1.get(i).getAltaData();
	    	data[i][4] = e1.get(i).getAltaOrdua();
	    	data[i][5] = Boolean.toString(e1.get(i).getZuzendari());
	    	data[i][6] = Integer.toString(e1.get(i).getDepartamentua_idDepartamentua());
	    	data[i][7] = Integer.toString(e1.get(i).getArdura_idArdura());
    	}
    
    	return data;
    }
}
