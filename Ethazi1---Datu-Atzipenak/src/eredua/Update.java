package eredua;

import java.sql.DriverManager;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import kontroladorea.Ardura;
import kontroladorea.Departamentua;
import kontroladorea.Enplegatua;

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
			 conexion.close();	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void EnplegatuaAldatu(int idEnplegatua, String izena, String soldata, String zuzendari, String idDept, String idArdura) {
		ArrayList <Enplegatua> e1 = new ArrayList();
    	ArrayList <Departamentua> d1 = new ArrayList();
    	ArrayList <Ardura> a1 = new ArrayList();
    	
    	d1 = Kontsultak.DepartamentuakIkusi();
    	a1 = Kontsultak.ArdurakIkusi();
    	
    	int idDeptINT = 0;
    	int idArduraINT = 0;
    	
    	for(int x = 0; x <= d1.size() - 1; x++) {
    		System.out.println(d1.get(x).getIzena() + " " + idDept);
    		if(d1.get(x).getIzena().matches(idDept)) {
    			idDeptINT = d1.get(x).getIdDepartamentua();
    			System.out.println(idDeptINT + " dept");
    		}
    	}
    	
    	for(int x = 0; x <= a1.size() - 1; x++) {
    		if(a1.get(x).getIzenArdura().matches(idArdura)) {
    			idArduraINT = a1.get(x).getIdArdura();
    			System.out.println(idArduraINT + " ardura");
    		}
    	}
		
		
		Connection conexion = null;
		Statement s = null;
		
		try {
			// Cargar el driver
			Class.forName("com.mysql.jdbc.Driver");
			conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/elorrieta", "root", "");
			s = (Statement) conexion.createStatement();

			// Preparamos la consulta y la ejecutamos
			// NumReg-> Para saber cuantos registros se han modificado
			 int numReg = s.executeUpdate( "UPDATE enplegatua SET izena = '"+izena+"', soldata = '"+soldata+"', zuzendari = '"+zuzendari+"', Departamentua_idDepartamentua = '"+idDeptINT+"', Ardura_idArdura = '"+idArduraINT+"' WHERE idEnplegatua= "+idEnplegatua);
			 // Informamos del número de registros borrados
			 System.out.println ("\nAldatuta\n");
			 conexion.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
