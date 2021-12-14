package model;

import java.util.ArrayList;

enum Status{B,S};

public class Student {
	
	private String 	lastname;
	private String 	name;
	private Date 	dateOfBirth;
	private Adress 	adress;
	private String 	phone;
	private String 	email;
	private String 	index;
	private int 	enrolmentYear;
	private int 	yearOfStudy;
	private Status 	status;
	private double 	averageGrade;
	
	private ArrayList<UnfinishedSubjects> 	listUnfinished 	= new ArrayList<UnfinishedSubjects>();
	private ArrayList<Grade> 				listPassed		= new ArrayList<Grade>();
	
	
	
	public Student(String lastname, String name, Date dateOfBirth, Adress adress, String phone, String email,
			String index, int enrolmentYear, int yearOfStudy, String status, double averageGrade) {
		
		this.lastname = lastname;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.adress = adress;
		this.phone = phone;
		this.email = email;
		this.index = index;
		this.enrolmentYear = enrolmentYear;
		this.yearOfStudy = yearOfStudy;
		
		if(status.equalsIgnoreCase("B")) {
			this.status = Status.B;
		}
		else {
			this.status = Status.S;
		}
		
		this.averageGrade = averageGrade;
		
	}
	
	public Student(String lastname, String name, Date dateOfBirth, Adress adress, String phone, String email,
			String index, int enrolmentYear, int yearOfStudy, int status) {
		
		this.lastname = lastname;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.adress = adress;
		this.phone = phone;
		this.email = email;
		this.index = index;
		this.enrolmentYear = enrolmentYear;
		this.yearOfStudy = yearOfStudy;
		
		if(status == 0) {
			this.status = Status.B;
		}
		else {
			this.status = Status.S;
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

	public Adress getAdress() {
		return adress;
	}

	public void setAdress(Adress adress) {
		this.adress = adress;
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

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public int getEnrolmentYear() {
		return enrolmentYear;
	}

	public void setEnrolmentYear(int enrolmentYear) {
		this.enrolmentYear = enrolmentYear;
	}

	public int getYearOfStudy() {
		return yearOfStudy;
	}

	public void setYearOfStudy(int yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}

	public String getStatus() {
		return status.toString();
	}

	public void setStatus(int status) {
		if(status == 0) {
			this.status = Status.B;
		}
		else {
			this.status = Status.S;
		}
	}

	public double getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(double averageGrade) {
		this.averageGrade = averageGrade;
	}

	public ArrayList<UnfinishedSubjects> getListUnfinished() {
		return listUnfinished;
	}

	public void setListUnfinished(ArrayList<UnfinishedSubjects> listUnfinished) {
		this.listUnfinished = listUnfinished;
	}

	public ArrayList<Grade> getListPassed() {
		return listPassed;
	}

	public void setListPassed(ArrayList<Grade> listPassed) {
		this.listPassed = listPassed;
	}

	@Override
	public String toString() {
		String splitter = "*/";
		return lastname + splitter +  name+ splitter + dateOfBirth.toString() + splitter + adress.toString() + 
				splitter + phone+ splitter + email + splitter + index+ splitter + enrolmentYear+ splitter +
				yearOfStudy + splitter + status.toString() + splitter + averageGrade;
		
	}
	
	
	
	
}
