package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import controller.RegXClass;
import controller.StudentController;
import controller.SubjectController;

public class AddSubjectsPanel extends JDialog{

	private boolean changesMade = false;
	private boolean[] validData = {false, false, false, false};
	
	public AddSubjectsPanel(Point location, Dimension size) {
		this.setModal(true);
		this.setResizable(false);
		
		double widthRatio = 40./100;
		double heightRatio = 95./100;
		
		Double minX = 1200 * widthRatio;
		Double minY = 600 * heightRatio;
	
		Double locationX = location.x + size.getWidth() / 2 - minX /2 ;
		Double locationY = location.y + size.getHeight() / 2 - minY / 2;
		
//		Double sizeX = minX;
//		Double sizeY = minY;
		
		setLocation(locationX.intValue() , locationY.intValue() );
		setSize(380, 450);
		
		this.setTitle("Dodavanje predmeta");
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JPanel fieldsPanel = new JPanel();
		JPanel buttonsPanel = new JPanel();
		
		Dimension d = new Dimension(340, 410);
		fieldsPanel.setLayout(new FlowLayout());//Ili mozda 6 ako bude i dodavanje profesora tu 
		fieldsPanel.setPreferredSize(d);
		
		Dimension d2 = new Dimension(130, 35);
		JLabel subjectCodeLabel = new JLabel("Šifra predmeta*:");
		JTextField subjectCodeField = new JTextField();
		subjectCodeLabel.setPreferredSize(d2);
		subjectCodeField.setPreferredSize(d2);
		
		JLabel titleLabel = new JLabel("Naziv predmeta*:");
		JTextField titleField = new JTextField();
		titleLabel.setPreferredSize(d2);
		titleField.setPreferredSize(d2);
		
		JLabel semesterLabel = new JLabel("Semestar*:");
		String  semesterChoices[] = {"Zimski", "Letnji"};     
		JComboBox<String> semesterField = new JComboBox<String>(semesterChoices);
		semesterLabel.setPreferredSize(d2);
		semesterField.setPreferredSize(d2);
		
		JLabel yearOfStudyLabel = new JLabel("Godina slušanja*:");
		String  yearChoices[] = {"I (prva)","II (druga)","III (treća)","IV (četvrta)", "V (peta)", "VI (šesta)"};     
		JComboBox<String> yearOfStudyField = new JComboBox<String>(yearChoices);
		yearOfStudyLabel.setPreferredSize(d2);
		yearOfStudyField.setPreferredSize(d2);
		
		JLabel numberECTSLabel = new JLabel("Broj ESPB*:");
		JTextField numberECTSField = new JTextField();
		numberECTSLabel.setPreferredSize(d2);
		numberECTSField.setPreferredSize(d2);
		
		fieldsPanel.add(subjectCodeLabel);
		fieldsPanel.add(subjectCodeField);
		fieldsPanel.add(titleLabel);
		fieldsPanel.add(titleField);
		fieldsPanel.add(semesterLabel);
		fieldsPanel.add(semesterField);
		fieldsPanel.add(yearOfStudyLabel);
		fieldsPanel.add(yearOfStudyField);
		fieldsPanel.add(numberECTSLabel);
		fieldsPanel.add(numberECTSField);
		
		mainPanel.setBorder(new EmptyBorder(20,30,20,30));
		((BorderLayout)mainPanel.getLayout()).setVgap(20);
		
		((FlowLayout)fieldsPanel.getLayout()).setVgap(25);
		((FlowLayout)fieldsPanel.getLayout()).setHgap(20);
		
		buttonsPanel.setLayout(new FlowLayout());
		
		JButton acceptButton = new JButton("Potvrdi");
		JButton cancelButton = new JButton("Odustani");
		
		acceptButton.setPreferredSize(new Dimension(110,30));
		cancelButton.setPreferredSize(new Dimension(110,30));
		
		JPanel spacer = new JPanel();
		spacer.setPreferredSize(new Dimension(10,30));
		
		buttonsPanel.add(acceptButton);
		buttonsPanel.add(spacer);
		buttonsPanel.add(cancelButton);
		
		acceptButton.setEnabled(false);
		
		cancelButton.addActionListener( e -> { this.dispose(); });
		
		//OVDE IDU PROVERE
		subjectCodeField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(true == RegXClass.checkSubjectCode(subjectCodeField.getText() + e.getKeyChar())) {
					validData[0] = true;
					subjectCodeField.setBorder(new MatteBorder(1,1,1,1,Color.GRAY));
				}
				else {
					validData[0] = false;
					subjectCodeField.setBorder(new MatteBorder(1,1,1,1,Color.red));
				}
				if(validData[0] && validData[1] && validData[2]) {
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
		titleField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(true == RegXClass.checkSubjectTitle(titleField.getText() + e.getKeyChar())) {
					validData[1] = true;
					titleField.setBorder(new MatteBorder(1,1,1,1,Color.GRAY));
				}
				else {
					validData[1] = false;
					titleField.setBorder(new MatteBorder(1,1,1,1,Color.red));
				}
				if(validData[0] && validData[1] && validData[2]) {
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
		numberECTSField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(true == RegXClass.checkNumberECTS(numberECTSField.getText() + e.getKeyChar())) {
					validData[2] = true;
					numberECTSField.setBorder(new MatteBorder(1,1,1,1,Color.GRAY));
				}
				else {
					validData[2] = false;
					numberECTSField.setBorder(new MatteBorder(1,1,1,1,Color.red));
				}
				if(validData[0] && validData[1] && validData[2]) {
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
		
		acceptButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SubjectController con = new SubjectController(subjectCodeField.getText(), titleField.getText(), semesterField.getSelectedIndex(),
				yearOfStudyField.getSelectedIndex(), numberECTSField.getText());
//				StudentController con = new StudentController(nameField.getText(), lastnameField.getText(), dateobField.getText(), adressField.getText(), phoneField.getText(),
//				emailField.getText(), indexField.getText(), enrolmentField.getText(), yearOfStudyField.getSelectedIndex(), statusField.getSelectedIndex());
				String err = con.addSubjectToData();
				if(err.equals("Sve je dobro!")) {
					changesMade = true;
					dispose();
				}
				else {
					String[] options = {"OK"};
	    			int result = JOptionPane.showOptionDialog((getRootPane()), 
		    				"Šifra predmeta je već zauzeta!", "GREŠKA!",
				            JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, options,"");
				}
			}
			
		});
		
		mainPanel.add(fieldsPanel, BorderLayout.CENTER);
		mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
		this.setContentPane(mainPanel);
		
		this.setVisible(true);
	}
	
	public boolean getChangesMade() {
		return changesMade;
	}
	
}
