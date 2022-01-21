package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
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
import javax.swing.table.TableColumnModel;

import controller.ChairController;
import main.DataClass;
import model.Chair;
import model.Professor;

public class AddProfessorToChairWindow extends JDialog {
	private JTable ableToAdd;
	private String chairCode;
	private JTable professorTable;
	
	public AddProfessorToChairWindow(String chairCode, JTable tab, Point location, Dimension size) {
		this.setModal(true);
		this.setResizable(false);
		
		this.chairCode = chairCode;
		this.professorTable = tab;
		
		double widthRatio = 40./100;
		double heightRatio = 95./100;
		
		Double minX = 1000 * widthRatio;
		Double minY = 400 * heightRatio;
		
		this.setLayout(new BorderLayout());
		
		Double sizeX = minX;
		Double sizeY = minY;
		
		Double locationX = location.getX() + size.getWidth()/2 - minX /2 ;
		Double locationY = location.getY() + size.getHeight()/2 - minY / 2;
		
		setLocation(locationX.intValue() , locationY.intValue() );
		
		setSize(sizeX.intValue(), sizeY.intValue());
		
		this.setTitle(MainWindow.getInstance().getResourceBundle().getString("addProfDialName"));
		
		JButton addButton = new JButton(MainWindow.getInstance().getResourceBundle().getString("addButton"));
		JButton leaveButton = new JButton(MainWindow.getInstance().getResourceBundle().getString("cancelButton"));
		
		String[] colHeadingsToAdd = {MainWindow.getInstance().getResourceBundle().getString("tableColName"), MainWindow.getInstance().getResourceBundle().getString("tableColLastname"),
			MainWindow.getInstance().getResourceBundle().getString("tableColEmail"), MainWindow.getInstance().getResourceBundle().getString("tableColTitle"),"ID"};
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
	    			String[] options = {MainWindow.getInstance().getResourceBundle().getString("yesButton"),MainWindow.getInstance().getResourceBundle().getString("noButton")};
	    			int result = JOptionPane.showOptionDialog( (getRootPane()), 
	    					MainWindow.getInstance().getResourceBundle().getString("addProfToChairLbl"), MainWindow.getInstance().getResourceBundle().getString("confirmationLbl"),
				            JOptionPane.WARNING_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options,"");
				        if (result == JOptionPane.YES_OPTION) {				        	
				        	ChairController con = new ChairController();
				        	con.addProfessorToChair(chairCode, ableToAdd.getModel().getValueAt(ableToAdd.getSelectedRow(), 4).toString());
				        	System.out.println(ableToAdd.getModel().getValueAt(ableToAdd.getSelectedRow(), 4).toString());
				        	fillData();
				        	
				        	
				        }
				        else if (result == JOptionPane.NO_OPTION) {
				        }
	    		}
				else {
	    			String[] options = {"OK"};
	    			int result = JOptionPane.showOptionDialog((getRootPane()), 
	    					MainWindow.getInstance().getResourceBundle().getString("addChairProfSelectFailed"), MainWindow.getInstance().getResourceBundle().getString("error"),
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
		
		TableColumnModel tcm = ableToAdd.getColumnModel();
		tcm.removeColumn( tcm.getColumn(4));

		buttonPane.add(addButton);
		buttonPane.add(leaveButton);
		
		this.add(buttonPane, BorderLayout.SOUTH);
		this.add(tablePane,BorderLayout.CENTER);
		
		fillData();
		this.setVisible(true);
	}
	
	private void fillData() {
		ArrayList<Professor> profs= DataClass.getInstance().getProfessorListData();
		ArrayList<Chair> chairs= DataClass.getInstance().getChairListData();
		
		updateProfessors();
		
		
		while(ableToAdd.getModel().getRowCount() > 0) {
			((DefaultTableModel) ableToAdd.getModel()).removeRow(0);
		}
		
		
		for(Professor pr: profs) {
			String[] data = {pr.getName(),pr.getLastname(), pr.getEmail(), pr.getTitle(), pr.getIdNumber()};
			((DefaultTableModel) ableToAdd.getModel()).addRow(data);		
		}
			
		
	}
	public void updateProfessors() {
		ArrayList<Chair> chairs = DataClass.getInstance().getChairListData();
		
		
		int number = 0;
		for(Chair ch : chairs) {
			if(ch.getChairCode().equals(chairCode)) {
				break;
			}
			number++;
		}
		
		while(professorTable.getModel().getRowCount() > 0) {
			((DefaultTableModel) professorTable.getModel()).removeRow(0);
		}
		
		for(String profID: chairs.get(number).getProfessorList() ){
			for(Professor pr: DataClass.getInstance().getProfessorListData()) {
				if(pr.getIdNumber().equals(profID)) {
					String[] data = {pr.getName(),pr.getLastname(), pr.getEmail(), pr.getTitle(), pr.getIdNumber()};
					((DefaultTableModel) professorTable.getModel()).addRow(data);
				}	
			}
			
		}
		
	}
}
