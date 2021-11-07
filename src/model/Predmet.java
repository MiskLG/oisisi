package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Predmet {
	
	private String 	polozeniFajl = "baza/vezaPredmetStudentOcena.txt";
	private String 	nepolozeniFajl = "baza/vezaPredmetStudentNepolozeni.txt";
	
	private String 	sifra;
	private String 	naziv;
	private String 	semestar;
	private int 	godinaStudija;
	private int 	predmetniProf;
	private int 	brojESPB;
	
	private List<String> 	spisakNepolozeni;
	private List<String> 	spisakPolozeni;
	
	
	
	
	public Predmet(String sifra, String naziv, String semestar, int godinaStudija, int predmetniProf, int brojESPB) {
		this.sifra = sifra;
		this.naziv = naziv;
		this.semestar = semestar;
		this.godinaStudija = godinaStudija;
		this.predmetniProf = predmetniProf;
		this.brojESPB = brojESPB;
		
		loadPolozeni();
		loadNepolozeni();
	}
	
	
// ucitavanje ce imati samo kljuc na koji se poziva
// lakse je za memoriju
	private void loadNepolozeni() {	
		try {
			
			File file = new File(nepolozeniFajl);
			Scanner reader = new Scanner(file);
			
			while(reader.hasNextLine()) {
				String dataLine = reader.nextLine();
				String[] data = dataLine.split("*\\");
				
				if(data[0].equals(getSifra())) {
					spisakNepolozeni.add(data[1]);
				}
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("ERROR kod citanja iz fajla");
			e.printStackTrace();
		}
		
		return;
	}


// ucitavanje ce imati samo kljuc na koji se poziva
// lakse je za memoriju
	private void loadPolozeni() {
		try {
			
			File file = new File(polozeniFajl);
			Scanner reader = new Scanner(file);
			
			while(reader.hasNextLine()) {
				String dataLine = reader.nextLine();
				String[] data = dataLine.split("*\\");
				
				if(data[0].equals(getSifra())) {
					spisakPolozeni.add(data[1]);
				}
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("ERROR kod citanja iz fajla");
			e.printStackTrace();
		}
		
	}



	public String getSifra() {
		return sifra;
	}
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getSemestar() {
		return semestar;
	}
	public void setSemestar(String semestar) {
		this.semestar = semestar;
	}
	public int getGodinaStudija() {
		return godinaStudija;
	}
	public void setGodinaStudija(int godinaStudija) {
		this.godinaStudija = godinaStudija;
	}
	public int getPredmetniProf() {
		return predmetniProf;
	}
	public void setPredmetniProf(int predmetniProf) {
		this.predmetniProf = predmetniProf;
	}
	public int getBrojESPB() {
		return brojESPB;
	}
	public void setBrojESPB(int brojESPB) {
		this.brojESPB = brojESPB;
	}



	public List<String> getSpisakNepolozeni() {
		return spisakNepolozeni;
	}


	public void setSpisakNepolozeni(List<String> spisakNepolozeni) {
		this.spisakNepolozeni = spisakNepolozeni;
	}


	public List<String> getSpisakPolozeni() {
		return spisakPolozeni;
	}


	public void setSpisakPolozeni(List<String> spisakPolozeni) {
		this.spisakPolozeni = spisakPolozeni;
	}


	@Override
	public String toString() {
		String splitter = "*\\";
		return sifra + splitter + naziv + splitter + semestar + splitter + godinaStudija + splitter + predmetniProf + splitter +  brojESPB;
	}
	
}
