package eredua;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import kontroladorea.Enplegatua;

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
		int zuzendari=0;
		
	
		
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
				zuzendari = rs.getInt("zuzendari");
				Enplegatua e1 = new Enplegatua (idEnplegatua, izena, soldata, altaData,	altaOrdua,	Departamentua_idDepartamentua, Ardura_idArdura, zuzendari);
				Apartamentuak.add(e1);

			}
			
			System.out.println();
			System.out.println("Conexioa eginda");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			
		}
		
		
		return Apartamentuak;
	}
	
}
