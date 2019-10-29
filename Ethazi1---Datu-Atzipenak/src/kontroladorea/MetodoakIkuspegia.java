package kontroladorea;

import ikuspegia.*;
import eredua.*;

public class MetodoakIkuspegia {

	public static void pasatuDepartamentura() {
		DepartamentuaKudeatu dk = new DepartamentuaKudeatu();
		dk.setVisible(true);
		dk.setBounds(100, 100, 630, 450);
		
	}
	
	public static void pasatuMenura() {
		Menua menua = new Menua();
		menua.setVisible(true);
		menua.setBounds(100, 100, 630, 450);
	}
	
	
	public static void pasatuEnplegatuetara() {
		EnplegatuaKudeatu ek = new EnplegatuaKudeatu();
		ek.setVisible(true);
		ek.setBounds(100, 100, 630, 450);
	}
	
}
