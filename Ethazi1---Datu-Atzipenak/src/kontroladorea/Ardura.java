package kontroladorea;

import java.util.ArrayList;

public class Ardura {

	private int idArdura;
	private String izenArdura;
	private ArrayList ardurak;
	
	
	public Ardura(int idArdura, String izenArdura) {
		super();
		this.idArdura = idArdura;
		this.izenArdura = izenArdura;
	}
	
	public Ardura(ArrayList ardurak) {
		super();
		this.ardurak = ardurak;
	}

	public int getIdArdura() {
		return idArdura;
	}

	public void setIdArdura(int idArdura) {
		this.idArdura = idArdura;
	}

	public String getIzenArdura() {
		return izenArdura;
	}

	public void setIzenArdura(String izenArdura) {
		this.izenArdura = izenArdura;
	}
	
		
	
}
