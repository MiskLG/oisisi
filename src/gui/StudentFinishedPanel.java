package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import controller.StudentController;
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
	
	private double averageGrade = 0;
	private int totalNumberOfECTS = 0;
	
	private JTextField averageGradeField = new JTextField();
	private JTextField totalNumberOfECTSField = new JTextField();
	
	public StudentFinishedPanel() {};
	
	public StudentFinishedPanel(Student s) {
		listFinishedSubjects = s.getListPassed();
		this.setLayout(new BorderLayout());
		indexOfStudent = s.getIndex();
		
		JButton cancelGradeButton = new JButton("Poništi ocenu");
		JPanel buttonPanel = new JPanel();
		
		
		JPanel infoPanel = new JPanel(new BorderLayout());
		infoPanel.setBorder(new EmptyBorder(15, 25, 25, 25));
		
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
				        		calculateAverageGrade();
				        		calculateECTS();
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
		
		
		Dimension d = new Dimension(100, 24);
		Dimension d2 = new Dimension(30, 24);
		
		calculateAverageGrade();
		
		JLabel averageGradeLabel = new JLabel("Prosečna ocena:");
		averageGradeField.setEditable(false);
		averageGradeField.setBackground(this.getBackground());
		averageGradeField.setBorder(null);
		averageGradeLabel.setPreferredSize(d);
		averageGradeField.setPreferredSize(d2);
		JPanel avgGradePanel = new JPanel();
		avgGradePanel.add(averageGradeLabel);
		avgGradePanel.add(averageGradeField);
		
		calculateECTS();
		
		JLabel totalNumberOfECTSLabel = new JLabel("Ukupno ESPB:");
		totalNumberOfECTSField.setEditable(false);
		totalNumberOfECTSField.setBackground(this.getBackground());
		totalNumberOfECTSField.setBorder(null);
		totalNumberOfECTSLabel.setPreferredSize(d);
		totalNumberOfECTSField.setPreferredSize(d2);
		JPanel numberECTSPanel = new JPanel();
		numberECTSPanel.add(totalNumberOfECTSLabel);
		numberECTSPanel.add(totalNumberOfECTSField);
		
		JPanel connectingPanel = new JPanel(new GridLayout(2, 1));
		
		connectingPanel.add(avgGradePanel);
		connectingPanel.add(numberECTSPanel);
		
		infoPanel.add(connectingPanel, BorderLayout.EAST);
		
		
		this.add(buttonPanel, BorderLayout.NORTH);
		this.add(finishedSubjectsTablePane, BorderLayout.CENTER);
		this.add(infoPanel, BorderLayout.SOUTH);
		
		
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
	
	public void calculateAverageGrade() {
		
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
		
		
		setAverageGrade(avgGrade);
		setAvgGradeField();
		
		
	};
	
	public void setAvgGradeField() {
		averageGradeField.setText(Double.toString(averageGrade));
	}
	
	public void calculateECTS() {
		ArrayList<Subject> subjects = DataClass.getInstance().getSubjectListData();
		
		int sum = 0;
	
		for(Grade g: listFinishedSubjects) {
			for(Subject s: subjects) {
				if(g.getSubjectCode().equals(s.getSubjectCode())) {
					sum = sum + s.getNumberECTS();
				}
			}
		}

		setTotalNumberOfECTS(sum);
		setNumOfECTSField();
		
	};

	public void setNumOfECTSField() {
		totalNumberOfECTSField.setText(Integer.toString(totalNumberOfECTS));
	}
	
	public JTable getFinishedSubjectsTable() {
		return finishedSubjectsTable;
	}

	public void setFinishedSubjectsTable(JTable finishedSubjectsTable) {
		this.finishedSubjectsTable = finishedSubjectsTable;
	}

	public double getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(double averageGrade) {
		this.averageGrade = averageGrade;
	}

	public int getTotalNumberOfECTS() {
		return totalNumberOfECTS;
	}

	public void setTotalNumberOfECTS(int totalNumberOfECTS) {
		this.totalNumberOfECTS = totalNumberOfECTS;
	}
	
	
	
}
