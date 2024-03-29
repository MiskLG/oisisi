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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import controller.RegXClass;
import controller.StudentController;
import model.Student;


public class EditStudentPanel extends JTabbedPane{
	
	private JPanel informations;
	private StudentFinishedPanel passedExams; 
	private StudentUnfinishedPanel unpassedExams;
	
	private boolean changesMade = false;
	private boolean[] validData = {true, true, true, true, true, true, true, true};
	
	private JButton acceptButton;
	
	private int currentTab = 0;
	
	public int getCurrentTab() {
		return currentTab;
	}
	
	public void setCurrentTab(int currentTab) {
		this.currentTab = currentTab;
	}

	
	public EditStudentPanel(Student s, EditStudent pan) {
		
		
		informations = new JPanel();
		passedExams = new StudentFinishedPanel(s);
		unpassedExams = new StudentUnfinishedPanel(s,pan);
		
		
		add(MainWindow.getInstance().getResourceBundle().getString("informationsLbl"), informations);
		add(MainWindow.getInstance().getResourceBundle().getString("studFinishedSubjLbl"), passedExams);
		add(MainWindow.getInstance().getResourceBundle().getString("studUnfinishedSubjLbl"), unpassedExams);
		
		acceptButton = new JButton(MainWindow.getInstance().getResourceBundle().getString("acceptButton"));
		acceptButton.setPreferredSize(new Dimension(140,30));
		
		this.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				currentTab = getSelectedIndex();
				switch(currentTab) {
				case 0:
					break;
				case 1:
					passedExams.updateFinished();
					passedExams.calculateAverageGrade(s);
					passedExams.calculateECTS();
				case 2:
					unpassedExams.updateUnfinished();
				}
				
			}
		});
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JPanel fieldsPanel = new JPanel();
		
		Dimension d = new Dimension(400, 400);
		fieldsPanel.setLayout(new FlowLayout());
		fieldsPanel.setPreferredSize(d);
		
		Dimension d2 = new Dimension(180, 25);
		
		JLabel nameLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("nameLbl"));
		JTextField nameField = new JTextField();
		nameField.setText(s.getName());
		nameLabel.setPreferredSize(d2);
		nameField.setPreferredSize(d2);
		
		JLabel lastnameLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("lastnameLbl"));
		JTextField lastnameField = new JTextField();
		lastnameField.setText(s.getLastname());
		lastnameLabel.setPreferredSize(d2);
		lastnameField.setPreferredSize(d2);
		
		
		JLabel dateobLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("dateobLbl"));
		JTextField dateobField = new JTextField();
		dateobField.setText(s.getDateOfBirth().toString());
		dateobLabel.setPreferredSize(d2);
		dateobField.setPreferredSize(d2);
		
		
		JLabel adressLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("adressLbl"));
		JTextField adressField = new JTextField();
		adressField.setText(s.getAdress().writeAsString());
		adressLabel.setPreferredSize(d2);
		adressField.setPreferredSize(d2);
		
		
		JLabel phoneLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("phoneLbl"));
		JTextField phoneField = new JTextField();
		phoneField.setText(s.getPhone());
		phoneLabel.setPreferredSize(d2);
		phoneField.setPreferredSize(d2);
		
		
		JLabel emailLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("emailLbl"));
		JTextField emailField = new JTextField();
		emailField.setText(s.getEmail());
		emailLabel.setPreferredSize(d2);
		emailField.setPreferredSize(d2);
		
		
		JLabel indexLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("indexLbl"));
		JTextField indexField = new JTextField();
		indexField.setText(s.getIndex());
		indexLabel.setPreferredSize(d2);
		indexField.setPreferredSize(d2);
		
		
		JLabel enrolmentLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("enrolmentLbl"));
		JTextField enrolmentField = new JTextField();
		enrolmentField.setText(Integer.toString(s.getEnrolmentYear()));
		enrolmentLabel.setPreferredSize(d2);
		enrolmentField.setPreferredSize(d2);
		
		
		JLabel yearOfStudyLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("yearOfStudyLbl"));
		String  yearChoices[] = {MainWindow.getInstance().getResourceBundle().getString("yearChoiceOne"),MainWindow.getInstance().getResourceBundle().getString("yearChoiceTwo"),
				MainWindow.getInstance().getResourceBundle().getString("yearChoiceThree"),MainWindow.getInstance().getResourceBundle().getString("yearChoiceFour"),
				MainWindow.getInstance().getResourceBundle().getString("yearChoiceFive"), MainWindow.getInstance().getResourceBundle().getString("yearChoiceSix")};     
		JComboBox<String> yearOfStudyField = new JComboBox<String>(yearChoices);
		if(s.getYearOfStudy() == 1) {
			yearOfStudyField.setSelectedIndex(0);
		}
		else if(s.getYearOfStudy() == 2) {
			yearOfStudyField.setSelectedIndex(1);
		}
		else if(s.getYearOfStudy() == 3) {
			yearOfStudyField.setSelectedIndex(2);
		}
		else if(s.getYearOfStudy() == 4) {
			yearOfStudyField.setSelectedIndex(3);
		}
		else if(s.getYearOfStudy() == 5) {
			yearOfStudyField.setSelectedIndex(4);
		}
		else if(s.getYearOfStudy() == 6) {
			yearOfStudyField.setSelectedIndex(5);
		}
		yearOfStudyLabel.setPreferredSize(d2);
		yearOfStudyField.setPreferredSize(d2);
		
		
		JLabel statusLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("statusLbl"));
		String  statusChoices[] = {MainWindow.getInstance().getResourceBundle().getString("statusChoiceOne"), MainWindow.getInstance().getResourceBundle().getString("statusChoiceTwo")};     
		JComboBox<String> statusField = new JComboBox<String>(statusChoices);
		if(s.getStatus().equals("B")) {
			statusField.setSelectedIndex(0);
		}else if(s.getStatus().equals("S")) {
			statusField.setSelectedIndex(1);
		}
		statusLabel.setPreferredSize(d2);
		statusField.setPreferredSize(d2);

		
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
		
		informations.add(fieldsPanel, BorderLayout.CENTER);
		
		acceptButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(s.getIndex().equals(indexField.getText())) {
					StudentController con = new StudentController(nameField.getText(), lastnameField.getText(), dateobField.getText(), adressField.getText(), phoneField.getText(),
					emailField.getText(), indexField.getText(), enrolmentField.getText(), yearOfStudyField.getSelectedIndex(), statusField.getSelectedIndex(), true);
					String err = con.editStudent(s.getIndex());
					if(err.equals("Sve je dobro!")) {
						changesMade = true;
						
					}
					else {
						String[] options = {"OK"};
		    			int result = JOptionPane.showOptionDialog((getRootPane()), 
		    					MainWindow.getInstance().getResourceBundle().getString("takenIndexLbl"), MainWindow.getInstance().getResourceBundle().getString("error"),
					            JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, options,"");
					}
				}
				else if(!s.getIndex().equals(indexField.getText())) {
					StudentController con = new StudentController(nameField.getText(), lastnameField.getText(), dateobField.getText(), adressField.getText(), phoneField.getText(),
					emailField.getText(), indexField.getText(), enrolmentField.getText(), yearOfStudyField.getSelectedIndex(), statusField.getSelectedIndex());
					String err = con.editStudent(s.getIndex());
					if(err.equals("Sve je dobro!")) {
						changesMade = true;
						
						
					}
					else {
						String[] options = {"OK"};
		    			int result = JOptionPane.showOptionDialog((getRootPane()), 
		    					MainWindow.getInstance().getResourceBundle().getString("takenIndexLbl"), MainWindow.getInstance().getResourceBundle().getString("error"),
					            JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, options,"");
					}
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
