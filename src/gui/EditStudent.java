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
	
		Double locationX = location.x + size.getWidth() / 2 - minX /2 ;
		Double locationY = location.y + size.getHeight() / 2 - minY / 2;
		
		Double sizeX = minX;
		Double sizeY = minY;
		
		setLocation(locationX.intValue() , locationY.intValue() );
		setSize(sizeX.intValue(), sizeY.intValue());
		
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
		
		buttonsPanel.add(acceptButton);
		buttonsPanel.add(spacer);
		buttonsPanel.add(cancelButton);
		
		//acceptButton.setEnabled(false);
	
		//cancelButton.addActionListener( e -> { this.dispose(); });
		// OVO NE RADI JER NEMAM POLJA A TREBAO BIH PODATKE O STUDENTU DA DOBIJEM IZ BAZE
		// ALI OPET BI TREBALA PROVERA I KAD SE IZMENE PODACI
		/*FocusListener focusLostCheck = new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {				
			}

			@Override
			public void focusLost(FocusEvent e) {
				StudentController con = new StudentController(nameField.getText(), lastnameField.getText(), dateobField.getText(), adressField.getText(), phoneField.getText(),
						emailField.getText(), indexField.getText(), enrolmentField.getText(), yearOfStudyField.getSelectedIndex(), statusField.getSelectedIndex());
				String text = con.getErr();
				
				errLabel.setText(text);
				
				if(text.equalsIgnoreCase("Sve je dobro")) {
					acceptButton.setEnabled(true);
				}
				else {
					acceptButton.setEnabled(false);
				}
						
			}
			
		};*/
		editTabs.getInformations().add(buttonsPanel);
		getContentPane().add(editTabs, BorderLayout.CENTER);
		//pack();
		

		this.setVisible(true);
	}

	public boolean getChangesMade() {
		// TODO Auto-generated method stub
		return editTabs.isChangesMade();
	}
	
}
