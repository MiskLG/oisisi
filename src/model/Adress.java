package model;

public class Adress {
	
		private String 		street;
		private String		number;
		private String 		city;
		private String		country;
		
		
		// constructor with fields
		public Adress(String street, String number, String city, String country) {
			this.street = street;
			this.number = number;
			this.city = city;
			this.country = country;
		}
		
	
		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}




		// toString method for serialization 
		// splitter is being used as text file database splitter
		@Override
		public String toString() {
			String splitter = "*\\";
			return street + splitter + number + splitter + city + splitter + country;
		}
		
		
		

}
