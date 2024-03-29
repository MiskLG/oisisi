package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.ImageIcon;
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
import controller.SubjectController;
import main.DataClass;
import model.Professor;
import model.Subject;

public class EditSubjectPanel extends JDialog {
	
	private boolean changesMade = false;
	private boolean[] validData = {true, true, true};
	
	private String newIconPNG = System.getProperty("user.dir") + File.separator + "images" + File.separator + "toolbar" + File.separator + "new.png";
	private String deleteIconPNG = System.getProperty("user.dir") + File.separator + "images" + File.separator + "toolbar" + File.separator + "delete.png";

	public EditSubjectPanel(Point location, Dimension size, String subjectCode) {
		this.setResizable(false);
		this.setModal(true);
		
		setTitle(MainWindow.getInstance().getResourceBundle().getString("editSubjDialName"));
		
		double widthRatio = 40./100;
		double heightRatio = 95./100;
		
		Double minX = 1200 * widthRatio;
		Double minY = 600 * heightRatio;
	
		Double locationX = location.x + size.getWidth() / 2 - minX /2 ;
		Double locationY = location.y + size.getHeight() / 2 - minY / 2;

		setLocation(locationX.intValue() , locationY.intValue() );
		setSize(500, 450);
		
		SubjectController con = new SubjectController();
		Subject s = con.findSubjectByCode(subjectCode);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JPanel fieldsPanel = new JPanel();
		JPanel buttonsPanel = new JPanel();
		
		Dimension d = new Dimension(340, 390);
		fieldsPanel.setLayout(new FlowLayout());
		fieldsPanel.setPreferredSize(d);
		
		Dimension d2 = new Dimension(100, 33);
		Dimension d3 = new Dimension(270, 33);
		
		JLabel subjectCodeLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("subjCodeLbl"));
		JTextField subjectCodeField = new JTextField();
		subjectCodeField.setText(s.getSubjectCode());
		subjectCodeLabel.setPreferredSize(d2);
		subjectCodeField.setPreferredSize(d3);
		
