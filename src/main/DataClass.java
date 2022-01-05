package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Adress;
import model.Chair;
import model.Date;
import model.Grade;
import model.Professor;
import model.Student;
import model.Subject;
import model.UnfinishedSubjects;

public class DataClass {
	
	private String 	professorsFile = System.getProperty("user.dir") + File.separator + "database" + File.separator + "professors.txt";
	private String 	chairsFile = System.getProperty("user.dir") + File.separator + "database" + File.separator + "chair.txt";
	private String 	studentsFile = System.getProperty("user.dir") + File.separator + "database" + File.separator + "students.txt";
	private String 	subjectsFile = System.getProperty("user.dir") + File.separator + "database" + File.separator + "subjects.txt";
	
	private String 	passedStudentsFile = System.getProperty("user.dir") + File.separator + "database" + File.separator + "linkSubjectStudentGrade.txt";
	private String 	unfinishedStudentsFile = System.getProperty("user.dir") + File.separator + "database" + File.separator + "linkSubjectStudentUnfinished.txt";
	
	private String 	passedFile = System.getProperty("user.dir") + File.separator + "database" + File.separator + "linkSubjectStudentGrade.txt";
	private String 	unfinishedFile= System.getProperty("user.dir") + File.separator + "database" + File.separator + "linkSubjectStudentUnfinished.txt";
	
	private String 	listSubjectsFile = System.getProperty("user.dir") + File.separator + "database" + File.separator + "linkProfessorSubject.txt";
	
	private String	professorListFile = System.getProperty("user.dir") + File.separator + "database" + File.separator + "linkProfessorChair.txt";
	
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
		      FileWriter filew = new FileWriter(professorsFile, false);
		      BufferedWriter writer = new BufferedWriter(filew);
		      
