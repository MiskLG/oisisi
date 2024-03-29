package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import controller.ProfessorController;
import controller.RegXClass;
import model.Professor;

public class EditProfessorPanel extends JTabbedPane {

	private JPanel informations;
	private JPanel subjectsTought;
	
	private boolean changesMade = false;
	private boolean[] validData = {true, true, true, true, true, true, true, true, true, true};
	
	private JButton acceptButton;
	
	public EditProfessorPanel(Professor p, EditProfessor pan) {
		
		informations = new JPanel();
		subjectsTought = new ProfessorTeachingSubjects(p,pan);
		
		add(MainWindow.getInstance().getResourceBundle().getString("informationsLbl"), informations);
		add(MainWindow.getInstance().getResourceBundle().getString("profTeachingSubjectsLbl"), subjectsTought);
		
		acceptButton = new JButton(MainWindow.getInstance().getResourceBundle().getString("acceptButton"));
		acceptButton.setPreferredSize(new Dimension(140,30));
		
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JPanel fieldsPanel = new JPanel();
		
		Dimension d = new Dimension(400, 400);
		fieldsPanel.setLayout(new FlowLayout());
		fieldsPanel.setPreferredSize(d);
		
		Dimension d2 = new Dimension(180, 25);
		
		JLabel nameLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("nameLbl"));
		JTextField nameField = new JTextField();
		nameField.setText(p.getName());
		nameLabel.setPreferredSize(d2);
		nameField.setPreferredSize(d2);
		
		JLabel lastnameLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("lastnameLbl"));
		JTextField lastnameField = new JTextField();
		lastnameField.setText(p.getLastname());
		lastnameLabel.setPreferredSize(d2);
		lastnameField.setPreferredSize(d2);
		
		
		JLabel dateobLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("dateobLbl"));
		JTextField dateobField = new JTextField();
		dateobField.setText(p.getDateOfBirth().toString());
		dateobLabel.setPreferredSize(d2);
		dateobField.setPreferredSize(d2);
		
		
		JLabel adressHomeLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("adressLbl"));
		JTextField adressHomeField = new JTextField();
		adressHomeField.setText(p.getAdressHome().writeAsString());
		adressHomeLabel.setPreferredSize(d2);
		adressHomeField.setPreferredSize(d2);
		
		
		JLabel phoneLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("phoneLbl"));
		JTextField phoneField = new JTextField();
		phoneField.setText(p.getPhone());
		phoneLabel.setPreferredSize(d2);
		phoneField.setPreferredSize(d2);
		
		
		JLabel emailLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("emailLbl"));
		JTextField emailField = new JTextField();
		emailField.setText(p.getEmail());
		emailLabel.setPreferredSize(d2);
		emailField.setPreferredSize(d2);
		
		
		JLabel adressWorkLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("adressWorkLbl"));
		JTextField adressWorkField = new JTextField();
		adressWorkField.setText(p.getAdressWork().writeAsString());
		adressWorkLabel.setPreferredSize(d2);
		adressWorkField.setPreferredSize(d2);
		
		
		JLabel idLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("idLbl"));
		JTextField idField = new JTextField();
		idField.setText(p.getIdNumber());
		idLabel.setPreferredSize(d2);
		idField.setPreferredSize(d2);
		
		
		JLabel titleLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("titleLbl"));
		JTextField titleField = new JTextField();
		titleField.setText(p.getTitle());
		titleLabel.setPreferredSize(d2);
		titleField.setPreferredSize(d2);
		
		
		JLabel workYearsLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("workYearsLbl"));
		JTextField workYearsField = new JTextField();
		workYearsField.setText(Integer.toString(p.getWorkYears()));
		workYearsLabel.setPreferredSize(d2);
		workYearsField.setPreferredSize(d2);
		
		
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
		
		((FlowLayout)fieldsPanel.getLayout()).setVgap(15);
		((FlowLayout)fieldsPanel.getLayout()).setHgap(15);
		
		
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
		
		informations.add(fieldsPanel, BorderLayout.CENTER);
		
		acceptButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(p.getIdNumber().equals(idField.getText())) {
					ProfessorController con = new ProfessorController(nameField.getText(), lastnameField.getText(), dateobField.getText(), adressHomeField.getText(), phoneField.getText(),
							emailField.getText(), adressWorkField.getText(), idField.getText(), titleField.getText(), workYearsField.getText(), true);
					String err = con.editProfessor(p.getIdNumber());
					if(err.equals("Sve je dobro!")) {
						changesMade = true;
						
					}
					else {
						String[] options = {"OK"};
		    			int result = JOptionPane.showOptionDialog((getRootPane()), 
		    					MainWindow.getInstance().getResourceBundle().getString("takenIdLbl"), MainWindow.getInstance().getResourceBundle().getString("error"),
					            JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, options,"");
					}
				}
				else if(!p.getIdNumber().equals(idField.getText())) {
					ProfessorController con = new ProfessorController(nameField.getText(), lastnameField.getText(), dateobField.getText(), adressHomeField.getText(), phoneField.getText(),
							emailField.getText(), adressWorkField.getText(), idField.getText(), titleField.getText(), workYearsField.getText());
					String err = con.editProfessor(p.getIdNumber());
					if(err.equals("Sve je dobro!")) {
						changesMade = true;
						
						
					}
					else {
						String[] options = {"OK"};
		    			int result = JOptionPane.showOptionDialog((getRootPane()), 
		    					MainWindow.getInstance().getResourceBundle().getString("takenIdLbl"), MainWindow.getInstance().getResourceBundle().getString("error"),
					            JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, options,"");
					}
				}		
			}
			
		});
		
		
	}

	public JPanel getInformations() {
		return informations;
	}

	public void setInformations(JPanel informations) {
		this.informations = informations;
	}

	public JPanel getSubjectsTought() {
		return subjectsTought;
	}

	public void setSubjectsTought(JPanel subjectsTought) {
		this.subjectsTought = subjectsTought;
	}

	public JButton getAcceptButton() {
		return acceptButton;
	}

	public void setAcceptButton(JButton acceptButton) {
		this.acceptButton = acceptButton;
	}

	public boolean isChangesMade() {
		return changesMade;
	}

	public void setChangesMade(boolean changesMade) {
		this.changesMade = changesMade;
	}
	
	
	
}
