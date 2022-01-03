package gui;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.DefaultRowSorter;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import main.DataClass;
import model.Professor;
import model.Student;
import model.Subject;


public class TablePanel extends JTabbedPane {
	
	private JTable studentsTable;
	private JTable professorsTable; 
	private JTable subjectsTable;
	
	private static AbstractTableModelStudents studentsModel = null;

	TableRowSorter<DefaultTableModel> subjectSorter = new TableRowSorter<DefaultTableModel>();
	TableRowSorter<DefaultTableModel> professorSorter = new TableRowSorter<DefaultTableModel>();
	TableRowSorter<DefaultTableModel> studentSorter = new TableRowSorter<DefaultTableModel>();

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
		studentsTable = new JTable(studentModel) {
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
		String[] colHeadingsProfessor = {"Prezime","Ime","Zvanje","E-mail Adresa","PIB"};
		DefaultTableModel professorModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			
		};
		
		professorModel.setColumnIdentifiers(colHeadingsProfessor);
		professorsTable = new JTable(professorModel) {
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
		subjectsTable = new JTable(subjectModel) {
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
		
		// making of the invisible row, useful for getting id from professor table
		TableColumnModel tcm = professorsTable.getColumnModel();
		tcm.removeColumn( tcm.getColumn(4));
		
		
		// sorters
		//professorsTable.setAutoCreateRowSorter(true);
	//	subjectsTable.setAutoCreateRowSorter(true);
		
		
		// overriding sorter for student index
		Comparator<String> comparator = new Comparator<String>() {
		    public int compare(String s1, String s2) {
		        String[] strings1 = s1.split("[-]");
		        String[] strings2 = s2.split("[-]");
		        if(strings1[0].equals(strings2[0])) {
		        	if(Integer.parseInt(strings1[2]) == Integer.parseInt(strings2[2])) {
			        	return Integer.parseInt(strings1[1]) - Integer.parseInt(strings2[1]);
			        }
		        	else {
		        		 return Integer.parseInt(strings1[2]) - Integer.parseInt(strings2[2]);
		        	}		       
		        }
		        return strings1[0].compareTo(strings2[0]);
		    }
		};
		

		studentsTable.setRowSorter(studentSorter);
		studentSorter.setModel(studentModel);
		studentSorter.setComparator(0, comparator);
		

		professorsTable.setRowSorter(professorSorter);
		professorSorter.setModel(professorModel);
		

		subjectsTable.setRowSorter(subjectSorter);
		subjectSorter.setModel(subjectModel);
		
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
		
		updateTable();

	}
	
	public void updateTable() {
		DataClass dc = DataClass.getInstance();
		
		ArrayList<Student> list1 = dc.getStudentListData();
		ArrayList<Professor> list2 = dc.getProfessorListData();
		ArrayList<Subject> list3 = dc.getSubjectListData();
		
		
		while(studentsTable.getModel().getRowCount() > 0) {
			((DefaultTableModel) studentsTable.getModel()).removeRow(0);
		}
		while(professorsTable.getModel().getRowCount() > 0) {
			((DefaultTableModel) professorsTable.getModel()).removeRow(0);
		}
		while(subjectsTable.getModel().getRowCount() > 0) {
			((DefaultTableModel) subjectsTable.getModel()).removeRow(0);
		}
		
		
		for(Student st: list1) {
			String[] studentData = {st.getIndex(),st.getName(),st.getLastname(),Integer.toString(st.getYearOfStudy()), st.getStatus(), Double.toString(st.getAverageGrade())} ;
			((DefaultTableModel) studentsTable.getModel()).addRow(studentData);
		}
		
		for(Professor pr: list2) {
			String[] professorData = {pr.getLastname(), pr.getName(), pr.getTitle(), pr.getEmail(), pr.getIdNumber()};
			((DefaultTableModel) professorsTable.getModel()).addRow(professorData);
			System.out.println(pr.getIdNumber());
		}
		
		for(Subject sb: list3) {
			String[] subjectData = {sb.getSubjectCode(),sb.getTitle(),Integer.toString(sb.getNumberECTS()),Integer.toString(sb.getYearOfStudy()),sb.getSemester(),};
			((DefaultTableModel) subjectsTable.getModel()).addRow(subjectData);
		}
		
	}

	public JTable getStudentsTable() {
		return studentsTable;
	}

	public void setStudentsTable(JTable studentsTable) {
		this.studentsTable = studentsTable;
	}

	public static AbstractTableModelStudents getStudentsModel() {
		return studentsModel;
	}

	public static void setStudentsModel(AbstractTableModelStudents studentsModel) {
		TablePanel.studentsModel = studentsModel;
	}
	
	public String getSelectedSubjectCode() {
		if(subjectsTable.getSelectedRow() != -1) {
			return subjectsTable.getValueAt(subjectsTable.getSelectedRow(), 0).toString(); 
		}
		return "-1";
	}
	
	public void setFilterProfessor(String filterString) {
	    
	    ArrayList< RowFilter<? super DefaultTableModel, ? super Integer>> a = new    ArrayList< RowFilter<? super DefaultTableModel, ? super Integer>>();
	    try {
	    	String[] filters = filterString.split("[,]");
	    	int i = 0;
	    	for(String filter: filters) {
	    		String correctedFilter = "(?i)"+filter.trim();
	    		RowFilter<? super DefaultTableModel, ? super Integer> rf  = RowFilter.regexFilter(correctedFilter, i);
	    		a.add(rf);
	    		i++;
	    		if(i == 3) {
	    			String[] options = {"OK"};
	    			int result = JOptionPane.showOptionDialog((getRootPane()), 
		    				"Previše unesenih parametara za pretragu!", "GREŠKA!",
				            JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, options,"");
	    			return;
	    		}
	    	}        
	    } catch (java.util.regex.PatternSyntaxException e) {
	    	System.out.println(e);
	    }
	    professorSorter.setRowFilter(RowFilter.andFilter(a));
	}
	public void setFilterSubject(String filterString) {
		 ArrayList< RowFilter<? super DefaultTableModel, ? super Integer>> a = new    ArrayList< RowFilter<? super DefaultTableModel, ? super Integer>>();
		    try {
		    	String[] filters = filterString.split("[,]");
		    	int i = 1;
		    	for(String filter: filters) {
		    		String correctedFilter = "(?i)"+filter.trim();
		    		RowFilter<? super DefaultTableModel, ? super Integer> rf  = RowFilter.regexFilter(correctedFilter, i);
		    		a.add(rf);
		    		i--;
		    		if(i == -2) {
		    			String[] options = {"OK"};
		    			int result = JOptionPane.showOptionDialog((getRootPane()), 
			    				"Previše unesenih parametara za pretragu!", "GREŠKA!",
					            JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, options,"");
		    			return;
		    		}
		    	}        
		    } catch (java.util.regex.PatternSyntaxException e) {
		    	System.out.println(e);
		    }
		    subjectSorter.setRowFilter(RowFilter.andFilter(a));
	}
	
}