		      for(Professor pr : professorListData) {
		    	  writeListSubjects(pr);
		    	  writer.write(pr.toString());
		    	  writer.newLine();
		      }
		      writer.close();
		      
		    } catch (IOException e) {
		      System.out.println("ERROR.");
		      e.printStackTrace();
		    }
	
	}
	private void writeOutChairs() {
		 try {
		      FileWriter filew = new FileWriter(chairsFile, false);       
		      BufferedWriter writer = new BufferedWriter(filew);
		      
		      for(Chair ch : chairListData) {
		    	  writeProfessorList(ch);
		    	  writer.write(ch.toString());
		    	  writer.newLine();
		      }
		      writer.close();
		      
		    } catch (IOException e) {
		      System.out.println("ERROR.");
		      e.printStackTrace();
		    }
	
	}
	private void writeOutStudents() {
		 try {
		      FileWriter filew = new FileWriter(studentsFile, false);       
		      BufferedWriter writer = new BufferedWriter(filew);
		      
		      for(Student st : studentListData) {
		    	  writePassedSubjects(st);
		    	  writeUnfinishedSubjects(st);
		    	  writer.write(st.toString());
		    	  writer.newLine();
		      }
		      writer.close();
		      
		    } catch (IOException e) {
		      System.out.println("ERROR.");
		      e.printStackTrace();
		    }
	
	}
	private void writeOutSubjects() {
		 try {
		      FileWriter filew = new FileWriter(subjectsFile, false);
		      BufferedWriter writer = new BufferedWriter(filew);
		      
		      for(Subject sb : subjectListData) {
		    	  writer.write(sb.toString());
		    	  writer.newLine();
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
				
				prof.setListSubjects(loadListSubjects(prof));
				
				professorListData.add(prof);
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("ERROR");
			e.printStackTrace();
		}
	}
	
	// main 3 arrays
	private void loadChairs() {
		try {				
			File file = new File(chairsFile);
			Scanner reader = new Scanner(file);
			
			while(reader.hasNextLine()) {
				String dataLine = reader.nextLine();
				String[] data = dataLine.split("[*][/]");
				
				Chair chair = new Chair(data[0], data[1], data[2]);
				
				chair.setProfessorList(loadProfessorList(chair));
						
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
				
				st.setListPassed(loadPassedSubjects(st));
				st.setListUnfinished(loadUnfinishedSubjects(st));
				
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
				
				
				sub.setListUnfinishedStudents(loadUnfinishedStudents(sub));
				sub.setListPassedStudents(loadPassedStudents(sub));
				
				subjectListData.add(sub);
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("ERROR");
			e.printStackTrace();
		}
	}
	
	// supporting arrays - lists in class
	// loading students that haven't passed the subject yet
	// less memory load
	private ArrayList<String> loadUnfinishedStudents(Subject sub) {	
		ArrayList<String> 	listUnfinishedStudents	= new ArrayList<String>();
		try {			
			File file = new File(unfinishedStudentsFile);
			Scanner reader = new Scanner(file);
			
			while(reader.hasNextLine()) {
				String dataLine = reader.nextLine();
				String[] data = dataLine.split("[*][/]");
				
				if(data[0].equals(sub.getSubjectCode())) {
					listUnfinishedStudents.add(data[1]);
				}
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("ERROR kod citanja iz fajla");
			e.printStackTrace();
		}
		
		return listUnfinishedStudents;
	}


	// loading students that have passed the subject
	// less memory load
	private ArrayList<String> loadPassedStudents(Subject sub) {
		ArrayList<String> 	listPassedStudents 		= new ArrayList<String>();
		try {
			
			File file = new File(passedStudentsFile);
			Scanner reader = new Scanner(file);
			
			while(reader.hasNextLine()) {
				String dataLine = reader.nextLine();
				String[] data = dataLine.split("[*][/]");
				
				if(data[0].equals(sub.getSubjectCode())) {
					listPassedStudents.add(data[1]);
				}
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("ERROR kod citanja iz fajla");
			e.printStackTrace();
		}
		return listPassedStudents;
	}

	private ArrayList<UnfinishedSubjects> loadUnfinishedSubjects(Student st) {	
		ArrayList<UnfinishedSubjects> listUnfinished = new ArrayList<UnfinishedSubjects>();
		try {
			
			File file = new File(unfinishedFile);
			Scanner reader = new Scanner(file);
			
			while(reader.hasNextLine()) {
				String dataLine = reader.nextLine();
				String[] data = dataLine.split("[*][/]");
				
				if(data[1].equals(st.getIndex())) {
					UnfinishedSubjects us = new UnfinishedSubjects(data[0],data[1]);
					listUnfinished.add(us);
				}
				
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Readfile ERROR");
			e.printStackTrace();
		}
		
		return listUnfinished;
	}
	
	private ArrayList<Grade> loadPassedSubjects(Student st) {
		ArrayList<Grade> listPassed = new ArrayList<Grade>();
		try {
	
			File file = new File(passedFile);
			Scanner reader = new Scanner(file);
			
			while(reader.hasNextLine()) {
				String dataLine = reader.nextLine();
				String[] data = dataLine.split("[*][/]");
				
				if(data[1].equals(st.getIndex())) {
					Grade o = new Grade(data[0],data[1],Integer.parseInt(data[2]),new Date(data[3]));
					listPassed.add(o);
				}
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("Readfile ERROR");
			e.printStackTrace();
		}
		
		return listPassed;
	}
	
	private void writeUnfinishedSubjects(Student st) {
		 try {
			 FileWriter filew = new FileWriter(unfinishedFile, false);
			 BufferedWriter writer = new BufferedWriter(filew);
		      
		      for(UnfinishedSubjects us : st.getListUnfinished()) {
		    	  writer.write(us.toString());
		    	  writer.newLine();
		      }
		      writer.close();
		      
		    } catch (IOException e) {
		      System.out.println("ERROR.");
		      e.printStackTrace();
		    }
	}
	
	private void writePassedSubjects(Student st) {
		 try {
		      FileWriter filew = new FileWriter(passedFile, false);
		      BufferedWriter writer = new BufferedWriter(filew);
		      
		      for(Grade o : st.getListPassed()) {
		    	  writer.write(o.toString());
		    	  writer.newLine();
		      }
		      writer.close();
		      
		    } catch (IOException e) {
		      System.out.println("ERROR.");
		      e.printStackTrace();
		    }
	}
	
	private ArrayList<String> loadListSubjects(Professor pr) {
		ArrayList<String> listSubjects = new ArrayList<String>();
		try {				
			File file = new File(listSubjectsFile);
			Scanner reader = new Scanner(file);
			
			while(reader.hasNextLine()) {
				String dataLine = reader.nextLine();
				String[] data = dataLine.split("[*][/]");
				
				if(data[0].equals(pr.getIdNumber())) {
					listSubjects.add(data[1]);
				}
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("ERROR");
			e.printStackTrace();
		}
		
		return listSubjects;
	}

	private void writeListSubjects(Professor pr) {
		 try {
		      FileWriter filew = new FileWriter(listSubjectsFile, false);
		      BufferedWriter writer = new BufferedWriter(filew);
		      String splitter = "*/";
		      for(String s : pr.getListSubjects()) {
		    	  String toWrite = pr.getIdNumber() + splitter + s;
		    	  writer.write(toWrite);
		    	  writer.newLine();
		      }
		      writer.close();
		      
		    } catch (IOException e) {
		      System.out.println("ERROR.");
		      e.printStackTrace();
		    }
	}
	

	private ArrayList<String> loadProfessorList(Chair ch) {
		ArrayList<String> professorList = new ArrayList<String>();
		try {				
			File file = new File(professorListFile);
			Scanner reader = new Scanner(file);
			
			while(reader.hasNextLine()) {
				String dataLine = reader.nextLine();
				String[] data = dataLine.split("[*][/]");
				
				if(data[1].equals(ch.getChairCode())) {
					professorList.add(data[0]);
				}
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("ERROR");
			e.printStackTrace();
		}
		
		return professorList;
	}

	private void writeProfessorList(Chair ch) {
		 try {
		      FileWriter filew = new FileWriter(professorListFile, false);   
		      BufferedWriter writer = new BufferedWriter(filew);
		      String splitter = "*/";
		      
		      for(String p : ch.getProfessorList()) {
		    	  String toWrite = p + splitter + ch.getChairCode();
		    	  writer.write(toWrite);
		    	  writer.newLine();
		      }
		      writer.close();
		      
		    } catch (IOException e) {
		      System.out.println("ERROR.");
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
