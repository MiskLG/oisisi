package model;

public class Adresa {
	private String 		ulica;
	private int			broj;
	private String 		grad;
	private String		drzava;
	
	
	// konstruktor sa parametrima
	public Adresa(String ulica, int broj, String grad, String drzava) {
		this.ulica = ulica;
		this.broj = broj;
		this.grad = grad;
		this.drzava = drzava;
	}
	
	// Seteri i geteri
	public String getUlica() {
		return ulica;
	}
	public void setUlica(String ulica) {
		this.ulica = ulica;
	}
	public int getBroj() {
		return broj;
	}
	public void setBroj(int broj) {
		this.broj = broj;
	}
	public String getGrad() {
		return grad;
	}
	public void setGrad(String grad) {
		this.grad = grad;
	}
	public String getDrzava() {
		return drzava;
	}
	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	// toString metoda za pisanje u bazu/txt fajl
	// spliter je *\ za svaki slucaj da ne dodje do komplikacija
	@Override
	public String toString() {
		String splitter = "*\\";
		return ulica + splitter + broj + splitter + grad + splitter + drzava;
	}
	
	
	
}
