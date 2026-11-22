public class Sotr {
	private int idSotr;
	private String fam;
	private String name;
	private String otchestvo;
	private String doljnost;
	private String telefon;

	public Sotr(int idSotr, String fam, String name, String otchestvo, String doljnost, String telefon) {
		this.idSotr = idSotr;
		this.fam = fam;
		this.name = name;
		this.otchestvo = otchestvo;
		this.doljnost = doljnost;
		this.telefon = telefon;
	}

	public int getIdSotr() { return idSotr; }
	public String getFam() { return fam; }
	public String getName() { return name; }
	public String getOtchestvo() { return otchestvo; }
	public String getDoljnost() { return doljnost; }
	public String getTelefon() { return telefon; }
}
