package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

enum Status{B,S};

public class Student {
	
	private String 	passedFile = "database/linkSubjectStudentGrade.txt";
	private String 	unfinishedFile= "database/linkSubjectStudentUnfinished.txt";
	
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
	
	private List<UnfinishedSubjects> 	listUnfinished;
	private List<Grade> 				listPassed;
	
	
	
	
	
	
	public Student(String lastname, String name, Date dateOfBirth, Adress adress, String phone, String email,
			String index, int enrolmentYear, int yearOfStudy, Status status, double averageGrade) {
		
		this.lastname = lastname;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.adress = adress;
		this.phone = phone;
		this.email = email;
		this.index = index;
		this.enrolmentYear = enrolmentYear;
		this.yearOfStudy = yearOfStudy;
		this.status = status;
		this.averageGrade = averageGrade;
		
		loadUnfinished();
		loadPassed();
		
	}

	private void loadUnfinished() {	
		try {
			
			File file = new File(unfinishedFile);
			Scanner reader = new Scanner(file);
			
			while(reader.hasNextLine()) {
				String dataLine = reader.nextLine();
				String[] data = dataLine.split("*\\");
				
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
				String[] data = dataLine.split("*\\");
				
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
		      FileWriter writer = new FileWriter(unfinishedFile, true);        
		      
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
		      FileWriter writer = new FileWriter(passedFile, true);        
		      
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public double getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(double averageGrade) {
		this.averageGrade = averageGrade;
	}

	public List<UnfinishedSubjects> getListUnfinished() {
		return listUnfinished;
	}

	public void setListUnfinished(List<UnfinishedSubjects> listUnfinished) {
		this.listUnfinished = listUnfinished;
	}

	public List<Grade> getListPassed() {
		return listPassed;
	}

	public void setListPassed(List<Grade> listPassed) {
		this.listPassed = listPassed;
	}

	@Override
	public String toString() {
		String splitter = "*\\";
		return lastname + splitter +  name+ splitter + dateOfBirth.toString() + splitter + adress.toString() + 
				splitter + phone+ splitter + email + splitter + index+ splitter + enrolmentYear+ splitter +
				yearOfStudy + splitter + status.toString() + splitter + averageGrade;
		
	}
	
	
	
	
}
