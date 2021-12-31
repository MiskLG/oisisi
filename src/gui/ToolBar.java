package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.SubjectController;


public class ToolBar extends JPanel {
	
	// Icons made by https://www.flaticon.com/authors/inkubators
	private String newIconPNG = System.getProperty("user.dir") + File.separator + "images" + File.separator + "toolbar" + File.separator + "new.png";
	private String editIconPNG = System.getProperty("user.dir") + File.separator + "images" + File.separator + "toolbar" + File.separator + "edit.png";
	private String deleteIconPNG = System.getProperty("user.dir") + File.separator + "images" + File.separator + "toolbar" + File.separator + "delete.png";
	private String searchIconPNG = System.getProperty("user.dir") + File.separator + "images" + File.separator + "toolbar" + File.separator + "search.png";
	
	private JButton buttonNew = new JButton();
	private JButton buttonEdit = new JButton();
	private JButton buttonDelete = new JButton();
	private JButton buttonSearch = new JButton();
	
	private enum OpenedTab {STUDENT, PROFESSOR, SUBJECT};
	private OpenedTab current = OpenedTab.STUDENT;
	
	public ToolBar() {
		
		this.setLayout(new BorderLayout(0,0));
		this.setBorder(BorderFactory.createRaisedBevelBorder());
		
		
		// left side of toolbar	
		JPanel toolbarLeft = new JPanel();
		toolbarLeft.setLayout(new GridLayout(1,3));
				
		((GridLayout)toolbarLeft.getLayout()).setVgap(0);
		((GridLayout)toolbarLeft.getLayout()).setHgap(15);
		
		
			
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
		
		buttonNew.setToolTipText("Dodavanje novog studenta");
		buttonEdit.setToolTipText("Promena izabranog studenta");
		buttonDelete.setToolTipText("Brisanje izabranog studenta");
		
		// adding action listeners
		buttonNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch(current) {
				case PROFESSOR:
					AddProfessorsPanel panelPr = new AddProfessorsPanel(getParent().getParent().getParent().getParent().getLocation(), getParent().getParent().getParent().getParent().getSize());
					if(panelPr.getChangesMade()) {
						((MainWindow) getParent().getParent().getParent().getParent()).updateTable();
						((MainWindow) getParent().getParent().getParent().getParent()).setChangesMade(true);
					}
					break;
				case STUDENT:
					AddStudentsPanel panelSt = new AddStudentsPanel(getParent().getParent().getParent().getParent().getLocation(), getParent().getParent().getParent().getParent().getSize());
					if(panelSt.getChangesMade()) {
						((MainWindow) getParent().getParent().getParent().getParent()).updateTable();
						((MainWindow) getParent().getParent().getParent().getParent()).setChangesMade(true);
					}
					break;
				case SUBJECT:
					break;
				default:
					break;
				}
							
			}		
		});
		
		buttonEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch(current) {
				case PROFESSOR:
					EditProfessor profEdit = new EditProfessor(getParent().getParent().getParent().getLocation(), getParent().getParent().getParent().getSize());
					break;
				case STUDENT:
					EditStudent panelEdit = new EditStudent(getParent().getParent().getParent().getLocation(), getParent().getParent().getParent().getSize());
					break;
				case SUBJECT:
					break;
				default:
					break;
				}
							
			}		
		});
		
		buttonDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch(current) {
				case PROFESSOR:
					//treba izmeniti da se poziva za oznacenog profesora a ne samo na klik
					DeleteProfessor deleteProf = new DeleteProfessor();
					break;
				case STUDENT:
					//isto i za studenta
					DeleteStudent deleteStud = new DeleteStudent();
					break;
				case SUBJECT:
					String index  = ((MainWindow) getParent().getParent().getParent().getParent()).getTablePanel().getSelectedSubjectCode();		
		    		
		    		if(!index.equals("-1")) {
		    			String[] options = {"Da","Ne"};
		    			Icon emptyIcon = new ImageIcon("");
		    			int result = JOptionPane.showOptionDialog( (getRootPane()), 
			    				"Da li želite da obrišete izabrani predmet?", "UPOZORENJE!",
					            JOptionPane.YES_NO_OPTION, 3, emptyIcon, options,"");
					        if (result == JOptionPane.YES_OPTION) {				        	
					        	SubjectController con = new SubjectController();
					        	if(con.deleteSubject(index)) {
					        		((MainWindow) getParent().getParent().getParent().getParent()).setChangesMade(true);
					        		((MainWindow) getParent().getParent().getParent().getParent()).updateTable();
					        	}
					        	
					        }
					        else if (result == JOptionPane.NO_OPTION) {
					        }
		    		}
		    		else {
		    			String[] options = {"OK"};
		    			Icon emptyIcon = new ImageIcon("");
		    			int result = JOptionPane.showOptionDialog((getRootPane()), 
			    				"Niste izabrali predmet koji želite da obrišete!", "GREŠKA!",
					            JOptionPane.INFORMATION_MESSAGE, 3, emptyIcon, options,"");
		    		}
					break;
				default:
					break;
				}
							
			}		
		});
		
		
		
		
		// pushing buttons to panel
		toolbarLeft.add(buttonNew);
		toolbarLeft.add(buttonEdit);
		toolbarLeft.add(buttonDelete);
		
		
		
		// right side of toolbar	
		JPanel toolbarRight = new JPanel();
		toolbarRight.setLayout(new FlowLayout());
		//toolbarRight.setLayout(new BoxLayout(toolbarRight,BoxLayout.X_AXIS));
		
	
		JTextField textSearch = new JTextField();
		
		
		textSearch.setPreferredSize(new Dimension(300,24));
		
		
		ImageIcon searchIcon = new ImageIcon(searchIconPNG);
		Image searchImage = searchIcon.getImage().getScaledInstance(24, 24, Image.SCALE_DEFAULT);
		buttonSearch.setIcon(new ImageIcon(searchImage));
		
		buttonSearch.setBorder(new EmptyBorder(0,0,0,0));
		buttonSearch.setBorderPainted(false); 
		buttonSearch.setContentAreaFilled(false); 
		buttonSearch.setFocusPainted(false); 
		
		buttonSearch.setToolTipText("Pretrega studenta");
		
		toolbarRight.add(textSearch);
		toolbarRight.add(buttonSearch);
	
		
		
		this.add(toolbarLeft,BorderLayout.WEST);
		this.add(toolbarRight, BorderLayout.EAST);
		
		
	}
	
	public void updateToolTips(String entitet) {
		if(entitet.equalsIgnoreCase("Studenti"))
		{
			buttonNew.setToolTipText("Dodavanje novog studenta");
			buttonEdit.setToolTipText("Promena izabranog studenta");
			buttonDelete.setToolTipText("Brisanje izabranog studenta");
			buttonSearch.setToolTipText("Pretraga studenta");
		}
		else if(entitet.equalsIgnoreCase("Profesori")) {
			buttonNew.setToolTipText("Dodavanje novog profesora");
			buttonEdit.setToolTipText("Promena izabranog profesora");
			buttonDelete.setToolTipText("Brisanje izabranog profesora");
			buttonSearch.setToolTipText("Pretraga profesora");
		}
		else if(entitet.equalsIgnoreCase("Predmeti")) {
			buttonNew.setToolTipText("Dodavanje novog predmeta");
			buttonEdit.setToolTipText("Promena izabranog predmeta");
			buttonDelete.setToolTipText("Brisanje izabranog predmeta");
			buttonSearch.setToolTipText("Pretraga predmeta");
		}
		return;
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
