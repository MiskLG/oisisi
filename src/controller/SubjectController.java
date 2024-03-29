package controller;

import java.util.ArrayList;

import main.DataClass;
import model.Date;
import model.Grade;
import model.Professor;
import model.Student;
import model.Subject;
import model.UnfinishedSubjects;

public class SubjectController {
	
	private String 		subjectCode;
	private String 		title;
	private int		 	semester;
	private int 		yearOfStudy;
	private String 		subjectProfessor;
	private int 		numberECTS;
	
	private String err;
	private boolean sameCode = false;
	
	public SubjectController() {}
	
	public SubjectController(String subjectCode, String title, int semester, int yearOfStudy, String numberECTS, String subjectProfessor) {
		this.err = checkData(subjectCode, title, numberECTS);
		this.subjectCode = subjectCode;
		this.title = title;
		this.semester = semester;
		this.yearOfStudy = yearOfStudy;
		this.numberECTS = Integer.parseInt(numberECTS);
		this.subjectProfessor = subjectProfessor;
	}
	
	public SubjectController(String subjectCode, String title, int semester, int yearOfStudy, String numberECTS, String subjectProfessor,
			boolean sameCode) {
		
		this.sameCode = sameCode;
		this.err = checkData(subjectCode, title, numberECTS);
		this.subjectCode = subjectCode;
		this.title = title;
		this.semester = semester;
		this.yearOfStudy = yearOfStudy;
		this.numberECTS = Integer.parseInt(numberECTS);
		this.subjectProfessor = subjectProfessor;
	}
	
	public String checkData(String subjectCode, String title, String numberECTS) {
		err = "Sve je dobro!";
		
		if(false == RegXClass.checkSubjectCode(subjectCode)) {
			err = "Loše unesena šifra predmeta";
			return err;
		}
		
		
		if(!sameCode) {
			DataClass data = DataClass.getInstance();
			ArrayList<Subject> listSubject = data.getSubjectListData();
			if(listSubject != null) {
				for(Subject s : listSubject) {
					if(subjectCode.equalsIgnoreCase(s.getSubjectCode())) {
						return "Šifra predmeta je zauzeta!";
					}
				}
			}
		}
		
		if(false == RegXClass.checkSubjectTitle(title)) {
			err = "Loše unesen naziv predmeta";
			return err;
		}
		if(false == RegXClass.checkNumberECTS(numberECTS)) {
			err = "Loše unesen broj ESPB";
			return err;
		}
		
		return err;
	}
	
	public String addSubjectToData() {
		DataClass data = DataClass.getInstance();
		
		if(!err.equals("Sve je dobro!")) {
			return err;
		}
			
		ArrayList<Subject> listSubject = data.getSubjectListData();
		listSubject.add(new Subject(this.subjectCode, this.title, this.semester, this.yearOfStudy+1, this.numberECTS));
		data.setSubjectListData(listSubject);
		
		return err;
	}
	
	public Subject findSubjectByCode(String subjectCode) {
		DataClass data = DataClass.getInstance();
		
		ArrayList<Subject> listSubject = data.getSubjectListData();
		for(Subject s: listSubject) {
			if(s.getSubjectCode().equals(subjectCode)) {
				return s;
			}
		}
		return null;
	}
	
	public String editSubject(String subjectCode) {
		DataClass data = DataClass.getInstance();
		System.out.println(err);
		if(!err.equals("Sve je dobro!")) {
			return err;
		}
		
		ArrayList<Subject> listSubject = data.getSubjectListData();
		for(Subject s: listSubject) {
			if(s.getSubjectCode().equals(subjectCode)) {
				s.setSubjectCode(this.subjectCode);
				s.setTitle(this.title);
				s.setSemester(this.semester);
				s.setYearOfStudy(this.yearOfStudy+1);
				s.setNumberECTS(this.numberECTS);
				//Ovde verovatno nece funkcionisati odmah
				s.setSubjectProfessor(this.subjectProfessor);
			}
		}
		data.setSubjectListData(listSubject);
		
		return err;
	}

	public boolean deleteSubject(String code) {
		
		// remove from the main list	
		ArrayList<Subject> subList = DataClass.getInstance().getSubjectListData();
		int j = 0;
		int subjectToRemove = -1;
		for(Subject s: subList) {
			if(s.getSubjectCode().equals(code)) {
				subjectToRemove = j;
			}
			j++;
		}
		if(subjectToRemove != -1) {
			subList.remove(subjectToRemove);
		}
		
		// remove from the students and update their average grade
		ArrayList<Student> studList = DataClass.getInstance().getStudentListData();
		
		for(Student stud: studList) {	
				ArrayList<Grade> subPassed = stud.getListPassed();
				ArrayList<UnfinishedSubjects> subUnfinished = stud.getListUnfinished();
				int studentPassedIndex = -1;
				int studentUnfinishedIndex = -1;
				int i = 0;
				for(Grade grade: subPassed) {
					if(grade.getSubjectCode().equals(code)) {
						studentPassedIndex = i;
					}
					i++;
				}
				if(studentPassedIndex != -1) {
					subPassed.remove(studentPassedIndex);
				}
				
				
				i = 0;
				for(UnfinishedSubjects unfinished: subUnfinished) {
					if(unfinished.getSubjectCode().equals(code)) {
						studentUnfinishedIndex = i;
					}
					i++;
				}
				if(studentUnfinishedIndex != -1) {
					subUnfinished.remove(studentUnfinishedIndex);
				}
				
				double sum = 0;
				int numberOfPassedSubjects = 0;
			
				for(Grade g: subPassed) {
					sum = sum + g.getGrade();
					numberOfPassedSubjects++;
				}
				
				double avgGrade;
				if(numberOfPassedSubjects == 0) {
					avgGrade = 0;
				}
				else {
					avgGrade = sum/numberOfPassedSubjects;
				}
				
				
				// taken from https://mkyong.com/java/how-to-round-double-float-value-to-2-decimal-points-in-java/#mathround
				avgGrade = Math.round(avgGrade * 100.0) / 100.0;
				
				
				stud.setAverageGrade(avgGrade);
				
		}

		
		
		
		// removing from the professors
		ArrayList<Professor> profList = DataClass.getInstance().getProfessorListData();
		
		
		for(Professor p: profList) {
			ArrayList<String> profSubList = p.getListSubjects();
			int profSubjectIndex = -1;
			int i = 0;
			for(String s: profSubList) {
				if(s.equals(code)) {
					profSubjectIndex = i;
				}
				i++;
			}
			if(profSubjectIndex != -1) {
				profSubList.remove(profSubjectIndex);
			}
			
		}
		
		DataClass.getInstance().setProfessorListData(profList);
		DataClass.getInstance().setStudentListData(studList);
		DataClass.getInstance().setSubjectListData(subList);
		
		return true;
	}
	
