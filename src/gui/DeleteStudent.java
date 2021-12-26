package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DeleteStudent extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DeleteStudent() {
		super();
		
		setTitle("Brisanje studenta");
		setSize(400, 150);
		setLocationRelativeTo(this.getParent());
		setResizable(false);
		setModal(true);
		
		JPanel delPanel = new JPanel();
		BoxLayout box = new BoxLayout(delPanel, BoxLayout.Y_AXIS);
		delPanel.setLayout(box);
		delPanel.add(Box.createVerticalStrut(25));
		
		JPanel message = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel lblMessage = new JLabel("Da li ste sigurni da izbrisete studenta?");
		Dimension dim = new Dimension(230, 20);
		lblMessage.setPreferredSize(dim);
		message.add(lblMessage);
		delPanel.add(message);
		
		delPanel.add(Box.createVerticalStrut(25));
		add(delPanel, BorderLayout.NORTH);
		
		JPanel buttonsPanel = new JPanel();
		BoxLayout box2 = new BoxLayout(buttonsPanel, BoxLayout.X_AXIS);
		Dimension dim2 = new Dimension(400, 40);
		buttonsPanel.setLayout(box2);
		buttonsPanel.setPreferredSize(dim2);
		
		JButton acceptButton = new JButton("Da");
		acceptButton.setPreferredSize(new Dimension(100, 25));
		acceptButton.setBackground(Color.LIGHT_GRAY);

		JButton cancelButton = new JButton("Ne");
		cancelButton.setPreferredSize(new Dimension(100, 25));
		cancelButton.setBackground(Color.LIGHT_GRAY);
		
		buttonsPanel.add(Box.createGlue());
		buttonsPanel.add(acceptButton);
		buttonsPanel.add(Box.createHorizontalStrut(10));
		buttonsPanel.add(cancelButton);
		buttonsPanel.add(Box.createHorizontalStrut(10));
		buttonsPanel.add(Box.createVerticalStrut(25));

		add(buttonsPanel, BorderLayout.SOUTH);
		
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			dispose();
			}

			});

			//acceptButton.addActionListener(new ActionListener() {

			//@Override
//			public void actionPerformed(ActionEvent e) {
//			int row = ATMStudenti.getSelectedRowIndex();
//			StudentController.getInstance().izbrisiStudenta(row);
//			Tabovi.getModelStudenti().fireTableDataChanged();
//			dispose();
//			}
//			});
		this.setVisible(true);
		
	}

}
