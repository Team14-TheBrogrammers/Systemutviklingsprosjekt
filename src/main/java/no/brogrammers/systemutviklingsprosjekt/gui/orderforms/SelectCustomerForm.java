package no.brogrammers.systemutviklingsprosjekt.gui.orderforms;

import no.brogrammers.systemutviklingsprosjekt.customer.Company;
import no.brogrammers.systemutviklingsprosjekt.customer.Customer;
import no.brogrammers.systemutviklingsprosjekt.customer.ManageCustomer;
import no.brogrammers.systemutviklingsprosjekt.customer.PrivateCustomer;
import no.brogrammers.systemutviklingsprosjekt.miscellaneous.NonEditTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

/**
 * Created by Knut on 20.04.2016.
 */
public class SelectCustomerForm extends JFrame {
    private JButton chooseCustomerButton;
    private JPanel mainPanel;
    private JTable customerTable;

    private AddNewOrderForm addNewOrderForm;
    private ManageCustomer manageCustomer;// = new ManageCustomer();

    public SelectCustomerForm(final AddNewOrderForm addNewOrderForm) {
        this.addNewOrderForm = addNewOrderForm;
        setContentPane(mainPanel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(500, 450);
        setTitle("Choose Customer for the Order");
        setVisible(true);
        setLocationRelativeTo(null);
        customerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        loadTable();
        chooseCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(customerTable.getSelectedRowCount() == 1) {
                    int selectedCustomerID = (int) customerTable.getValueAt(customerTable.getSelectedRow(), 0);
                    addNewOrderForm.setCustomerID(selectedCustomerID);
                    dispose();
                }
            }
        });
        customerTable.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                System.out.println(customerTable.getSelectedRowCount());
                /*if(customerTable.getSelectedRowCount() == 0) {
                    chooseCustomerButton.setEnabled(false);
                } else {
                    chooseCustomerButton.setEnabled(true);
                }*/
            }
        });
        customerTable.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                System.out.println(customerTable.getSelectedRowCount());//TODO:FIX THIS SHIT
                /*if(customerTable.getSelectedRowCount() == 0) {
                    chooseCustomerButton.setEnabled(false);
                } else {
                    chooseCustomerButton.setEnabled(true);
                }*/
            }
        });
    }

    private void loadTable() {
        String customerColumns[] = {"ID", "Name", "Address", "Zip Address", "Email Address", "Phone"};
        NonEditTableModel tableModel = new NonEditTableModel(customerColumns);
        customerTable.setModel(tableModel);
        manageCustomer = new ManageCustomer();
        ArrayList<Customer> customers = manageCustomer.viewAllCustomers();
        for(int i = 0; i < customers.size(); i++) {
            int id = customers.get(i).getID();
            String name = "";
            if(customers.get(i) instanceof PrivateCustomer) {
                name = ((PrivateCustomer) customers.get(i)).getFirstName() + " " + ((PrivateCustomer) customers.get(i)).getLastName();
            } else {
                name = ((Company) customers.get(i)).getName();
            }
            String address = customers.get(i).getAddress();
            int zipCode = customers.get(i).getZip();
            String emailAddress = customers.get(i).getEmail();
            int phone = customers.get(i).getPhone();
            Object objects[] = {id, name, address, zipCode, emailAddress, phone};
            tableModel.addRow(objects);
        }
        manageCustomer.stopConnection();
    }
}
