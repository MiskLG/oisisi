package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ToolBar extends JPanel {
	
	// Icons made by https://www.flaticon.com/authors/inkubators
	private String newIconPNG = System.getProperty("user.dir") + File.separator + "images" + File.separator + "toolbar" + File.separator + "new.png";
	private String editIconPNG = System.getProperty("user.dir") + File.separator + "images" + File.separator + "toolbar" + File.separator + "edit.png";
	private String deleteIconPNG = System.getProperty("user.dir") + File.separator + "images" + File.separator + "toolbar" + File.separator + "delete.png";
	private String searchIconPNG = System.getProperty("user.dir") + File.separator + "images" + File.separator + "toolbar" + File.separator + "search.png";
	
	
	public ToolBar() {
		
		this.setLayout(new BorderLayout(0,0));
		this.setBorder(BorderFactory.createRaisedBevelBorder());
		
		
		// left side of toolbar	
		JPanel toolbarLeft = new JPanel();
		toolbarLeft.setLayout(new GridLayout(1,3));
				
		((GridLayout)toolbarLeft.getLayout()).setVgap(0);
		((GridLayout)toolbarLeft.getLayout()).setHgap(15);
		
		
		
		JButton buttonNew = new JButton();
		JButton buttonEdit = new JButton();
		JButton buttonDelete = new JButton();
			
		// adding icons to buttons
		ImageIcon newIcon = new ImageIcon(newIconPNG);
		Image newImage = newIcon.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT);
		buttonNew.setIcon(new ImageIcon(newImage));
		
		ImageIcon editIcon = new ImageIcon(editIconPNG);
		Image editImage = editIcon.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT);
		buttonEdit.setIcon(new ImageIcon(editImage));
		
		ImageIcon deleteIcon = new ImageIcon(deleteIconPNG);
		Image deleteImage = deleteIcon.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT);
		buttonDelete.setIcon(new ImageIcon(deleteImage));
		
		
		// making buttons look pretty
		buttonNew.setBorder(new EmptyBorder(0,0,0,0));
		buttonNew.setBorderPainted(false); 
		buttonNew.setContentAreaFilled(false); 
		buttonNew.setFocusPainted(false); 
		
		buttonEdit.setBorder(new EmptyBorder(0,0,0,0));
		buttonEdit.setBorderPainted(false); 
		buttonEdit.setContentAreaFilled(false); 
		buttonEdit.setFocusPainted(false); 
			
		buttonDelete.setBorder(new EmptyBorder(0,0,0,0));
		buttonDelete.setBorderPainted(false); 
		buttonDelete.setContentAreaFilled(false); 
		buttonDelete.setFocusPainted(false); 
		
		buttonNew.setToolTipText("New");
		buttonEdit.setToolTipText("Edit");
		buttonDelete.setToolTipText("Delete");
		
		
		// pushing buttons to panel
		toolbarLeft.add(buttonNew);
		toolbarLeft.add(buttonEdit);
		toolbarLeft.add(buttonDelete);
		
		
		
		// right side of toolbar	
		JPanel toolbarRight = new JPanel();
		toolbarRight.setLayout(new FlowLayout());
		//toolbarRight.setLayout(new BoxLayout(toolbarRight,BoxLayout.X_AXIS));
		
		JButton buttonSearch = new JButton();
		JTextField textSearch = new JTextField();
		
		
		textSearch.setPreferredSize(new Dimension(300,24));
		
		
		ImageIcon searchIcon = new ImageIcon(searchIconPNG);
		Image searchImage = searchIcon.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT);
		buttonSearch.setIcon(new ImageIcon(searchImage));
		
		buttonSearch.setBorder(new EmptyBorder(0,0,0,0));
		buttonSearch.setBorderPainted(false); 
		buttonSearch.setContentAreaFilled(false); 
		buttonSearch.setFocusPainted(false); 
		
		buttonSearch.setToolTipText("Search");
		
		toolbarRight.add(textSearch);
		toolbarRight.add(buttonSearch);
	
		
		
		this.add(toolbarLeft,BorderLayout.WEST);
		this.add(toolbarRight, BorderLayout.EAST);
		
		
	}
	
}
