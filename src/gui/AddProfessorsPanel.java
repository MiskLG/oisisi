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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import controller.ProfessorController;
import controller.RegXClass;

public class AddProfessorsPanel extends JDialog {
	
	private boolean changesMade = false;
	private boolean[] validData = {false, false, false, false, false, false, false, false, false, false};
	
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
		
		this.setTitle(MainWindow.getInstance().getResourceBundle().getString("addProfDialName"));
		
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JPanel fieldsPanel = new JPanel();
		JPanel buttonsPanel = new JPanel();
		
		fieldsPanel.setLayout(new GridLayout(10,2));
		
		JLabel nameLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("nameLbl"));
		JTextField nameField = new JTextField();		
		
		JLabel lastnameLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("lastnameLbl"));
		JTextField lastnameField = new JTextField();
		
		
		JLabel dateobLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("dateobLbl"));
		JTextField dateobField = new JTextField();
		
		
		JLabel adressHomeLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("adressLbl"));
		JTextField adressHomeField = new JTextField();
		
		
		JLabel phoneLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("phoneLbl"));
		JTextField phoneField = new JTextField();
		
		
		JLabel emailLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("emailLbl"));
		JTextField emailField = new JTextField();
		
		
		JLabel adressWorkLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("adressWorkLbl"));
		JTextField adressWorkField = new JTextField();
		
		
		JLabel idLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("idLbl"));
		JTextField idField = new JTextField();
		
		
		JLabel titleLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("titleLbl"));
		JTextField titleField = new JTextField();
		
		
		JLabel workYearsLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("workYearsLbl"));
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
		
		JButton acceptButton = new JButton(MainWindow.getInstance().getResourceBundle().getString("acceptButton"));
		JButton cancelButton = new JButton(MainWindow.getInstance().getResourceBundle().getString("cancelButton"));
		
		acceptButton.setPreferredSize(new Dimension(140,30));
		cancelButton.setPreferredSize(new Dimension(140,30));
		
		JPanel spacer = new JPanel();
		spacer.setPreferredSize(new Dimension(15,30));
		
		buttonsPanel.add(acceptButton);
		buttonsPanel.add(spacer);
		buttonsPanel.add(cancelButton);
		
		acceptButton.setEnabled(false);
	
		cancelButton.addActionListener( e -> { this.dispose(); });
		
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
				if(validData[0] && validData[1] && validData[2] && validData[3] && validData[4] && validData[5] && validData[6] && validData[7] && validData[8] && validData[9]) {
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
				if(validData[0] && validData[1] && validData[2] && validData[3] && validData[4] && validData[5] && validData[6] && validData[7] && validData[8] && validData[9]) {
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
				if(validData[0] && validData[1] && validData[2] && validData[3] && validData[4] && validData[5] && validData[6] && validData[7] && validData[8] && validData[9]) {
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
	adressHomeField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(true == RegXClass.checkAdress(adressHomeField.getText() + e.getKeyChar())) {
					validData[3] = true;
					adressHomeField.setBorder(new MatteBorder(1,1,1,1,Color.GRAY));
				}
				else {
					validData[3] = false;
					adressHomeField.setBorder(new MatteBorder(1,1,1,1,Color.red));
				}
				if(validData[0] && validData[1] && validData[2] && validData[3] && validData[4] && validData[5] && validData[6] && validData[7] && validData[8] && validData[9]) {
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
			if(validData[0] && validData[1] && validData[2] && validData[3] && validData[4] && validData[5] && validData[6] && validData[7] && validData[8] && validData[9]) {
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
			if(validData[0] && validData[1] && validData[2] && validData[3] && validData[4] && validData[5] && validData[6] && validData[7] && validData[8] && validData[9]) {
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
	adressWorkField.addKeyListener(new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
			if(true == RegXClass.checkAdress(adressWorkField.getText() + e.getKeyChar())) {
				validData[6] = true;
				adressWorkField.setBorder(new MatteBorder(1,1,1,1,Color.GRAY));
			}
			else {
				validData[6] = false;
				adressWorkField.setBorder(new MatteBorder(1,1,1,1,Color.red));
			}
			if(validData[0] && validData[1] && validData[2] && validData[3] && validData[4] && validData[5] && validData[6] && validData[7] && validData[8] && validData[9]) {
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
	idField.addKeyListener(new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
			if(true == RegXClass.checkId(idField.getText() + e.getKeyChar())) {
				validData[7] = true;
				idField.setBorder(new MatteBorder(1,1,1,1,Color.GRAY));
			}
			else {
				validData[7] = false;
				idField.setBorder(new MatteBorder(1,1,1,1,Color.red));
			}
			if(validData[0] && validData[1] && validData[2] && validData[3] && validData[4] && validData[5] && validData[6] && validData[7] && validData[8] && validData[9]) {
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
			if(true == RegXClass.checkTitle(titleField.getText() + e.getKeyChar())) {
				validData[8] = true;
				titleField.setBorder(new MatteBorder(1,1,1,1,Color.GRAY));
			}
			else {
				validData[8] = false;
				titleField.setBorder(new MatteBorder(1,1,1,1,Color.red));
			}
			if(validData[0] && validData[1] && validData[2] && validData[3] && validData[4] && validData[5] && validData[6] && validData[7] && validData[8] && validData[9]) {
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
	workYearsField.addKeyListener(new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {
			if(true == RegXClass.checkWorkYears(workYearsField.getText() + e.getKeyChar())) {
				validData[9] = true;
				workYearsField.setBorder(new MatteBorder(1,1,1,1,Color.GRAY));
			}
			else {
				validData[9] = false;
				workYearsField.setBorder(new MatteBorder(1,1,1,1,Color.red));
			}
			if(validData[0] && validData[1] && validData[2] && validData[3] && validData[4] && validData[5] && validData[6] && validData[7] && validData[8] && validData[9]) {
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
				ProfessorController con = new ProfessorController(nameField.getText(), lastnameField.getText(), dateobField.getText(), adressHomeField.getText(), phoneField.getText(),
						emailField.getText(), adressWorkField.getText(), idField.getText(), titleField.getText(), workYearsField.getText());
				String err = con.addProfessorToData();	
				if(err.equals("Sve je dobro!")) {
					changesMade = true;
					dispose();
				}
				else {
					String[] options = {"OK"};
	    			int result = JOptionPane.showOptionDialog((getRootPane()), 
	    					MainWindow.getInstance().getResourceBundle().getString("takenIdLbl"), MainWindow.getInstance().getResourceBundle().getString("error"),
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
