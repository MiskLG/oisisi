package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import controller.ChairController;
import controller.RegXClass;

public class AddChairPanel extends JDialog{

	private boolean[] validData = {false, false};
	
	public AddChairPanel(Point location, Dimension size) {
		this.setModal(true);
		this.setResizable(false);
		
		int minX = 350;
		int minY = 200;
		
		Double sizeX = (double) minX;
		Double sizeY = (double) minY;
		
		Double locationX = location.getX() + size.getWidth()/2 - minX /2 ;
		Double locationY = location.getY() + size.getHeight()/2 - minY / 2;
		setLocation(locationX.intValue() , locationY.intValue() );
		
		setSize(sizeX.intValue(), sizeY.intValue());
		
		setSize(350, 200);
		
		setTitle(MainWindow.getInstance().getResourceBundle().getString("addChairDialName"));
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JPanel fieldsPanel = new JPanel();
		JPanel buttonsPanel = new JPanel();
		
		JButton acceptButton = new JButton(MainWindow.getInstance().getResourceBundle().getString("acceptButton"));
		JButton cancelButton = new JButton(MainWindow.getInstance().getResourceBundle().getString("cancelButton"));
		
		JPanel spacer = new JPanel();
		spacer.setPreferredSize(new Dimension(15,30));
		
		buttonsPanel.add(acceptButton);
		buttonsPanel.add(spacer);
		buttonsPanel.add(cancelButton);
		
		fieldsPanel.setLayout(new GridLayout(2,2));
		
		JLabel chairCodeLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("chairCodeLbl"));
		JTextField chairCodeField = new JTextField();		
		
		JLabel titleLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("chairNameLbl"));
		JTextField titleField = new JTextField();
		
		fieldsPanel.add(chairCodeLabel);
		fieldsPanel.add(chairCodeField);
		fieldsPanel.add(titleLabel);
		fieldsPanel.add(titleField);
		
		mainPanel.setBorder(new EmptyBorder(20,30,20,30));
		((BorderLayout)mainPanel.getLayout()).setVgap(20);
		
		((GridLayout)fieldsPanel.getLayout()).setVgap(15);
		((GridLayout)fieldsPanel.getLayout()).setHgap(15);
		
		acceptButton.setEnabled(false);
		
		cancelButton.addActionListener( e -> { this.dispose(); });
		
		//PROVERE ZA POLJA
		chairCodeField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				if(true == RegXClass.checkChairCode(chairCodeField.getText() + e.getKeyChar())) {
					validData[0] = true;
					chairCodeField.setBorder(new MatteBorder(1,1,1,1,Color.GRAY));
				}
				else {
					validData[0] = false;
					chairCodeField.setBorder(new MatteBorder(1,1,1,1,Color.red));
				}
				if(validData[0] && validData[1]) {
					acceptButton.setEnabled(true);
				}
				else {
					acceptButton.setEnabled(false);
				}
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		titleField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				if(true == RegXClass.checkChairTitle(titleField.getText() + e.getKeyChar())) {
					validData[1] = true;
					titleField.setBorder(new MatteBorder(1,1,1,1,Color.GRAY));
				}
				else {
					validData[1] = false;
					titleField.setBorder(new MatteBorder(1,1,1,1,Color.red));
				}
				if(validData[0] && validData[1]) {
					acceptButton.setEnabled(true);
				}
				else {
					acceptButton.setEnabled(false);
				}
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//ACTION LISTENER ZA ACCEPT BUTTON
		acceptButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChairController con = new ChairController(chairCodeField.getText(), titleField.getText(), "Nema šefa");
				String err = con.addChairToData();
				if(err.equals("Sve je dobro!")) {
					dispose();
				}
				else {
					String[] options = {"OK"};
	    			int result = JOptionPane.showOptionDialog((getRootPane()), 
	    					MainWindow.getInstance().getResourceBundle().getString("takenChairCodeLbl"), MainWindow.getInstance().getResourceBundle().getString("error"),
				            JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, options,"");
				}
			}
			
		});
		
		
		
		mainPanel.add(fieldsPanel, BorderLayout.CENTER);
		mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
		this.setContentPane(mainPanel);

		this.setVisible(true);
	}
}
