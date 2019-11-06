package kontroladorea;

import java.util.ArrayList;

public class Departamentua {

	public Departamentua() {
		super();
	}

	private int idDepartamentua;
	private String izena;	
	private String kokapena;
	private ArrayList departamentuak;
	
	public Departamentua(int idDepartamentua, String izena, String kokapena) {
		super();
		this.idDepartamentua = idDepartamentua;
		this.izena = izena;
		this.kokapena = kokapena;
	}

	public Departamentua(ArrayList departamentuak) {
		super();
		this.departamentuak = departamentuak;
	}
	
	public int getIdDepartamentua() {
		return idDepartamentua;
	}

	public void setIdDepartamentua(int idDepartamentua) {
		this.idDepartamentua = idDepartamentua;
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public String getKokapena() {
		return kokapena;
	}

	public void setKokapena(String kokapena) {
		this.kokapena = kokapena;
	}

	@Override
	public String toString() {
		return "idDepartamentua: " + idDepartamentua + "\nizena: " + izena + "\nkokapena: " + kokapena
				+ "\n************************************\n";
	}

	public ArrayList getDepartamentuak() {
		return departamentuak;
	}

	public void setDepartamentuak(ArrayList departamentuak) {
		this.departamentuak = departamentuak;
	}
	
	
	
}
