package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Katedra {
	
	private String listaProfesoraFajl = "vezaProfesorKatedra";
	
	private String sifra;
	private String naziv;
	private String sef;
	
	private List<String> listaProfesora;
	

	public Katedra(String sifra, String naziv, String sef) {
		this.sifra = sifra;
		this.naziv = naziv;
		this.sef = sef;
		
		loadListaProfesora();
	}


	private void loadListaProfesora() {
		try {				
			File file = new File(listaProfesoraFajl);
			Scanner reader = new Scanner(file);
			
			while(reader.hasNextLine()) {
				String dataLine = reader.nextLine();
				String[] data = dataLine.split("*\\");
				
				if(data[1].equals(getSifra())) {
					listaProfesora.add(data[0]);
				}
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("ERROR kod citanja iz fajla");
			e.printStackTrace();
		}
	}

	public void writeListaProfesora() {
		 try {
		      FileWriter writer = new FileWriter(listaProfesoraFajl, true);        
		      String splitter = "*\\";
		      
		      for(String p : listaProfesora) {
		    	  String toWrite = p + splitter + getSifra();
		    	  writer.write(toWrite);
		      }
		      writer.close();
		      
		    } catch (IOException e) {
		      System.out.println("ERROR.");
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


	public String getSef() {
		return sef;
	}


	public void setSef(String sef) {
		this.sef = sef;
	}


	public List<String> getListaProfesora() {
		return listaProfesora;
	}


	public void setListaProfesora(List<String> listaProfesora) {
		this.listaProfesora = listaProfesora;
	}
	
	
	@Override
	public String toString() {
		String splitter = "*\\";
		return sifra + splitter + naziv + splitter + sef;
		
	}
	
}
