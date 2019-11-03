package eredua;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Update {
	
	public static void DepartamentuaAldatu(int idDepartamentua, String izena, String kokapena) {

		Connection conexion = null;
		Statement s = null;
		
		try {
			// Cargar el driver
			Class.forName("com.mysql.jdbc.Driver");
			conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/elorrieta", "root", "");
			s = (Statement) conexion.createStatement();

			// Preparamos la consulta y la ejecutamos
			// NumReg-> Para saber cuantos registros se han modificado
			 int numReg = s.executeUpdate( "UPDATE departamentua SET izena = '"+izena+"', kokapena = '"+kokapena+"' WHERE idDepartamentua= "+idDepartamentua);
			 // Informamos del número de registros borrados
			 System.out.println ("\nAldatuta\n");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void EnplegatuaAldatu(int idEnplegatua, String izena, String soldata, String zuzendari, int idDept, int idArdura) {

		Connection conexion = null;
		Statement s = null;
		
		try {
			// Cargar el driver
			Class.forName("com.mysql.jdbc.Driver");
			conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/elorrieta", "root", "");
			s = (Statement) conexion.createStatement();

			// Preparamos la consulta y la ejecutamos
			// NumReg-> Para saber cuantos registros se han modificado
			 int numReg = s.executeUpdate( "UPDATE enplegatua SET izena = '"+izena+"', soldata = '"+soldata+"', zuzendari = '"+zuzendari+"', Departamentua_idDepartamentua = '"+idDept+"', Ardura_idArdura = '"+idArdura+"' WHERE idEnplegatua= "+idEnplegatua);
			 // Informamos del número de registros borrados
			 System.out.println ("\nAldatuta\n");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
