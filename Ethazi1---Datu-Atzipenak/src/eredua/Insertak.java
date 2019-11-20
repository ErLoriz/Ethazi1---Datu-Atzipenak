package eredua;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import kontroladorea.Departamentua;
import kontroladorea.Enplegatua;

public class Insertak {

	public static void sartuErabiltzailea(Enplegatua e1) {

		Connection conexion = null;
		Statement s = null;

		try {
			// Cargar el driver
			Class.forName("com.mysql.jdbc.Driver");
			conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/elorrieta", "root", "");
			s = (Statement) conexion.createStatement();

			// Se realiza la consulta. Los resultados se guardan en el ResultSet rs

			String query = "INSERT INTO enplegatua(idEnplegatua,izena,soldata,altaData,altaOrdua,zuzendari,Departamentua_idDepartamentua,Ardura_idArdura)"
					+ " VALUES(?,?,?,?,?,?,?,?)";

			PreparedStatement preparedStmt = (PreparedStatement) conexion.prepareStatement(query);
			preparedStmt.setInt(1, e1.getIdEnplegatua());
			preparedStmt.setString(2, e1.getIzena());
			preparedStmt.setDouble(3, e1.getSoldata());
			preparedStmt.setString(4, e1.getAltaData());
			preparedStmt.setString(5, e1.getAltaOrdua());
			preparedStmt.setString(6, e1.getZuzendari());
			preparedStmt.setInt(7, e1.getDepartamentua_idDepartamentua());
			preparedStmt.setInt(8, e1.getArdura_idArdura());

			preparedStmt.execute();

			System.out.println("Sartuta");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	public static void SartuDepartamentua(Departamentua d1) {

		Connection conexion = null;
		Statement s = null;
		String zuzendari = null;

		try {
			// Cargar el driver
			Class.forName("com.mysql.jdbc.Driver");
			conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/elorrieta", "root", "");
			s = (Statement) conexion.createStatement();

			// Se realiza la consulta. Los resultados se guardan en el ResultSet rs

			String query = "INSERT INTO departamentua(idDepartamentua,izena,kokapena)"
					+ " VALUES(?,?,?)";

			PreparedStatement preparedStmt = (PreparedStatement) conexion.prepareStatement(query);
			preparedStmt.setInt(1, d1.getIdDepartamentua());
			preparedStmt.setString(2, d1.getIzena());
			preparedStmt.setString(3, d1.getKokapena());

			preparedStmt.execute();

			System.out.println("Sartuta");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	
}