		JLabel titleLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("subjNameLbl"));
		JTextField titleField = new JTextField();
		titleField.setText(s.getTitle());
		titleLabel.setPreferredSize(d2);
		titleField.setPreferredSize(d3);
		
		JLabel semesterLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("semesterLbl"));
		String  semesterChoices[] = {MainWindow.getInstance().getResourceBundle().getString("semesterChoiceOne"), MainWindow.getInstance().getResourceBundle().getString("semesterChoiceTwo")};     
		JComboBox<String> semesterField = new JComboBox<String>(semesterChoices);
		if(s.getSemester().equals("Zimski")) {
			semesterField.setSelectedIndex(0);
		}else if(s.getSemester().equals("Letnji")) {
			semesterField.setSelectedIndex(1);
		}
		semesterLabel.setPreferredSize(d2);
		semesterField.setPreferredSize(d3);
		
		JLabel yearOfStudyLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("subjYearOfStudyLbl"));
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
		yearOfStudyField.setPreferredSize(d3);
		
		JLabel numberECTSLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("subjECTSLbl"));
		JTextField numberECTSField = new JTextField();
		numberECTSField.setText(Integer.toString(s.getNumberECTS()));
		numberECTSLabel.setPreferredSize(d2);
		numberECTSField.setPreferredSize(d3);
		
		
		Dimension d4 = new Dimension(162, 33);
		JLabel professorLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("subjProfessorLbl"));
				
		JTextField professorField = new JTextField();
		
		
		professorLabel.setPreferredSize(d2);
		professorField.setPreferredSize(d4);
		
		JButton addButton = new JButton();
		JButton deleteButton = new JButton();
		
		deleteButton.setEnabled(false);
		for(Professor pr: DataClass.getInstance().getProfessorListData()) {
			if(pr.getIdNumber().equals(s.getSubjectProfessor())) {
				professorField.setText(pr.getName() + " " + pr.getLastname() );
				addButton.setEnabled(false);
				deleteButton.setEnabled(true);
				break;
			}
		}
		
		
		addButton.setPreferredSize(new Dimension(33,33));
		deleteButton.setPreferredSize(new Dimension(33,33));
		
		ImageIcon newIcon = new ImageIcon(newIconPNG);
		Image newImage = newIcon.getImage().getScaledInstance(33, 33, Image.SCALE_DEFAULT);
		addButton.setIcon(new ImageIcon(newImage));
		
		professorField.setFocusable(false);
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AddProfessorToSubject(s, getLocation(),getSize());
				
				for(Professor pr: DataClass.getInstance().getProfessorListData()) {
					if(pr.getIdNumber().equals(s.getSubjectProfessor())) {
						professorField.setText(pr.getName() + " " + pr.getLastname() );
						addButton.setEnabled(false);
						deleteButton.setEnabled(true);
						break;
					}
				}
				
			}
			
		});
		
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] options = {MainWindow.getInstance().getResourceBundle().getString("yesButton"),MainWindow.getInstance().getResourceBundle().getString("noButton")};
    			int result = JOptionPane.showOptionDialog( (getRootPane()), 
    					MainWindow.getInstance().getResourceBundle().getString("deleteProfFromSubjLbl"), MainWindow.getInstance().getResourceBundle().getString("warning"),
			            JOptionPane.WARNING_MESSAGE, JOptionPane.WARNING_MESSAGE, null, options,"");
			        if (result == JOptionPane.YES_OPTION) {				        	
			        	s.setSubjectProfessor("");
						professorField.setText("");
						addButton.setEnabled(true);
						deleteButton.setEnabled(false);
			        	
			        }
			        else if (result == JOptionPane.NO_OPTION) {
			        }
				
			}
			
		});
		
		ImageIcon deleteIcon = new ImageIcon(deleteIconPNG);
		Image deleteImage = deleteIcon.getImage().getScaledInstance(33, 33, Image.SCALE_DEFAULT);
		deleteButton.setIcon(new ImageIcon(deleteImage));
		
		
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
		fieldsPanel.add(professorLabel);
		fieldsPanel.add(professorField);
		fieldsPanel.add(addButton);
		fieldsPanel.add(deleteButton);
		
		mainPanel.setBorder(new EmptyBorder(20,30,20,30));
		((BorderLayout)mainPanel.getLayout()).setVgap(20);
		
		((FlowLayout)fieldsPanel.getLayout()).setVgap(15);
		((FlowLayout)fieldsPanel.getLayout()).setHgap(20);
		
		buttonsPanel.setLayout(new FlowLayout());
		
		JButton acceptButton = new JButton(MainWindow.getInstance().getResourceBundle().getString("acceptButton"));
		JButton cancelButton = new JButton(MainWindow.getInstance().getResourceBundle().getString("cancelButton"));
		
		acceptButton.setPreferredSize(new Dimension(110,30));
		cancelButton.setPreferredSize(new Dimension(110,30));
		
		JPanel spacer = new JPanel();
		spacer.setPreferredSize(new Dimension(10,30));
		
		buttonsPanel.add(acceptButton);
		buttonsPanel.add(spacer);
		buttonsPanel.add(cancelButton);
		
		acceptButton.setEnabled(true);
		
		cancelButton.addActionListener( e -> { this.dispose(); });
		

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
				if(s.getSubjectCode().equals(subjectCodeField.getText())) {
					SubjectController con = new SubjectController(subjectCodeField.getText(), titleField.getText(), semesterField.getSelectedIndex(),
							yearOfStudyField.getSelectedIndex(), numberECTSField.getText(), s.getSubjectProfessor(), true);
					String err = con.editSubject(s.getSubjectCode());
					if(err.equals("Sve je dobro!")) {
						changesMade = true;
						dispose();
						
					}
					else {
						String[] options = {"OK"};
		    			int result = JOptionPane.showOptionDialog((getRootPane()), 
		    					MainWindow.getInstance().getResourceBundle().getString("takenSubjCodeLbl"), MainWindow.getInstance().getResourceBundle().getString("error"),
					            JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, options,"");
					}
				}
				else if(!s.getSubjectCode().equals(subjectCodeField.getText())) {
					SubjectController con = new SubjectController(subjectCodeField.getText(), titleField.getText(), semesterField.getSelectedIndex(),
							yearOfStudyField.getSelectedIndex(), numberECTSField.getText(), s.getSubjectProfessor());
					String err = con.editSubject(s.getSubjectCode());
					if(err.equals("Sve je dobro!")) {
						changesMade = true;
						dispose();
						
						
					}
					else {
						String[] options = {"OK"};
		    			int result = JOptionPane.showOptionDialog((getRootPane()), 
		    					MainWindow.getInstance().getResourceBundle().getString("takenSubjCodeLbl"), MainWindow.getInstance().getResourceBundle().getString("error"),
					            JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, options,"");
					}
				}
			}
		});
		
		mainPanel.add(fieldsPanel, BorderLayout.CENTER);
		mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
		this.setContentPane(mainPanel);
		
		
		this.setVisible(true);
	}
}
