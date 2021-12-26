package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class EditProfessor extends JDialog {

	private EditProfessorPanel editProfTabs;
	
	public EditProfessor(Point location, Dimension size) {
		this.setResizable(false);
		setTitle("Izmena profesora");
		
		double widthRatio = 40./100;
		double heightRatio = 95./100;
		
		Double minX = 1200 * widthRatio;
		Double minY = 600 * heightRatio;
		
		Double sizeX = minX;
		Double sizeY = minY;
		
		setSize(sizeX.intValue(), sizeY.intValue());
		setLocationRelativeTo(this.getParent());
		
		editProfTabs = new EditProfessorPanel();
		//JLabel errLabel = new JLabel(); NJEGA MORAM DODATI U IZMENU
		
		JPanel buttonsPanel = new JPanel();
		
		buttonsPanel.setLayout(new FlowLayout());
		
		JButton acceptButton = editProfTabs.getAcceptButton();
		JButton cancelButton = new JButton("Odustani");
		
		acceptButton.setPreferredSize(new Dimension(140,30));
		cancelButton.setPreferredSize(new Dimension(140,30));
		
		JPanel spacer = new JPanel();
		spacer.setPreferredSize(new Dimension(15,30));
		
		acceptButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//TablePanel.getStudentModel().fireTableDataChanged();
				dispose();
				
			}
			
		});
		
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
			
		});
		
		buttonsPanel.add(acceptButton);
		buttonsPanel.add(spacer);
		buttonsPanel.add(cancelButton);
		
		
		editProfTabs.getInformations().add(buttonsPanel);
		getContentPane().add(editProfTabs, BorderLayout.CENTER);
		

		this.setVisible(true);
	}
	
	public boolean getChangesMade() {
		// TODO Auto-generated method stub
		return editProfTabs.isChangesMade();
	}
}
