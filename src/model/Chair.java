package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Chair {
	private String professorListFile = System.getProperty("user.dir") + File.separator + "database" + File.separator + "linkProfessorChair.txt";
	
	private String chairCode;
	private String title;
	private String head;
	
	private List<String> professorList;
	

	public Chair(String chairCode, String title, String head) {
		this.chairCode = chairCode;
		this.title = title;
		this.head = head;
		
		loadProfessorList();
	}


	private void loadProfessorList() {
		try {				
			File file = new File(professorListFile);
			Scanner reader = new Scanner(file);
			
			while(reader.hasNextLine()) {
				String dataLine = reader.nextLine();
				String[] data = dataLine.split("*\\");
				
				if(data[1].equals(getChairCode())) {
					professorList.add(data[0]);
				}
			}
			
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("ERROR");
			e.printStackTrace();
		}
	}

	public void writeListaProfesora() {
		 try {
		      FileWriter writer = new FileWriter(professorListFile, true);        
		      String splitter = "*\\";
		      
		      for(String p : professorList) {
		    	  String toWrite = p + splitter + getChairCode();
		    	  writer.write(toWrite);
		      }
		      writer.close();
		      
		    } catch (IOException e) {
		      System.out.println("ERROR.");
		      e.printStackTrace();
		    }
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

	public List<String> getProfessorList() {
		return professorList;
	}

	public void setProfessorList(List<String> professorList) {
		this.professorList = professorList;
	}


	@Override
	public String toString() {
		String splitter = "*\\";
		return chairCode + splitter + title + splitter + head;
		
	}
}
