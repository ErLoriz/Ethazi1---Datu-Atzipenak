package kontroladorea;

import eredua.Konexioa;
import eredua.Kontsultak;

public class Nagusia {

	public static void main(String[] args) {

		
		Konexioa konexioa = new Konexioa("elorrieta");
		//System.out.println(eredua.Kontsultak.DepartamentuakIkusi().toString().replaceAll("[\\[\\]]", "").replaceAll(",", " "));
		System.out.println(eredua.Kontsultak.EnplegatuakIkusi().toString());

	
		System.out.println(eredua.Kontsultak.CSVOharraIrakurri());
		
		
	}

}
