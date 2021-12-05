package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

enum Status{B,S};

public class Student {
	
	private String 	passedFile = System.getProperty("user.dir") + File.separator + "database" + File.separator + "linkSubjectStudentGrade.txt";
	private String 	unfinishedFile= System.getProperty("user.dir") + File.separator + "database" + File.separator + "linkSubjectStudentUnfinished.txt";
	
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
		
		loadUnfinished();
		loadPassed();
		
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

	private void loadUnfinished() {	
		try {
			
			File file = new File(unfinishedFile);
			Scanner reader = new Scanner(file);
			
			while(reader.hasNextLine()) {
				String dataLine = reader.nextLine();
				String[] data = dataLine.split("[*][/]");
				
				if(data[1].equals(getIndex())) {
					UnfinishedSubjects us = new UnfinishedSubjects(data[0],data[1]);
					listUnfinished.add(us);
				}
				
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Readfile ERROR");
			e.printStackTrace();
		}
		
		return;
	}
	
	private void loadPassed() {
		try {
			
			File file = new File(passedFile);
			Scanner reader = new Scanner(file);
			
			while(reader.hasNextLine()) {
				String dataLine = reader.nextLine();
				String[] data = dataLine.split("[*][/]");
				
				if(data[1].equals(getIndex())) {
					Grade o = new Grade(data[0],data[1],Integer.parseInt(data[2]),new Date(data[3]));
					listPassed.add(o);
				}
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Readfile ERROR");
			e.printStackTrace();
		}
		
	}
	
	public void writeUnfinished() {
		 try {
		      FileWriter writer = new FileWriter(unfinishedFile, false);        
		      
		      for(UnfinishedSubjects us : listUnfinished) {
		    	  writer.write(us.toString());
		      }
		      writer.close();
		      
		    } catch (IOException e) {
		      System.out.println("ERROR.");
		      e.printStackTrace();
		    }
	}
	
	public void writePassed() {
		 try {
		      FileWriter writer = new FileWriter(passedFile, false);        
		      
		      for(Grade o : listPassed) {
		    	  writer.write(o.toString());
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
