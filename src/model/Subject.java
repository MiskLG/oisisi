package model;

import java.util.ArrayList;

enum Semester{WINTER,SUMMER};

public class Subject {
	
	private String 		subjectCode;
	private String 		title;
	private Semester 	semester;
	private int 		yearOfStudy;
	private String 		subjectProfessor;
	private int 		numberECTS;
	
	private ArrayList<String> 	listUnfinishedStudents	= new ArrayList<String>();
	private ArrayList<String> 	listPassedStudents 		= new ArrayList<String>();
	
	
	
	
	public Subject(String subjectCode, String title, String semester, int yearOfStudy, String subjectProfessor, int numberECTS) {
		this.subjectCode = subjectCode;
		this.title = title;
		
		if(semester.compareToIgnoreCase("WINTER") == 0) {
			this.semester = Semester.WINTER;
		}
		else {
			this.semester = Semester.SUMMER;
		}
		
		this.yearOfStudy = yearOfStudy;
		this.subjectProfessor = subjectProfessor;
		this.numberECTS = numberECTS;
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


	public String getSemester() {
		return semester.toString();
	}


	public void setSemester(int semester) {
		if(semester == 0) {
			this.semester = Semester.WINTER;
		}
		else {
			this.semester = Semester.SUMMER;
		}
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

	


	public ArrayList<String> getListUnfinishedStudents() {
		return listUnfinishedStudents;
	}


	public void setListUnfinishedStudents(ArrayList<String> listUnfinishedStudents) {
		this.listUnfinishedStudents = listUnfinishedStudents;
	}


	public ArrayList<String> getListPassedStudents() {
		return listPassedStudents;
	}


	public void setListPassedStudents(ArrayList<String> listPassedStudents) {
		this.listPassedStudents = listPassedStudents;
	}


	@Override
	public String toString() {
		String splitter = "*/";
		return subjectCode + splitter + title + splitter + semester + splitter + yearOfStudy + splitter + subjectProfessor + splitter +  numberECTS;
	}
}
