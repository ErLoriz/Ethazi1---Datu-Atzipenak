package eredua;

import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Delete {
	
	public static void DepartamentuaEzabatu(int idDept) {

		Connection conexion = null;
		Statement s = null;
		
		try {
			// Cargar el driver
			Class.forName("com.mysql.jdbc.Driver");
			conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/elorrieta", "root", "");
			s = (Statement) conexion.createStatement();
			
			PreparedStatement st = conexion.prepareStatement("DELETE FROM departamentua WHERE idDepartamentua = '"+idDept);
			st.setInt(1,idDept);
			st.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
