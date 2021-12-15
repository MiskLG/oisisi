package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import main.DataClass;

public class MainWindow extends JFrame {

	private TablePanel tablePanel;
	private boolean changesMade = false;
	
	public MainWindow() {
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		
		this.setMaximumSize(new Dimension(1920,1080));
		this.setMinimumSize(new Dimension(960,540));
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setSize(screenWidth / 4 * 3, screenHeight / 4 * 3);
		
		setLocation(screenWidth / 8, screenHeight / 8);
		
		setTitle("Studenstka služba");
		
		

		MenuBar menuBar = new MenuBar();
		this.setJMenuBar(menuBar);
		
		JPanel contentPanel = new JPanel(new BorderLayout());
		this.setContentPane(contentPanel);
		
		
		ToolBar toolbar = new ToolBar();	
		StatusBar statusbar = new StatusBar();
		tablePanel = new TablePanel();
		
		// Initial change and change listener
		statusbar.setCurrentTab(tablePanel.getTitleAt(tablePanel.getSelectedIndex()));
		toolbar.updateToolTips(tablePanel.getTitleAt(tablePanel.getSelectedIndex()));
		tablePanel.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		    	
		    	toolbar.setIndicator(tablePanel.getSelectedIndex());
		    	menuBar.setIndicator(tablePanel.getSelectedIndex());
		    	
		        statusbar.setCurrentTab(tablePanel.getTitleAt(tablePanel.getSelectedIndex()));
		        toolbar.updateToolTips(tablePanel.getTitleAt(tablePanel.getSelectedIndex()));
		    }
		});
		
		
		contentPanel.add(toolbar,BorderLayout.NORTH);
		contentPanel.add(statusbar,BorderLayout.SOUTH);
		contentPanel.add(tablePanel,BorderLayout.CENTER);
		
		
		// checking if changes were made and displaying option to save them if they were
		// closing this window does nothing, pressing OK saves, pressing cancel exits program
		this.addWindowListener(new WindowAdapter() {
		      public void windowClosing(WindowEvent we) {
		    	checkDisposability();
		      }
		      });
		
		
		setVisible(true);
	}
	
	public void setChangesMade(boolean changes){
		this.changesMade = changes;
	}
	
	public void changeTab(int index) {
		tablePanel.setSelectedIndex(index);
		return;
	}
	
	public void updateTable() {
		tablePanel.updateTable();
	}
	
	// popup window for menubar close button
	public void checkDisposability() {
		if(changesMade == true) {
    		String[] options = {"Da","Ne"};
    		Icon emptyIcon = new ImageIcon("");
    		int result = JOptionPane.showOptionDialog((getContentPane()), 
    				"Da li želite da ih sačuvate?", "Podaci nisu sačuvani!",
		            JOptionPane.YES_NO_OPTION, 3, emptyIcon, options,"");
		        if (result == JOptionPane.YES_OPTION) {				        	
		        	DataClass.getInstance().writeOutData();
		        	this.dispose();
		        }
		        else if (result == JOptionPane.NO_OPTION) {
		        	this.dispose();
		        }
		}
		else {
			this.dispose();
		}
    	
	}
	
}
