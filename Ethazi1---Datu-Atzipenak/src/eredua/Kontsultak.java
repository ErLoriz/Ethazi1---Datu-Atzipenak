package eredua;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import kontroladorea.*;

public class Kontsultak {

	public static ArrayList <Enplegatua> ApartamentuakIkusi(){
		ArrayList <Enplegatua> Apartamentuak = new ArrayList<Enplegatua>();
		int idEnplegatua=0;
		String izena=null;
		double soldata;
		String altaData=null;
		String altaOrdua=null;
		int Departamentua_idDepartamentua=0;
		int Ardura_idArdura=0;
		String zuzendari=null;
		boolean zuzendariBoolean=false;
		Enplegatua e = new Enplegatua();
		
		
		Connection Conexion = (Connection) Konexioa.getConnection();
		Statement s =null;

		try {
			
			s =(Statement) Conexion.createStatement();

			ResultSet rs = ((java.sql.Statement) s).executeQuery("SELECT idEnplegatua, izena, soldata, altaData, altaOrdua,	Departamentua_idDepartamentua, Ardura_idArdura, zuzendari FROM `enplegatua`");
			while (rs.next()) {
				idEnplegatua = rs.getInt("idEnplegatua");
				izena = rs.getString("izena");
				soldata = rs.getDouble("soldata");
				altaData = rs.getString("altaData");
				altaOrdua = rs.getString("altaOrdua");
				Departamentua_idDepartamentua = rs.getInt("Departamentua_idDepartamentua");
				Ardura_idArdura = rs.getInt("Ardura_idArdura");
				zuzendari = rs.getString("zuzendari");
				zuzendariBoolean = e.zuzendariaStringToBoolean(zuzendari);
				Enplegatua e1 = new Enplegatua (idEnplegatua, izena, soldata, altaData,	altaOrdua,	Departamentua_idDepartamentua, Ardura_idArdura, zuzendariBoolean);
				Apartamentuak.add(e1);

			}
			
			System.out.println();
			System.out.println("Conexioa eginda");
		}catch(Exception y) {
			System.out.println(y.getMessage());
			
		}
		
		
		return Apartamentuak;
	}
	
	public static ArrayList <Departamentua> DepartamentuakIkusi(){
		ArrayList <Departamentua> Departamentuak = new ArrayList<Departamentua>();
		int idDepartamentua=0;
		String izena=null;
		String kokapena=null;
	
		
		Connection Conexion = (Connection) Konexioa.getConnection();
		Statement s =null;

		try {
			
			s =(Statement) Conexion.createStatement();

			ResultSet rs = ((java.sql.Statement) s).executeQuery("SELECT idDepartamentua, izena, kokapena FROM `departamentua`");
			while (rs.next()) {
				idDepartamentua = rs.getInt(1);
				izena = rs.getString(2);
				kokapena = rs.getString(3);
				Departamentua d1 = new Departamentua(idDepartamentua, izena, kokapena);
				Departamentuak.add(d1);

			}
			
			System.out.println();
			System.out.println("Conexioa eginda");
		}catch(Exception y) {
			System.out.println(y.getMessage());
			
		}
		
		
		return Departamentuak;
	}
	
	public static ArrayList<Enplegatua> CSVOharraIrakurri (){

	//Bariableak
	Enplegatua e = new Enplegatua();
	ArrayList<Enplegatua> enplegatuak = new ArrayList<Enplegatua>();
	String idEnplegatua=null;
	int idEnplegatuaInt=0;
	String izena=null;
	String soldata=null;
	double soldataDouble=0;
	String altaData=null;
	String altaOrdua=null;
	String Departamentua_idDepartamentua=null;
	int Dept_Int=0;
	String Ardura_idArdura=null;
	int Ard_Int=0;
	String zuzendari=null;
	boolean zuzendariBoolean=false;

	String csvFile = ".\\Ethazi1---Datu-Atzipenak\\src\\Oharra.csv";
	BufferedReader br = null;
	String line = "";
	//Se define separador ","
	String cvsSplitBy = ",";
	try {
	   br = new BufferedReader(new FileReader(csvFile));
	   //Lehengo linea ez irakurtzeko
	   br.readLine();
	   while ((line = br.readLine()) != null) {                
	       String[] datos = line.split(cvsSplitBy);
	      idEnplegatua = datos[0];
	      izena = datos[1];
	      soldata = datos[2];
	      altaData = datos[3];
	      altaOrdua = datos[4];
	      zuzendari = datos[5];
	      Departamentua_idDepartamentua = datos[6];
	      Ardura_idArdura = datos[7];
	      
	      
	      
	     
	      //Imprime datos.
	      System.out.println(datos[0] + " " + datos[1] + " " + datos[2] + " " + datos[3] + " " + datos[4] + " " + datos[5] + " " + datos[6] + " " + datos[7]);
	      
	      idEnplegatuaInt = Integer.parseInt(idEnplegatua);
	      soldataDouble = Double.parseDouble(soldata);
	      Dept_Int = Integer.parseInt(Departamentua_idDepartamentua);
	      Ard_Int = Integer.parseInt(Ardura_idArdura);
	      zuzendariBoolean = e.zuzendariaStringToBoolean(zuzendari);
	     
	Enplegatua e1 = new Enplegatua(idEnplegatuaInt, izena, soldataDouble, altaData, altaOrdua, Dept_Int, Ard_Int, zuzendariBoolean);
	enplegatuak.add(e1);

	 eredua.Insertak.sartuErabiltzailea(e1);
	//reiniciarArray(datos);

	   }
	   
	  
	   
	} catch (FileNotFoundException y) {
	   y.printStackTrace();
	} catch (IOException y) {
	   y.printStackTrace();
	} finally {
	   if (br != null) {
	       try {
	           br.close();
	       } catch (IOException y) {
	           y.printStackTrace();
	       }
	   }
	}


	           
	return enplegatuak;

	}
	
}
