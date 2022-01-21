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

import controller.ChairController;
import main.DataClass;
import model.Chair;
import model.Professor;

public class ChairPanel extends JDialog{
	
	private JTable chairsTable;
	
	public ChairPanel(Point location, Dimension size) {
		this.setModal(true);
		this.setResizable(false);
		
		double widthRatio = 40./100;
		double heightRatio = 95./100;
		
		Double minX = 1200 * widthRatio;
		Double minY = 600 * heightRatio;
		
		this.setLayout(new BorderLayout());
		
		Double sizeX = minX;
		Double sizeY = minY;
		
		Double locationX = location.x + size.getWidth() / 2 - minX /2 ;
		Double locationY = location.y + size.getHeight() / 2 - minY / 2;
		
		setLocation(locationX.intValue() , locationY.intValue());
		
		setSize(sizeX.intValue(), sizeY.intValue());
		
		this.setTitle("Katedre");
		
		JButton addButton = new JButton("Dodaj katedru");
		JButton editButton = new JButton("Promeni šefa");
		JButton deleteButton = new JButton("Obriši");
		JButton addProfessor = new JButton("Dodaj na katedru");
		
		JPanel buttonPanel = new JPanel();
		
		JPanel buttonPanelTOP = new JPanel();
		JPanel buttonPanelBOT = new JPanel();
		
		buttonPanel.setLayout(new BorderLayout());
		
		buttonPanel.add(buttonPanelTOP, BorderLayout.NORTH);
		buttonPanel.add(buttonPanelBOT, BorderLayout.SOUTH);
		
		buttonPanelTOP.add(addButton);
		buttonPanelBOT.add(addProfessor);
		buttonPanelTOP.add(editButton);
		buttonPanelTOP.add(deleteButton);
		
		
		String[] colHeadingsToAdd = {"Šifra", "Naziv", "Šef"};
		DefaultTableModel chairModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		chairModel.setColumnIdentifiers(colHeadingsToAdd);
		
		chairsTable = new JTable(chairModel) {
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
		
		chairsTable.setBorder(new LineBorder(Color.black, 1));
		chairsTable.setRowHeight(24);
		
		JScrollPane tablePane = new JScrollPane(chairsTable);
		
		//OVDE AKCIJE ZA DUGMAD
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddChairPanel(getLocation(), getSize());
				updateChairs();
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(chairsTable.getSelectedRow() != -1) {
					String[] options = {"Da", "Ne"};
					int result = JOptionPane.showOptionDialog((getRootPane()),
							"Da li ste sigurni da želite da brišete katedru?", "Brisanje katedre",
							JOptionPane.WARNING_MESSAGE, JOptionPane.WARNING_MESSAGE, null, options, "");
						if(result == JOptionPane.YES_OPTION) {
							ChairController con = new ChairController();
							if(con.deleteChair(chairsTable.getValueAt(chairsTable.getSelectedRow(), 0).toString())) {
				        		updateChairs();
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
		editButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(chairsTable.getSelectedRow() != -1){	  
					String chCode = chairsTable.getModel().getValueAt(chairsTable.getSelectedRow(), 0).toString();
					ChairEditHead chairEdit = new ChairEditHead(chCode,getLocation(),getSize());
//					StudentAddFinished stAddFinished = new StudentAddFinished(studentIndex, subCode, unfinishedTable);
					updateChairs();		        		
	    		}
				else {
	    			String[] options = {"OK"};
	    			int result = JOptionPane.showOptionDialog((getRootPane()), 
		    				"Niste izabrali katedru koju želite da izmenite!", "GREŠKA!",
				            JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, options,"");
	    		}				
			}
			
		});
		
		addProfessor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(chairsTable.getSelectedRow() != -1){	  
					String chCode = chairsTable.getModel().getValueAt(chairsTable.getSelectedRow(), 0).toString();
					String chName = chairsTable.getModel().getValueAt(chairsTable.getSelectedRow(), 1).toString();
					AddProfessorToChairPanel pan = new AddProfessorToChairPanel(chCode,chName,getLocation(),getSize());	    
					updateChairs();
	    		}
				else {
	    			String[] options = {"OK"};
	    			int result = JOptionPane.showOptionDialog((getRootPane()), 
		    				"Niste izabrali katedru na koju želite da dodate profesora!", "GREŠKA!",
				            JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, options,"");
	    		}				
			}
			
		});
		
		
		tablePane.setBorder(new EmptyBorder(15,25,15,25));
		
		
		this.add(buttonPanel, BorderLayout.NORTH);
		this.add(tablePane,BorderLayout.CENTER);
		
		//mozda i ovo treba ali ne verujem
		//fillData();
		
		updateChairs();
		
		this.setVisible(true);
	}
	
	public void updateChairs() {
		ArrayList<Chair> chairs = DataClass.getInstance().getChairListData();
		ArrayList<Professor> professors = DataClass.getInstance().getProfessorListData();
		
		while(chairsTable.getModel().getRowCount() > 0) {
			((DefaultTableModel) chairsTable.getModel()).removeRow(0);
		}
		
		
		for(Chair c: chairs) {
			for(Professor p: professors) {
				if(c.getHead().equals("Nema šefa")) {
					String[] subjectData = {c.getChairCode(), c.getTitle(), "Nema šefa"};
					((DefaultTableModel) chairsTable.getModel()).addRow(subjectData);
					break;
				}
				else if(c.getHead().equals(p.getIdNumber())) {
					String[] subjectData = {c.getChairCode(), c.getTitle(), p.getName() + " " + p.getLastname()};
					((DefaultTableModel) chairsTable.getModel()).addRow(subjectData);
					break;
				}
			}
		}
	}
}
