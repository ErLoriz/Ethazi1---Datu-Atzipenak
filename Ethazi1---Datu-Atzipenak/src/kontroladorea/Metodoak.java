package kontroladorea;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JScrollPane;

import eredua.*;
import ikuspegia.*;

public class Metodoak {

	public static void departamentuakIkusi(JScrollPane scroll) {
		ArrayList<Departamentua> array = new ArrayList();
		array = Kontsultak.DepartamentuakIkusi();
		
		
	}
	
    public static String[][] enplegatuakIkusi(){
    	ArrayList <Enplegatua> e1 = new ArrayList();
    	ArrayList <Departamentua> d1 = new ArrayList();
    	ArrayList <Ardura> a1 = new ArrayList();
    	e1 = Kontsultak.EnplegatuakIkusi();
    	String[][] data = new String[e1.size()][8];
    	
    	for(int i = 0; i <= e1.size() - 1; i++) {
	    		
	    	data[i][0] = Integer.toString(e1.get(i).getIdEnplegatua());
	    	data[i][1] = e1.get(i).getIzena();
	    	data[i][2] = Double.toString(e1.get(i).getSoldata());
	    	data[i][3] = e1.get(i).getAltaData();
	    	data[i][4] = e1.get(i).getAltaOrdua();
	    	
	    	if(e1.get(i).getZuzendari() == true) {
	    		data[i][5] = "Bai";
	    	} else {
	    		data[i][5] = "Ez";
	    	}
	    	
	    	/*for(int x = 0; i <= d1.size() - 1; i++) {
	    		if(d1.get(x).getIdDepartamentua() == e1.get(i).getDepartamentua_idDepartamentua()) {
	    			System.out.println(d1.get(x).getIzena());
	    			data[i][6] = d1.get(x).getIzena();
	    		}
	    	}
	    	
	    	for(int x = 0; i <= a1.size() - 1; i++) {
	    		if(a1.get(x).getIdArdura() == e1.get(i).getArdura_idArdura()) {
	    			data[i][7] = a1.get(x).getIzenArdura();
	    		}
	    	}
	    	
	    	data[i][5] = Boolean.toString(e1.get(i).getZuzendari());*/
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
	
	public static String[] arduraIzenakIkusi() {
		ArrayList <Ardura> a1 = new ArrayList();
    	a1 = Kontsultak.ArdurakIkusi();
    	String[] data = new String[a1.size()];
    	
    	for(int i = 0; i <= a1.size() - 1; i++) {
    		
	    	data[i] = a1.get(i).getIzenArdura();
    	}
    	
		return data;
	}
	
	public static String[] departamentuIzenakIkusi() {
		ArrayList <Departamentua> d1 = new ArrayList();
    	d1 = Kontsultak.DepartamentuakIkusi();
		String[] data = new String[d1.size()];

		for (int i = 0; i <= d1.size() - 1; i++) {

			data[i] = d1.get(i).getIzena();

		}

		return data;
	}
	
	public static int departamentuIdLortu(String izena) {
		return Kontsultak.DepartamentuIdLortu(izena);
	}
	
	public static int arduraIdLortu(String izena) {
		return Kontsultak.ArduraIdLortu(izena);
	}
	
	public static void sartuEnplegatua(int id, String izena, Double soldata, String altaData, String altaOrdua, String zuzendaria, int idDept, int idArdura) {
		boolean zuzendariBoolean = false;
		
		if(zuzendaria.equalsIgnoreCase("false"))
			zuzendariBoolean = false;
		else if(zuzendaria.equalsIgnoreCase("true"))
			zuzendariBoolean = true;
		
		Enplegatua e1 = new Enplegatua();
		
		e1.setIdEnplegatua(id);
		e1.setIzena(izena);
		e1.setSoldata(soldata);
		e1.setAltaData(altaData);
		e1.setAltaOrdua(altaOrdua);
		e1.setZuzendari(zuzendariBoolean);
		e1.setDepartamentua_idDepartamentua(idDept);
		e1.setArdura_idArdura(idArdura);
		
		Insertak.sartuErabiltzailea(e1);
	}
	
}
