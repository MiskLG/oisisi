package controller;

import java.util.ArrayList;
import main.DataClass;
import model.Adress;
import model.Date;
import model.Professor;
import model.Student;
import model.Subject;
import model.UnfinishedSubjects;

public class ProfessorController {
	private String name;
	private String lastname;
	private Date date;
	private Adress adressHome;
	private String phone;
	private String email;
	private Adress adressWork;
	private String id;
	private String title;
	private int workYears;
	
	private String err;
	private boolean sameId = false;

	public ProfessorController(String name, String lastname, String date, String adressHome,  String phone, String email, String adressWork,
			String id, String title, String workYears) {
		this.err = checkData(name,lastname,date,adressHome,phone,email,adressWork,id,title,workYears);
		if(err.equals("Sve je dobro!")) {
			this.name = name;
			this.lastname = lastname;
			this.date = new Date(date);
			this.adressHome = new Adress(adressHome);
			this.phone = phone;
			this.id = id;
			this.workYears = Integer.parseInt(workYears);
			this.title = title;
			this.adressWork = new Adress(adressWork);
			this.email = email;
		}
		
	}

	public ProfessorController() {};
	
	public ProfessorController(String name, String lastname, String date, String adressHome,  String phone, String email, String adressWork,
			String id, String title, String workYears, boolean sameId) {
		this.sameId = sameId;
		this.err = checkData(name,lastname,date,adressHome,phone,email,adressWork,id,title,workYears);
		if(err.equals("Sve je dobro!")) {
			this.name = name;
			this.lastname = lastname;
			this.date = new Date(date);
			this.adressHome = new Adress(adressHome);
			this.phone = phone;
			this.id = id;
			this.workYears = Integer.parseInt(workYears);
			this.title = title;
			this.adressWork = new Adress(adressWork);
			this.email = email;
		}
		
	}
	
	public String addProfessorToData() {
		DataClass data = DataClass.getInstance();
		
		if(!err.equals("Sve je dobro!")) {
			return err;
		}
			
		
		ArrayList<Professor> listProfessor = data.getProfessorListData();
		listProfessor.add(new Professor(this.lastname, this.name, this.date, this.adressHome, this.phone, this.email, this.adressWork, this.id, this.title, this.workYears));
		data.setProfessorListData(listProfessor);
		
		return err;
	}
	
	
	public String checkData(String name, String lastname, String date, String adressHome, String phone, String email, String adressWork, String id, String title, String workYears) {
		 err = "Sve je dobro!";
		
		if(false == RegXClass.checkName(name)) {
			err = "Loše uneseno ime";
			return err;
		}
		if(false == RegXClass.checkLastname(lastname)) {
			err = "Loše uneseno prezime";
			return err;
		}
		if(false == RegXClass.checkDate(date)) {
			err = "Loše unesen datum";
			return err;
		}
		if(false == RegXClass.checkAdress(adressHome)) {
			err = "Loše unesena adresa stanovanja";
			return err;
		}
		if(false == RegXClass.checkPhone(phone)) {
			err = "Loše unesen broj telefona";
			return err;
		}
		if(false == RegXClass.checkEmail(email)) {
			err = "Loše unesen email";
			return err;
		}
		if(false == RegXClass.checkAdress(adressWork)) {
			err = "Loše unesena adresa kancelarije";
			return err;
		}
		if(false == RegXClass.checkId(id)) {
			err = "Loše unesen lični broj";
			return err;
		}
		
		if(!sameId) {
			DataClass data = DataClass.getInstance();
			ArrayList<Professor> listProfessor = data.getProfessorListData();
			if(listProfessor != null) {
				for(Professor p : listProfessor) {
					if(id.equalsIgnoreCase(p.getIdNumber())) {
						return "Postoji vec taj lični broj u bazi!";
					}
				}
			}
		}	
			
		if(false == RegXClass.checkTitle(title)) {
			err = "Loše unesena titula";
			return err;
		}
		
		if(false == RegXClass.checkWorkYears(workYears)) {
			err = "Loše unesena godina staža";
			return err;
		}
		
		
		
		return err;
	}
	
