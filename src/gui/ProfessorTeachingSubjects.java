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

import controller.ProfessorController;
import main.DataClass;
import model.Professor;
import model.Subject;

public class ProfessorTeachingSubjects extends JPanel{
	
	private JTable teachingSubjectsTable;
	private ArrayList<String> listTeachingSubjects = new ArrayList<String>();
	private String professorId = "";
	
	public ProfessorTeachingSubjects(Professor p, EditProfessor pan) {
		listTeachingSubjects = p.getListSubjects();
		professorId = p.getIdNumber();
		this.setLayout(new BorderLayout());
		
		JButton addButton = new JButton(MainWindow.getInstance().getResourceBundle().getString("profAddTeachingSubjButton"));
		JButton deleteButton = new JButton(MainWindow.getInstance().getResourceBundle().getString("profDeleteTeachingSubjButton"));

		
		String[] colHeadingsTeaching = {MainWindow.getInstance().getResourceBundle().getString("tableColSubjCode"), MainWindow.getInstance().getResourceBundle().getString("tableColSubjName"),
				MainWindow.getInstance().getResourceBundle().getString("tableColYearOfStudy"), MainWindow.getInstance().getResourceBundle().getString("tableColSubjSemester")};
		DefaultTableModel teachingModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		teachingModel.setColumnIdentifiers(colHeadingsTeaching);
		
		teachingSubjectsTable = new JTable(teachingModel) {
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
		teachingSubjectsTable.setBorder(new LineBorder(Color.black, 1));
		teachingSubjectsTable.setRowHeight(24);
		
		JPanel buttonPanel = new JPanel();
		
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ProfessorAddTeachingSubject(professorId, teachingSubjectsTable, pan.getLocation(), pan.getSize());
				updateTeachingSubjects(); 				
			}
			
		});
		
		deleteButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if(teachingSubjectsTable.getSelectedRow() != -1){
		    			String[] options = {MainWindow.getInstance().getResourceBundle().getString("yesButton"),MainWindow.getInstance().getResourceBundle().getString("noButton")};
		    			int result = JOptionPane.showOptionDialog( (getRootPane()), 
		    					MainWindow.getInstance().getResourceBundle().getString("removingSubjLbl"), MainWindow.getInstance().getResourceBundle().getString("warning"),
					            JOptionPane.WARNING_MESSAGE, JOptionPane.WARNING_MESSAGE, null, options,"");
					        if (result == JOptionPane.YES_OPTION) {	
					        	ProfessorController con = new ProfessorController();
					        	if(con.deleteTeachingSubject(professorId, teachingSubjectsTable.getValueAt(teachingSubjectsTable.getSelectedRow(), 0).toString())) {
					        		updateTeachingSubjects();
					        	}
					        	
					        }
					        else if (result == JOptionPane.NO_OPTION) {
					        }
		    		}
					else {
		    			String[] options = {"OK"};
		    			int result = JOptionPane.showOptionDialog((getRootPane()), 
		    					MainWindow.getInstance().getResourceBundle().getString("delSubjSelectionFailedLbl"), MainWindow.getInstance().getResourceBundle().getString("error"),
					            JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, options,"");
		    		}
					
				}
				
		});
		
		JScrollPane teachingSubjectsPane = new JScrollPane(teachingSubjectsTable);
		
		teachingSubjectsPane.setBorder(new EmptyBorder(15, 25, 15, 25));
		
		buttonPanel.add(addButton);
		buttonPanel.add(deleteButton);
		
		this.add(buttonPanel, BorderLayout.NORTH);
		this.add(teachingSubjectsPane, BorderLayout.CENTER);
		
		updateTeachingSubjects();
			
		this.setVisible(true);
		
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
