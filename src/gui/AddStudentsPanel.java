package gui;

import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JDialog;

public class AddStudentsPanel extends JDialog {

	public AddStudentsPanel(Point location, Dimension size) {
		this.setModal(true);
		
		double widthRatio = 40./100;
		double heightRatio = 95./100;
		
		Double locationX = location.x + size.getWidth() / 2 - size.getWidth() * widthRatio / 2;
		Double locationY = location.y + size.getHeight() / 2 - size.getHeight() * heightRatio / 2;
		Double sizeX = size.getWidth() * widthRatio;
		Double sizeY = size.getHeight() * heightRatio;
		Double maxX = 1920 * widthRatio;
		Double maxY = 1080 * heightRatio;
		Double minX = 960 * widthRatio;
		Double minY = 540 * heightRatio;
		
		this.setMaximumSize(new Dimension(maxX.intValue(),maxY.intValue()));
		this.setMinimumSize(new Dimension(minX.intValue(),minY.intValue()));	
		setLocation(locationX.intValue() , locationY.intValue() );
		setSize(sizeX.intValue(), sizeY.intValue());
		
		this.setVisible(true);
	}
	
}
