package gui;

import javax.swing.WindowConstants;

public class App {

	public static void main(String[] args) {
		MainWindow mw = new MainWindow();
		mw.setVisible(true);
		mw.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

}
