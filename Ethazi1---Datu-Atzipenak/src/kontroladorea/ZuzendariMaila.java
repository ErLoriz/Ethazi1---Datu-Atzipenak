package kontroladorea;

public class ZuzendariMaila {

	private int idZuzendariMaila;
	private String mailaIzena;
	
	public ZuzendariMaila(int idZuzendariMaila, String mailaIzena) {
		super();
		this.idZuzendariMaila = idZuzendariMaila;
		this.mailaIzena = mailaIzena;
	}
	
	public int getIdZuzendariMaila() {
		return idZuzendariMaila;
	}
	public void setIdZuzendariMaila(int idZuzendariMaila) {
		this.idZuzendariMaila = idZuzendariMaila;
	}
	public String getMailaIzena() {
		return mailaIzena;
	}
	public void setMailaIzena(String mailaIzena) {
		this.mailaIzena = mailaIzena;
	}

	
	
}
