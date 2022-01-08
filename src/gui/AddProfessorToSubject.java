package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import controller.SubjectController;
import main.DataClass;
import model.Grade;
import model.Professor;
import model.Subject;
import model.UnfinishedSubjects;

public class AddProfessorToSubject extends JDialog{
	
	JTable professorTable;
	
	public AddProfessorToSubject(Subject s) {
		this.setModal(true);
		this.setResizable(false);
		
		double widthRatio = 40./100;
		double heightRatio = 95./100;
		
		Double minX = 1000 * widthRatio;
		Double minY = 400 * heightRatio;
		
		this.setLayout(new BorderLayout());
		
		Double sizeX = minX;
		Double sizeY = minY;
		
		this.setLocationRelativeTo(this.getParent());
		
		setSize(sizeX.intValue(), sizeY.intValue());
		
		this.setTitle("Dodavanje profesora na predmet");
		
		JButton leaveButton = new JButton("Odustani");
		JButton confirmButton = new JButton("Potvrdi");
		

		String[] professorTableHead = {"Professor", "Sifra"};
		DefaultTableModel professorTableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		professorTableModel.setColumnIdentifiers(professorTableHead);
		
		professorTable = new JTable(professorTableModel) {
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
		professorTable.setBorder(new LineBorder(Color.black, 1));
		professorTable.setRowHeight(24);
		
		TableColumnModel tcm = professorTable.getColumnModel();
		tcm.removeColumn( tcm.getColumn(1));

		
		confirmButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(professorTable.getSelectedRow() != -1) {
					s.setSubjectProfessor(professorTable.getModel().getValueAt(professorTable.getSelectedRow(), 1).toString());
									
					dispose();
				}
				else {
					String[] options = {"OK"};
	    			int result = JOptionPane.showOptionDialog((getRootPane()), 
		    				"Niste izabrali profesora!", "GREŠKA!",
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
		
		JPanel buttonPane = new JPanel();
		buttonPane.add(confirmButton);
		buttonPane.add(leaveButton);
		
		JScrollPane tablePanel = new JScrollPane(professorTable);
		
		// Borders for tables to make them not glued to pane
		tablePanel.setBorder(new EmptyBorder(15,25,15,25));
		
		this.add(buttonPane,BorderLayout.SOUTH);
		this.add(tablePanel,BorderLayout.CENTER);
		
		fillData();
		
		this.setVisible(true);
	}
	
	private void fillData() {
		while(professorTable.getModel().getRowCount() > 0) {
			((DefaultTableModel) professorTable.getModel()).removeRow(0);
		}
		
		
		for(Professor pr: DataClass.getInstance().getProfessorListData()) {
			String[] professorData = {pr.getName()+ " " + pr.getLastname(), pr.getIdNumber()};
			((DefaultTableModel) professorTable.getModel()).addRow(professorData);
		}
	}

}
