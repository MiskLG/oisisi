package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import controller.RegXClass;
import controller.SubjectController;
import main.DataClass;
import model.Grade;
import model.Student;
import model.Subject;
import model.UnfinishedSubjects;

public class StudentAddFinished extends JDialog{
	private String index;
	private String subjectCode;
	private JTable unfinishedTable;
	
	private boolean validData = false;
	
	public StudentAddFinished(String index, String subjectCode, JTable table) {
		this.setModal(true);
		this.setResizable(false);
		
		this.index = index;
		this.subjectCode = subjectCode;
		this.unfinishedTable = table;
		
		setTitle("Unos ocene");
		
		setLocationRelativeTo(this.getParent());
		

		setSize(450, 350);
		
		SubjectController con = new SubjectController();
		Subject s = con.findSubjectByCode(subjectCode);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JPanel fieldsPanel = new JPanel();
		JPanel buttonsPanel = new JPanel();
		
		Dimension d = new Dimension(340, 390);
		fieldsPanel.setLayout(new FlowLayout());
		fieldsPanel.setPreferredSize(d);
		
		Dimension d2 = new Dimension(100, 33);//dimesion of label
		Dimension d3 = new Dimension(220, 33);//dimension of text field
		
		JLabel subjectCodeLabel = new JLabel("Šifra*:");
		JTextField subjectCodeField = new JTextField();
		subjectCodeField.setText(s.getSubjectCode());
		subjectCodeLabel.setPreferredSize(d2);
		subjectCodeField.setPreferredSize(d3);
		subjectCodeField.setEditable(false);
		
		JLabel titleLabel = new JLabel("Naziv*:");
		JTextField titleField = new JTextField();
		titleField.setText(s.getTitle());
		titleLabel.setPreferredSize(d2);
		titleField.setPreferredSize(d3);
		titleField.setEditable(false);
		
		JLabel gradeLabel = new JLabel("Ocena*:");
		String  gradeChoices[] = {"6", "7", "8", "9", "10"};     
		JComboBox<String> gradeField = new JComboBox<String>(gradeChoices);
		
		gradeLabel.setPreferredSize(d2);
		gradeField.setPreferredSize(d3);
		
		JLabel dateLabel = new JLabel("Datum*:");
		JTextField dateField = new JTextField();
		
		dateLabel.setPreferredSize(d2);
		dateField.setPreferredSize(d3);
		
		fieldsPanel.add(subjectCodeLabel);
		fieldsPanel.add(subjectCodeField);
		fieldsPanel.add(titleLabel);
		fieldsPanel.add(titleField);
		fieldsPanel.add(gradeLabel);
		fieldsPanel.add(gradeField);
		fieldsPanel.add(dateLabel);
		fieldsPanel.add(dateField);
		
		mainPanel.setBorder(new EmptyBorder(20,30,20,30));
		((BorderLayout)mainPanel.getLayout()).setVgap(20);
		
		((FlowLayout)fieldsPanel.getLayout()).setVgap(15);
		((FlowLayout)fieldsPanel.getLayout()).setHgap(15);
		
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
		

		dateField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(true == RegXClass.checkDate(dateField.getText() + e.getKeyChar())) {
					validData = true;
					dateField.setBorder(new MatteBorder(1,1,1,1,Color.GRAY));
				}
				else {
					validData = false;
					dateField.setBorder(new MatteBorder(1,1,1,1,Color.red));
				}
				if(validData) {
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
				if(con.addFinishedSubject(index, subjectCodeField.getText(),
						gradeField.getSelectedIndex(), dateField.getText())) {
					con.deleteUnfinishedSubject(index, subjectCode);
					dispose();
				}
				
			}
		});
		
		mainPanel.add(fieldsPanel, BorderLayout.CENTER);
		mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
		this.setContentPane(mainPanel);
		
		this.setVisible(true);
	}
	
}
