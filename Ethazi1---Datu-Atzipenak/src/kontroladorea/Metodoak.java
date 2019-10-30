package kontroladorea;

import java.util.ArrayList;

import javax.swing.JScrollPane;

import eredua.*;
import ikuspegia.*;

public class Metodoak {

	public static void departamentuakIkusi(JScrollPane scroll) {
		ArrayList<Departamentua> array = new ArrayList();
		array = Kontsultak.DepartamentuakIkusi();
		
		
	}
	
    public static String[][] enplegatuakIkusi(ArrayList <Enplegatua> e1){
    	String[][] data = new String[e1.size()][8];
    	
    	for(int i = 0; i <= e1.size() - 1; i++) {
	    		
	    	data[i][0] = Integer.toString(e1.get(i).getIdEnplegatua());
	    	data[i][1] = e1.get(i).getIzena();
	    	data[i][2] = Double.toString(e1.get(i).getSoldata());
	    	data[i][3] = e1.get(i).getAltaData();
	    	data[i][4] = e1.get(i).getAltaOrdua();
	    	data[i][5] = Boolean.toString(e1.get(i).getZuzendari());
	    	data[i][6] = Integer.toString(e1.get(i).getDepartamentua_idDepartamentua());
	    	data[i][7] = Integer.toString(e1.get(i).getArdura_idArdura());
    	}
    
    	return data;
    }
    
	public static String[][] departamentuaIkusi(ArrayList<Departamentua> d1) {
		String[][] data = new String[d1.size()][3];

		for (int i = 0; i <= d1.size() - 1; i++) {

			data[i][0] = Integer.toString(d1.get(i).getIdDepartamentua());
			data[i][1] = d1.get(i).getIzena();
			data[i][2] = d1.get(i).getKokapena();

		}

		return data;
	}
	
	public static String zuzendariaBalidatu(boolean z) {
		String zuzendari = "false";
		
		if (z == true) {
			zuzendari = "true";
		}
		
		return zuzendari;
	}
	
}
