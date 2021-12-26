package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.StudentController;

public class EditStudent extends JDialog {
	
	private EditStudentPanel editTabs;
	
	public EditStudent(Point location, Dimension size) {
		this.setResizable(false);
		setTitle("Izmena studenta");
		
		double widthRatio = 40./100;
		double heightRatio = 95./100;
		
		Double minX = 1200 * widthRatio;
		Double minY = 600 * heightRatio;
		
		Double sizeX = minX;
		Double sizeY = minY;
		
		setSize(sizeX.intValue(), sizeY.intValue());
		setLocationRelativeTo(this.getParent());
		
		editTabs = new EditStudentPanel();
		//JLabel errLabel = new JLabel(); NJEGA MORAM DODATI U IZMENU
		
		JPanel buttonsPanel = new JPanel();
		
		buttonsPanel.setLayout(new FlowLayout());
		
		JButton acceptButton = editTabs.getAcceptButton();
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
		
		
		editTabs.getInformations().add(buttonsPanel);
		getContentPane().add(editTabs, BorderLayout.CENTER);
		

		this.setVisible(true);
	}

	public boolean getChangesMade() {
		// TODO Auto-generated method stub
		return editTabs.isChangesMade();
	}
	
}
