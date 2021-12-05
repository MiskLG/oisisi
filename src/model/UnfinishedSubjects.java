package model;

// class that has fields only for subject code and student index who hasn't finished that subject
public class UnfinishedSubjects {
	private String index;
	private String subjectCode;
	
	public UnfinishedSubjects(String index, String subjectCode) {
		this.index = index;
		this.subjectCode = subjectCode;
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

	
	@Override
	public String toString() {
		String splitter = "*/";
		return subjectCode + splitter + index;
	}
	
}
