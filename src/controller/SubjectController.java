package controller;

import java.util.ArrayList;

import main.DataClass;
import model.Grade;
import model.Professor;
import model.Student;
import model.Subject;
import model.UnfinishedSubjects;

public class SubjectController {

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
}
