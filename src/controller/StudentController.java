package controller;

import java.util.ArrayList;
import main.DataClass;
import model.Adress;
import model.Date;
import model.Student;

public class StudentController {
	
	private String name;
	private String lastname;
	private Date date;
	private Adress adress;
	private String phone;
	private String index;
	private String email;
	private int year;
	private int yearOfStudy;
	private int status;
	
	private String err;
	
	
	public StudentController(String name, String lastname, String date, String adress,  String phone, String email, String index,
			String year, int yearOfStudy, int status) {
		this.err = checkData(name,lastname,date,adress,phone,email,index,year);
		if(err.equals("Sve je dobro!")) {
			this.name = name;
			this.lastname = lastname;
			this.date = new Date(date);
			this.adress = new Adress(adress);
			this.phone = phone;
			this.index = index;
			this.year = Integer.parseInt(year);
			this.yearOfStudy = yearOfStudy;
			this.status = status;
			this.email = email;
		}
		
	}

	public StudentController() {};

	public String addStudentToData() {
		DataClass data = DataClass.getInstance();
		
		if(!err.equals("Sve je dobro!")) {
			return err;
		}
			
		
		ArrayList<Student> listStudent = data.getStudentListData();
		listStudent.add(new Student(this.lastname, this.name, this.date, this.adress, this.phone, this.email, this.index, this.year, this.yearOfStudy+1, this.status));
		data.setStudentListData(listStudent);
		
		return err;
	}
	
	
	public String checkData(String name, String lastname, String date, String adress, String phone, String email, String index, String year) {
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
		if(false == RegXClass.checkAdress(adress)) {
			err = "Loše unesena adresa";
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
		if(false == RegXClass.checkIndex(index)) {
			err = "Loše unesen index";
			return err;
		}
		
		DataClass data = DataClass.getInstance();
		ArrayList<Student> listStudent = data.getStudentListData();
		if(listStudent != null) {
			for(Student s : listStudent) {
				if(index.equalsIgnoreCase(s.getIndex())) {
					return "Indeks je zauzet!";
				}
			}
		}
		
		if(false == RegXClass.checkYear(year)) {
			err = "Loše unesena godina upisa";
			return err;
		}
		
		
		
		return err;
	}
	
	public boolean deleteStudent(String indexOfStudent) {		
		DataClass data = DataClass.getInstance();			
		
		ArrayList<Student> listStudent = data.getStudentListData();
		int i = 0;
		int studentRemoveNumber = -1;
		for(Student s: listStudent) {
			if(s.getIndex().equals(indexOfStudent)) {
				studentRemoveNumber = i;
			}
			i++;
		}
		
		if(studentRemoveNumber != -1) {
			listStudent.remove(studentRemoveNumber);
		}
		
		data.setStudentListData(listStudent);
		return true;
	}
	
	public String getErr() {
		return this.err;
	}
}
