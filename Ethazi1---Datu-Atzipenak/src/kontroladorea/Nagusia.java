package kontroladorea;

import eredua.Konexioa;

public class Nagusia {

	public static void main(String[] args) {

		
		Konexioa konexioa = new Konexioa("elorrieta");
		//System.out.println(eredua.Kontsultak.DepartamentuakIkusi().toString().replaceAll("[\\[\\]]", "").replaceAll(",", " "));
		
		Enplegatua e1 = new Enplegatua();
		
		System.out.println(eredua.Kontsultak.CSVOharraIrakurri());
		
		
	}

}
