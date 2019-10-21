package eredua;

	import java.io.BufferedReader;
	import java.io.FileReader;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	/**
	 * 
	 * @author Oier Vicente,Diego,Erlantz,Aintzane
	 *
	 */
	public class Konexioa {
		
		private String makina;
		private String projeckt;
		private String erabiltzailea;
		private String pasahitza;
		private String server;
		private static Connection conexion  = null;
		
		
		
		/*******Konexioa objektua sortzen dugu metodo batekin konexioa egiteko**********/
		public Konexioa() {
		
			String fichero = "src\\Eredua\\Konexioa.txt";

			int kontagailua =0;
			String server="jdbc:mysql://";
			try {
			      FileReader fr = new FileReader(fichero);
			      BufferedReader br = new BufferedReader(fr);
			 
			      String linea;
			      while((linea = br.readLine()) != null) {
			    	  System.out.println(linea);
			        if(kontagailua==0) {
			        	this.makina=linea;}
			      	if (kontagailua==1) {
			      		this.projeckt=linea;}
			      	if (kontagailua==2) {
			      		this.erabiltzailea=linea;}
			      	kontagailua++;
			      	linea="";
			      	}
			      
			      if (kontagailua==3) {
			    	  this.pasahitza="";
			    	  
			      }
			      
			 
			      fr.close();
			    }
			    catch(Exception e) {
			      System.out.println("Excepcion leyendo fichero "+ fichero + ": " + e);
			    }
			server=server+this.makina+"/"+this.projeckt;
			System.out.println(server);
			
				try {
	     
	            Class.forName("com.mysql.jdbc.Driver");
				}catch(ClassNotFoundException e) {
					System.out.println("Error al cargar el controlador");
					System.exit(0);
				}

	            
	            try {
	            this.conexion = DriverManager.getConnection(server,this.erabiltzailea,this.pasahitza);
	            }catch(SQLException e) {
	            	System.out.println("Error al conectar con la base de datos");
	            	System.exit(0);
	            }
	            System.out.println("Conectado a "+this.projeckt);


	        
		
			
			
			
		}
		public static Connection getConexion() {
	        return conexion;
	    }
		
		
		

	}

