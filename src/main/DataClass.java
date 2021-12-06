package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Adress;
import model.Chair;
import model.Date;
import model.Professor;
import model.Student;
import model.Subject;
import model.UnfinishedSubjects;

public class DataClass {
	
	private String 	professorsFile = System.getProperty("user.dir") + File.separator + "database" + File.separator + "professors.txt";
	private String 	chairsFile = System.getProperty("user.dir") + File.separator + "database" + File.separator + "chair.txt";
	private String 	studentsFile = System.getProperty("user.dir") + File.separator + "database" + File.separator + "students.txt";
	private String 	subjectsFile = System.getProperty("user.dir") + File.separator + "database" + File.separator + "subjects.txt";
	
	private static DataClass dataClass = null;
	
	private ArrayList<Chair> chairListData = new ArrayList<Chair>();
	private ArrayList<Professor> professorListData = new ArrayList<Professor>();
	private ArrayList<Student> studentListData = new ArrayList<Student>();
	private ArrayList<Subject> subjectListData = new ArrayList<Subject>();
	
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
	
	public void writeOutData() {
		writeOutProfessors();
		writeOutChairs();
		writeOutSubjects();
		writeOutStudents();
	}
	
	
	// methods to write to files
	private void writeOutProfessors() {
		 try {
		      FileWriter writer = new FileWriter(professorsFile, false);        
		      
		      for(Professor pr : professorListData) {
		    	  pr.writeListSubject();
		    	  writer.write(pr.toString());
		      }
		      writer.close();
		      
		    } catch (IOException e) {
		      System.out.println("ERROR.");
		      e.printStackTrace();
		    }
	
	}
	private void writeOutChairs() {
		 try {
		      FileWriter writer = new FileWriter(chairsFile, false);        
		      
		      for(Chair ch : chairListData) {
		    	  ch.writeProfessorList();
		    	  writer.write(ch.toString());
		      }
		      writer.close();
		      
		    } catch (IOException e) {
		      System.out.println("ERROR.");
		      e.printStackTrace();
		    }
	
	}
	private void writeOutStudents() {
		 try {
		      FileWriter writer = new FileWriter(studentsFile, false);        
		      
		      for(Student st : studentListData) {
		    	  st.writePassed();
		    	  st.writeUnfinished();
		    	  writer.write(st.toString());
		      }
		      writer.close();
		      
		    } catch (IOException e) {
		      System.out.println("ERROR.");
		      e.printStackTrace();
		    }
	
	}
	private void writeOutSubjects() {
		 try {
		      FileWriter writer = new FileWriter(subjectsFile, false);        
		      
		      for(Subject sb : subjectListData) {
		    	  writer.write(sb.toString());
		      }
		      writer.close();
		      
		    } catch (IOException e) {
		      System.out.println("ERROR.");
		      e.printStackTrace();
		    }
	
	}
	
	
	
	
	// methods to load data from .txt files to lists
	private void loadProfessors() {
		try {				
			File file = new File(professorsFile);
			Scanner reader = new Scanner(file);
			
			while(reader.hasNextLine()) {
				String dataLine = reader.nextLine();
				String[] data = dataLine.split("[*][/]");
				
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
				String[] data = dataLine.split("[*][/]");
				
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
				String[] data = dataLine.split("[*][/]");

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
				String[] data = dataLine.split("[*][/]");
				
				Subject sub = new Subject(data[0], data[1], data[2], Integer.parseInt(data[3]), data[4], Integer.parseInt(data[5]));
				
				subjectListData.add(sub);
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("ERROR");
			e.printStackTrace();
		}
	}

	public ArrayList<Chair> getChairListData() {
		return chairListData;
	}

	public void setChairListData(ArrayList<Chair> chairListData) {
		this.chairListData = chairListData;
	}

	public ArrayList<Professor> getProfessorListData() {
		return professorListData;
	}

	public void setProfessorListData(ArrayList<Professor> professorListData) {
		this.professorListData = professorListData;
	}

	public ArrayList<Student> getStudentListData() {
		return studentListData;
	}

	public void setStudentListData(ArrayList<Student> studentListData) {
		this.studentListData = studentListData;
	}

	public ArrayList<Subject> getSubjectListData() {
		return subjectListData;
	}

	public void setSubjectListData(ArrayList<Subject> subjectListData) {
		this.subjectListData = subjectListData;
	}
	

	
	
	
	
}
