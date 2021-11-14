package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Professor {

	private String 	listSubjectsFile = System.getProperty("user.dir") + File.separator + "database" + File.separator + "linkProfessorSubject.txt";
	
	private String 	lastname;
	private String 	name;
	private Date	dateOfBirth;
	private Adress 	adressHome;
	private String 	phone;
	private String 	email;
	private Adress 	adressWork;
	private String 	idNumber;
	private String 	title;
	private int 	workYears;
	
	private List<String> listSubjects;



	public Professor(String lastname, String name, Date dateOfBirth, Adress adressHome, String phone, String email,
			Adress adressWork, String idNumber, String title, int workYears) {
		this.lastname = lastname;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.adressHome = adressHome;
		this.phone = phone;
		this.email = email;
		this.adressWork = adressWork;
		this.idNumber = idNumber;
		this.title = title;
		this.workYears = workYears;
		
		loadListSubject();
	}

	private void loadListSubject() {
		try {				
			File file = new File(listSubjectsFile);
			Scanner reader = new Scanner(file);
			
			while(reader.hasNextLine()) {
				String dataLine = reader.nextLine();
				String[] data = dataLine.split("*\\");
				
				if(data[0].equals(getIdNumber())) {
					listSubjects.add(data[1]);
				}
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("ERROR");
			e.printStackTrace();
		}
	}

	public void writeListSubject() {
		 try {
		      FileWriter writer = new FileWriter(listSubjectsFile, true);        
		      String splitter = "*\\";
		      for(String s : listSubjects) {
		    	  String toWrite = getIdNumber() + splitter + s;
		    	  writer.write(toWrite);
		      }
		      writer.close();
		      
		    } catch (IOException e) {
		      System.out.println("ERROR.");
		      e.printStackTrace();
		    }
	}
	

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Adress getAdressHome() {
		return adressHome;
	}

	public void setAdressHome(Adress adressHome) {
		this.adressHome = adressHome;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Adress getAdressWork() {
		return adressWork;
	}

	public void setAdressWork(Adress adressWork) {
		this.adressWork = adressWork;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getWorkYears() {
		return workYears;
	}

	public void setWorkYears(int workYears) {
		this.workYears = workYears;
	}

	public List<String> getListSubjects() {
		return listSubjects;
	}

	public void setListSubjects(List<String> listSubjects) {
		this.listSubjects = listSubjects;
	}

	@Override
	public String toString() {
		String splitter = "*\\";
		return lastname + splitter + name + splitter + dateOfBirth.toString() + splitter +  adressHome.toString() + 
				splitter + phone + splitter + email + splitter + adressWork.toString() + splitter + idNumber + splitter + title + splitter +
				workYears;
		
	}
}
