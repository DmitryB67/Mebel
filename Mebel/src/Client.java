public class Client {
	private int idClient;
	private String fam;
	private String name;
	private String otchestvo;
	private String telefon;
	private String adres;

	public Client(int idClient, String fam, String name, String otchestvo, String telefon, String adres) {
		this.idClient = idClient;
		this.fam = fam;
		this.name = name;
		this.otchestvo = otchestvo;
		this.telefon = telefon;
		this.adres = adres;
	}

	public int getIdClient() { return idClient; }
	public String getFam() { return fam; }
	public String getName() { return name; }
	public String getOtchestvo() { return otchestvo; }
	public String getTelefon() { return telefon; }
	public String getAdres() { return adres; }
}
