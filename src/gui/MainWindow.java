package gui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class MainWindow extends JFrame {

	public MainWindow() {
		super();
		
		setVisible(true);
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		
		setSize(screenWidth / 4 * 3, screenHeight / 4 * 3);
		
		setLocation(screenWidth / 8, screenHeight / 8);
		
		setTitle("Studenstka služba");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		
	}
}
