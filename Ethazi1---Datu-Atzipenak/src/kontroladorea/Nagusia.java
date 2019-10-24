package kontroladorea;

import eredua.Konexioa;

public class Nagusia {

	public static void main(String[] args) {

		
		Konexioa konexioa = new Konexioa("elorrieta");
		System.out.println(eredua.Kontsultak.ApartamentuakIkusi().toString().replaceAll("[\\[\\]]", "").replaceAll(",", " "));
		
	}

}
