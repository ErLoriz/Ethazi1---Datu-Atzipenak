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

import kontroladorea.Metodoak;
import kontroladorea.MetodoakIkuspegia;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DepartamentuaKudeatu extends JFrame {

	 /**************** ATRIBUTOS ***************************/
    //CONTENEDOR PRINCIPAL
    private JPanel contenedor;
 
    //DEFINICIÓN DE LAS ETIQUETAS
    private JLabel lblIzena;
    private JLabel lblKokapena;
 
    //DEFINICIÓN DE LOS CUADROS DE TEXTO
    protected JTextField txtIzena;
    protected JTextField txtKokapena;
 
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
    private JButton btnBaieztatu;
 
    /**************** MÉTODOS ***************************/
    //CONSTRUCTOR
    public DepartamentuaKudeatu(){
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
        lblIzena.setEnabled(false);
        sp.putConstraint(SpringLayout.WEST, lblIzena, 10, SpringLayout.WEST, contenedor);
        contenedor.add(lblIzena);
       
        //ETIQUETA NIF
        lblKokapena = new JLabel("Kokapena:");
        lblKokapena.setEnabled(false);
        sp.putConstraint(SpringLayout.WEST, lblKokapena, 10, SpringLayout.WEST, contenedor);
        contenedor.add(lblKokapena);
        /**************** EOF ETIQUETAS  ^^^^^^^^^^^^^^^^ **/
 
        /**************** BOF CUADROS DE  TEXTO vvvvvvvvv **/
        //CUADRO DE TEXTO PARA EL NOMBRE
        txtIzena = new JTextField();
        txtIzena.setEnabled(false);
        sp.putConstraint(SpringLayout.NORTH, lblIzena, 3, SpringLayout.NORTH, txtIzena);
        contenedor.add(txtIzena);
        sp.putConstraint(SpringLayout.NORTH, txtIzena, 10,
                        SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, txtIzena, 100,
                        SpringLayout.WEST, contenedor);
        sp.putConstraint(SpringLayout.EAST, txtIzena, 300,
                        SpringLayout.WEST, contenedor);
        //CUADRO DE TEXTO PARA EL NIF
        txtKokapena = new JTextField();
        txtKokapena.setEnabled(false);
        sp.putConstraint(SpringLayout.NORTH, lblKokapena, 3, SpringLayout.NORTH, txtKokapena);
        contenedor.add(txtKokapena);    //añadir al contenedor
        sp.putConstraint(SpringLayout.NORTH, txtKokapena, 50,
                        SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, txtKokapena, 100,
                        SpringLayout.WEST, contenedor);
        sp.putConstraint(SpringLayout.EAST, txtKokapena, 300,
                        SpringLayout.WEST, contenedor);
        /**************** EOF CUADROS DE  TEXTO ^^^^^^^^^ **/
 
        /**************** BOF TABLA  vvvvvvvvvvvvvvvvvvvv **/
        scroll      = 
        		new JScrollPane();
        cabecera    = new String[] {"ID","IZENA","KOKAPENA"};
        dtm         = new DefaultTableModel(datos,cabecera);
        tabla       = new JTable(dtm);
        scroll.setViewportView(tabla);
        Metodoak.departamentuakIkusi(scroll);
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
        sp.putConstraint(SpringLayout.NORTH, btnDel, 0, SpringLayout.NORTH, btnAdd);
        sp.putConstraint(SpringLayout.WEST, btnDel, 59, SpringLayout.EAST, btnAdd);
        contenedor.add(btnDel);
        //BOTÓN MODIFICAR
        btnUpd          = new JButton("Editatu");
        btnUpd.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		lblIzena.setEnabled(true);
        		lblKokapena.setEnabled(true);
        		txtIzena.setEnabled(true);
        		txtKokapena.setEnabled(true);
        		btnBaieztatu.setEnabled(true);
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
        btnBaieztatu.setEnabled(false);
        sp.putConstraint(SpringLayout.NORTH, btnBaieztatu, 20, SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, btnBaieztatu, 52, SpringLayout.EAST, txtIzena);
        sp.putConstraint(SpringLayout.SOUTH, btnBaieztatu, 0, SpringLayout.SOUTH, lblKokapena);
        sp.putConstraint(SpringLayout.EAST, btnBaieztatu, 152, SpringLayout.EAST, txtIzena);
        contenedor.add(btnBaieztatu);
        /**************** EOF BOTONES ^^^^^^^^^^^^^^^^^^^^ **/
 
        //Se hace visible la ventana
        setVisible(true);
    }
}
