package model;

import java.util.ArrayList;

public class Chair {
	
	private String chairCode;
	private String title;
	private String head;
	
	private ArrayList<String> professorList = new ArrayList<String>();
	

	public Chair(String chairCode, String title, String head) {
		this.chairCode = chairCode;
		this.title = title;
		this.head = head;
		
	}


	public String getChairCode() {
		return chairCode;
	}

	public void setChairCode(String chairCode) {
		this.chairCode = chairCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}


	public ArrayList<String> getProfessorList() {
		return professorList;
	}


	public void setProfessorList(ArrayList<String> professorList) {
		this.professorList = professorList;
	}


	@Override
	public String toString() {
		String splitter = "*/";
		return chairCode + splitter + title + splitter + head;
		
	}
}
