package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import model.Professor;

public class ChairEditHead extends JDialog{
	
	JTable professorTable;
	
	public ChairEditHead(String chairCode, Point location, Dimension size) {
		this.setModal(true);
		this.setResizable(false);
		
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
		
		this.setTitle("Izmena šefa katedre");
		
		JButton acceptButton = new JButton("Potvrdi");
		JButton cancelButton = new JButton("Odustani");
		
		

		String[] professorTableHead = {"Profesor", "Šifra"};
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
		tcm.removeColumn(tcm.getColumn(1));
		
		acceptButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(professorTable.getSelectedRow() != -1) {
					ChairController con = new ChairController();
					con.setHead(chairCode, professorTable.getModel().getValueAt(professorTable.getSelectedRow(), 1).toString());		
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
		
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
			
		});
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(acceptButton);
		buttonPanel.add(cancelButton);
		
		JScrollPane tablePanel = new JScrollPane(professorTable);
		
		tablePanel.setBorder(new EmptyBorder(15,25,15,25));
		
		this.add(tablePanel,BorderLayout.CENTER);
		this.add(buttonPanel,BorderLayout.SOUTH);
		
		fillData();
		
		this.setVisible(true);
	}
	
	private void fillData() {
		while(professorTable.getModel().getRowCount() > 0) {
			((DefaultTableModel) professorTable.getModel()).removeRow(0);
		}
		
		
		for(Professor pr: DataClass.getInstance().getProfessorListData()) {
			if((pr.getWorkYears() > 4) && (pr.getTitle().equals("Redovni profesor") || pr.getTitle().equals("Vanredni profesor"))) {
				String[] professorData = {pr.getName()+ " " + pr.getLastname(), pr.getIdNumber()};
				((DefaultTableModel) professorTable.getModel()).addRow(professorData);
			}
			
		}
	}

}
