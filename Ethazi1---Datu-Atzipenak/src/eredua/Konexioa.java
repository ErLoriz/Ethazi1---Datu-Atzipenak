package eredua;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Konexioa {
private String makina="localhost/";
    private String erabiltzailea="root";
    private String kodea="";
    private int portua = 3306;
    private String zerbitzaria="127.0.0.1";
    private static Connection conexion  = null;
 
    //CONSTRUCTOR
    //Recibe el nombre de la base de datos
    /**
     *
     * @param baseDatos
     * @return 
     */
    public Konexioa(String baseDatos){
       
    String server="jdbc:mysql://";
   
    this.zerbitzaria="jdbc:mysql://"+this.makina+baseDatos+":"+ this.portua+"/"+baseDatos;
    //String Servidor2="jdbc:mysql://localhost/filmeak:3306/filmeak";
        //Registrar el driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("ERROREA DRIVERRA ERREGISTRATZEAN");
            System.exit(0); //parar la ejecución
        }
 
        //Establecer la conexión con el servidor
        try {
           conexion = DriverManager.getConnection("jdbc:mysql://"+this.makina+baseDatos+"",this.erabiltzailea, this.kodea);
        } catch (SQLException e) {
            System.err.println("ERROREA ZERBITZARIAREKIN KONEKTATZEAN");
            System.exit(0); //parar la ejecución
        }
        System.out.println("Conectado a "+baseDatos);
    }

    
 
/**
 *
 * @return conexion
 */
    //Devuelve el objeto Connection que se usará en la clase Controller
    public static Connection getConnection() {
        return conexion;
    }
 
}
