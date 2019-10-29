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
}