	public boolean deleteUnfinishedSubject(String index, String subCode) {
		ArrayList<Student> studList = DataClass.getInstance().getStudentListData();
		ArrayList<Subject> subList = DataClass.getInstance().getSubjectListData();

		// deleting from student
		int numberStudent = -1;
		int numberSubject = -1;
		int i = 0;
		int j = 0;
		for(Student s: studList) {
			if(s.getIndex().equals(index)){
				numberStudent = i;
				for(UnfinishedSubjects us: s.getListUnfinished()) {
					if(us.getSubjectCode().equals(subCode)) {
						numberSubject = j;
					}
					j++;
				}
			}
			i++;
		}
		if(numberStudent != -1 && numberSubject != -1) {
			studList.get(numberStudent).getListUnfinished().remove(numberSubject);
		}
		
		i = 0;
		j = 0;
		int numberSub = -1;
		int numberStud = -1;
		
		for(Subject s: subList) {
			if(s.getSubjectCode().equals(subCode)){
				numberStudent = i;
				for(String st: s.getListUnfinishedStudents()){
					if(st.equals(index)) {
						numberSubject = j;
					}
					j++;
				}
			}
			i++;
		}
		if(numberSub != -1 && numberStud != -1) {
			subList.get(numberSub).getListUnfinishedStudents().remove(numberStud);
		}
		
		DataClass.getInstance().setStudentListData(studList);
		DataClass.getInstance().setSubjectListData(subList);
		
		return true;
	}
	
	public boolean addUnfinishedSubject(String index, String subCode) {
		ArrayList<Student> studList = DataClass.getInstance().getStudentListData();
		ArrayList<Subject> subList = DataClass.getInstance().getSubjectListData();

		
		for(Student s: studList) {
			if(s.getIndex().equals(index)) {
				s.getListUnfinished().add(new UnfinishedSubjects(index, subCode));
			}
		}
	 
		
		for(Subject s: subList) {
			if(s.getSubjectCode().equals(subCode)) {
				s.getListUnfinishedStudents().add(index);
			}
		}
		
		
		DataClass.getInstance().setStudentListData(studList);
		DataClass.getInstance().setSubjectListData(subList);
		
		return true;
	}
	
	
	public boolean deleteFinishedSubject(String index, String subCode) {
		ArrayList<Student> studList = DataClass.getInstance().getStudentListData();
		ArrayList<Subject> subList = DataClass.getInstance().getSubjectListData();
		
		addUnfinishedSubject(index, subCode);
		
		// deleting from student
		int numberStudent = -1;
		int numberSubject = -1;
		int i = 0;
		int j = 0;
		for(Student s: studList) {
			if(s.getIndex().equals(index)){
				numberStudent = i;
				for(Grade gradedSubjects: s.getListPassed()) {
					if(gradedSubjects.getSubjectCode().equals(subCode)) {
						numberSubject = j;
					}
					j++;
				}
			}
			i++;
		}
		if(numberStudent != -1 && numberSubject != -1) {
			studList.get(numberStudent).getListPassed().remove(numberSubject);
		}
		
		i = 0;
		j = 0;
		int numberSub = -1;
		int numberStud = -1;
		
		for(Subject s: subList) {
			if(s.getSubjectCode().equals(subCode)){
				numberStudent = i;
				for(String st: s.getListPassedStudents()){
					if(st.equals(index)) {
						numberSubject = j;
					}
					j++;
				}
			}
			i++;
		}
		if(numberSub != -1 && numberStud != -1) {
			subList.get(numberSub).getListPassedStudents().remove(numberStud);
		}
		
		DataClass.getInstance().setStudentListData(studList);
		DataClass.getInstance().setSubjectListData(subList);
		
		return true;
	}
	
	public boolean addFinishedSubject(String index, String subCode, int grade, String gradingDate) {
		ArrayList<Student> studList = DataClass.getInstance().getStudentListData();
		ArrayList<Subject> subList = DataClass.getInstance().getSubjectListData();
		
		
		for(Student s: studList) {
			if(s.getIndex().equals(index)) {
				s.getListPassed().add(new Grade(index, subCode, grade+6, new Date(gradingDate)));
			}
		}
		
		
	 
		
		for(Subject s: subList) {
			if(s.getSubjectCode().equals(subCode)) {
				s.getListPassedStudents().add(index);

			}
		}
		
		DataClass.getInstance().setStudentListData(studList);
		DataClass.getInstance().setSubjectListData(subList);
		
		return true;
	}
}
