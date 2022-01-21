package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import controller.SubjectController;
import main.DataClass;
import model.Grade;
import model.Student;
import model.Subject;
import model.UnfinishedSubjects;

public class StudentUnfinishedPanel extends JPanel{

	private JTable unfinishedTable;
	
	private String studentIndex = "";
	
	public StudentUnfinishedPanel(Student s, EditStudent pan) {
		
		s.getListUnfinished();
		this.setLayout(new BorderLayout());
		
		studentIndex = s.getIndex();
		
		JButton addButton = new JButton(MainWindow.getInstance().getResourceBundle().getString("addButton"));
		JButton deleteButton = new JButton(MainWindow.getInstance().getResourceBundle().getString("delete"));
		JButton finishButton = new JButton(MainWindow.getInstance().getResourceBundle().getString("finishButton"));
		
		JPanel buttonPane = new JPanel();
		
		String[] colHeadingsUnfinished = {MainWindow.getInstance().getResourceBundle().getString("tableColSubjCode"), MainWindow.getInstance().getResourceBundle().getString("tableColSubjName"), 
				MainWindow.getInstance().getResourceBundle().getString("tableColSubjECTS"), MainWindow.getInstance().getResourceBundle().getString("tableColYearOfStudy"),
				MainWindow.getInstance().getResourceBundle().getString("tableColSubjSemester")};
		DefaultTableModel unfinishedModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		unfinishedModel.setColumnIdentifiers(colHeadingsUnfinished);
		
		unfinishedTable = new JTable(unfinishedModel) {
			// taken from https://stackhowto.com/how-to-alternate-row-color-of-jtable-in-java/
			public Component prepareRenderer(TableCellRenderer renderer, 
		         int row, int column) 
		         {
		            Component c = super.prepareRenderer(renderer, row, column);
		            Color color1 = new Color(220,220,220);
		            Color color2 = Color.WHITE;
		            if(!c.getBackground().equals(getSelectionBackground())) {
		               Color coleur = (row % 2 == 0 ? color1 : color2);
		               c.setBackground(coleur);
		               coleur = null;
		            }
		            return c;
		         }
		};
		unfinishedTable.setBorder(new LineBorder(Color.black, 1));
		unfinishedTable.setRowHeight(24);	
		
		
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(unfinishedTable.getSelectedRow() != -1){
	    			String[] options = {MainWindow.getInstance().getResourceBundle().getString("yesButton"),MainWindow.getInstance().getResourceBundle().getString("noButton")};
	    			int result = JOptionPane.showOptionDialog( (getRootPane()), 
	    					MainWindow.getInstance().getResourceBundle().getString("removingSubjLbl"), MainWindow.getInstance().getResourceBundle().getString("warning"),
				            JOptionPane.WARNING_MESSAGE, JOptionPane.WARNING_MESSAGE, null, options,"");
				        if (result == JOptionPane.YES_OPTION) {				        	
				        	SubjectController con = new SubjectController();
				        	if(con.deleteUnfinishedSubject(studentIndex, unfinishedTable.getValueAt(unfinishedTable.getSelectedRow(), 0).toString())) {
				        		updateUnfinished();
				        	}
				        	
				        }
				        else if (result == JOptionPane.NO_OPTION) {
				        }
	    		}
				else {
	    			String[] options = {"OK"};
	    			JOptionPane.showOptionDialog((getRootPane()), 
	    					MainWindow.getInstance().getResourceBundle().getString("delSubjSelectionFailedLbl"), MainWindow.getInstance().getResourceBundle().getString("error"),
				            JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, options,"");
	    		}
				
			}
			
		});
		
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new StudentAddUnfinished(studentIndex,unfinishedTable, pan.getLocation(), pan.getSize());
				updateUnfinished();
			}
			
		});
		

		finishButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(unfinishedTable.getSelectedRow() != -1){	  
					String subCode = unfinishedTable.getModel().getValueAt(unfinishedTable.getSelectedRow(), 0).toString();
					new StudentAddFinished(studentIndex, subCode, unfinishedTable, pan.getLocation(), pan.getSize());
					updateUnfinished();	
	        		calculateAverageGrade(s);
	    		}
				else {
	    			String[] options = {"OK"};
	    			JOptionPane.showOptionDialog((getRootPane()), 
	    					MainWindow.getInstance().getResourceBundle().getString("passSubjSelectionFailedLbl"), MainWindow.getInstance().getResourceBundle().getString("error"),
				            JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, options,"");
	    		}				
			}
			
		});
		
		
		JScrollPane unfinishedTablePane = new JScrollPane(unfinishedTable);

		// Borders for tables to make them not glued to pane
		unfinishedTablePane.setBorder(new EmptyBorder(15,25,15,25));


		buttonPane.add(addButton);
		buttonPane.add(deleteButton);
		buttonPane.add(finishButton);
		
		this.add(buttonPane, BorderLayout.NORTH);
		this.add(unfinishedTablePane,BorderLayout.CENTER);
		
		updateUnfinished();
		
		this.setVisible(true);
	}
	
	public void updateUnfinished() {
		ArrayList<Student> students = DataClass.getInstance().getStudentListData();
		
		
		int number = 0;
		for(Student st : students) {
			if(st.getIndex().equals(studentIndex)) {
				break;
			}
			number++;
		}
		
		while(unfinishedTable.getModel().getRowCount() > 0) {
			((DefaultTableModel) unfinishedTable.getModel()).removeRow(0);
		}
		
		for(UnfinishedSubjects sub: students.get(number).getListUnfinished()) {
			for(Subject sb: DataClass.getInstance().getSubjectListData()) {
				if(sub.getSubjectCode().equals(sb.getSubjectCode())) {
					String[] subjectData = {sb.getSubjectCode(),sb.getTitle(),Integer.toString(sb.getNumberECTS()),Integer.toString(sb.getYearOfStudy()),sb.getSemester()};
					((DefaultTableModel) unfinishedTable.getModel()).addRow(subjectData);
				}	
			}
			
		}
		
	}
	
public void calculateAverageGrade(Student s) {
		ArrayList<Grade> listFinishedSubjects = s.getListPassed();
	
		double sum = 0;
		int numberOfPassedSubjects = 0;
	
		for(Grade g: listFinishedSubjects) {
			sum = sum + g.getGrade();
			numberOfPassedSubjects++;
		}
		
		double avgGrade;
		if(numberOfPassedSubjects == 0) {
			avgGrade = 0;
		}
		else {
			avgGrade = sum/numberOfPassedSubjects;
		}
		
		
		// taken from https://mkyong.com/java/how-to-round-double-float-value-to-2-decimal-points-in-java/#mathround
		avgGrade = Math.round(avgGrade * 100.0) / 100.0;
		
		
		s.setAverageGrade(avgGrade);
		
		
	};
	
}
