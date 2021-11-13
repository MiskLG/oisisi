package gui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MenuBar extends JMenuBar{
	
	public MenuBar() {
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		JMenu edit = new JMenu("Edit");
		edit.setMnemonic(KeyEvent.VK_E);
		JMenu help = new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_H);
		
		add(file);
		add(edit);
		add(help);
		
		JMenuItem menuItemNew = new JMenuItem("New");
		menuItemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		menuItemNew.setMnemonic(KeyEvent.VK_N);
		
		JMenuItem menuItemSave = new JMenuItem("Save");
		menuItemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		menuItemSave.setMnemonic(KeyEvent.VK_S);
		
		JMenu menuItemOpen = new JMenu("Open");
		menuItemOpen.setMnemonic(KeyEvent.VK_O);
		
		JMenuItem menuItemClose = new JMenuItem("Close");
		menuItemClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
		menuItemClose.setMnemonic(KeyEvent.VK_C);
		
		file.add(menuItemNew);
		file.addSeparator();
		file.add(menuItemSave);
		file.addSeparator();
		file.add(menuItemOpen);
		file.addSeparator();
		file.add(menuItemClose);
		
		JMenuItem menuItemStudents = new JMenuItem("Students");
		JMenuItem menuItemSubjects = new JMenuItem("Subjects");
		JMenuItem menuItemProfessors = new JMenuItem("Professors");
		JMenuItem menuItemChairs = new JMenuItem("Chairs");
		
		menuItemOpen.add(menuItemStudents);
		menuItemOpen.add(menuItemSubjects);
		menuItemOpen.add(menuItemProfessors);
		menuItemOpen.add(menuItemChairs);
		
		JMenuItem menuItemEdit = new JMenuItem("Edit");
		menuItemEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		menuItemEdit.setMnemonic(KeyEvent.VK_E);
		
		JMenuItem menuItemDelete = new JMenuItem("Delete");
		menuItemDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		menuItemDelete.setMnemonic(KeyEvent.VK_D);
		
		edit.add(menuItemEdit);
		edit.addSeparator();
		edit.add(menuItemDelete);
		
		JMenuItem menuItemHelp = new JMenuItem("Help");
		menuItemHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		menuItemHelp.setMnemonic(KeyEvent.VK_F1);
		
		JMenuItem menuItemAbout = new JMenuItem("About");
		menuItemAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
		menuItemAbout.setMnemonic(KeyEvent.VK_F2);
		
		help.add(menuItemHelp);
		help.addSeparator();
		help.add(menuItemAbout);
	}
}
