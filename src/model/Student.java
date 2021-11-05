package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

enum Status{B,S};

public class Student {
	
	private String 	polozeniFajl = "baza/vezaPredmetStudentOcena.txt";
	private String 	nepolozeniFajl = "baza/vezaPredmetStudentNepolozeni.txt";
	
	private String 	prezime;
	private String 	ime;
	private Datum 	datumRodjenja;
	private String 	adresaStanovanja;
	private String 	kontaktTelefon;
	private String 	email;
	private String 	brojIndeksa;
	private int 	godinaUpisa;
	private int 	trenutnaGodinaStudija;
	private Status 	status;
	private double 	prosecnaOcena;
	
	private List<NepolozenPredmet> 	spisakNepolozeni;
	private List<Ocena> 			spisakPolozeni;
	
	
	
	
	public Student(String prezime, String ime, Datum datumRodjenja, String adresaStanovanja, String kontaktTelefon,
			String email, String brojIndeksa, int godinaUpisa, int trenutnaGodinaStudija, Status status,
			double prosecnaOcena) {
		
		this.prezime = prezime;
		this.ime = ime;
		this.datumRodjenja = datumRodjenja;
		this.adresaStanovanja = adresaStanovanja;
		this.kontaktTelefon = kontaktTelefon;
		this.email = email;
		this.brojIndeksa = brojIndeksa;
		this.godinaUpisa = godinaUpisa;
		this.trenutnaGodinaStudija = trenutnaGodinaStudija;
		this.status = status;
		this.prosecnaOcena = prosecnaOcena;
		
		loadNepolozeni();
		loadPolozeni();
	}
	
	private void loadNepolozeni() {	
		try {
			
			File file = new File(nepolozeniFajl);
			Scanner reader = new Scanner(file);
			
			while(reader.hasNextLine()) {
				String dataLine = reader.nextLine();
				String[] data = dataLine.split("*\\");
				
				if(data[1].equals(getBrojIndeksa())) {
					NepolozenPredmet np = new NepolozenPredmet(data[0],data[1]);
					spisakNepolozeni.add(np);
				}
				
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("ERROR kod citanja iz fajla");
			e.printStackTrace();
		}
		
		return;
	}
	
	private void loadPolozeni() {
		try {
			
			File file = new File(polozeniFajl);
			Scanner reader = new Scanner(file);
			
			while(reader.hasNextLine()) {
				String dataLine = reader.nextLine();
				String[] data = dataLine.split("*\\");
				
				if(data[1].equals(getBrojIndeksa())) {
					Ocena o = new Ocena(data[0],data[1],Integer.parseInt(data[2]),new Datum(data[3]));
					spisakPolozeni.add(o);
				}
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("ERROR kod citanja iz fajla");
			e.printStackTrace();
		}
		
	}
	
	public void wirteNepolozeni() {
		 try {
		      FileWriter writer = new FileWriter(nepolozeniFajl, true);        
		      
		      for(NepolozenPredmet np : spisakNepolozeni) {
		    	  writer.write(np.toString());
		      }
		      writer.close();
		      
		    } catch (IOException e) {
		      System.out.println("ERROR.");
		      e.printStackTrace();
		    }
	}
	
	public void writePolozeni() {
		 try {
		      FileWriter writer = new FileWriter(polozeniFajl, true);        
		      
		      for(Ocena o : spisakPolozeni) {
		    	  writer.write(o.toString());
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
	public String getAdresaStanovanja() {
		return adresaStanovanja;
	}
	public void setAdresaStanovanja(String adresaStanovanja) {
		this.adresaStanovanja = adresaStanovanja;
	}
	public String getKontaktTelefon() {
		return kontaktTelefon;
	}
	public void setKontaktTelefon(String kontaktTelefon) {
		this.kontaktTelefon = kontaktTelefon;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBrojIndeksa() {
		return brojIndeksa;
	}
	public void setBrojIndeksa(String brojIndeksa) {
		this.brojIndeksa = brojIndeksa;
	}
	public int getGodinaUpisa() {
		return godinaUpisa;
	}
	public void setGodinaUpisa(int godinaUpisa) {
		this.godinaUpisa = godinaUpisa;
	}
	public int getTrenutnaGodinaStudija() {
		return trenutnaGodinaStudija;
	}
	public void setTrenutnaGodinaStudija(int trenutnaGodinaStudija) {
		this.trenutnaGodinaStudija = trenutnaGodinaStudija;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public double getProsecnaOcena() {
		return prosecnaOcena;
	}
	public void setProsecnaOcena(double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}
	
	
	
	
}
