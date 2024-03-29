package model;

public class Grade {
	
	private String 	index;
	private String 	subjectCode;
	private int 	grade;
	private Date 	gradingDate;
	
	
	public Grade(String index, String subjectCode, int grade, Date gradingDate) {
		this.index = index;
		this.subjectCode = subjectCode;
		this.grade = grade;
		this.gradingDate = gradingDate;
	}
		
	
	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public Date getGradingDate() {
		return gradingDate;
	}

	public void setGradingDate(Date gradingDate) {
		this.gradingDate = gradingDate;
	}

	
	// toString method for writing to database
	@Override
	public String toString() {
		String splitter = "*/";
		return index + splitter + subjectCode + splitter + grade + splitter + gradingDate;
	}
}
