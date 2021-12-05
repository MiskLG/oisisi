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

import controller.AddProfessorsController;
import controller.AddStudentsController;

public class AddProfessorsPanel extends JDialog {
	
	public AddProfessorsPanel(Point location, Dimension size) {
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
		
		this.setTitle("Dodavanje profesora");
		
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
		
		
		JLabel adressHomeLabel = new JLabel("Adresa stanovanja*:");
		JTextField adressHomeField = new JTextField();
		
		
		JLabel phoneLabel = new JLabel("Broj telefona*:");
		JTextField phoneField = new JTextField();
		
		
		JLabel emailLabel = new JLabel("E-mail adresa*:");
		JTextField emailField = new JTextField();
		
		
		JLabel adressWorkLabel = new JLabel("Adresa kancelarije*:");
		JTextField adressWorkField = new JTextField();
		
		
		JLabel idLabel = new JLabel("Broj lične karte*:");
		JTextField idField = new JTextField();
		
		
		JLabel titleLabel = new JLabel("Zvanje*:");
		JTextField titleField = new JTextField();
		
		
		JLabel workYearsLabel = new JLabel("Radni staž*:");
		JTextField workYearsField = new JTextField();
		
		
		// adding stuff to their place
		fieldsPanel.add(nameLabel);
		fieldsPanel.add(nameField);
		fieldsPanel.add(lastnameLabel);
		fieldsPanel.add(lastnameField);
		fieldsPanel.add(dateobLabel);
		fieldsPanel.add(dateobField);
		fieldsPanel.add(adressHomeLabel);
		fieldsPanel.add(adressHomeField);
		fieldsPanel.add(phoneLabel);
		fieldsPanel.add(phoneField);
		fieldsPanel.add(emailLabel);
		fieldsPanel.add(emailField);
		fieldsPanel.add(adressWorkLabel);
		fieldsPanel.add(adressWorkField);
		fieldsPanel.add(idLabel);
		fieldsPanel.add(idField);
		fieldsPanel.add(titleLabel);
		fieldsPanel.add(titleField);
		fieldsPanel.add(workYearsLabel);
		fieldsPanel.add(workYearsField);
		
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
		
		// lost focus listeners to check if its time to enable the button
		nameField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {				
			}

			@Override
			public void focusLost(FocusEvent e) {
				AddProfessorsController con = new AddProfessorsController(nameField.getText(), lastnameField.getText(), dateobField.getText(), adressHomeField.getText(), phoneField.getText(),
						emailField.getText(), adressWorkField.getText(), idField.getText(), titleField.getText(), workYearsField.getText());
				String text = con.checkData(nameField.getText(), lastnameField.getText(), dateobField.getText(), adressHomeField.getText(), phoneField.getText(),
						emailField.getText(), adressWorkField.getText(), idField.getText(), titleField.getText(), workYearsField.getText());
				
				errLabel.setText(text);
				
				if(text.equalsIgnoreCase("Sve je dobro")) {
					acceptButton.setEnabled(true);
				}
				else {
					acceptButton.setEnabled(false);
				}
						
			}
			
		});
		lastnameField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {				
			}

			@Override
			public void focusLost(FocusEvent e) {
				AddProfessorsController con = new AddProfessorsController(nameField.getText(), lastnameField.getText(), dateobField.getText(), adressHomeField.getText(), phoneField.getText(),
						emailField.getText(), adressWorkField.getText(), idField.getText(), titleField.getText(), workYearsField.getText());
				String text = con.checkData(nameField.getText(), lastnameField.getText(), dateobField.getText(), adressHomeField.getText(), phoneField.getText(),
				emailField.getText(), adressWorkField.getText(), idField.getText(), titleField.getText(), workYearsField.getText());
				
				errLabel.setText(text);
				
				if(text.equalsIgnoreCase("Sve je dobro")) {
					acceptButton.setEnabled(true);
				}
				else {
					acceptButton.setEnabled(false);
				}
						
			}
			
		});
		dateobField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {				
			}

			@Override
			public void focusLost(FocusEvent e) {
				AddProfessorsController con = new AddProfessorsController(nameField.getText(), lastnameField.getText(), dateobField.getText(), adressHomeField.getText(), phoneField.getText(),
						emailField.getText(), adressWorkField.getText(), idField.getText(), titleField.getText(), workYearsField.getText());
				String text = con.checkData(nameField.getText(), lastnameField.getText(), dateobField.getText(), adressHomeField.getText(), phoneField.getText(),
						emailField.getText(), adressWorkField.getText(), idField.getText(), titleField.getText(), workYearsField.getText());
				
				errLabel.setText(text);
				
				errLabel.setText(text);
				
				if(text.equalsIgnoreCase("Sve je dobro")) {
					acceptButton.setEnabled(true);
				}
				else {
					acceptButton.setEnabled(false);
				}
						
			}
			
		});		
		adressHomeField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {				
			}

			@Override
			public void focusLost(FocusEvent e) {
				AddProfessorsController con = new AddProfessorsController(nameField.getText(), lastnameField.getText(), dateobField.getText(), adressHomeField.getText(), phoneField.getText(),
						emailField.getText(), adressWorkField.getText(), idField.getText(), titleField.getText(), workYearsField.getText());
				String text = con.checkData(nameField.getText(), lastnameField.getText(), dateobField.getText(), adressHomeField.getText(), phoneField.getText(),
						emailField.getText(), adressWorkField.getText(), idField.getText(), titleField.getText(), workYearsField.getText());

				errLabel.setText(text);
				
				if(text.equalsIgnoreCase("Sve je dobro")) {
					acceptButton.setEnabled(true);
				}
				else {
					acceptButton.setEnabled(false);
				}
						
			}
			
		});
		phoneField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {				
			}

			@Override
			public void focusLost(FocusEvent e) {
				AddProfessorsController con = new AddProfessorsController(nameField.getText(), lastnameField.getText(), dateobField.getText(), adressHomeField.getText(), phoneField.getText(),
						emailField.getText(), adressWorkField.getText(), idField.getText(), titleField.getText(), workYearsField.getText());
				String text = con.checkData(nameField.getText(), lastnameField.getText(), dateobField.getText(), adressHomeField.getText(), phoneField.getText(),
						emailField.getText(), adressWorkField.getText(), idField.getText(), titleField.getText(), workYearsField.getText());
				
				errLabel.setText(text);
				
				if(text.equalsIgnoreCase("Sve je dobro")) {
					acceptButton.setEnabled(true);
				}
				else {
					acceptButton.setEnabled(false);
				}
						
			}
			
		});
		emailField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {				
			}

			@Override
			public void focusLost(FocusEvent e) {
				AddProfessorsController con = new AddProfessorsController(nameField.getText(), lastnameField.getText(), dateobField.getText(), adressHomeField.getText(), phoneField.getText(),
						emailField.getText(), adressWorkField.getText(), idField.getText(), titleField.getText(), workYearsField.getText());
				String text = con.checkData(nameField.getText(), lastnameField.getText(), dateobField.getText(), adressHomeField.getText(), phoneField.getText(),
						emailField.getText(), adressWorkField.getText(), idField.getText(), titleField.getText(), workYearsField.getText());
				
				errLabel.setText(text);
				
				if(text.equalsIgnoreCase("Sve je dobro")) {
					acceptButton.setEnabled(true);
				}
				else {
					acceptButton.setEnabled(false);
				}
						
			}
			
		});
		adressWorkField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {				
			}

			@Override
			public void focusLost(FocusEvent e) {
				AddProfessorsController con = new AddProfessorsController(nameField.getText(), lastnameField.getText(), dateobField.getText(), adressHomeField.getText(), phoneField.getText(),
						emailField.getText(), adressWorkField.getText(), idField.getText(), titleField.getText(), workYearsField.getText());
				String text = con.checkData(nameField.getText(), lastnameField.getText(), dateobField.getText(), adressHomeField.getText(), phoneField.getText(),
						emailField.getText(), adressWorkField.getText(), idField.getText(), titleField.getText(), workYearsField.getText());
				
				errLabel.setText(text);
				
				if(text.equalsIgnoreCase("Sve je dobro")) {
					acceptButton.setEnabled(true);
				}
				else {
					acceptButton.setEnabled(false);
				}
				
			}
			
		});
		idField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {				
			}

			@Override
			public void focusLost(FocusEvent e) {
				AddProfessorsController con = new AddProfessorsController(nameField.getText(), lastnameField.getText(), dateobField.getText(), adressHomeField.getText(), phoneField.getText(),
						emailField.getText(), adressWorkField.getText(), idField.getText(), titleField.getText(), workYearsField.getText());
				String text = con.checkData(nameField.getText(), lastnameField.getText(), dateobField.getText(), adressHomeField.getText(), phoneField.getText(),
						emailField.getText(), adressWorkField.getText(), idField.getText(), titleField.getText(), workYearsField.getText());
				
				errLabel.setText(text);
				
				if(text.equalsIgnoreCase("Sve je dobro")) {
					acceptButton.setEnabled(true);
				}
				else {
					acceptButton.setEnabled(false);
				}
						
			}
			
		});
		titleField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {				
			}

			@Override
			public void focusLost(FocusEvent e) {
				AddProfessorsController con = new AddProfessorsController(nameField.getText(), lastnameField.getText(), dateobField.getText(), adressHomeField.getText(), phoneField.getText(),
						emailField.getText(), adressWorkField.getText(), idField.getText(), titleField.getText(), workYearsField.getText());
				String text = con.checkData(nameField.getText(), lastnameField.getText(), dateobField.getText(), adressHomeField.getText(), phoneField.getText(),
						emailField.getText(), adressWorkField.getText(), idField.getText(), titleField.getText(), workYearsField.getText());
				
				errLabel.setText(text);
				
				if(text.equalsIgnoreCase("Sve je dobro")) {
					acceptButton.setEnabled(true);
				}
				else {
					acceptButton.setEnabled(false);
				}
						
			}
			
		});
		workYearsField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {				
			}

			@Override
			public void focusLost(FocusEvent e) {
				AddProfessorsController con = new AddProfessorsController(nameField.getText(), lastnameField.getText(), dateobField.getText(), adressHomeField.getText(), phoneField.getText(),
						emailField.getText(), adressWorkField.getText(), idField.getText(), titleField.getText(), workYearsField.getText());
				String text = con.checkData(nameField.getText(), lastnameField.getText(), dateobField.getText(), adressHomeField.getText(), phoneField.getText(),
						emailField.getText(), adressWorkField.getText(), idField.getText(), titleField.getText(), workYearsField.getText());
				
				errLabel.setText(text);
				
				if(text.equalsIgnoreCase("Sve je dobro")) {
					acceptButton.setEnabled(true);
				}
				else {
					acceptButton.setEnabled(false);
				}
						
			}
			
		});
		
		acceptButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddProfessorsController con = new AddProfessorsController(nameField.getText(), lastnameField.getText(), dateobField.getText(), adressHomeField.getText(), phoneField.getText(),
						emailField.getText(), adressWorkField.getText(), idField.getText(), titleField.getText(), workYearsField.getText());
				con.addProfessorToData();
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
