package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
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

public class StudentAddUnfinished extends JDialog{
	
	private JTable ableToAdd;
	private String index;
	private JTable unfinishedTable;
	
	public StudentAddUnfinished(String index, JTable tab) {
		this.setModal(true);
		this.setResizable(false);
		
		this.index = index;
		this.unfinishedTable = tab;
		
		double widthRatio = 40./100;
		double heightRatio = 95./100;
		
		Double minX = 1000 * widthRatio;
		Double minY = 400 * heightRatio;
		
		this.setLayout(new BorderLayout());
		
		Double sizeX = minX;
		Double sizeY = minY;
		
		this.setLocationRelativeTo(this.getParent());
		
		setSize(sizeX.intValue(), sizeY.intValue());
		
		this.setTitle("Dodavanje predmeta");
		
		JButton addButton = new JButton("Dodaj");
		JButton leaveButton = new JButton("Odustani");
		
		String[] colHeadingsToAdd = {"Sifra Predmeta", "Naziv Predmeta"};
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
		
		
		JPanel buttonPane = new JPanel();
		buttonPane.add(addButton);
		buttonPane.add(leaveButton);
		
		JScrollPane tablePane = new JScrollPane(ableToAdd);

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(ableToAdd.getSelectedRow() != -1){
	    			String[] options = {"Da","Ne"};
	    			int result = JOptionPane.showOptionDialog( (getRootPane()), 
		    				"Da li želite da dodate izabrani predmet?", "Potvrditi!",
				            JOptionPane.WARNING_MESSAGE, JOptionPane.WARNING_MESSAGE, null, options,"");
				        if (result == JOptionPane.YES_OPTION) {				        	
				        	SubjectController con = new SubjectController();
				        	if(con.addUnfinishedSubject(index, ableToAdd.getValueAt(ableToAdd.getSelectedRow(), 0).toString())) {
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
		
		leaveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
			
		});
		tablePane.setBorder(new EmptyBorder(15,25,15,25));

		buttonPane.add(addButton);
		buttonPane.add(leaveButton);
		
		this.add(buttonPane, BorderLayout.SOUTH);
		this.add(tablePane,BorderLayout.CENTER);
		
		fillData();
		this.setVisible(true);
	}
	
	private void fillData() {
		ArrayList<Student> students = DataClass.getInstance().getStudentListData();
		
		updateUnfinished();
		
		int number = 0;
		for(Student st : students) {
			if(st.getIndex().equals(index)) {
				break;
			}
			number++;
		}
		
		while(ableToAdd.getModel().getRowCount() > 0) {
			((DefaultTableModel) ableToAdd.getModel()).removeRow(0);
		}
		
		
		for(Subject sb: DataClass.getInstance().getSubjectListData()) {
			int indicator = 0;
			for(UnfinishedSubjects sub: students.get(number).getListUnfinished()) {
				if(sub.getSubjectCode().equals(sb.getSubjectCode())) {
					indicator = 1;
					break;
				}
			}
			if(indicator == 1) {
				continue;
			}
			for(Grade sub: students.get(number).getListPassed()) {
				if(sub.getSubjectCode().equals(sb.getSubjectCode())) {
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
	public void updateUnfinished() {
		ArrayList<Student> students = DataClass.getInstance().getStudentListData();
		
		
		int number = 0;
		for(Student st : students) {
			if(st.getIndex().equals(index)) {
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
}
