package no.brogrammers.systemutviklingsprosjekt.miscellaneous;

import javax.swing.table.DefaultTableModel;

/**
 * Created by Knut on 26.04.2016.
 */
public class ChooseEditTableModel extends DefaultTableModel {

    private final int columnEditable;

    public ChooseEditTableModel(Object[] columnNames, int columnEditable) {
        super(columnNames, 0);
        this.columnEditable = columnEditable;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == columnEditable) {
            return true;
        } else {
            return false;
        }
    }
}
