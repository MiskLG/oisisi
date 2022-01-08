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

public class StudentFinishedPanel extends JPanel{
	
	private JTable finishedSubjectsTable;
	private ArrayList<Grade> listFinishedSubjects = new ArrayList<Grade>();
	private String indexOfStudent = "";
	
	
	public StudentFinishedPanel() {};
	
	public StudentFinishedPanel(Student s) {
		listFinishedSubjects = s.getListPassed();
		this.setLayout(new BorderLayout());
		indexOfStudent = s.getIndex();
		
		JButton cancelGradeButton = new JButton("Poništi ocenu");
		JPanel buttonPanel = new JPanel();
		
		buttonPanel.add(cancelGradeButton);
		
		String[] colHeadingsFinished = {"Šifra predmeta", "Naziv predmeta", "ESPB", "Ocena", "Datum"};
		DefaultTableModel finishedModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		finishedModel.setColumnIdentifiers(colHeadingsFinished);
		
		finishedSubjectsTable = new JTable(finishedModel) {
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
		
		finishedSubjectsTable.setBorder(new LineBorder(Color.black, 1));
		finishedSubjectsTable.setRowHeight(24);
		
		cancelGradeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(finishedSubjectsTable.getSelectedRow() != -1) {
					String[] options = {"Da", "Ne"};
					int result = JOptionPane.showOptionDialog((getRootPane()),
							"Da li ste sigurni da želite da poništite ocenu?", "Poništavanje ocene",
							JOptionPane.WARNING_MESSAGE, JOptionPane.WARNING_MESSAGE, null, options, "");
						if(result == JOptionPane.YES_OPTION) {
							SubjectController con = new SubjectController();
							if(con.deleteFinishedSubject(indexOfStudent, finishedSubjectsTable.getValueAt(finishedSubjectsTable.getSelectedRow(), 0).toString())) {
				        		updateFinished();
				        	}
						}
						else if (result == JOptionPane.NO_OPTION) {
				        }
				}
				else {
	    			String[] options = {"OK"};
	    			int result = JOptionPane.showOptionDialog((getRootPane()), 
		    				"Niste izabrali predmet koji želite da obrišete!", "GREŠKA!",
				            JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, options,"");
	    		}
			}

		});
		
		JScrollPane finishedSubjectsTablePane = new JScrollPane(finishedSubjectsTable);
		
		
		finishedSubjectsTablePane.setBorder(new EmptyBorder(15, 25, 25, 25));
		
		this.add(buttonPanel, BorderLayout.NORTH);
		this.add(finishedSubjectsTablePane, BorderLayout.CENTER);
		
		updateFinished();
		
		this.setVisible(true);
	}
	
	public void updateFinished() {
		ArrayList<Student> students = DataClass.getInstance().getStudentListData();
		
		int number = 0;
		for(Student s: students) {
			if(s.getIndex().equals(indexOfStudent)) {
				break;
			}
			number++;
		}
		
		
		while(finishedSubjectsTable.getModel().getRowCount() > 0) {
			((DefaultTableModel) finishedSubjectsTable.getModel()).removeRow(0);
		}
					
		for(Grade gradedSubjects: students.get(number).getListPassed()) {
			for(Subject sub: DataClass.getInstance().getSubjectListData()) {
				if(gradedSubjects.getSubjectCode().equals(sub.getSubjectCode())) {
					String[] subjectData = {sub.getSubjectCode(), sub.getTitle(), Integer.toString(sub.getNumberECTS()), Integer.toString(gradedSubjects.getGrade()), gradedSubjects.getGradingDate().toString()};
					((DefaultTableModel) finishedSubjectsTable.getModel()).addRow(subjectData);
				}
			}
		}
	}

	public JTable getFinishedSubjectsTable() {
		return finishedSubjectsTable;
	}

	public void setFinishedSubjectsTable(JTable finishedSubjectsTable) {
		this.finishedSubjectsTable = finishedSubjectsTable;
	}
	
	
	
}
