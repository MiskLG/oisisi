package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Profesor {
	
	private String listaPredmetaFajl = "vezaProfesorPredmet";
	
	private String 	prezime;
	private String 	ime;
	private Datum	datumRodjenja;
	private Adresa 	adresaStanovanja;
	private String 	telefon;
	private String 	email;
	private Adresa 	adresaKancelarije;
	private String 	brojLicne;
	private String 	zvanje;
	private int 	godinaStaza;
	
	private List<String> listaPredmeta;



	public Profesor(String prezime, String ime, Datum datumRodjenja, Adresa adresaStanovanja, String telefon,
			String email, Adresa adresaKancelarije, String brojLicne, String zvanje, int godinaStaza) {
		this.prezime = prezime;
		this.ime = ime;
		this.datumRodjenja = datumRodjenja;
		this.adresaStanovanja = adresaStanovanja;
		this.telefon = telefon;
		this.email = email;
		this.adresaKancelarije = adresaKancelarije;
		this.brojLicne = brojLicne;
		this.zvanje = zvanje;
		this.godinaStaza = godinaStaza;
		
		loadListaPredmeta();
	}

	private void loadListaPredmeta() {
		try {				
			File file = new File(listaPredmetaFajl);
			Scanner reader = new Scanner(file);
			
			while(reader.hasNextLine()) {
				String dataLine = reader.nextLine();
				String[] data = dataLine.split("*\\");
				
				if(data[0].equals(getBrojLicne())) {
					listaPredmeta.add(data[1]);
				}
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("ERROR kod citanja iz fajla");
			e.printStackTrace();
		}
	}

	public void writeListaPredmeta() {
		 try {
		      FileWriter writer = new FileWriter(listaPredmetaFajl, true);        
		      String splitter = "*\\";
		      for(String p : listaPredmeta) {
		    	  String toWrite = getBrojLicne() + splitter + p;
		    	  writer.write(toWrite);
		      }
		      writer.close();
		      
		    } catch (IOException e) {
		      System.out.println("ERROR.");
		      e.printStackTrace();
		    }
	}
	
	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public Datum getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Datum datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public Adresa getAdresaStanovanja() {
		return adresaStanovanja;
	}

	public void setAdresaStanovanja(Adresa adresaStanovanja) {
		this.adresaStanovanja = adresaStanovanja;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Adresa getAdresaKancelarije() {
		return adresaKancelarije;
	}

	public void setAdresaKancelarije(Adresa adresaKancelarije) {
		this.adresaKancelarije = adresaKancelarije;
	}

	public String getBrojLicne() {
		return brojLicne;
	}

	public void setBrojLicne(String brojLicne) {
		this.brojLicne = brojLicne;
	}

	public String getZvanje() {
		return zvanje;
	}

	public void setZvanje(String zvanje) {
		this.zvanje = zvanje;
	}

	public int getGodinaStaza() {
		return godinaStaza;
	}

	public void setGodinaStaza(int godinaStaza) {
		this.godinaStaza = godinaStaza;
	}
	
	

	public List<String> getListaPredmeta() {
		return listaPredmeta;
	}

	public void setListaPredmeta(List<String> listaPredmeta) {
		this.listaPredmeta = listaPredmeta;
	}

	@Override
	public String toString() {
		String splitter = "*\\";
		return prezime + splitter +  ime + splitter + datumRodjenja.toString() + splitter +  adresaStanovanja.toString() + 
				splitter + telefon+ splitter + email + splitter + adresaKancelarije.toString() + splitter + brojLicne + splitter + zvanje + splitter +
				godinaStaza;
		
	}
	
}
