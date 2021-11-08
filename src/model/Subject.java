package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

enum Semester{WINTER,SUMMER};

public class Subject {

	private String 		passedStudentsFile = "database/linkSubjectStudentGrade.txt";
	private String 		unfinishedStudentsFile = "database/linkSubjectStudentUnfinished.txt";
	
	private String 		subjectCode;
	private String 		title;
	private Semester 	semester;
	private int 		yearOfStudy;
	private String 		subjectProfessor;
	private int 		numberECTS;
	
	private List<String> 	listUnfinishedStudents;
	private List<String> 	listPassedStudents;
	
	
	
	
	public Subject(String subjectCode, String title, Semester semester, int yearOfStudy, String subjectProfessor, int numberECTS) {
		this.subjectCode = subjectCode;
		this.title = title;
		this.semester = semester;
		this.yearOfStudy = yearOfStudy;
		this.subjectProfessor = subjectProfessor;
		this.numberECTS = numberECTS;
		
		loadPassedStudents();
		loadUnfinishedStudents();
	}
	
	
	// loading students that haven't passed the subject yet
	// less memory load
	private void loadUnfinishedStudents() {	
		try {
			
			File file = new File(unfinishedStudentsFile);
			Scanner reader = new Scanner(file);
			
			while(reader.hasNextLine()) {
				String dataLine = reader.nextLine();
				String[] data = dataLine.split("*\\");
				
				if(data[0].equals(getSubjectCode())) {
					listUnfinishedStudents.add(data[1]);
				}
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("ERROR kod citanja iz fajla");
			e.printStackTrace();
		}
		
		return;
	}


	// loading students that have passed the subject
	// less memory load
	private void loadPassedStudents() {
		try {
			
			File file = new File(passedStudentsFile);
			Scanner reader = new Scanner(file);
			
			while(reader.hasNextLine()) {
				String dataLine = reader.nextLine();
				String[] data = dataLine.split("*\\");
				
				if(data[0].equals(getSubjectCode())) {
					listPassedStudents.add(data[1]);
				}
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("ERROR kod citanja iz fajla");
			e.printStackTrace();
		}
		
	}

	


	public String getSubjectCode() {
		return subjectCode;
	}


	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Semester getSemester() {
		return semester;
	}


	public void setSemester(Semester semester) {
		this.semester = semester;
	}


	public int getYearOfStudy() {
		return yearOfStudy;
	}


	public void setYearOfStudy(int yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}


	public String getSubjectProfessor() {
		return subjectProfessor;
	}


	public void setSubjectProfessor(String subjectProfessor) {
		this.subjectProfessor = subjectProfessor;
	}


	public int getNumberECTS() {
		return numberECTS;
	}


	public void setNumberECTS(int numberECTS) {
		this.numberECTS = numberECTS;
	}


	public List<String> getListUnpassedStudents() {
		return getListUnpassedStudents();
	}


	public void setListUnpassedStudents(List<String> listUnpassedStudents) {
		this.listUnfinishedStudents = listUnpassedStudents;
	}


	public List<String> getListPassedStudents() {
		return listPassedStudents;
	}


	public void setListPassedStudents(List<String> listPassedStudents) {
		this.listPassedStudents = listPassedStudents;
	}


	@Override
	public String toString() {
		String splitter = "*\\";
		return subjectCode + splitter + title + splitter + semester + splitter + yearOfStudy + splitter + subjectProfessor + splitter +  numberECTS;
	}
}
