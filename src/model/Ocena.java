package model;

import java.io.FileWriter;
import java.io.IOException;

public class Ocena {
	
	private String 	oceneFajl = "baza/vezaPredmetStudentOcena";
	
	private String 	indexStudenta;
	private String 	sifraPredmeta;
	private int 	ocena;
	private Datum 	datumPolaganja;
	
	
	
	
	
	public Ocena(String indexStudenta, String sifraPredmeta, int ocena, Datum datumPolaganja) {
		this.indexStudenta = indexStudenta;
		this.sifraPredmeta = sifraPredmeta;
		this.ocena = ocena;
		this.datumPolaganja = datumPolaganja;
	}
	
	public void writeOcenu() {
		 try {
		      FileWriter writer = new FileWriter(oceneFajl, true);  	      
		      writer.write(toString());
		      writer.close();
		      
		    } catch (IOException e) {
		      System.out.println("ERROR.");
		      e.printStackTrace();
		    }
	}
	
	
	public String getIndexStudenta() {
		return indexStudenta;
	}
	public void setIndexStudenta(String indexStudenta) {
		this.indexStudenta = indexStudenta;
	}
	public String getSifraPredmeta() {
		return sifraPredmeta;
	}
	public void setSifraPredmeta(String sifraPredmeta) {
		this.sifraPredmeta = sifraPredmeta;
	}
	public int getOcena() {
		return ocena;
	}
	public void setOcena(int ocena) {
		this.ocena = ocena;
	}
	public Datum getDatumPolaganja() {
		return datumPolaganja;
	}
	public void setDatumPolaganja(Datum datumPolaganja) {
		this.datumPolaganja = datumPolaganja;
	}
	
	// tostring metoda za pisanje u bazu
		@Override
		public String toString() {
			String splitter = "*\\";
			return indexStudenta + splitter + sifraPredmeta + splitter + ocena + splitter + datumPolaganja;
		}
}
