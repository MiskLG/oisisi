package gui;

import java.awt.Component;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.ObjectInputFilter.Status;

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
import controller.StudentController;
import main.DataClass;
import model.Professor;
import model.Student;
import model.Subject;

public class EditStudentPanel extends JTabbedPane{
	
	private JPanel informations;
	private JPanel passedExams; 
	private JPanel unpassedExams;
	
	private boolean changesMade = false;
	private boolean[] validData = {true, true, true, true, true, true, true, true};
	
	private JButton acceptButton;
	
	private int currentYear;
	
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
		nameField.setText(DataClass.getInstance().getSelectedStudent(AbstractTableModelStudents.getSelectedRowIndex()).getName());

		
		JLabel lastnameLabel = new JLabel("Prezime*:");
		JTextField lastnameField = new JTextField();
		lastnameField.setText(DataClass.getInstance().getSelectedStudent(AbstractTableModelStudents.getSelectedRowIndex()).getLastname());
		
		
		JLabel dateobLabel = new JLabel("Datum roÄ‘enja*:");
		JTextField dateobField = new JTextField();
		dateobField.setText(DataClass.getInstance().getSelectedStudent(AbstractTableModelStudents.getSelectedRowIndex()).getDateOfBirth().toString());
		
		
		JLabel adressLabel = new JLabel("Adresa stanovanja*:");
		JTextField adressField = new JTextField();
		adressField.setText(DataClass.getInstance().getSelectedStudent(AbstractTableModelStudents.getSelectedRowIndex()).getAdress().toString());
		
		
		JLabel phoneLabel = new JLabel("Broj telefona*:");
		JTextField phoneField = new JTextField();
		phoneField.setText(DataClass.getInstance().getSelectedStudent(AbstractTableModelStudents.getSelectedRowIndex()).getPhone());
		
		
		
		JLabel emailLabel = new JLabel("E-mail adresa*:");
		JTextField emailField = new JTextField();
		emailField.setText(DataClass.getInstance().getSelectedStudent(AbstractTableModelStudents.getSelectedRowIndex()).getEmail());
		
		
		JLabel indexLabel = new JLabel("Broj indeksa*:");
		JTextField indexField = new JTextField();
		indexField.setText(DataClass.getInstance().getSelectedStudent(AbstractTableModelStudents.getSelectedRowIndex()).getIndex());
		
		
		JLabel enrolmentLabel = new JLabel("Godina upisa*:");
		JTextField enrolmentField = new JTextField();
		enrolmentField.setText(String.valueOf(DataClass.getInstance().getSelectedStudent(AbstractTableModelStudents.getSelectedRowIndex()).getEnrolmentYear()));
		
		
		JLabel yearOfStudyLabel = new JLabel("Trenutna godina studija*:");
		String  yearChoices[] = {"I (prva)","II (druga)","III (treÄ‡a)","IV (Ä�etvrta)", "V (peta)", "VI (Å¡esta)"};     
		JComboBox<String> yearOfStudyField = new JComboBox<String>(yearChoices);
		//yearOfStudyField.setText(DataClass.getInstance().getSelectedStudent(AbstractTableModelStudents.getSelectedRowIndex()).getName());
		//if(DataClass.getInstance().getSelectedStudent(AbstractTableModelStudents.getSelectedRowIndex()).getYearOfStudy() == )
		currentYear = DataClass.getInstance().getSelectedStudent(AbstractTableModelStudents.getSelectedRowIndex()).getYearOfStudy();
		if(currentYear == 1) {
			yearOfStudyField.setSelectedItem("I (prva)");
		}
		else if(currentYear == 2) {
			yearOfStudyField.setSelectedItem("II (druga)");
		}
		else if(currentYear == 3) {
			yearOfStudyField.setSelectedItem("III (treca)");
		}
		else if(currentYear == 4) {
			yearOfStudyField.setSelectedItem("IV (cetvrta)");
		}
		else if(currentYear == 5) {
			yearOfStudyField.setSelectedItem("V (peta)");
		}
		else {
			yearOfStudyField.setSelectedItem("VI (sesta)");
		}
		
		
		JLabel statusLabel = new JLabel("NaÄ�in finansiranja*:");
		String  statusChoices[] = {"BudÅ¾et", "SamofinansirajuÄ‡e"};     
		JComboBox<String> statusField = new JComboBox<String>(statusChoices);
		// dodati status naknadno
		
		
		
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
		
		acceptButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StudentController con = new StudentController(nameField.getText(), lastnameField.getText(), dateobField.getText(), adressField.getText(), phoneField.getText(),
						emailField.getText(), indexField.getText(), enrolmentField.getText(), yearOfStudyField.getSelectedIndex(), statusField.getSelectedIndex());
				String err = con.addStudentToData();
				if(err.equals("Sve je dobro!")) {
					changesMade = true;
				}
				else {
					errLabel.setText(err);
				}
				
			}
			
		});
		
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
	
	
	
	
}
