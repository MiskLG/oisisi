package gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import controller.ProfessorController;
import controller.StudentController;

import controller.SubjectController;

import main.DataClass;

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
		JMenu file = new JMenu(MainWindow.getInstance().getResourceBundle().getString("file"));
		file.setMnemonic(KeyEvent.VK_F);
		JMenu edit = new JMenu(MainWindow.getInstance().getResourceBundle().getString("edit"));
		edit.setMnemonic(KeyEvent.VK_E);
		JMenu help = new JMenu(MainWindow.getInstance().getResourceBundle().getString("help"));
		help.setMnemonic(KeyEvent.VK_H);
		JMenu language = new JMenu(MainWindow.getInstance().getResourceBundle().getString("language"));
		language.setMnemonic(KeyEvent.VK_L);
		
		add(file);
		add(edit);
		add(help);
		add(language);
		
		JMenuItem menuItemNew = new JMenuItem(MainWindow.getInstance().getResourceBundle().getString("menuItemNew"));
		menuItemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		menuItemNew.setMnemonic(KeyEvent.VK_N);
			
		
		JMenuItem menuItemSave = new JMenuItem(MainWindow.getInstance().getResourceBundle().getString("menuItemSave"));
		menuItemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		menuItemSave.setMnemonic(KeyEvent.VK_S);
		
		JMenu menuItemOpen = new JMenu(MainWindow.getInstance().getResourceBundle().getString("menuItemOpen"));
		menuItemOpen.setMnemonic(KeyEvent.VK_O);
		
		JMenuItem menuItemClose = new JMenuItem(MainWindow.getInstance().getResourceBundle().getString("menuItemClose"));
		menuItemClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK + ActionEvent.SHIFT_MASK));
		menuItemClose.setMnemonic(KeyEvent.VK_C);
		
		// setting up action listeners
		menuItemNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch(current) {
				case PROFESSOR:
					AddProfessorsPanel panelPr = new AddProfessorsPanel(getParent().getParent().getParent().getLocation(), getParent().getParent().getParent().getSize());
					if(panelPr.getChangesMade()) {
						((MainWindow) getParent().getParent().getParent()).updateTable();
						((MainWindow) getParent().getParent().getParent()).setChangesMade(true);
					}		
					break;
				case STUDENT:
					AddStudentsPanel panelSt = new AddStudentsPanel(getParent().getParent().getParent().getLocation(), getParent().getParent().getParent().getSize());
					if(panelSt.getChangesMade()) {
						((MainWindow) getParent().getParent().getParent()).updateTable();
						((MainWindow) getParent().getParent().getParent()).setChangesMade(true);
					}
					break;
				case SUBJECT:
					AddSubjectsPanel panelSu = new AddSubjectsPanel(getParent().getParent().getParent().getLocation(), getParent().getParent().getParent().getSize());
					if(panelSu.getChangesMade()) {
						((MainWindow) getParent().getParent().getParent()).updateTable();
						((MainWindow) getParent().getParent().getParent()).setChangesMade(true);
					}
					break;
				default:
					break;}		
			}		
		});
		
		// close button action listener
		menuItemClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((MainWindow) getParent().getParent().getParent()).checkDisposability();
			}		
		});
		
		// save button action listener
		menuItemSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] list = {"OK"};
				int confimed = JOptionPane.showOptionDialog((getRootPane()), 
						MainWindow.getInstance().getResourceBundle().getString("saveConfirmedLbl"), MainWindow.getInstance().getResourceBundle().getString("infoLbl"),
			            JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, list,"");
				DataClass.getInstance().writeOutData();
				((MainWindow) getParent().getParent().getParent()).setChangesMade(false);
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
		
		JMenuItem menuItemStudents = new JMenuItem(MainWindow.getInstance().getResourceBundle().getString("menuItemStudents"));
		JMenuItem menuItemSubjects = new JMenuItem(MainWindow.getInstance().getResourceBundle().getString("menuItemSubjects"));
		JMenuItem menuItemProfessors = new JMenuItem(MainWindow.getInstance().getResourceBundle().getString("menuItemProfessors"));
		JMenuItem menuItemChairs = new JMenuItem(MainWindow.getInstance().getResourceBundle().getString("menuItemChairs"));
		
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
		menuItemChairs.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ChairPanel(getParent().getParent().getParent().getLocation(), getParent().getParent().getParent().getSize());
				((MainWindow) getParent().getParent().getParent()).updateTable();
				((MainWindow) getParent().getParent().getParent()).setChangesMade(true);
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
		
		JMenuItem menuItemEdit = new JMenuItem(MainWindow.getInstance().getResourceBundle().getString("edit"));
		menuItemEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		menuItemEdit.setMnemonic(KeyEvent.VK_E);
		

		//edit button action listener
		menuItemEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch(current) {
				case PROFESSOR:
					String id  = ((MainWindow) getParent().getParent().getParent()).getTablePanel().getSelectedProfessorId();
					
					if(!id.equals("-1")) {
						EditProfessor panelEditProfessor = new EditProfessor(getParent().getParent().getParent().getLocation(), getParent().getParent().getParent().getSize(), id); 
						((MainWindow) getParent().getParent().getParent()).updateTable();
						((MainWindow) getParent().getParent().getParent()).setChangesMade(true);
						
					}
		    		else {
		    			String[] options = {"OK"};
		    			int result = JOptionPane.showOptionDialog((getRootPane()), 
		    					MainWindow.getInstance().getResourceBundle().getString("editProfSelectionFailedLbl"), MainWindow.getInstance().getResourceBundle().getString("error"),
					            JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, options,"");
		    		}
					break;
				case STUDENT:
					String idx  = ((MainWindow) getParent().getParent().getParent()).getTablePanel().getSelectedStudentIndex();
					
					if(!idx.equals("-1")) {
						EditStudent panelEditStudent = new EditStudent(getParent().getParent().getParent().getLocation(), getParent().getParent().getParent().getSize(), idx);
						((MainWindow) getParent().getParent().getParent()).updateTable();
						((MainWindow) getParent().getParent().getParent()).setChangesMade(true);			
						
					}
		    		else {
		    			String[] options = {"OK"};
		    			int result = JOptionPane.showOptionDialog((getRootPane()), 
		    					MainWindow.getInstance().getResourceBundle().getString("editStudSelectionFailedLbl"), MainWindow.getInstance().getResourceBundle().getString("error"),
					            JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, options,"");
		    		}
					break;
				case SUBJECT:
					String subjectCode  = ((MainWindow) getParent().getParent().getParent()).getTablePanel().getSelectedSubjectCode();
					
					if(!subjectCode.equals("-1")) {
						EditSubjectPanel panelEditSubject = new EditSubjectPanel(getParent().getParent().getParent().getLocation(), getParent().getParent().getParent().getSize(), subjectCode);
						((MainWindow) getParent().getParent().getParent()).updateTable();
						((MainWindow) getParent().getParent().getParent()).setChangesMade(true);			
						
					}
		    		else {
		    			String[] options = {"OK"};
		    			int result = JOptionPane.showOptionDialog((getRootPane()), 
		    					MainWindow.getInstance().getResourceBundle().getString("editSubjSelectionFailedLbl"), MainWindow.getInstance().getResourceBundle().getString("error"),
					            JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, options,"");
		    		}
					break;
				default:
					break;}		
			}		
		});
		
		JMenuItem menuItemDelete = new JMenuItem(MainWindow.getInstance().getResourceBundle().getString("delete"));
		menuItemDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		menuItemDelete.setMnemonic(KeyEvent.VK_D);
		
		menuItemDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch(current) {
				case PROFESSOR:
					String id  = ((MainWindow) getParent().getParent().getParent()).getTablePanel().getSelectedProfessorId();		
		    		
		    		if(!id.equals("-1")) {
		    			String[] options = {MainWindow.getInstance().getResourceBundle().getString("yesButton"),MainWindow.getInstance().getResourceBundle().getString("noButton")};
		    			int result = JOptionPane.showOptionDialog( (getRootPane()), 
		    					MainWindow.getInstance().getResourceBundle().getString("deletingProfLbl"), MainWindow.getInstance().getResourceBundle().getString("warning"),
					            JOptionPane.WARNING_MESSAGE, JOptionPane.WARNING_MESSAGE, null, options,"");
					        if (result == JOptionPane.YES_OPTION) {				        	
					        	ProfessorController con = new ProfessorController();
					        	if(con.deleteProfessor(id)) {
					        		((MainWindow) getParent().getParent().getParent()).setChangesMade(true);
					        		((MainWindow) getParent().getParent().getParent()).updateTable();
					        	}
					        	
					        }
					        else if (result == JOptionPane.NO_OPTION) {
					        }
		    		}
		    		else {
		    			String[] options = {"OK"};
		    			int result = JOptionPane.showOptionDialog((getRootPane()), 
		    					MainWindow.getInstance().getResourceBundle().getString("delProfSelectionFailedLbl"), MainWindow.getInstance().getResourceBundle().getString("error"),
					            JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, options,"");
		    		}
					break;
				case STUDENT:
					String idx  = ((MainWindow) getParent().getParent().getParent()).getTablePanel().getSelectedStudentIndex();		
		    		
		    		if(!idx.equals("-1")) {
		    			String[] options = {MainWindow.getInstance().getResourceBundle().getString("yesButton"),MainWindow.getInstance().getResourceBundle().getString("noButton")};
		    			int result = JOptionPane.showOptionDialog( (getRootPane()), 
		    					MainWindow.getInstance().getResourceBundle().getString("deletingStudLbl"), MainWindow.getInstance().getResourceBundle().getString("warning"),
					            JOptionPane.WARNING_MESSAGE, JOptionPane.WARNING_MESSAGE, null, options,"");
					        if (result == JOptionPane.YES_OPTION) {				        	
					        	StudentController con = new StudentController();
					        	if(con.deleteStudent(idx)) {
					        		((MainWindow) getParent().getParent().getParent()).setChangesMade(true);
					        		((MainWindow) getParent().getParent().getParent()).updateTable();
					        	}
					        	
					        }
					        else if (result == JOptionPane.NO_OPTION) {
					        }
		    		}
		    		else {
		    			String[] options = {"OK"};
		    			int result = JOptionPane.showOptionDialog((getRootPane()), 
		    					MainWindow.getInstance().getResourceBundle().getString("delStudSelectionFailedLbl"), MainWindow.getInstance().getResourceBundle().getString("error"),
					            JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, options,"");
		    		}
		    		break;
				case SUBJECT:
					String index  = (((MainWindow) getParent().getParent().getParent()).getTablePanel()).getSelectedSubjectCode();				
					    		
		    		if(!index.equals("-1")) {
		    			String[] options = {MainWindow.getInstance().getResourceBundle().getString("yesButton"),MainWindow.getInstance().getResourceBundle().getString("noButton")};
		    			int result = JOptionPane.showOptionDialog((getRootPane()), 
		    					MainWindow.getInstance().getResourceBundle().getString("deletingSubjLbl"), MainWindow.getInstance().getResourceBundle().getString("warning"),
					            JOptionPane.WARNING_MESSAGE, JOptionPane.WARNING_MESSAGE, null, options,"");
					        if (result == JOptionPane.YES_OPTION) {				        	
					        	SubjectController con = new SubjectController();
					        	if(con.deleteSubject(index)) {
					        		((MainWindow) getParent().getParent().getParent()).setChangesMade(true);
					        		((MainWindow) getParent().getParent().getParent()).updateTable();
					        	}
					        	
					        }
					        else if (result == JOptionPane.NO_OPTION) {
					        }
		    		}
		    		else {
		    			String[] options = {"OK"};
		    			int result = JOptionPane.showOptionDialog((getRootPane()), 
		    					MainWindow.getInstance().getResourceBundle().getString("delSubjSelectionFailedLbl"), MainWindow.getInstance().getResourceBundle().getString("error"),
					            JOptionPane.ERROR_MESSAGE, JOptionPane.ERROR_MESSAGE, null, options,"");
		    		}
					break;
				default:
					break;}		
			}		
		});
		
		// setting up icons	
		ImageIcon editIcon = new ImageIcon(editIconPNG);
		Image editImage = editIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
		menuItemEdit.setIcon(new ImageIcon(editImage));
		
		ImageIcon deleteIcon = new ImageIcon(deleteIconPNG);
		Image deleteImage = deleteIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
		menuItemDelete.setIcon(new ImageIcon(deleteImage));
		
		
		edit.add(menuItemEdit);
		edit.add(menuItemDelete);
		
		JMenuItem menuItemHelp = new JMenuItem(MainWindow.getInstance().getResourceBundle().getString("help"));
		menuItemHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		menuItemHelp.setMnemonic(KeyEvent.VK_H);
		
		JMenuItem menuItemAbout = new JMenuItem(MainWindow.getInstance().getResourceBundle().getString("about"));
		menuItemAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
		menuItemAbout.setMnemonic(KeyEvent.VK_A);
		
		// setting up icons	
		ImageIcon helpIcon = new ImageIcon(helpIconPNG);
		Image helpImage = helpIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
		menuItemHelp.setIcon(new ImageIcon(helpImage));
		
		menuItemHelp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog d = new JDialog();
				d.setTitle("Pomoć");
				d.setModal(true);
				d.setSize(900, 700);
				JPanel panel = new JPanel();
				panel.setBackground(Color.WHITE);
				JTextArea text = new JTextArea();
				text.setText("Ovaj dijalog će vam pomoći prilikom korišćenja ove aplikacije. Sve što je potrebno jeste da pažljivo čitate naredna uputstva. "
						+ "Aplikacija treba da podržava \nmnogobrojne funkcionalnosti koje bi trebalo maksimalno da olakšaju rad referentu studentske službe.\n\n"
						+ "Funkcionalnosti koje aplikacija podržava:\n\n"
						+ "Prikaz entiteta sistema (#prikaz_studenata, #prikaz_profesora, #prikaz_predmeta)\n\n"
						+ "Prilikom izbora taba studenti, u centralnom delu panela taba prikazaće se tabela sa svim studentima. Tabela prikazuje sledeće podatke studenata: indeks, ime, \nprezime, godina studija, status i prosek. "
						+ "Prilikom izbora taba profesori tabela će prikazati sledeće informacije o profesorima: ime, prezime, zvanje, e-mail adresa. \n"
						+ "Izborom taba predmeti, prikazaće se tabela sa sledećim informacijama predmeta: šifra predmeta, naziv predmeta, broj ESPB bodova, godina na kojoj se \npredmet izvodi i semestar u kojem se predmet izvodi."
						+ "Prikaz entiteta se moze pokrenuti i pomoću akceleratora u zavisnosti od željenog prikaza ali taj deo je \nobjašnjen u sekciji 'Lista akceleratora'.\n\n"
						+ "Dodavanje novog entiteta (#dodavanje_studenta, #dodavanje_profesora, #dodavanje_predmeta)\n\n"
						+ "Da bi se entitet registrovao u sistem, neophodno je da se na glavnom prozoru odabere potrebni tab (za dodavanje studenta potreban tab je Studenti, za \ndodavanje profesora potreban tab je Profesori, za "
						+ "dodavanje predmeta potreban tab je Predmeti). Nakon toga, potrebno je da referent odabere jedan način za \ndodavanje novog entiteta (od 4 moguća načina): pritisak prvog dugmeta u ToolBar-u, izbor stavke "
						+ "menija New, upotrebom odgovarajućih mnemonika ili \nupotrebom odgovarajućih mnemonika. Nakon toga, otvara se dijalog za dodavanje studenta u koji je potrebno uneti željene podatke.\n\n"
						+ "Izmena informacija o entitetu (#izmena_studenta, #izmena_profesora, #izmena_predmeta)\n\n"
						+ "Da bi referent izmenio informacije o entitetu, neophodno je da izabere odgovarajući tab (kao što je objašnjeno za dodavanje novog entiteta). Nakon toga, potrebno\nje da označi odgovarajući red tabele, "
						+ "koji sadrži sažetak informacija o konkretnom entitetu. Zatim, potrebno je da izabere funkcionalnost izmene entiteta, pri čemu \nse otvara dijalog za izmenu postojećih podataka.\n\n"
						+ "Brisanje entiteta iz sistema (#brisanje_studenta, #brisanje_profesora, #brisanje_predmeta)\n\n"
						+ "Da bi referent bio u mogućnosti da ukloni entitet iz sistema, neophodno je da na početnom prozoru odabere odgovarajući tab (kao što je opisano u \nfunkcionalnosti za dodavanje novog entiteta). "
						+ "Nakon toga, označiti red tabele kojim se reprezentuje entitet koga treba ukloniti iz sistema. Zatim, referent mora \nodabrati funkcionalnost brisanja (dugme u toolbaru, stavka menija) i potvrditi brisanje željenog entiteta.\n\n"
						+ "Prikaz položenih ispita (#prikaz_polozenih_ispita)\n\n"
						+ "Prilikom rada sa dijalogom za izmenu podataka o studentu, referent može da odabere tab Položeni, nakon čega se u panelu taba prikazuje tabela sa položenim \nispitima. "
						+ "Iznad tabele nalazi se dugme 'Poništi ocenu'.\n\n"
						+ "Poništavanje ocene (#ponistavanje_ocene)\n\n"
						+ "Ako se predmet nalazi u tabeli sa položenim predmetima, njemu je dodeljena odgovarajuća ocena i moguće ju je poništiti. Da bi se ocena poništila, potrebno \nje "
						+ "označiti predmet i kliknuti na dugme 'Poništi ocenu'. Prikazuje se novi dijalog sa odgovarajućom porukom koji sadrži dva dugmeta za potvrdu \nponištavanja ocene i odustanak.\n\n"
						+ "Prikaz nepoloženih predmeta (#prikaz_nepolozenih_ispita)\n\n"
						+ "Prilikom rada sa dijalogom za izmenu podataka o studentu, referent može da odabere tab Nepoloženi, nakon čega se prikazuje tabela sa svim nepoloženim \npredmetima, koje student sluša.\n\n"
						+ "Dodavanje studenta na predmet\n\n"
						+ "Da bi se dodao novi student na predmet, potrebno je pozicionirati se na tab Nepoloženi i kliknuti dugme Dodaj. Nakon toga, referentu se prikazuje dijalog sa \n"
						+ "listom predmeta koje student može da sluša. Izborom odgovarajućeg predmeta i klikom na dugme Dodaj, dodaje se novi predmet u tabelu nepoloženih \npredmeta.\n\n"
						+ "Uklanjanje studenta sa predmeta (#uklanjanje_predmeta_sa_studenta)\n\n"
						+ "U okviru taba Nepoloženi moguće je ukloniti predmete, koje označeni student sluša. Nakon što se označi odgovarajući predmet i klikne na dugme Ukloni, \nprikazuje "
						+ "se dijalog gde je potrebno potvrditi operaciju uklanjanja predmeta.\n\n"
						+ "Upis ocene (#upis_ocene)\n\n"
						+ "Tokom pregleda nepoloženih ispita, referent je u mogućnosti da upiše ocenu na neki od nepoloženih predmeta klikom na dugme Polaganje i popunjavanjem \nodgovarajuce forme.\n\n"
						+ "Pretraga entiteta (#pretraga_studenata, #pretraga_profesora, #pretraga_predmeta)\n\n"
						+ "Pretragu entiteta moguće je izvršiti korišćenjem polja koje se nalazi u ToolBar-u tako što ćete uneti odgovarajuće parametre odvojene zarezom.\n\n"
						+ "Sortiranje entiteta (#sortiranje_studenata, #sortiranje_profesora, #sortiranje_predmeta)\n\n"
						+ "Sortiranje entiteta je moguće odraditi klikom na naziv kolone po kojoj želite da odradite sortiranje, a po kojoj je sortiranje dozvoljeno.\n\n"
						+ "Prikaz predmeta koje profesor predaje (#profesor_predaje_predmete)\n\n"
						+ "Ukoliko referent izabere tab Profesori glavnog prozora, tada mu se prikazuje tabela sa svim profesorima registrovanim u sistemu. Selekcijom odgovarajućeg \n"
						+ "reda i izborom funkcionalnosti za izmenu entiteta, prikazuje se dijalog Izmena profesora. Pomenuti dijalog poseduje dva taba: Informacije i Predmeti. Prvi tab \n"
						+ "sadrži formu za izmenu podataka o profesoru. Drugi tab prikazuje tabelu sa spiskom predmeta koje profesor predaje u koju je moguće dodati nove i obrisati \npostojeće predmete.\n\n"
						+ "Lokalizacija\n\n"
						+ "Ukoliko želite da promenite jezik u aplikaciji to je moguće učiniti izborom jednog od dva ponuđena jezika u stavci menija 'Jezik'.\n\n"
						+ "Lista akceleratora:\n"
						+ "CTRL + N - pravljenj novog entiteta\n"
						+ "CTRL + S - čuvanje podataka\n"
						+ "CTRL + ALT + S - prikaz studenata\n"
						+ "CTRL + ALT + P - prikaz profesora\n"
						+ "CTRL + ALT + B - prikaz predmeta\n"
						+ "CTRL + ALT + C - prikaz katedri\n"
						+ "ALT + SHIFT + Q - zatvaranje aplikacije\n"
						+ "CTRL + E - otvaranje dijaloga za izmenu entiteta\n"
						+ "CTRL + D - brisanje entiteta\n"
						+ "CTRL + H - otvaranje dijaloga za pomoć\n"
						+ "CTRL + B - otvaranje dijaloga koji sadrži dodatne informacije o aplikaciji");
				text.setEditable(false);
				JScrollPane scroll = new JScrollPane(text);
				d.add(scroll);
				d.setLocationRelativeTo(null);
				d.setVisible(true);
				
			}
			
		});
		
		ImageIcon aboutIcon = new ImageIcon(informationIconPNG);
		Image aboutImage = aboutIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
		menuItemAbout.setIcon(new ImageIcon(aboutImage));
		
		menuItemAbout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String text = "Verzija aplikacije : v1.17a\n\n"
						+ "Aplikacija je napravljenja za predmet OISISI na FTN-u.\n"
						+ "Aplikacija simulira rad studentske sluzbe na njihovom\n"
						+ "radnom mestu\n\n"
						+ "Autori:\n"
						+ "Aleksandar Hornjak\n"
						+ "Radicic Dusko\n\n"
						+ "Biografija:\n"
						+ "Aleksandar Hornjak je upisao fakultet 2019 godine.\n"
						+ "Omiljena boja mu je narandzasta\n\n"
						+ "Radicic Dusko je upisao fakultet 2019 godine.\n"
						+ "Omiljena boja mu je zelena";
				String title = "Opis";
				String[] options = {"OK"};
				JOptionPane.showOptionDialog((getRootPane()), 
	    				text, title,
			            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options,"");
				
			}
			
		});
		
		help.add(menuItemHelp);
		help.add(menuItemAbout);
		
		JCheckBoxMenuItem menuItemSerbian = new JCheckBoxMenuItem(MainWindow.getInstance().getResourceBundle().getString("serbian"));
		JCheckBoxMenuItem menuItemEnglish = new JCheckBoxMenuItem(MainWindow.getInstance().getResourceBundle().getString("english"));
		menuItemSerbian.setSelected(true);
		
		
		menuItemEnglish.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("en", "US"));
				MainWindow.getInstance().changeLanguage();
				menuItemSerbian.setSelected(false);
				file.setText(MainWindow.getInstance().getResourceBundle().getString("file"));
				edit.setText(MainWindow.getInstance().getResourceBundle().getString("edit"));
				help.setText(MainWindow.getInstance().getResourceBundle().getString("help"));
				language.setText(MainWindow.getInstance().getResourceBundle().getString("language"));
				menuItemNew.setText(MainWindow.getInstance().getResourceBundle().getString("menuItemNew"));
				menuItemSave.setText(MainWindow.getInstance().getResourceBundle().getString("menuItemSave"));
				menuItemOpen.setText(MainWindow.getInstance().getResourceBundle().getString("menuItemOpen"));
				menuItemClose.setText(MainWindow.getInstance().getResourceBundle().getString("menuItemClose"));
				menuItemStudents.setText(MainWindow.getInstance().getResourceBundle().getString("menuItemStudents"));
				menuItemProfessors.setText(MainWindow.getInstance().getResourceBundle().getString("menuItemProfessors"));
				menuItemSubjects.setText(MainWindow.getInstance().getResourceBundle().getString("menuItemSubjects"));
				menuItemChairs.setText(MainWindow.getInstance().getResourceBundle().getString("menuItemChairs"));
				menuItemEdit.setText(MainWindow.getInstance().getResourceBundle().getString("edit"));
				menuItemDelete.setText(MainWindow.getInstance().getResourceBundle().getString("delete"));
				menuItemHelp.setText(MainWindow.getInstance().getResourceBundle().getString("help"));
				menuItemAbout.setText(MainWindow.getInstance().getResourceBundle().getString("about"));
				menuItemSerbian.setText(MainWindow.getInstance().getResourceBundle().getString("serbian"));
				menuItemEnglish.setText(MainWindow.getInstance().getResourceBundle().getString("english"));
			}
			
		});
		
		menuItemSerbian.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("sr", "RS"));
				MainWindow.getInstance().changeLanguage();
				menuItemEnglish.setSelected(false);
				file.setText(MainWindow.getInstance().getResourceBundle().getString("file"));
				help.setText(MainWindow.getInstance().getResourceBundle().getString("help"));
				edit.setText(MainWindow.getInstance().getResourceBundle().getString("edit"));
				language.setText(MainWindow.getInstance().getResourceBundle().getString("language"));
				menuItemNew.setText(MainWindow.getInstance().getResourceBundle().getString("menuItemNew"));
				menuItemSave.setText(MainWindow.getInstance().getResourceBundle().getString("menuItemSave"));
				menuItemOpen.setText(MainWindow.getInstance().getResourceBundle().getString("menuItemOpen"));
				menuItemClose.setText(MainWindow.getInstance().getResourceBundle().getString("menuItemClose"));
				menuItemStudents.setText(MainWindow.getInstance().getResourceBundle().getString("menuItemStudents"));
				menuItemProfessors.setText(MainWindow.getInstance().getResourceBundle().getString("menuItemProfessors"));
				menuItemSubjects.setText(MainWindow.getInstance().getResourceBundle().getString("menuItemSubjects"));
				menuItemChairs.setText(MainWindow.getInstance().getResourceBundle().getString("menuItemChairs"));
				menuItemEdit.setText(MainWindow.getInstance().getResourceBundle().getString("edit"));
				menuItemDelete.setText(MainWindow.getInstance().getResourceBundle().getString("delete"));
				menuItemHelp.setText(MainWindow.getInstance().getResourceBundle().getString("help"));
				menuItemAbout.setText(MainWindow.getInstance().getResourceBundle().getString("about"));
				menuItemSerbian.setText(MainWindow.getInstance().getResourceBundle().getString("serbian"));
				menuItemEnglish.setText(MainWindow.getInstance().getResourceBundle().getString("english"));
				
				
			}
			
		});
		
		language.add(menuItemSerbian);
		language.add(menuItemEnglish);
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
