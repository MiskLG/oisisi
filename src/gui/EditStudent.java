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
import controller.StudentController;
import model.Student;

public class EditStudent extends JDialog {
	
	private EditStudentPanel editTabs;
	
	public EditStudent(Point location, Dimension size, String index) {
		this.setResizable(false);
		this.setModal(true);
		setTitle(MainWindow.getInstance().getResourceBundle().getString("editStudDialName"));
		
		double widthRatio = 40./100;
		double heightRatio = 95./100;
		
		Double minX = 1600 * widthRatio;
		Double minY = 600 * heightRatio;
		
		Double sizeX = minX;
		Double sizeY = minY;
		
		Double locationX = location.x + size.getWidth() / 2 - minX /2 ;
		Double locationY = location.y + size.getHeight() / 2 - minY / 2;
		
		setSize(sizeX.intValue(), sizeY.intValue());
		setLocation(locationX.intValue() , locationY.intValue());
		
		StudentController con = new StudentController();
		Student s = con.findStudentByIdx(index);
		
		editTabs = new EditStudentPanel(s,this);
		
		JPanel buttonsPanel = new JPanel();
		
		buttonsPanel.setLayout(new FlowLayout());
		
		JButton acceptButton = editTabs.getAcceptButton();
		JButton cancelButton = new JButton(MainWindow.getInstance().getResourceBundle().getString("cancelButton"));
		
		acceptButton.setPreferredSize(new Dimension(140,30));
		cancelButton.setPreferredSize(new Dimension(140,30));
		
		JPanel spacer = new JPanel();
		spacer.setPreferredSize(new Dimension(15,30));
		
		acceptButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				if(getChangesMade()) {
//					dispose();
//				}
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

	public Point getLoc() {
		System.out.println(this.getLocation());
		return this.getLocation();
	}
	
	public boolean getChangesMade() {
		// TODO Auto-generated method stub
		return editTabs.isChangesMade();
	}
	
}
