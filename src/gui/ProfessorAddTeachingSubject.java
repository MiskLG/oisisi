package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import controller.ProfessorController;
import controller.SubjectController;
import main.DataClass;
import model.Grade;
import model.Professor;
import model.Student;
import model.Subject;
import model.UnfinishedSubjects;

public class ProfessorAddTeachingSubject extends JDialog{
	
	private JTable ableToAdd;
	private JTable teachingSubjectsTable;
	private String professorId;
	
	public ProfessorAddTeachingSubject(String id, JTable table) {
		this.setModal(true);
		this.setResizable(false);
		
		this.professorId = id;
		this.teachingSubjectsTable = table;
		
		double widthRatio = 40./100;
		double heightRatio = 95./100;
		
		Double minX = 1000 * widthRatio;
		Double minY = 400 * heightRatio;
		
		this.setLayout(new BorderLayout());
		
		Double sizeX = minX;
		Double sizeY = minY;
		
		this.setLocationRelativeTo(this.getParent());
		
		setSize(sizeX.intValue(), sizeY.intValue());
		
		this.setTitle("Dodaj predmet");
		
		JPanel labelPanel = new JPanel(new BorderLayout());
		JLabel subjectLabel = new JLabel("Predmeti:");
		labelPanel.add(subjectLabel, BorderLayout.WEST);
		
		JButton acceptButton = new JButton("Potvrdi");
		JButton cancelButton = new JButton("Odustani");
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(acceptButton);
		buttonPanel.add(cancelButton);
		
		String[] colHeadingsToAdd = {"Šifra predmeta", "Naziv predmeta"};
		DefaultTableModel toAddModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		toAddModel .setColumnIdentifiers(colHeadingsToAdd);
		
		ableToAdd = new JTable(toAddModel ) {
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
		
		ableToAdd.setBorder(new LineBorder(Color.black, 1));
		ableToAdd.setRowHeight(24);
		
		JScrollPane tablePane = new JScrollPane(ableToAdd);
		

		acceptButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(ableToAdd.getSelectedRow() != -1){
	    			String[] options = {"Da","Ne"};
	    			int result = JOptionPane.showOptionDialog( (getRootPane()), 
		    				"Da li želite da dodate izabrani predmet?", "Potvrditi!",
				            JOptionPane.WARNING_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options,"");
				        if (result == JOptionPane.YES_OPTION) {				        	
				        	ProfessorController con = new ProfessorController();
				        	if(con.addTeachingSubject(professorId, ableToAdd.getValueAt(ableToAdd.getSelectedRow(), 0).toString())) {
				        		fillData();
				        	}
				        	
				        }
				        else if (result == JOptionPane.NO_OPTION) {
				        }
	    		}
				else {
	    			String[] options = {"OK"};
	    			int result = JOptionPane.showOptionDialog((getRootPane()), 
		    				"Niste izabrali predmet koji želite da dodate!", "GREŠKA!",
				            JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, options,"");
	    		}
				
			}
			
		});
		
		
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
			
		});
		
		tablePane.setBorder(new EmptyBorder(15,25,15,25));
		
		this.add(labelPanel, BorderLayout.NORTH);
		this.add(tablePane,BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		fillData();
		this.setVisible(true);
	}
	
	private void fillData() {
		ArrayList<Professor> professors = DataClass.getInstance().getProfessorListData();
		
		updateTeachingSubjects();
		
		
		int number = 0;
		for(Professor pr: professors) {
			if(pr.getIdNumber().equals(professorId)) {
				break;
			}
			number++;
		}
		
		while(ableToAdd.getModel().getRowCount() > 0) {
			((DefaultTableModel) ableToAdd.getModel()).removeRow(0);
		}
		
		
		for(Subject sb: DataClass.getInstance().getSubjectListData()) {
			int indicator = 0;
			
			if(sb.getSubjectProfessor().equals(professors.get(number).getIdNumber())) {
				indicator = 1;
			}
			
			if(indicator == 1) {
				continue;
			}
			for(String sub: professors.get(number).getListSubjects()) {
				if(sub.equals(sb.getSubjectCode())) {
					indicator = 1;
					break;
				}
			}
			if(indicator == 0) {
				String[] subjectData = {sb.getSubjectCode(),sb.getTitle()};
				((DefaultTableModel) ableToAdd.getModel()).addRow(subjectData);
			}	
		}			
		
	}
	
	public void updateTeachingSubjects() {
		ArrayList<Professor> professors = DataClass.getInstance().getProfessorListData();
		
		int number = 0;
		for(Professor pr: professors) {
			if(pr.getIdNumber().equals(professorId)) {
				break;
			}
			number++;
		}
		
		while(teachingSubjectsTable.getModel().getRowCount() > 0) {
			((DefaultTableModel) teachingSubjectsTable.getModel()).removeRow(0);
		}
		
		for(Subject sub: DataClass.getInstance().getSubjectListData()) {
			if ((professors.get(number).getIdNumber()).equals(sub.getSubjectProfessor())) {
				String[] subjectData = {sub.getSubjectCode(), sub.getTitle(), Integer.toString(sub.getYearOfStudy()), sub.getSemester()};
				((DefaultTableModel) teachingSubjectsTable.getModel()).addRow(subjectData);
			}
		}
	}
	
}
