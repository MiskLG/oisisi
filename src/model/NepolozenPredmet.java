package model;

// klasa koja sadrzi samo sifru predmeta i index studenta koji nije polozio taj predmet
public class NepolozenPredmet {
	private String index;
	private String sifra;
	
	public NepolozenPredmet(String index, String sifra) {
		this.index = index;
		this.sifra = sifra;
	}
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getSifra() {
		return sifra;
	}
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	
	
	@Override
	public String toString() {
		String splitter = "*\\";
		return sifra + splitter + index;
	}
	
	
}
