package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegXClass {
	
	static boolean checkName(String name) {
		
		Pattern pattern = Pattern.compile("([A-ZŽĐŠĆČ][a-zžđšćč]*)(([ ]|[-])[A-ZŠĐŽČĆ][a-zšđžčć]*)*",Pattern.UNICODE_CHARACTER_CLASS);	
		Matcher matcher = pattern.matcher(name);

		return matcher.find();

	}
	
	static boolean checkLastname(String lastname) {
		// same as the name for now
		
		return checkName(lastname);
	}
	
	static boolean checkDate(String date) {
		
		// a big pattern to check kinda valid dates, still needs to go through a check for leap year
		Pattern pattern = Pattern.compile("((((([3][0-1])|(([0]?[1-9])|([1-2][0-9])))[.](([13578])|([1][0,2])))|((([3][0])|(([0]?[1-9])|([1-2][0-9])))[.](([0]?[468])|([1][1])))) | ((([0]?[1-9])|([1-2][0-9]))[.][0]?[2]))[.](19[3-9][0-9])|20[0-2][0-9]");	
		Matcher matcher = pattern.matcher(date);

		return matcher.find();
	}
	
	static boolean checkAdress(String adress) {
		
		Pattern pattern = Pattern.compile("(([A-ZŽĐŠĆČ][a-zžđšćč]*)([ ][A-ZŠĐŽČĆ][a-zšđžčć]*)*)[ ]?[,][ ]?[1-9]+[0-9]*[a-z]*[ ]?[,][ ]?(([A-ZŽĐŠĆČ][a-zžđšćč]*)([ ][A-ZŠĐŽČĆ][a-zšđžčć]*)*)[ ]?[,][ ]?(([A-ZŽĐŠĆČ][a-zžđšćč]*)([ ][A-ZŠĐŽČĆ][a-zšđžčć]*)*)",Pattern.UNICODE_CHARACTER_CLASS);	
		Matcher matcher = pattern.matcher(adress);
		
		return true;
	}
	
	static boolean checkIndex(String index) {
		
		Pattern pattern = Pattern.compile("([a-zšđžćčA-ZŠĐŽČĆ0-9]{2}-[0-9]{1,3}-19[6-9][0-9])|([a-zA-Z0-9]{2}-[0-9]{1,3}-20[0-2][0-9])",Pattern.UNICODE_CHARACTER_CLASS);	
		Matcher matcher = pattern.matcher(index);

		return matcher.find();
	}
	
	
	static boolean checkPhone(String phone) {
		
		Pattern pattern = Pattern.compile("(06[0-9]{7,8})|([+][0-9]{11,12})|(00[0-9]{11,12})");		
		Matcher matcher = pattern.matcher(phone);
		
		return matcher.find();
	}
	
	static boolean checkEmail(String email) {
		
		Pattern pattern = Pattern.compile("[a-zA-Z0-9,.+]+@([a-zA-Z0-9]+[.])+[a-zA-Z0-9]");
		Matcher matcher = pattern.matcher(email);
		
		return matcher.find();
	}
	
	static boolean checkYear(String year) {
		
		Pattern pattern = Pattern.compile("(19[6-9][0-9])|20[0-2][0-9]");	
		Matcher matcher = pattern.matcher(year);
		
		return matcher.find();
	}
}
