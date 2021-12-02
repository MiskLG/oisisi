package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import model.Adress;
import model.Chair;
import model.Date;
import model.Professor;
import model.Student;
import model.Subject;

public class DataClass {
	
	private String 	professorsFile = System.getProperty("user.dir") + File.separator + "database" + File.separator + "professors.txt";
	private String 	chairsFile = System.getProperty("user.dir") + File.separator + "database" + File.separator + "chairs.txt";
	private String 	studentsFile = System.getProperty("user.dir") + File.separator + "database" + File.separator + "students.txt";
	private String 	subjectsFile = System.getProperty("user.dir") + File.separator + "database" + File.separator + "subjects.txt";
	
	private static DataClass dataClass = null;
	
	private List<Chair> chairListData;
	private List<Professor> professorListData;
	private List<Student> studentListData;
	private List<Subject> subjectListData;
	
	private DataClass() {
		// loading from database
		loadProfessors();
		loadChairs();
		loadStudents();
		loadSubjects();
	}
	
	public static DataClass getInstance()
    {
        if (dataClass == null)
            dataClass = new DataClass();
 
        return dataClass;
    }
	
	
	// methods to load data from .txt files to lists
	private void loadProfessors() {
		try {				
			File file = new File(professorsFile);
			Scanner reader = new Scanner(file);
			
			while(reader.hasNextLine()) {
				String dataLine = reader.nextLine();
				String[] data = dataLine.split("*\\");
				
				Professor prof = new Professor(data[0], data[1], new Date(data[2]), new Adress( data[3], data[4], data[5], data[6]), data[7], data[8],
						  new Adress( data[9], data[10], data[11], data[12]),data[13],data[14], Integer.parseInt(data[15]));
				
				professorListData.add(prof);
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("ERROR");
			e.printStackTrace();
		}
	}
	
	private void loadChairs() {
		try {				
			File file = new File(chairsFile);
			Scanner reader = new Scanner(file);
			
			while(reader.hasNextLine()) {
				String dataLine = reader.nextLine();
				String[] data = dataLine.split("*\\");
				
				Chair chair = new Chair(data[0], data[1], data[2]);
						
				chairListData.add(chair);
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("ERROR");
			e.printStackTrace();
		}
	}
	private void loadStudents() {
		try {				
			File file = new File(studentsFile);
			Scanner reader = new Scanner(file);
			
			while(reader.hasNextLine()) {
				String dataLine = reader.nextLine();
				String[] data = dataLine.split("*\\");
				Student st = new Student(data[0], data[1], new Date(data[2]), new Adress(data[3], data[4], data[5], data[6]), data[7], data[8], data[9], 
						Integer.parseInt(data[10]), Integer.parseInt(data[11]), data[12], Double.parseDouble(data[13]));
				
				studentListData.add(st);
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("ERROR");
			e.printStackTrace();
		}
	}
	private void loadSubjects() {
		try {				
			File file = new File(subjectsFile);
			Scanner reader = new Scanner(file);
			
			while(reader.hasNextLine()) {
				String dataLine = reader.nextLine();
				String[] data = dataLine.split("*\\");
				
				Subject sub = new Subject(data[0], data[1], data[2], Integer.parseInt(data[3]), data[4], Integer.parseInt(data[5]));
				
				subjectListData.add(sub);
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("ERROR");
			e.printStackTrace();
		}
	}
	

	public List<Chair> getChairListData() {
		return chairListData;
	}

	public void setChairListData(List<Chair> chairListData) {
		this.chairListData = chairListData;
	}

	public List<Professor> getProfessorListData() {
		return professorListData;
	}

	public void setProfessorListData(List<Professor> professorListData) {
		this.professorListData = professorListData;
	}

	public List<Student> getStudentListData() {
		return studentListData;
	}

	public void setStudentListData(List<Student> studentListData) {
		this.studentListData = studentListData;
	}

	public List<Subject> getSubjectListData() {
		return subjectListData;
	}

	public void setSubjectListData(List<Subject> subjectListData) {
		this.subjectListData = subjectListData;
	}
	
	
	
}
