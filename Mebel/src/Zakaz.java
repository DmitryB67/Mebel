import java.math.BigDecimal;
import java.sql.Date;

public class Zakaz {
	private int idZakaza;
	private String sotr;
	private String client;
	private String mebel;
	private int kolvo;
	private Date dataZakaza;
	private BigDecimal summa;

	public Zakaz(int idZakaza, String sotr, String client, String mebel, int kolvo, Date dataZakaza, BigDecimal summa) {
		this.idZakaza = idZakaza;
		this.sotr = sotr;
		this.client = client;
		this.mebel = mebel;
		this.kolvo = kolvo;
		this.dataZakaza = dataZakaza;
		this.summa = summa;
	}

	public int getIdZakaza() { return idZakaza; }
	public String getSotr() { return sotr; }
	public String getClient() { return client; }
	public String getMebel() { return mebel; }
	public int getKolvo() { return kolvo; }
	public Date getDataZakaza() { return dataZakaza; }
	public BigDecimal getSumma() { return summa; }
}
