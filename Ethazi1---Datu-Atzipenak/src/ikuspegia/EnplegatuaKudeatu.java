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

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import java.awt.Font;

public class EnplegatuaKudeatu extends JFrame {

	 /**************** ATRIBUTOS ***************************/
    //CONTENEDOR PRINCIPAL
    private JPanel contenedor;
 
    //DEFINICI�N DE LAS ETIQUETAS
    private JLabel lblIzena;
    private JLabel lblKokapena;
 
    //DEFINICI�N DE LOS CUADROS DE TEXTO
    protected JTextField txtIzena;
    protected JTextField txtAbizena;
 
    //DEFINICI�N DE LOS BOTONES
    protected JButton btnAdd;
    protected JButton btnDel;
    protected JButton btnUpd;
 
    //DEFINICI�N DE LOS OBJETOS PARA LA TABLA
    private JScrollPane scroll; //Panel de scroll que contiene la tabla
    protected Object[][] datos; //Cuerpo de la tabla
    protected String[] cabecera;    //Cabecera de la tabla
    protected DefaultTableModel dtm;//Uni�n de la cabecera y la tabla
    protected JTable tabla; //Tabla propiamente dicha
 
    /**************** M�TODOS ***************************/
    //CONSTRUCTOR
    EnplegatuaKudeatu(){
    	this.setResizable(false);
        //M�todos de la JFrame
        setBounds(100, 100, 630, 450);//Definir las dimensiones de la ventana
        setTitle("DEPARTAMENTUEN KUDEAPENA");    //Barra de t�tulo
        setDefaultCloseOperation(EXIT_ON_CLOSE);    //Acci�n al pulsar salir
 
        //CREAR EL CONTENEDOR PRINCIPAL Y A�ADIRLO A LA VENTANA
        contenedor = new JPanel();
        getContentPane().add(contenedor);
 
        //INDICAR QUE SE QUIERE USAR SPRINGLAYOUT
        SpringLayout sp = new SpringLayout();
        contenedor.setLayout(sp);
 
        //Vamos al l�o
        /**************** BOF ETIQUETAS  vvvvvvvvvvvvvvvv **/
        //ETIQUETA NOMBRE
        lblIzena = new JLabel("Izena:");  //Crear el objeto
        contenedor.add(lblIzena);      //A�adirlo al contenedor
        sp.putConstraint(SpringLayout.NORTH, lblIzena, 10,
                        SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, lblIzena,  10,
                        SpringLayout.WEST, contenedor);
       
        //ETIQUETA NIF
        lblKokapena = new JLabel("Kokapena:");
        sp.putConstraint(SpringLayout.WEST, lblKokapena, 0, SpringLayout.WEST, lblIzena);
        contenedor.add(lblKokapena);
        /**************** EOF ETIQUETAS  ^^^^^^^^^^^^^^^^ **/
 
        /**************** BOF CUADROS DE  TEXTO vvvvvvvvv **/
        //CUADRO DE TEXTO PARA EL NOMBRE
        txtIzena = new JTextField();
        contenedor.add(txtIzena);
        sp.putConstraint(SpringLayout.NORTH, txtIzena, 10,
                        SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, txtIzena, 100,
                        SpringLayout.WEST, contenedor);
        sp.putConstraint(SpringLayout.EAST, txtIzena, 300,
                        SpringLayout.WEST, contenedor);
        //CUADRO DE TEXTO PARA EL NIF
        txtAbizena = new JTextField();
        sp.putConstraint(SpringLayout.NORTH, lblKokapena, 3, SpringLayout.NORTH, txtAbizena);
        contenedor.add(txtAbizena);    //a�adir al contenedor
        sp.putConstraint(SpringLayout.NORTH, txtAbizena, 50,
                        SpringLayout.NORTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, txtAbizena, 100,
                        SpringLayout.WEST, contenedor);
        sp.putConstraint(SpringLayout.EAST, txtAbizena, 300,
                        SpringLayout.WEST, contenedor);
        /**************** EOF CUADROS DE  TEXTO ^^^^^^^^^ **/
 
        /**************** BOF TABLA  vvvvvvvvvvvvvvvvvvvv **/
        scroll      = 
        		new JScrollPane();
        cabecera    = new String[] {"ID","IZENA","SOLDATA","ALTA DATA","ALTA ORDUA","ZUZENDARIA","DEPT.","ARDURA"};
        dtm         = new DefaultTableModel(datos,cabecera);
        tabla       = new JTable(dtm);
        scroll.setViewportView(tabla);
        //y ahora se coloca el scrollpane...
        contenedor.add(scroll); //a�adir al contenedor
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
        //BOT�N A�ADIR
        btnAdd          = new JButton("Gehitu");
        contenedor.add(btnAdd);
        sp.putConstraint(SpringLayout.SOUTH, btnAdd, -10,
                        SpringLayout.SOUTH, contenedor);//colocarlo
        sp.putConstraint(SpringLayout.WEST, btnAdd,   60,
                        SpringLayout.WEST, contenedor);
        //BOT�N BORRAR
        btnDel          = new JButton("Ezabatu");
        contenedor.add(btnDel);
        sp.putConstraint(SpringLayout.SOUTH, btnDel, -10,
                        SpringLayout.SOUTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, btnDel,  190,
                        SpringLayout.WEST, contenedor);
        //BOT�N MODIFICAR
        btnUpd          = new JButton("Editatu");
        contenedor.add(btnUpd);
        sp.putConstraint(SpringLayout.SOUTH, btnUpd, -10,
                        SpringLayout.SOUTH, contenedor);
        sp.putConstraint(SpringLayout.WEST, btnUpd,  310,
                        SpringLayout.WEST, contenedor);
        /**************** EOF BOTONES ^^^^^^^^^^^^^^^^^^^^ **/
 
        //Se hace visible la ventana
        setVisible(true);
    }
    
    
}
