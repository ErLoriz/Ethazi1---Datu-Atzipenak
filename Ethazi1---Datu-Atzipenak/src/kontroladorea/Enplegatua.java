package kontroladorea;

public class Enplegatua {

	public Enplegatua() {
		
	}

	private int idEnplegatua;
	private String izena;
	private double soldata;
	private String altaData;
	private String altaOrdua;
	private int Departamentua_idDepartamentua;
	private int Ardura_idArdura;
	private boolean zuzendari;
	
		public Enplegatua(int idEnplegatua, String izena, double soldata, String altaData, String altaOrdua,
			int departamentua_idDepartamentua, int ardura_idArdura, boolean zuzendari) {
		super();
		this.idEnplegatua = idEnplegatua;
		this.izena = izena;
		this.soldata = soldata;
		this.altaData = altaData;
		this.altaOrdua = altaOrdua;
		Departamentua_idDepartamentua = departamentua_idDepartamentua;
		Ardura_idArdura = ardura_idArdura;
		this.zuzendari = zuzendari;
	}
	
	public int getIdEnplegatua() {
		return idEnplegatua;
	}
	public void setIdEnplegatua(int idEnplegatua) {
		this.idEnplegatua = idEnplegatua;
	}
	public String getIzena() {
		return izena;
	}
	public void setIzena(String izena) {
		this.izena = izena;
	}
	public double getSoldata() {
		return soldata;
	}
	public void setSoldata(double soldata) {
		this.soldata = soldata;
	}
	public String getAltaData() {
		return altaData;
	}
	public void setAltaData(String altaData) {
		this.altaData = altaData;
	}
	public String getAltaOrdua() {
		return altaOrdua;
	}
	public void setAltaOrdua(String altaOrdua) {
		this.altaOrdua = altaOrdua;
	}
	public int getDepartamentua_idDepartamentua() {
		return Departamentua_idDepartamentua;
	}
	public void setDepartamentua_idDepartamentua(int departamentua_idDepartamentua) {
		Departamentua_idDepartamentua = departamentua_idDepartamentua;
	}
	
	public int getArdura_idArdura() {
		return Ardura_idArdura;
	}
	public void setArdura_idArdura(int ardura_idArdura) {
		Ardura_idArdura = ardura_idArdura;
	}
	public boolean zuzendariaStringToBoolean(String zuzendari) {
		boolean balidatu = false;
		if(zuzendari.equalsIgnoreCase("true"))
			balidatu = true;
		else if(zuzendari.equalsIgnoreCase("false"))
			balidatu = false;
		else
			System.out.println("Zuzendaria txarto dago");
		
		return balidatu;
	}
	
	public String zuzendariaBooleanToString(Boolean zuzendari) {
		String balidatu = null;
		if(zuzendari == true)
			balidatu = "true";
		else if(zuzendari == false)
			balidatu = "false";
		
		return balidatu;
	}
	
	@Override
	public String toString() {
		
		return "idEnplegatua: " + idEnplegatua + "\nizena: " + izena + "\nsoldata: " + soldata + "\naltaData: "
				+ altaData + "\naltaOrdua: " + altaOrdua + "\nZuzendaria: " + zuzendari + "\nDepartamentua_idDepartamentua: "
				+ Departamentua_idDepartamentua + "\nArdura_idArdura: " + Ardura_idArdura;
	}

	public boolean getZuzendari() {
		return zuzendari;
	}

	public void setZuzendari(boolean zuzendari) {
		this.zuzendari = zuzendari;
	}
	
	
	
}
