package kontroladorea;

public class Enplegatua {

	private int idEnplegatua;
	private String izena;
	private double soldata;
	private String altaData;
	private String altaOrdua;
	private int Departamentua_idDepartamentua;
	private int Zuzendaria_idZuzendaria;
	private int Ardura_idArdura;
	
		public Enplegatua(int idEnplegatua, String izena, double soldata, String altaData, String altaOrdua,
			int departamentua_idDepartamentua, int zuzendaria_idZuzendaria, int ardura_idArdura) {
		super();
		this.idEnplegatua = idEnplegatua;
		this.izena = izena;
		this.soldata = soldata;
		this.altaData = altaData;
		this.altaOrdua = altaOrdua;
		Departamentua_idDepartamentua = departamentua_idDepartamentua;
		Zuzendaria_idZuzendaria = zuzendaria_idZuzendaria;
		Ardura_idArdura = ardura_idArdura;
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
	public int getZuzendaria_idZuzendaria() {
		return Zuzendaria_idZuzendaria;
	}
	public void setZuzendaria_idZuzendaria(int zuzendaria_idZuzendaria) {
		Zuzendaria_idZuzendaria = zuzendaria_idZuzendaria;
	}
	public int getArdura_idArdura() {
		return Ardura_idArdura;
	}
	public void setArdura_idArdura(int ardura_idArdura) {
		Ardura_idArdura = ardura_idArdura;
	}

	@Override
	public String toString() {
		
		return "idEnplegatua: " + idEnplegatua + "\nizena: " + izena + "\nsoldata: " + soldata + "\naltaData: "
				+ altaData + "\naltaOrdua: " + altaOrdua + "\nDepartamentua_idDepartamentua: "
				+ Departamentua_idDepartamentua + "\nZuzendaria_idZuzendaria: " + Zuzendaria_idZuzendaria
				+ "\nArdura_idArdura: " + Ardura_idArdura
				+ "\n************************************\n";
	}
	
	
	
}
