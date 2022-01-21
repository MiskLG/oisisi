package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import main.DataClass;

public class MainWindow extends JFrame {
	
	private static MainWindow instance = null;

	private ResourceBundle resourceBundle;
	
	public static MainWindow getInstance() {
		if (instance == null) {
			instance = new MainWindow();
			instance.initMainWindow();
		}

		return instance;
	}

	private TablePanel tablePanel;
	private boolean changesMade = false;
	
	private MainWindow() {
		
	}
	
	private void initMainWindow() {
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		
		this.setMaximumSize(new Dimension(1920,1080));
		this.setMinimumSize(new Dimension(960,540));
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setSize(screenWidth / 4 * 3, screenHeight / 4 * 3);
		
		setLocation(screenWidth / 8, screenHeight / 8);
		
		Locale.setDefault(new Locale("sr", "RS"));

		resourceBundle = ResourceBundle.getBundle("messageResources.message", Locale.getDefault());
		
		setTitle(resourceBundle.getString("appName"));
		
		
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
	
	// for getting selected values from table in different dialogs
	public TablePanel getTablePanel() {
		return tablePanel;
	}
	
	
	// popup window for menubar close button
	public void checkDisposability() {
		if(changesMade == true) {
    		String[] options = {MainWindow.getInstance().getResourceBundle().getString("yesButton"),MainWindow.getInstance().getResourceBundle().getString("noButton")};
    		int result = JOptionPane.showOptionDialog((getContentPane()), 
    				MainWindow.getInstance().getResourceBundle().getString("saveQuestionLbl"), MainWindow.getInstance().getResourceBundle().getString("dataNotSavedLbl"),
		            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,"");
		        if (result == JOptionPane.YES_OPTION) {			
		        	String[] list = {"OK"};
		        	int confimed = JOptionPane.showOptionDialog((getRootPane()), 
		        			MainWindow.getInstance().getResourceBundle().getString("saveConfirmedLbl"), MainWindow.getInstance().getResourceBundle().getString("infoLbl"),
				            JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, list,"");
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
	
	public void changeLanguage() {

		resourceBundle = ResourceBundle.getBundle("messageResources.message", Locale.getDefault());
		setTitle(resourceBundle.getString("appName"));

	}
	
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	
	
}
