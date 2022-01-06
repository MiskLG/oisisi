package model;

public class Date {
	private int day;
	private int month;
	private int year;
	
	
	// constructor with fields
	public Date(int day, int month, int year) {
		if(false == checkDate(day,month,year)) {
			System.out.println("ERROR");
			// current implication of wrong date format TODO
			day = 0;
			month = 0;
			year = 0;
		}
		else {
			this.day = day;
			this.month = month;
			this.year = year;
		}	
	}
	
	public Date(String dateString) {
		String[] data = dateString.split("[.]");
		if( false == checkDate(Integer.parseInt(data[0]),Integer.parseInt(data[1]),Integer.parseInt(data[2])) ) {
			System.out.println("ERROR");
			this.day = 0;
			this.month = 0;
			this.year = 0;
		}
		else {
			this.day = Integer.parseInt(data[0]);
			this.month = Integer.parseInt(data[1]);
			this.year = Integer.parseInt(data[2]);
		}

	}
	
	
	// check date format
	public static boolean checkDate(int day, int month, int year) {
		// some checks
		if(month < 1 || month > 12) {
			return false;
		}
		if(day < 1 || day > 31) {
			return false;
		}
		switch(month) {
			case 1: case 3: case 5: case 7: case 8: case 10: case 12:
				break;
			case 2:
				if(day > 29) {
					if(day == 29) {
						if(year % 4 != 0) {
							return false;
						}
						else if(year % 100 == 0){
							return false;
						}
						else if(year % 400 != 0){
							return false;
						}
					break;
					}
					return false;
				}
				break;
			case 4: case 6: case 9: case 11:
				if(day > 30)
					return false;
				break;
			default:
				return false;
		
		}
		if(year < 1920)
			return false;
		
		
		return true;
	}
	
	

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	// toString method
	@Override
	public String toString() {
		return day + "." + month + "." + year + "." ;
	}
	
}