	public boolean deleteProfessor(String idOfProfessor) {
		DataClass data = DataClass.getInstance();			
		
		ArrayList<Professor> listProfessor = data.getProfessorListData();
		int i = 0;	
		int professorRemoveNumber = -1;
		for(Professor p: listProfessor) {
			if(p.getIdNumber().equals(idOfProfessor)) {
				professorRemoveNumber = i;
			}
			i++;
		}
		
		if(professorRemoveNumber != -1) {
			listProfessor.remove(professorRemoveNumber);
		}
		
		data.setProfessorListData(listProfessor);
		
		return true;
	}
	
	public Professor findProfessorById(String id) {
		DataClass data = DataClass.getInstance();
		
		ArrayList<Professor> listProfessor = data.getProfessorListData();
		for(Professor p: listProfessor) {
			if(p.getIdNumber().equals(id)) {
				return p;
			}
		}
		return null;
		
	}
	
	public String editProfessor(String idOfProfessor) {
		DataClass data = DataClass.getInstance();
		
		if(!err.equals("Sve je dobro!")) {
			return err;
		}
			
		ArrayList<Professor> listProfessor = data.getProfessorListData();
		for(Professor p: listProfessor) {
			if(p.getIdNumber().equals(idOfProfessor)) {
				
				p.setName(this.name);
				p.setLastname(this.lastname);
				p.setDateOfBirth(this.date);
				p.setAdressHome(this.adressHome);
				p.setPhone(this.phone);
				p.setEmail(this.email);
				p.setAdressWork(this.adressWork);
				p.setIdNumber(this.id);
				p.setTitle(this.title);
				p.setWorkYears(this.workYears);
				
		
			}
		}
		data.setProfessorListData(listProfessor);
		
		return err;
		
	}
	
	public boolean addTeachingSubject(String idOfProf, String subCode) {
		ArrayList<Professor> profList = DataClass.getInstance().getProfessorListData();
		ArrayList<Subject> subList = DataClass.getInstance().getSubjectListData();
		
		for(Professor p: profList) {
			if(p.getIdNumber().equals(idOfProf)) {
				p.getListSubjects().add(subCode);
			}
		}
		
		for(Subject s: subList) {
			if(s.getSubjectCode().equals(subCode)) {
				s.setSubjectProfessor(idOfProf);
			}
		}
		
		DataClass.getInstance().setProfessorListData(profList);
		DataClass.getInstance().setSubjectListData(subList);
		
		return true;
	}
	
	public String getErr() {
		return this.err;
	}
	
	public boolean deleteTeachingSubject(String idOfProf, String subCode) {
		ArrayList<Professor> profList = DataClass.getInstance().getProfessorListData();
		ArrayList<Subject> subList = DataClass.getInstance().getSubjectListData();
		
		//deleting subjec from prof
		int numberProfessor = -1;
		int numberSubject = -1;
		int i = 0;
		int j = 0;
		for(Professor p: profList) {
			if(p.getIdNumber().equals(idOfProf)){
				numberProfessor = i;
				for(String subId: p.getListSubjects()) {
					if(subId.equals(subCode)) {
						numberSubject = j;
					}
					j++;
				}
			}
			i++;
		}
		if(numberProfessor != -1 && numberSubject != -1) {
			profList.get(numberProfessor).getListSubjects().remove(numberSubject);
		}
		
		//deleting professor from subject
		for(Subject s: subList) {
			if(s.getSubjectCode().equals(subCode)){
				s.setSubjectProfessor("");
			}
		}
		
		
		DataClass.getInstance().setProfessorListData(profList);
		DataClass.getInstance().setSubjectListData(subList);
		
		return true;
	
	}
}
