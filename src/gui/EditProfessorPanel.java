//package gui;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.FlowLayout;
//import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//
//import javax.swing.JButton;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JTabbedPane;
//import javax.swing.JTextField;
//import javax.swing.border.EmptyBorder;
//import javax.swing.border.MatteBorder;
//
//import controller.ProfessorController;
//import controller.RegXClass;
//import main.DataClass;
//
//public class EditProfessorPanel extends JTabbedPane {
//
//	private JPanel informations;
//	private JPanel subjectsTought;
//	
//	private boolean changesMade = false;
//	private boolean[] validData = {true, true, true, true, true, true, true, true, true, true};
//	
//	private JButton acceptButton;
//	
//	public EditProfessorPanel() {
//		
//		informations = new JPanel();
//		subjectsTought = new JPanel();
//		
//		add("Informacije", informations);
//		add("Predmeti na kojima predaje", subjectsTought);
//		
//		acceptButton = new JButton("Potvrdi");
//		acceptButton.setPreferredSize(new Dimension(140,30));
//		
//		JLabel errLabel = new JLabel();
//		
//		JPanel mainPanel = new JPanel();
//		mainPanel.setLayout(new BorderLayout());
//		
//		JPanel fieldsPanel = new JPanel();
//		JPanel buttonsPanel = new JPanel();
//		
//		fieldsPanel.setLayout(new GridLayout(10,2));
//		
//		JLabel nameLabel = new JLabel("Ime*:");
//		JTextField nameField = new JTextField();
//		nameField.setText(DataClass.getInstance().getSelectedProfessor(AbstractTableModelProfessors.getSelectedRowIndex()).getName());
//		
//		JLabel lastnameLabel = new JLabel("Prezime*:");
//		JTextField lastnameField = new JTextField();
//		lastnameField.setText(DataClass.getInstance().getSelectedProfessor(AbstractTableModelProfessors.getSelectedRowIndex()).getLastname());
//		
//		
//		JLabel dateobLabel = new JLabel("Datum roÄ‘enja*:");
//		JTextField dateobField = new JTextField();
//		dateobField.setText(DataClass.getInstance().getSelectedProfessor(AbstractTableModelProfessors.getSelectedRowIndex()).getDateOfBirth().toString());
//		
//		
//		JLabel adressHomeLabel = new JLabel("Adresa stanovanja*:");
//		JTextField adressHomeField = new JTextField();
//		adressHomeField.setText(DataClass.getInstance().getSelectedProfessor(AbstractTableModelProfessors.getSelectedRowIndex()).getAdressHome().toString());
//		
//		
//		JLabel phoneLabel = new JLabel("Broj telefona*:");
//		JTextField phoneField = new JTextField();
//		phoneField.setText(DataClass.getInstance().getSelectedProfessor(AbstractTableModelProfessors.getSelectedRowIndex()).getPhone());
//		
//		
//		JLabel emailLabel = new JLabel("E-mail adresa*:");
//		JTextField emailField = new JTextField();
//		emailField.setText(DataClass.getInstance().getSelectedProfessor(AbstractTableModelProfessors.getSelectedRowIndex()).getEmail());
//		
//		
//		JLabel adressWorkLabel = new JLabel("Adresa kancelarije*:");
//		JTextField adressWorkField = new JTextField();
//		adressWorkField.setText(DataClass.getInstance().getSelectedProfessor(AbstractTableModelProfessors.getSelectedRowIndex()).getAdressWork().toString());
//		
//		
//		JLabel idLabel = new JLabel("Broj liÄ�ne karte*:");
//		JTextField idField = new JTextField();
//		idField.setText(DataClass.getInstance().getSelectedProfessor(AbstractTableModelProfessors.getSelectedRowIndex()).getIdNumber());
//		
//		
//		JLabel titleLabel = new JLabel("Zvanje*:");
//		JTextField titleField = new JTextField();
//		titleField.setText(DataClass.getInstance().getSelectedProfessor(AbstractTableModelProfessors.getSelectedRowIndex()).getTitle());
//		
//		
//		JLabel workYearsLabel = new JLabel("Radni staÅ¾*:");
//		JTextField workYearsField = new JTextField();
//		workYearsField.setText(String.valueOf(DataClass.getInstance().getSelectedProfessor(AbstractTableModelProfessors.getSelectedRowIndex()).getWorkYears()));
//		
//		
//		fieldsPanel.add(nameLabel);
//		fieldsPanel.add(nameField);
//		fieldsPanel.add(lastnameLabel);
//		fieldsPanel.add(lastnameField);
//		fieldsPanel.add(dateobLabel);
//		fieldsPanel.add(dateobField);
//		fieldsPanel.add(adressHomeLabel);
//		fieldsPanel.add(adressHomeField);
//		fieldsPanel.add(phoneLabel);
//		fieldsPanel.add(phoneField);
//		fieldsPanel.add(emailLabel);
//		fieldsPanel.add(emailField);
//		fieldsPanel.add(adressWorkLabel);
//		fieldsPanel.add(adressWorkField);
//		fieldsPanel.add(idLabel);
//		fieldsPanel.add(idField);
//		fieldsPanel.add(titleLabel);
//		fieldsPanel.add(titleField);
//		fieldsPanel.add(workYearsLabel);
//		fieldsPanel.add(workYearsField);
//		
//		mainPanel.setBorder(new EmptyBorder(20,30,20,30));
//		((BorderLayout)mainPanel.getLayout()).setVgap(20);
//		
//		((GridLayout)fieldsPanel.getLayout()).setVgap(15);
//		((GridLayout)fieldsPanel.getLayout()).setHgap(15);
//		
//		//buttonsPanel.setLayout(new FlowLayout());
//		
//		nameField.addKeyListener(new KeyListener() {
//			@Override
//			public void keyTyped(KeyEvent e) {
//				if(true == RegXClass.checkName(nameField.getText() + e.getKeyChar())) {
//					validData[0] = true;
//					nameField.setBorder(new MatteBorder(1,1,1,1,Color.GRAY));
//				}
//				else {
//					validData[0] = false;
//					nameField.setBorder(new MatteBorder(1,1,1,1,Color.red));
//				}
//				if(validData[0] && validData[1] && validData[2] && validData[3] && validData[4] && validData[5] && validData[6] && validData[7] && validData[8] && validData[9]) {
//					acceptButton.setEnabled(true);
//				}
//				else {
//					acceptButton.setEnabled(false);
//				}
//			}
//
//			@Override
//			public void keyPressed(KeyEvent e) {		
//			}
//
//			@Override
//			public void keyReleased(KeyEvent e) {	
//			}
//	});
//	lastnameField.addKeyListener(new KeyListener() {
//			@Override
//			public void keyTyped(KeyEvent e) {
//				if(true == RegXClass.checkLastname(lastnameField.getText() + e.getKeyChar())) {
//					validData[1] = true;
//					lastnameField.setBorder(new MatteBorder(1,1,1,1,Color.GRAY));
//				}
//				else {
//					validData[1] = false;
//					lastnameField.setBorder(new MatteBorder(1,1,1,1,Color.red));
//				}
//				if(validData[0] && validData[1] && validData[2] && validData[3] && validData[4] && validData[5] && validData[6] && validData[7] && validData[8] && validData[9]) {
//					acceptButton.setEnabled(true);
//				}
//				else {
//					acceptButton.setEnabled(false);
//				}
//			}
//
//			@Override
//			public void keyPressed(KeyEvent e) {
//			}
//
//			@Override
//			public void keyReleased(KeyEvent e) {
//			}
//	});
//	dateobField.addKeyListener(new KeyListener() {
//			@Override
//			public void keyTyped(KeyEvent e) {
//				if(true == RegXClass.checkDate(dateobField.getText() + e.getKeyChar())) {
//					validData[2] = true;
//					dateobField.setBorder(new MatteBorder(1,1,1,1,Color.GRAY));
//				}
//				else {
//					validData[2] = false;
//					dateobField.setBorder(new MatteBorder(1,1,1,1,Color.red));
//				}
//				if(validData[0] && validData[1] && validData[2] && validData[3] && validData[4] && validData[5] && validData[6] && validData[7] && validData[8] && validData[9]) {
//					acceptButton.setEnabled(true);
//				}
//				else {
//					acceptButton.setEnabled(false);
//				}
//			}
//
//			@Override
//			public void keyPressed(KeyEvent e) {	
//			}
//
//			@Override
//			public void keyReleased(KeyEvent e) {
//			}
//	});
//	adressHomeField.addKeyListener(new KeyListener() {
//			@Override
//			public void keyTyped(KeyEvent e) {
//				if(true == RegXClass.checkAdress(adressHomeField.getText() + e.getKeyChar())) {
//					validData[3] = true;
//					adressHomeField.setBorder(new MatteBorder(1,1,1,1,Color.GRAY));
//				}
//				else {
//					validData[3] = false;
//					adressHomeField.setBorder(new MatteBorder(1,1,1,1,Color.red));
//				}
//				if(validData[0] && validData[1] && validData[2] && validData[3] && validData[4] && validData[5] && validData[6] && validData[7] && validData[8] && validData[9]) {
//					acceptButton.setEnabled(true);
//				}
//				else {
//					acceptButton.setEnabled(false);
//				}
//			}
//
//			@Override
//			public void keyPressed(KeyEvent e) {	
//			}
//
//			@Override
//			public void keyReleased(KeyEvent e) {	
//			}
//	});
//	phoneField.addKeyListener(new KeyListener() {
//		@Override
//		public void keyTyped(KeyEvent e) {
//			if(true == RegXClass.checkPhone(phoneField.getText() + e.getKeyChar())) {
//				validData[4] = true;
//				phoneField.setBorder(new MatteBorder(1,1,1,1,Color.GRAY));
//			}
//			else {
//				validData[4] = false;
//				phoneField.setBorder(new MatteBorder(1,1,1,1,Color.red));
//			}
//			if(validData[0] && validData[1] && validData[2] && validData[3] && validData[4] && validData[5] && validData[6] && validData[7] && validData[8] && validData[9]) {
//				acceptButton.setEnabled(true);
//			}
//			else {
//				acceptButton.setEnabled(false);
//			}
//		}
//
//		@Override
//		public void keyPressed(KeyEvent e) {
//		}
//
//		@Override
//		public void keyReleased(KeyEvent e) {
//		}
//	});
//	emailField.addKeyListener(new KeyListener() {
//		@Override
//		public void keyTyped(KeyEvent e) {
//			if(true == RegXClass.checkEmail(emailField.getText() + e.getKeyChar())) {
//				validData[5] = true;
//				emailField.setBorder(new MatteBorder(1,1,1,1,Color.GRAY));
//			}
//			else {
//				validData[5] = false;
//				emailField.setBorder(new MatteBorder(1,1,1,1,Color.red));
//			}
//			if(validData[0] && validData[1] && validData[2] && validData[3] && validData[4] && validData[5] && validData[6] && validData[7] && validData[8] && validData[9]) {
//				acceptButton.setEnabled(true);
//				
//			}
//			else {
//				acceptButton.setEnabled(false);
//			}
//		}
//
//		@Override
//		public void keyPressed(KeyEvent e) {
//		}
//
//		@Override
//		public void keyReleased(KeyEvent e) {
//		}
//	});
//	adressWorkField.addKeyListener(new KeyListener() {
//		@Override
//		public void keyTyped(KeyEvent e) {
//			if(true == RegXClass.checkAdress(adressWorkField.getText() + e.getKeyChar())) {
//				validData[6] = true;
//				adressWorkField.setBorder(new MatteBorder(1,1,1,1,Color.GRAY));
//			}
//			else {
//				validData[6] = false;
//				adressWorkField.setBorder(new MatteBorder(1,1,1,1,Color.red));
//			}
//			if(validData[0] && validData[1] && validData[2] && validData[3] && validData[4] && validData[5] && validData[6] && validData[7] && validData[8] && validData[9]) {
//				acceptButton.setEnabled(true);
//			}
//			else {
//				acceptButton.setEnabled(false);
//			}
//		}
//
//		@Override
//		public void keyPressed(KeyEvent e) {		
//		}
//
//		@Override
//		public void keyReleased(KeyEvent e) {			
//		}
//	});
//	idField.addKeyListener(new KeyListener() {
//		@Override
//		public void keyTyped(KeyEvent e) {
//			if(true == RegXClass.checkId(idField.getText() + e.getKeyChar())) {
//				validData[7] = true;
//				idField.setBorder(new MatteBorder(1,1,1,1,Color.GRAY));
//			}
//			else {
//				validData[7] = false;
//				idField.setBorder(new MatteBorder(1,1,1,1,Color.red));
//			}
//			if(validData[0] && validData[1] && validData[2] && validData[3] && validData[4] && validData[5] && validData[6] && validData[7] && validData[8] && validData[9]) {
//				acceptButton.setEnabled(true);
//			}
//			else {
//				acceptButton.setEnabled(false);
//			}	
//		}
//
//		@Override
//		public void keyPressed(KeyEvent e) {
//		}
//
//		@Override
//		public void keyReleased(KeyEvent e) {
//		}
//	});
//	titleField.addKeyListener(new KeyListener() {
//		@Override
//		public void keyTyped(KeyEvent e) {
//			if(true == RegXClass.checkTitle(titleField.getText() + e.getKeyChar())) {
//				validData[8] = true;
//				titleField.setBorder(new MatteBorder(1,1,1,1,Color.GRAY));
//			}
//			else {
//				validData[8] = false;
//				titleField.setBorder(new MatteBorder(1,1,1,1,Color.red));
//			}
//			if(validData[0] && validData[1] && validData[2] && validData[3] && validData[4] && validData[5] && validData[6] && validData[7] && validData[8] && validData[9]) {
//				acceptButton.setEnabled(true);
//			}
//			else {
//				acceptButton.setEnabled(false);
//			}	
//		}
//
//		@Override
//		public void keyPressed(KeyEvent e) {
//		}
//
//		@Override
//		public void keyReleased(KeyEvent e) {
//		}
//	});
//	workYearsField.addKeyListener(new KeyListener() {
//		@Override
//		public void keyTyped(KeyEvent e) {
//			if(true == RegXClass.checkWorkYears(workYearsField.getText() + e.getKeyChar())) {
//				validData[9] = true;
//				workYearsField.setBorder(new MatteBorder(1,1,1,1,Color.GRAY));
//			}
//			else {
//				validData[9] = false;
//				workYearsField.setBorder(new MatteBorder(1,1,1,1,Color.red));
//			}
//			if(validData[0] && validData[1] && validData[2] && validData[3] && validData[4] && validData[5] && validData[6] && validData[7] && validData[8] && validData[9]) {
//				acceptButton.setEnabled(true);
//			}
//			else {
//				acceptButton.setEnabled(false);
//			}	
//		}
//
//		@Override
//		public void keyPressed(KeyEvent e) {
//		}
//
//		@Override
//		public void keyReleased(KeyEvent e) {
//		}
//	});
//		
//		acceptButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				ProfessorController con = new ProfessorController(nameField.getText(), lastnameField.getText(), dateobField.getText(), adressHomeField.getText(), phoneField.getText(),
//						emailField.getText(), adressWorkField.getText(), idField.getText(), titleField.getText(), workYearsField.getText());
//				String err = con.addProfessorToData();	
//				if(err.equals("Sve je dobro!")) {
//					changesMade = true;
//				}
//				else {
//					errLabel.setText(err);
//				}
//			}
//			
//		});
//		
//		informations.add(errLabel, BorderLayout.NORTH);
//		informations.add(fieldsPanel, BorderLayout.CENTER);
////		mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
////		this.setContentPane(mainPanel);
//		
//		this.setVisible(true);
//	}
//
//	public JPanel getInformations() {
//		return informations;
//	}
//
//	public void setInformations(JPanel informations) {
//		this.informations = informations;
//	}
//
//	public JPanel getSubjectsTought() {
//		return subjectsTought;
//	}
//
//	public void setSubjectsTought(JPanel subjectsTought) {
//		this.subjectsTought = subjectsTought;
//	}
//
//	public JButton getAcceptButton() {
//		return acceptButton;
//	}
//
//	public void setAcceptButton(JButton acceptButton) {
//		this.acceptButton = acceptButton;
//	}
//
//	public boolean isChangesMade() {
//		return changesMade;
//	}
//
//	public void setChangesMade(boolean changesMade) {
//		this.changesMade = changesMade;
//	}
//	
//	
//	
//}
