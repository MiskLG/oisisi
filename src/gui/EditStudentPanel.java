package gui;

import java.awt.Component;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import controller.RegXClass;
import main.DataClass;
import model.Professor;
import model.Student;
import model.Subject;

public class EditStudentPanel extends JTabbedPane{
	
	private JPanel informations;
	private JPanel passedExams; 
	private JPanel unpassedExams;
	
	private boolean changesMade = false;
	private boolean[] validData = {false, false, false, false, false, false, false, false};
	
	private JButton acceptButton;
	
	public EditStudentPanel() {
		
		informations = new JPanel();
		passedExams = new JPanel();
		unpassedExams = new JPanel();
		
		
		add("Informacije", informations);
		add("Polozeni", passedExams);
		add("Nepolozeni", unpassedExams);
		
		acceptButton = new JButton("Potvrdi");
		acceptButton.setPreferredSize(new Dimension(140,30));
		
		JLabel errLabel = new JLabel();
		
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JPanel fieldsPanel = new JPanel();
		//JPanel buttonsPanel = new JPanel();
		
		fieldsPanel.setLayout(new GridLayout(10,2));
		
		JLabel nameLabel = new JLabel("Ime*:");
		JTextField nameField = new JTextField();		
		
		JLabel lastnameLabel = new JLabel("Prezime*:");
		JTextField lastnameField = new JTextField();
		
		
		JLabel dateobLabel = new JLabel("Datum roÄ‘enja*:");
		JTextField dateobField = new JTextField();
		
		
		JLabel adressLabel = new JLabel("Adresa stanovanja*:");
		JTextField adressField = new JTextField();
		
		
		JLabel phoneLabel = new JLabel("Broj telefona*:");
		JTextField phoneField = new JTextField();
		
		
		JLabel emailLabel = new JLabel("E-mail adresa*:");
		JTextField emailField = new JTextField();
		
		
		JLabel indexLabel = new JLabel("Broj indeksa*:");
		JTextField indexField = new JTextField();
		
		
		JLabel enrolmentLabel = new JLabel("Godina upisa*:");
		JTextField enrolmentField = new JTextField();
		
		
		JLabel yearOfStudyLabel = new JLabel("Trenutna godina studija*:");
		String  yearChoices[] = {"I (prva)","II (druga)","III (treÄ‡a)","IV (Ä�etvrta)", "V (peta)", "VI (Å¡esta)"};     
		JComboBox<String> yearOfStudyField = new JComboBox<String>(yearChoices);
		
		
		JLabel statusLabel = new JLabel("NaÄ�in finansiranja*:");
		String  statusChoices[] = {"BudÅ¾et", "SamofinansirajuÄ‡e"};     
		JComboBox<String> statusField = new JComboBox<String>(statusChoices);
		
		
		// adding stuff to their place
		fieldsPanel.add(nameLabel);
		fieldsPanel.add(nameField);
		fieldsPanel.add(lastnameLabel);
		fieldsPanel.add(lastnameField);
		fieldsPanel.add(dateobLabel);
		fieldsPanel.add(dateobField);
		fieldsPanel.add(adressLabel);
		fieldsPanel.add(adressField);
		fieldsPanel.add(phoneLabel);
		fieldsPanel.add(phoneField);
		fieldsPanel.add(emailLabel);
		fieldsPanel.add(emailField);
		fieldsPanel.add(indexLabel);
		fieldsPanel.add(indexField);
		fieldsPanel.add(enrolmentLabel);
		fieldsPanel.add(enrolmentField);
		fieldsPanel.add(yearOfStudyLabel);
		fieldsPanel.add(yearOfStudyField);
		fieldsPanel.add(statusLabel);
		fieldsPanel.add(statusField);
		
		mainPanel.setBorder(new EmptyBorder(20,30,20,30));
		((BorderLayout)mainPanel.getLayout()).setVgap(20);
		
		((GridLayout)fieldsPanel.getLayout()).setVgap(15);
		((GridLayout)fieldsPanel.getLayout()).setHgap(15);
		
		
		/*buttonsPanel.setLayout(new FlowLayout());
		
		JButton acceptButton = new JButton("Potvrdi");
		JButton cancelButton = new JButton("Odustani");
		
		acceptButton.setPreferredSize(new Dimension(140,30));
		cancelButton.setPreferredSize(new Dimension(140,30));
		
		JPanel spacer = new JPanel();
		spacer.setPreferredSize(new Dimension(15,30));
		
		buttonsPanel.add(acceptButton);
		buttonsPanel.add(spacer);
		buttonsPanel.add(cancelButton);
		
		acceptButton.setEnabled(false);
	
		cancelButton.addActionListener( e -> { this.dispose(); });
		
		FocusListener focusLostCheck = new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {				
			}

			@Override
			public void focusLost(FocusEvent e) {
				StudentController con = new StudentController(nameField.getText(), lastnameField.getText(), dateobField.getText(), adressField.getText(), phoneField.getText(),
						emailField.getText(), indexField.getText(), enrolmentField.getText(), yearOfStudyField.getSelectedIndex(), statusField.getSelectedIndex());
				String text = con.getErr();
				
				errLabel.setText(text);
				
				if(text.equalsIgnoreCase("Sve je dobro")) {
					acceptButton.setEnabled(true);
				}
				else {
					acceptButton.setEnabled(false);
				}
						
			}
			
		};*/
		
		
		// lost focus listeners to check if its time to enable the button
//		nameField.addFocusListener(focusLostCheck);
//		lastnameField.addFocusListener(focusLostCheck);
//		dateobField.addFocusListener(focusLostCheck);		
//		adressField.addFocusListener(focusLostCheck);
//		phoneField.addFocusListener(focusLostCheck);
//		emailField.addFocusListener(focusLostCheck);
//		indexField.addFocusListener(focusLostCheck);
//		enrolmentField.addFocusListener(focusLostCheck);
//		
//		acceptButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				StudentController con = new StudentController(nameField.getText(), lastnameField.getText(), dateobField.getText(), adressField.getText(), phoneField.getText(),
//				emailField.getText(), indexField.getText(), enrolmentField.getText(), yearOfStudyField.getSelectedIndex(), statusField.getSelectedIndex());
//				con.addStudentToData();
//				dispose();
//			}
//			
//		});
//		
//		
		nameField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(true == RegXClass.checkName(nameField.getText() + e.getKeyChar())) {
					validData[0] = true;
					nameField.setBorder(new MatteBorder(1,1,1,1,Color.GRAY));
				}
				else {
					validData[0] = false;
					nameField.setBorder(new MatteBorder(1,1,1,1,Color.red));
				}
				if(validData[0] && validData[1] && validData[2] && validData[3] && validData[4] && validData[5] && validData[6] && validData[7]) {
					acceptButton.setEnabled(true);
				}
				else {
					acceptButton.setEnabled(false);
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {		
			}

			@Override
			public void keyReleased(KeyEvent e) {	
			}
		});
		lastnameField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(true == RegXClass.checkLastname(lastnameField.getText() + e.getKeyChar())) {
					validData[1] = true;
					lastnameField.setBorder(new MatteBorder(1,1,1,1,Color.GRAY));
				}
				else {
					validData[1] = false;
					lastnameField.setBorder(new MatteBorder(1,1,1,1,Color.red));
				}
				if(validData[0] && validData[1] && validData[2] && validData[3] && validData[4] && validData[5] && validData[6] && validData[7]) {
					acceptButton.setEnabled(true);
				}
				else {
					acceptButton.setEnabled(false);
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
	});
	dateobField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(true == RegXClass.checkDate(dateobField.getText() + e.getKeyChar())) {
					validData[2] = true;
					dateobField.setBorder(new MatteBorder(1,1,1,1,Color.GRAY));
				}
				else {
					validData[2] = false;
					dateobField.setBorder(new MatteBorder(1,1,1,1,Color.red));
				}
				if(validData[0] && validData[1] && validData[2] && validData[3] && validData[4] && validData[5] && validData[6] && validData[7]) {
					acceptButton.setEnabled(true);
				}
				else {
					acceptButton.setEnabled(false);
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {	
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		adressField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(true == RegXClass.checkAdress(adressField.getText() + e.getKeyChar())) {
					validData[3] = true;
					adressField.setBorder(new MatteBorder(1,1,1,1,Color.GRAY));
				}
				else {
					validData[3] = false;
					adressField.setBorder(new MatteBorder(1,1,1,1,Color.red));
				}
				if(validData[0] && validData[1] && validData[2] && validData[3] && validData[4] && validData[5] && validData[6] && validData[7]) {
					acceptButton.setEnabled(true);
				}
				else {
					acceptButton.setEnabled(false);
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {	
			}

			@Override
			public void keyReleased(KeyEvent e) {	
			}
		});
		phoneField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(true == RegXClass.checkPhone(phoneField.getText() + e.getKeyChar())) {
					validData[4] = true;
					phoneField.setBorder(new MatteBorder(1,1,1,1,Color.GRAY));
				}
				else {
					validData[4] = false;
					phoneField.setBorder(new MatteBorder(1,1,1,1,Color.red));
				}
				if(validData[0] && validData[1] && validData[2] && validData[3] && validData[4] && validData[5] && validData[6] && validData[7]) {
					acceptButton.setEnabled(true);
				}
				else {
					acceptButton.setEnabled(false);
				}
			}
	
			@Override
			public void keyPressed(KeyEvent e) {
			}
	
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		emailField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(true == RegXClass.checkEmail(emailField.getText() + e.getKeyChar())) {
					validData[5] = true;
					emailField.setBorder(new MatteBorder(1,1,1,1,Color.GRAY));
				}
				else {
					validData[5] = false;
					emailField.setBorder(new MatteBorder(1,1,1,1,Color.red));
				}
				if(validData[0] && validData[1] && validData[2] && validData[3] && validData[4] && validData[5] && validData[6] && validData[7]) {
					acceptButton.setEnabled(true);
					
				}
				else {
					acceptButton.setEnabled(false);
				}
			}
	
			@Override
			public void keyPressed(KeyEvent e) {
			}
	
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		indexField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(true == RegXClass.checkIndex(indexField.getText() + e.getKeyChar())) {
					validData[6] = true;
					indexField.setBorder(new MatteBorder(1,1,1,1,Color.GRAY));
				}
				else {
					validData[6] = false;
					indexField.setBorder(new MatteBorder(1,1,1,1,Color.red));
				}
				if(validData[0] && validData[1] && validData[2] && validData[3] && validData[4] && validData[5] && validData[6] && validData[7]) {
					acceptButton.setEnabled(true);
				}
				else {
					acceptButton.setEnabled(false);
				}
			}
	
			@Override
			public void keyPressed(KeyEvent e) {		
			}
	
			@Override
			public void keyReleased(KeyEvent e) {			
			}
		});
		enrolmentField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(true == RegXClass.checkYear(enrolmentField.getText() + e.getKeyChar())) {
					validData[7] = true;
					enrolmentField.setBorder(new MatteBorder(1,1,1,1,Color.GRAY));
				}
				else {
					validData[7] = false;
					enrolmentField.setBorder(new MatteBorder(1,1,1,1,Color.red));
				}
				if(validData[0] && validData[1] && validData[2] && validData[3] && validData[4] && validData[5] && validData[6] && validData[7]) {
					acceptButton.setEnabled(true);
				}
				else {
					acceptButton.setEnabled(false);
				}	
			}
	
			@Override
			public void keyPressed(KeyEvent e) {
			}
	
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		
		informations.add(errLabel, BorderLayout.NORTH);
		informations.add(fieldsPanel, BorderLayout.CENTER);
		//mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
		//this.setContentPane(mainPanel);
		
		//updateTable();
	}

	
	public boolean isChangesMade() {
		return changesMade;
	}


	public void setChangesMade(boolean changesMade) {
		this.changesMade = changesMade;
	}


	public JButton getAcceptButton() {
		return acceptButton;
	}


	public void setAcceptButton(JButton acceptButton) {
		this.acceptButton = acceptButton;
	}


	public JPanel getInformations() {
		return informations;
	}

	public void setInformations(JPanel informations) {
		this.informations = informations;
	}
	
	/*public void updateTable() {
		DataClass dc = DataClass.getInstance();
		
		//ArrayList<Student> list1 = dc.getStudentListData();
		/*ArrayList<Professor> list2 = dc.getProfessorListData();
		ArrayList<Subject> list3 = dc.getSubjectListData();
		
		
		while(studentsTable.getModel().getRowCount() > 0) {
			((DefaultTableModel) studentsTable.getModel()).removeRow(0);
		}
		while(professorsTable.getModel().getRowCount() > 0) {
			((DefaultTableModel) professorsTable.getModel()).removeRow(0);
		}
		while(subjectsTable.getModel().getRowCount() > 0) {
			((DefaultTableModel) subjectsTable.getModel()).removeRow(0);
		}
		
		
		for(Student st: list1) {
			String[] studentData = {st.getIndex(),st.getName(),st.getLastname(),Integer.toString(st.getYearOfStudy()), st.getStatus(), Double.toString(st.getAverageGrade())} ;
			((DefaultTableModel) studentsTable.getModel()).addRow(studentData);
		}
		
		for(Professor pr: list2) {
			String[] professorData = {pr.getName(), pr.getLastname(), pr.getTitle(), pr.getEmail()};
			((DefaultTableModel) professorsTable.getModel()).addRow(professorData);
		}
		
		for(Subject sb: list3) {
			String[] subjectData = {sb.getSubjectCode(),sb.getTitle(),Integer.toString(sb.getNumberECTS()),Integer.toString(sb.getYearOfStudy()),sb.getSemester(),};
			((DefaultTableModel) subjectsTable.getModel()).addRow(subjectData);
		}	
		
		
		
	}*/
	
	
}
