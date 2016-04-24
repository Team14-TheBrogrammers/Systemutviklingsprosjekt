package no.brogrammers.systemutviklingsprosjekt.miscellaneous;

import javax.swing.table.DefaultTableModel;

/**
 * Created by Knut on 24.04.2016.
 */
public class NonEditTableModel extends DefaultTableModel {

    public NonEditTableModel(Object[] columnNames, int rowCount) {
        super(columnNames, rowCount);
    }//TODO:remove constructor

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
