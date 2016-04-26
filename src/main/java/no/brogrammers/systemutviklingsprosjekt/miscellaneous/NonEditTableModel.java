package no.brogrammers.systemutviklingsprosjekt.miscellaneous;

import javax.swing.table.DefaultTableModel;

/**
 * Created by Knut on 24.04.2016.
 * Class for making a custom DefaultTableModel that have non-editable rows.
 */
public class NonEditTableModel extends DefaultTableModel {

    public NonEditTableModel(Object[] columnNames) {
        super(columnNames, 0);
    }//TODO:remove constructor

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
