package kontroladorea;

public class Zuzendaria {
	
	private int idEnplegatua;
	private String izena;
	private int ZuzendariMaila_idZuzendariMaila;
	
	public Zuzendaria(int idEnplegatua, String izena, int zuzendariMaila_idZuzendariMaila) {
		super();
		this.idEnplegatua = idEnplegatua;
		this.izena = izena;
		ZuzendariMaila_idZuzendariMaila = zuzendariMaila_idZuzendariMaila;
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
	public int getZuzendariMaila_idZuzendariMaila() {
		return ZuzendariMaila_idZuzendariMaila;
	}
	public void setZuzendariMaila_idZuzendariMaila(int zuzendariMaila_idZuzendariMaila) {
		ZuzendariMaila_idZuzendariMaila = zuzendariMaila_idZuzendariMaila;
	}
	
	
	
}
