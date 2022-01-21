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

public class AddProfessorToChairPanel extends JDialog{

	private JTable professorTable;
	
	//private ArrayList<UnfinishedSubjects> listUnfinished = new ArrayList<UnfinishedSubjects>();
	private String chairCode = "";
	
	public AddProfessorToChairPanel(String chairCode, String chairName, Point location, Dimension size) {
		
		this.setLayout(new BorderLayout());
		
		this.chairCode = chairCode;
		this.setTitle(chairName);
		
		double widthRatio = 40./100;
		double heightRatio = 95./100;
		
		Double minX = 1000 * widthRatio;
		Double minY = 400 * heightRatio;
		Double sizeX = minX;
		Double sizeY = minY;
		
		this.setModal(true);
		
		Double locationX = location.getX() + size.getWidth()/2 - minX /2 ;
		Double locationY = location.getY() + size.getHeight()/2 - minY / 2;
		
		setLocation(locationX.intValue() , locationY.intValue() );
		
		setSize(sizeX.intValue(), sizeY.intValue());
		
		JButton addButton = new JButton(MainWindow.getInstance().getResourceBundle().getString("addButton"));
		JButton deleteButton = new JButton(MainWindow.getInstance().getResourceBundle().getString("delete"));
		
		JPanel buttonPane = new JPanel();
		
		String[] colHeadingsProfessor = {MainWindow.getInstance().getResourceBundle().getString("tableColName"), MainWindow.getInstance().getResourceBundle().getString("tableColLastname"),
				MainWindow.getInstance().getResourceBundle().getString("tableColEmail"), MainWindow.getInstance().getResourceBundle().getString("tableColTitle"),"Licni Broj"};
		DefaultTableModel professorModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		professorModel.setColumnIdentifiers(colHeadingsProfessor);
		
		professorTable = new JTable(professorModel) {
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
		
		
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(professorTable.getSelectedRow() != -1){
	    			String[] options = {MainWindow.getInstance().getResourceBundle().getString("yesButton"),MainWindow.getInstance().getResourceBundle().getString("noButton")};
	    			int result = JOptionPane.showOptionDialog( (getRootPane()), 
	    					MainWindow.getInstance().getResourceBundle().getString("removeProfFromChairLbl"), MainWindow.getInstance().getResourceBundle().getString("warning"),
				            JOptionPane.WARNING_MESSAGE, JOptionPane.WARNING_MESSAGE, null, options,"");
				        if (result == JOptionPane.YES_OPTION) {				        	
				        	ChairController con = new ChairController();
				        	con.removeProfessorFromChair(chairCode, professorTable.getModel().getValueAt(professorTable.getSelectedRow(), 4).toString());
				        	updateProfessors();
				        }
				        else if (result == JOptionPane.NO_OPTION) {
				        }
	    		}
				else {
	    			String[] options = {"OK"};
	    			int result = JOptionPane.showOptionDialog((getRootPane()), 
	    					MainWindow.getInstance().getResourceBundle().getString("delProfSelectionFailedLbl"), MainWindow.getInstance().getResourceBundle().getString("error"),
				            JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, options,"");
	    		}
				
			}
			
		});
		
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AddProfessorToChairWindow(chairCode,professorTable,getLocation(),getSize());
				updateProfessors();
			}
			
		});
		
		TableColumnModel tcm = professorTable.getColumnModel();
		tcm.removeColumn( tcm.getColumn(4));
		
		
		
		JScrollPane unfinishedTablePane = new JScrollPane(professorTable);

		// Borders for tables to make them not glued to pane
		unfinishedTablePane.setBorder(new EmptyBorder(15,25,15,25));


		buttonPane.add(addButton);
		buttonPane.add(deleteButton);
		
		this.add(buttonPane, BorderLayout.NORTH);
		this.add(unfinishedTablePane,BorderLayout.CENTER);
		
		updateProfessors();
		
		this.setVisible(true);
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
