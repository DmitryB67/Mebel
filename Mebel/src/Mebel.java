import java.math.BigDecimal;

public class Mebel {
	private int idMebel;
	private String naimenovanie;
	private String opisanie;
	private String material;
	private int kolvoSklad;
	private BigDecimal cena;

	public Mebel(int idMebel, String naimenovanie, String opisanie, String material, int kolvoSklad, BigDecimal cena) {
		this.idMebel = idMebel;
		this.naimenovanie = naimenovanie;
		this.opisanie = opisanie;
		this.material = material;
		this.kolvoSklad = kolvoSklad;
		this.cena = cena;
	}

	public int getIdMebel() { return idMebel; }
	public String getNaimenovanie() { return naimenovanie; }
	public String getOpisanie() { return opisanie; }
	public String getMaterial() { return material; }
	public int getKolvoSklad() { return kolvoSklad; }
	public BigDecimal getCena() { return cena; }
}
