package gui;

import javax.swing.table.AbstractTableModel;

import main.DataClass;

public class AbstractTableModelProfessors extends AbstractTableModel {
	
	private static int selectedRowIndex = 0;
	private static int selectedColumnIndex = 0;
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return DataClass.getInstance().getStudentListData().size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 6;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		setSelectedRowIndex(rowIndex);
		setSelectedColumnIndex(columnIndex);
		if(columnIndex <= 4) {
			return DataClass.getInstance().getValueAt(rowIndex, columnIndex);
		}
		return null;
	}

	public static int getSelectedRowIndex() {
		return selectedRowIndex;
	}

	public static void setSelectedRowIndex(int selectedRowIndex) {
		AbstractTableModelProfessors.selectedRowIndex = selectedRowIndex;
	}

	public static int getSelectedColumnIndex() {
		return selectedColumnIndex;
	}

	public static void setSelectedColumnIndex(int selectedColumnIndex) {
		AbstractTableModelProfessors.selectedColumnIndex = selectedColumnIndex;
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		super.setValueAt(aValue, rowIndex, columnIndex);
	}
}
