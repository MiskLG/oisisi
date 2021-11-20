package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainWindow extends JFrame {

	public MainWindow() {
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		
		setSize(screenWidth / 4 * 3, screenHeight / 4 * 3);
		
		setLocation(screenWidth / 8, screenHeight / 8);
		
		setTitle("Studenstka služba");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

		MenuBar menuBar = new MenuBar();
		this.setJMenuBar(menuBar);
		
		JPanel contentPanel = new JPanel(new BorderLayout());
		this.setContentPane(contentPanel);
		
		
		ToolBar toolbar = new ToolBar();	
		StatusBar statusbar = new StatusBar();
		TablePanel tablePanel = new TablePanel();
		
		// Initial change and change listener
		statusbar.setCurrentTab(tablePanel.getTitleAt(tablePanel.getSelectedIndex()));
		toolbar.updateToolTips(tablePanel.getTitleAt(tablePanel.getSelectedIndex()));
		tablePanel.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		        statusbar.setCurrentTab(tablePanel.getTitleAt(tablePanel.getSelectedIndex()));
		        toolbar.updateToolTips(tablePanel.getTitleAt(tablePanel.getSelectedIndex()));
		    }
		});
		
		
		contentPanel.add(toolbar,BorderLayout.NORTH);
		contentPanel.add(statusbar,BorderLayout.SOUTH);
		contentPanel.add(tablePanel,BorderLayout.CENTER);
		
		
		setVisible(true);
	}
}
