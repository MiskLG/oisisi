package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


public class TablePanel extends JTabbedPane {
	
	public TablePanel() {
		
		this.setBorder(new EmptyBorder(15,25,15,25));
		
		// Setting up columns - students
		String[] colHeadingsStudent = {"Indeks","Ime","Prezime","Godina Studija","Status","Prosek"};
		DefaultTableModel studentModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		studentModel.setColumnIdentifiers(colHeadingsStudent);
		JTable studentsTable = new JTable(studentModel) {
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
		studentsTable.setBorder(new LineBorder(Color.black, 1));
		studentsTable.setRowHeight(24);
		
		// Setting up columns - professors 
		String[] colHeadingsProfessor = {"Ime","Prezime","Zvanje","E-mail Adresa"};
		DefaultTableModel professorModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		professorModel.setColumnIdentifiers(colHeadingsProfessor);
		JTable professorsTable = new JTable(professorModel) {
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
		professorsTable.setBorder(new LineBorder(Color.black, 1));
		professorsTable.setRowHeight(24);
		
		// Setting up columns - subjects 
		String[] colHeadingsSubjects = {"Sifra Predmeta", "Naziv Predmeta", "ESPB", "Godina Studija", "Semestar"};
		DefaultTableModel subjectModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		subjectModel.setColumnIdentifiers(colHeadingsSubjects);
		JTable subjectsTable = new JTable(subjectModel) {
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
		subjectsTable.setBorder(new LineBorder(Color.black, 1));
		subjectsTable.setRowHeight(24);
		
		
		// Load data from database TODO
		
		//
		studentsTable.setAutoCreateRowSorter(true);
		professorsTable.setAutoCreateRowSorter(true);
		subjectsTable.setAutoCreateRowSorter(true);
		
		// Linking tables with scrollPanes
		JScrollPane studentsTablePanel = new JScrollPane(studentsTable);
		JScrollPane professorsTablePanel = new JScrollPane(professorsTable);
		JScrollPane subjectsTablePanel = new JScrollPane(subjectsTable);
		
		// Borders for tables to make them not glued to pane
		studentsTablePanel.setBorder(new EmptyBorder(15,25,15,25));
		professorsTablePanel.setBorder(new EmptyBorder(15,25,15,25));
		subjectsTablePanel.setBorder(new EmptyBorder(15,25,15,25));
		
		this.add("Studenti",studentsTablePanel);
		this.add("Profesori",professorsTablePanel);
		this.add("Predmeti",subjectsTablePanel);

		
		// placeholders
		studentModel.addRow(colHeadingsStudent);
		studentModel.addRow(colHeadingsStudent);
		studentModel.addRow(colHeadingsStudent);
		studentModel.addRow(colHeadingsStudent);
		studentModel.addRow(colHeadingsStudent);
		studentModel.addRow(colHeadingsStudent);
		studentModel.addRow(colHeadingsStudent);
		
		professorModel.addRow(colHeadingsProfessor);
		professorModel.addRow(colHeadingsProfessor);
		professorModel.addRow(colHeadingsProfessor);
		professorModel.addRow(colHeadingsProfessor);
		professorModel.addRow(colHeadingsProfessor);
		professorModel.addRow(colHeadingsProfessor);
		professorModel.addRow(colHeadingsProfessor);
		
		subjectModel.addRow(colHeadingsSubjects);
		subjectModel.addRow(colHeadingsSubjects);
		subjectModel.addRow(colHeadingsSubjects);
		subjectModel.addRow(colHeadingsSubjects);
		subjectModel.addRow(colHeadingsSubjects);
		subjectModel.addRow(colHeadingsSubjects);
		
		

	}
}
