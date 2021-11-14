package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class MainWindow extends JFrame {

	public MainWindow() {
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		
		setSize(screenWidth / 4 * 3, screenHeight / 4 * 3);
		
		setLocation(screenWidth / 8, screenHeight / 8);
		
		setTitle("Studenstka slu�ba");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		MenuBar menuBar = new MenuBar();
		this.setJMenuBar(menuBar);
		
		JPanel contentPanel = new JPanel(new BorderLayout());
		this.setContentPane(contentPanel);
		
		
		ToolBar toolbar = new ToolBar();
		contentPanel.add(toolbar,BorderLayout.NORTH);
		
		setVisible(true);
	}
}
