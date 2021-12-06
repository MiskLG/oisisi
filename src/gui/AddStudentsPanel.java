package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.StudentController;

public class AddStudentsPanel extends JDialog {

	public AddStudentsPanel(Point location, Dimension size) {
		this.setModal(true);
		this.setResizable(false);
		
		double widthRatio = 40./100;
		double heightRatio = 95./100;
		
		Double minX = 1200 * widthRatio;
		Double minY = 600 * heightRatio;
	
		Double locationX = location.x + size.getWidth() / 2 - minX /2 ;
		Double locationY = location.y + size.getHeight() / 2 - minY / 2;
		
		Double sizeX = minX;
		Double sizeY = minY;
		
		setLocation(locationX.intValue() , locationY.intValue() );
		setSize(sizeX.intValue(), sizeY.intValue());
		
		this.setTitle("Dodavanje studenta");
		
		JLabel errLabel = new JLabel();
		
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JPanel fieldsPanel = new JPanel();
		JPanel buttonsPanel = new JPanel();
		
		fieldsPanel.setLayout(new GridLayout(10,2));
		
		JLabel nameLabel = new JLabel("Ime*:");
		JTextField nameField = new JTextField();		
		
		JLabel lastnameLabel = new JLabel("Prezime*:");
		JTextField lastnameField = new JTextField();
		
		
		JLabel dateobLabel = new JLabel("Datum rođenja*:");
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
		String  yearChoices[] = {"I (prva)","II (druga)","III (treća)","IV (četvrta)", "V (peta)", "VI (šesta)"};     
		JComboBox<String> yearOfStudyField = new JComboBox<String>(yearChoices);
		
		
		JLabel statusLabel = new JLabel("Način finansiranja*:");
		String  statusChoices[] = {"Budžet", "Samofinansirajuće"};     
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
		
		
		buttonsPanel.setLayout(new FlowLayout());
		
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
			
		};
		
		
		// lost focus listeners to check if its time to enable the button
		nameField.addFocusListener(focusLostCheck);
		lastnameField.addFocusListener(focusLostCheck);
		dateobField.addFocusListener(focusLostCheck);		
		adressField.addFocusListener(focusLostCheck);
		phoneField.addFocusListener(focusLostCheck);
		emailField.addFocusListener(focusLostCheck);
		indexField.addFocusListener(focusLostCheck);
		enrolmentField.addFocusListener(focusLostCheck);
		
		acceptButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StudentController con = new StudentController(nameField.getText(), lastnameField.getText(), dateobField.getText(), adressField.getText(), phoneField.getText(),
				emailField.getText(), indexField.getText(), enrolmentField.getText(), yearOfStudyField.getSelectedIndex(), statusField.getSelectedIndex());
				con.addStudentToData();
				dispose();
			}
			
		});
		
		
		mainPanel.add(errLabel, BorderLayout.NORTH);
		mainPanel.add(fieldsPanel, BorderLayout.CENTER);
		mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
		this.setContentPane(mainPanel);

		this.setVisible(true);
	}
}
