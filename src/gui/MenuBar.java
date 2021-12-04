package gui;

import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MenuBar extends JMenuBar{
	
	// Icons made by https://www.flaticon.com/authors/inkubators
	private String newIconPNG = System.getProperty("user.dir") + File.separator + "images" + File.separator + "menubar" + File.separator + "new.png";
	private String editIconPNG = System.getProperty("user.dir") + File.separator + "images" + File.separator + "menubar" + File.separator + "edit.png";
	private String deleteIconPNG = System.getProperty("user.dir") + File.separator + "images" + File.separator + "menubar" + File.separator + "delete.png";
	private String closeIconPNG = System.getProperty("user.dir") + File.separator + "images" + File.separator + "menubar" + File.separator + "close.png";
	private String helpIconPNG = System.getProperty("user.dir") + File.separator + "images" + File.separator + "menubar" + File.separator + "help.png";
	private String informationIconPNG = System.getProperty("user.dir") + File.separator + "images" + File.separator + "menubar" + File.separator + "information.png";
	private String openIconPNG = System.getProperty("user.dir") + File.separator + "images" + File.separator + "menubar" + File.separator + "open.png";
	private String saveIconPNG = System.getProperty("user.dir") + File.separator + "images" + File.separator + "menubar" + File.separator + "save.png";
	private String professorsIconPNG = System.getProperty("user.dir") + File.separator + "images" + File.separator + "menubar" + File.separator + "professors.png";
	private String studentsIconPNG = System.getProperty("user.dir") + File.separator + "images" + File.separator + "menubar" + File.separator + "students.png";
	private String subjectsIconPNG = System.getProperty("user.dir") + File.separator + "images" + File.separator + "menubar" + File.separator + "subjects.png";
	private String chairsIconPNG = System.getProperty("user.dir") + File.separator + "images" + File.separator + "menubar" + File.separator + "chairs.png";
	
	private enum OpenedTab {STUDENT, PROFESSOR, SUBJECT};
	private OpenedTab current = OpenedTab.STUDENT;
	
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
		menuItemClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK + ActionEvent.SHIFT_MASK));
		menuItemClose.setMnemonic(KeyEvent.VK_C);
		
		// setting up action listeners
		menuItemNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch(current) {
				case PROFESSOR:
					break;
				case STUDENT:
					AddStudentsPanel panel = new AddStudentsPanel(getParent().getParent().getParent().getLocation(), getParent().getParent().getParent().getSize());
					break;
				case SUBJECT:
					break;
				default:
					break;}		
			}		
		});
		
		// close button action listener
		menuItemClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((MainWindow) getParent().getParent().getParent()).dispose();	
			}		
		});
		
		
		// setting icons
		
		ImageIcon newIcon = new ImageIcon(newIconPNG);
		Image newImage = newIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
		menuItemNew.setIcon(new ImageIcon(newImage));
		
		ImageIcon saveIcon = new ImageIcon(saveIconPNG);
		Image saveImage = saveIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
		menuItemSave.setIcon(new ImageIcon(saveImage));
		
		ImageIcon openIcon = new ImageIcon(openIconPNG);
		Image openImage = openIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
		menuItemOpen.setIcon(new ImageIcon(openImage));
		
		ImageIcon closeIcon = new ImageIcon(closeIconPNG);
		Image closeImage = closeIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
		menuItemClose.setIcon(new ImageIcon(closeImage));
		
		
		
		file.add(menuItemNew);
		file.add(menuItemSave);
		file.add(menuItemOpen);
		file.add(menuItemClose);
		
		JMenuItem menuItemStudents = new JMenuItem("Students");
		JMenuItem menuItemSubjects = new JMenuItem("Subjects");
		JMenuItem menuItemProfessors = new JMenuItem("Professors");
		JMenuItem menuItemChairs = new JMenuItem("Chairs");
		
		menuItemStudents.setMnemonic(KeyEvent.VK_S);
		menuItemSubjects.setMnemonic(KeyEvent.VK_B);
		menuItemProfessors.setMnemonic(KeyEvent.VK_P);
		menuItemChairs.setMnemonic(KeyEvent.VK_C);
		
		// action listeners for open tab
		menuItemStudents.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((MainWindow)getParent().getParent().getParent()).changeTab(0);
			}
			
			});
		menuItemSubjects.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((MainWindow)getParent().getParent().getParent()).changeTab(2);
			}
			
			});
		menuItemProfessors.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((MainWindow)getParent().getParent().getParent()).changeTab(1);
			}
			
			});
		
		
		menuItemStudents.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
		menuItemSubjects.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
		menuItemProfessors.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
		menuItemChairs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK + ActionEvent.ALT_MASK));
		
		ImageIcon professorsIcon = new ImageIcon(professorsIconPNG);
		Image professorsImage = professorsIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
		menuItemProfessors.setIcon(new ImageIcon(professorsImage));
		
		ImageIcon subjectsIcon = new ImageIcon(subjectsIconPNG);	
		Image subjectsImage = subjectsIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
		menuItemSubjects.setIcon(new ImageIcon(subjectsImage));
		
		ImageIcon chairsIcon = new ImageIcon(chairsIconPNG);	
		Image chairsImage = chairsIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
		menuItemChairs.setIcon(new ImageIcon(chairsImage));
		
		ImageIcon studentsIcon = new ImageIcon(studentsIconPNG);
		Image studentsImage = studentsIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
		menuItemStudents.setIcon(new ImageIcon(studentsImage));
		
		menuItemOpen.add(menuItemStudents);
		menuItemOpen.add(menuItemProfessors);
		menuItemOpen.add(menuItemSubjects);
		menuItemOpen.add(menuItemChairs);
		
		JMenuItem menuItemEdit = new JMenuItem("Edit");
		menuItemEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		menuItemEdit.setMnemonic(KeyEvent.VK_E);
		
		JMenuItem menuItemDelete = new JMenuItem("Delete");
		menuItemDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		menuItemDelete.setMnemonic(KeyEvent.VK_D);
		
		// setting up icons	
		ImageIcon editIcon = new ImageIcon(editIconPNG);
		Image editImage = editIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
		menuItemEdit.setIcon(new ImageIcon(editImage));
		
		ImageIcon deleteIcon = new ImageIcon(deleteIconPNG);
		Image deleteImage = deleteIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
		menuItemDelete.setIcon(new ImageIcon(deleteImage));
		
		
		edit.add(menuItemEdit);
		edit.add(menuItemDelete);
		
		JMenuItem menuItemHelp = new JMenuItem("Help");
		menuItemHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		menuItemHelp.setMnemonic(KeyEvent.VK_H);
		
		JMenuItem menuItemAbout = new JMenuItem("About");
		menuItemAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
		menuItemAbout.setMnemonic(KeyEvent.VK_A);
		
		// setting up icons	
		ImageIcon helpIcon = new ImageIcon(helpIconPNG);
		Image helpImage = helpIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
		menuItemHelp.setIcon(new ImageIcon(helpImage));
		
		ImageIcon aboutIcon = new ImageIcon(informationIconPNG);
		Image aboutImage = aboutIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
		menuItemAbout.setIcon(new ImageIcon(aboutImage));
		
		help.add(menuItemHelp);
		help.add(menuItemAbout);
	}
	
	public void setIndicator(int number) {
		switch(number) {
			case 0:
				this.current = OpenedTab.STUDENT;
				return;
			case 1:
				this.current = OpenedTab.PROFESSOR;
				return;
			case 2:
				this.current = OpenedTab.SUBJECT;
				return;
			default:
				return;
		}
	}
}
