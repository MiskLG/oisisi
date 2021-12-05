package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Date;

public class RegXClass {
	
	static boolean checkName(String name) {
		
		Pattern pattern = Pattern.compile("([A-ZŽĐŠĆČ]{1}[a-zžđšćč]*)(([ ]|[-])[A-ZŠĐŽČĆ]{1}[a-zšđžčć]*)*",Pattern.UNICODE_CHARACTER_CLASS);	
		Matcher matcher = pattern.matcher(name);

		return matcher.matches();

	}
	
	static boolean checkLastname(String lastname) {
		// same as the name for now
		
		return checkName(lastname);
	}
	
	static boolean checkDate(String date) {
		
		// a big pattern to check kinda valid dates, still needs to go through a check for leap year	

		//Pattern pattern = Pattern.compile("((((([3][0-1])|(([0]?[1-9])|([1-2][0-9])))[.]([13578]|([1][0,2])))|((([3][0])|(([0]?[1-9])|([1-2][0-9])))[.](([0]?[468])|([1][1])))) | ((([0]?[1-9])|([1-2][0-9]))[.][0]?[2]{1}))[.]((19[3-9][0-9])|(20[0-2][0-9]))");	
		Pattern pattern = Pattern.compile("[0-9]{2}[.][0-9]{2}[.][0-9]{4}[.]");
		Matcher matcher = pattern.matcher(date);
		
		if(true ==  matcher.matches()) {
			Date d = new Date(date);
			if(d.getDay() != 0) {
				return true;
			}
		}	
		return false;
	}
	
	static boolean checkAdress(String adress) {
		
		Pattern pattern = Pattern.compile("(([A-ZŽĐŠĆČ][a-zžđšćč]*)([ ][A-ZŠĐŽČĆ][a-zšđžčć]*)*)[ ]?[,][ ]?[1-9]+[0-9]*[a-z]*[ ]?[,][ ]?(([A-ZŽĐŠĆČ][a-zžđšćč]*)([ ][A-ZŠĐŽČĆ][a-zšđžčć]*)*)[ ]?[,][ ]?(([A-ZŽĐŠĆČ][a-zžđšćč]*)([ ][A-ZŠĐŽČĆ][a-zšđžčć]*)*)",Pattern.UNICODE_CHARACTER_CLASS);	
		Matcher matcher = pattern.matcher(adress);
		
		return matcher.matches();
	}
	
	static boolean checkIndex(String index) {
		
		Pattern pattern = Pattern.compile("([a-zšđžćčA-ZŠĐŽČĆ0-9]{2}-[0-9]{1,3}-19[6-9][0-9])|([a-zA-Z0-9]{2}-[0-9]{1,3}-20[0-2][0-9])",Pattern.UNICODE_CHARACTER_CLASS);	
		Matcher matcher = pattern.matcher(index);

		return matcher.matches();
	}
	
	
	static boolean checkPhone(String phone) {
		
		Pattern pattern = Pattern.compile("(06[0-9]{7,8})|([+][0-9]{11,12})|(00[0-9]{11,12})");		
		Matcher matcher = pattern.matcher(phone);
		
		return matcher.matches();
	}
	
	static boolean checkEmail(String email) {
		
		Pattern pattern = Pattern.compile("[a-zA-Z0-9,.+]+@([a-zA-Z0-9]+[.])+[a-zA-Z0-9]");
		Matcher matcher = pattern.matcher(email);
		
		return matcher.matches();
	}
	
	static boolean checkYear(String year) {
		
		Pattern pattern = Pattern.compile("(19[6-9][0-9])|20[0-2][0-9]");	
		Matcher matcher = pattern.matcher(year);
		
		return matcher.matches();
	}
	static boolean checkId(String id) {
		
		Pattern pattern = Pattern.compile("[0-9]{9}");	
		Matcher matcher = pattern.matcher(id);
		
		return matcher.matches();
	}
	
	static boolean checkTitle(String title) {
		// for now same as the name, dont know any limiters
		return checkName(title);
	}
	
	static boolean checkWorkYears(String years) {
		
		Pattern pattern = Pattern.compile("[0-9]{2}");	
		Matcher matcher = pattern.matcher(years);
		
		return matcher.matches();
	}
	
}
