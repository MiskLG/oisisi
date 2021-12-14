package model;

import java.util.ArrayList;


public class Professor {
	
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
	
	private ArrayList<String> listSubjects = new ArrayList<String>();



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

	public ArrayList<String> getListSubjects() {
		return listSubjects;
	}

	public void setListSubjects(ArrayList<String> listSubjects) {
		this.listSubjects = listSubjects;
	}

	@Override
	public String toString() {
		String splitter = "*/";
		return lastname + splitter + name + splitter + dateOfBirth.toString() + splitter +  adressHome.toString() + 
				splitter + phone + splitter + email + splitter + adressWork.toString() + splitter + idNumber + splitter + title + splitter +
				workYears;
		
	}
}
