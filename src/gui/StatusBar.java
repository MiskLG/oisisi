package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class StatusBar extends JPanel{
	
	private String currentTab;

	public StatusBar() {	
		this.setLayout(new BorderLayout(0,0));
		this.setBorder(BorderFactory.createRaisedBevelBorder());	
		currentTab = "Studentska služba";
	
		// Left side of the status bar
		JPanel currentTabPanel = new JPanel();
		currentTabPanel.setLayout(new FlowLayout());
			
		JLabel tab = new JLabel(currentTab);
		
	
		// Right side of the status bar
		JPanel currentTimePanel = new JPanel();
		currentTimePanel.setLayout(new FlowLayout());
		
		// Setting initial values so there is no 1s downtime without date
		JLabel  currentDate = new JLabel(new java.text.SimpleDateFormat("HH:mm").format(new java.util.Date(System.currentTimeMillis())));
		JLabel  currentTime = new JLabel(new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date(System.currentTimeMillis())));
		
		
		// clock updater
		int delay = 1000; //milliseconds
	      ActionListener taskPerformer = new ActionListener() {
	          public void actionPerformed(ActionEvent evt) {
	              String date = new java.text.SimpleDateFormat("HH:mm").format(new java.util.Date(System.currentTimeMillis()));
	              String time = new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date(System.currentTimeMillis()));
	              
	              currentTime.setText(time);
	              currentDate.setText(date);
	              
	          }
	      };
	      Timer timer = new Timer(delay, taskPerformer);
	      timer.start();
		
		// Adding stuff where they belong
	    currentTimePanel.add(currentDate);  
	    currentTimePanel.add(currentTime);
	      
	    currentTabPanel.add(tab);
		
		this.add(currentTimePanel,BorderLayout.EAST);
		this.add(currentTabPanel,BorderLayout.WEST);
			
	}

	public String getCurrentTab() {
		return currentTab;
	}

	public void setCurrentTab(String currentTab) {
		this.currentTab = currentTab;
	}
	
	
	
	
}
