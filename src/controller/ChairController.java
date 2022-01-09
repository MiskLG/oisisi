package controller;

import java.util.ArrayList;

import main.DataClass;
import model.Chair;

public class ChairController {
	
	private String chairCode;
	private String title;
	private String head;
	
	private String err;
	
	
	public ChairController() {}
	
	public ChairController(String chCode, String title, String head) {
		this.err = checkData(chCode, title);
		if(err.equals("Sve je dobro!")) {
			this.chairCode = chCode;
			this.title = title;
			this.head = head;
		}
	}
	
	public String checkData(String chCode, String title) {
		err = "Sve je dobro!";
		
		if(false == RegXClass.checkChairCode(chCode)) {
			err = "Loše unesen kod";
			return err;
		}
		
		DataClass data = DataClass.getInstance();
		ArrayList<Chair> listChair = data.getChairListData();
		if(listChair != null) {
			for(Chair c: listChair) {
				if(chCode.equalsIgnoreCase(c.getChairCode())) {
					return "Šifra predmeta je zauzeta!";
				}
			}
		}
		
		if(false == RegXClass.checkChairTitle(title)) {
			err = "Loše unesen naziv";
			return err;
		}
		
		return err;
	}
	
	public String addChairToData() {
		DataClass data = DataClass.getInstance();
		
		if(!err.equals("Sve je dobro!")) {
			return err;
		}
		
		ArrayList<Chair> listChair = data.getChairListData();
		listChair.add(new Chair(this.chairCode, this.title, this.head));
		data.setChairListData(listChair);
		
		return err;
	}
	
	public boolean deleteChair(String chCode) {
		ArrayList<Chair> chairs = DataClass.getInstance().getChairListData();
		
		int i = 0;
		int chairRemoveNumber = -1;
		for(Chair c: chairs) {
			if(c.getChairCode().equals(chCode)) {
				chairRemoveNumber = i;
			}
			i++;
		}
		
		if(chairRemoveNumber != -1) {
			chairs.remove(chairRemoveNumber);
		}
		
		DataClass.getInstance().setChairListData(chairs);
		return true;
	}
	
	public void setHead(String chCode, String profId) {
		ArrayList<Chair> chairs = DataClass.getInstance().getChairListData();
		
		//brisanje ukoliko vec postoji kao sef
		for(Chair c: chairs) {
			if(c.getHead().equals(profId)) {
				c.setHead("Nema šefa");
				break;
			}
		}
		
		for(Chair c: chairs) {
			if(c.getChairCode().equals(chCode)) {
				c.setHead(profId);
				break;
			}
		}
		
		
		
		
	}
}
