package model;

public class Datum {
	int dan;
	int mesec;
	int godina;
	
	
	// konstruktor sa parametrima
	public Datum(int dan, int mesec, int godina) {
		if(false == proveraDatuma(dan,mesec,godina)) {
			System.out.println("ERROR");
			// trenutna implikacija errora bice poboljsano i ispravljeno kasnije TODO
			dan = 0;
			mesec = 0;
			godina = 0;
		}
		this.dan = dan;
		this.mesec = mesec;
		this.godina = godina;
	}

	// geteri i seteri
	public int getDan() {
		return dan;
	}

	public void setDan(int dan) {
		this.dan = dan;
	}

	public int getMesec() {
		return mesec;
	}

	public void setMesec(int mesec) {
		this.mesec = mesec;
	}

	public int getGodina() {
		return godina;
	}

	public void setGodina(int godina) {
		this.godina = godina;
	}
	
	// promeva datuma
	private boolean proveraDatuma(int dan, int mesec, int godina) {
		// neke provera za korenktan datum
		if(mesec < 1 || mesec > 12) {
			return false;
		}
		if(dan < 1 || dan > 31) {
			return false;
		}
		switch(mesec) {
			case 1: case 3: case 5: case 7: case 8: case 10: case 12:
				break;
			case 2:
				if(dan > 28)
					return false;
				break;
			case 4: case 6: case 9: case 11:
				if(dan > 30)
					return false;
				break;
			default:
				return false;
		
		}
		if(godina < 1920)
			return false;
		return true;
	}
	
	
	
	
	// tostring metoda za pisanje u bazu
	@Override
	public String toString() {
		String splitter = "*\\";
		return dan + "." + mesec + "." + godina + "." + splitter;
	}
}
