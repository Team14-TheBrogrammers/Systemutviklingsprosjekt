package no.brogrammers.systemutviklingsprosjekt.gui.customerforms;

import no.brogrammers.systemutviklingsprosjekt.customer.ManageCustomer;
import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.CustomerConnection;
import no.brogrammers.systemutviklingsprosjekt.gui.MainForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by Knut on 21.04.2016.
 */
public class AddNewCustomerForm extends JFrame {
    private JPanel mainPanel;
    private JTextField addressTextField;
    private JTextField zipTextField;
    private JButton addNewCustomerButton;
    private JCheckBox isCompanyCheckBox;
    private JTextField companyNameTextField;
    private JPanel companyPanel;
    private JPanel privateCustomerPanel;
    private JTextField emailTextField;
    private JTextField phoneTextField;
    private JPanel defaultInformationPanel;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;

    private MainForm mainForm;
    private ManageCustomer manageCustomer;

    public AddNewCustomerForm(final MainForm mainForm, boolean isPrivateCustomer) {
        this.mainForm = mainForm;

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(mainPanel);
        setTitle("Add new Customer");
        setSize(500, 700);
        setVisible(true);
        setLocationRelativeTo(null);
        companyPanel.setVisible(false);

        isCompanyCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(isCompanyCheckBox.isSelected()) {
                    companyPanel.setVisible(true);
                    privateCustomerPanel.setVisible(false);
                } else {
                    companyPanel.setVisible(false);
                    privateCustomerPanel.setVisible(true);
                }
            }
        });
        addNewCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewCustomerButton.setEnabled(false);
                manageCustomer = new ManageCustomer();
                String address = addressTextField.getText();
                int zip = Integer.parseInt(zipTextField.getText());
                String email = emailTextField.getText();
                int phone = Integer.parseInt(phoneTextField.getText());
                if(isCompanyCheckBox.isSelected()) {
                    showMessageDialog(null, manageCustomer.registerCompany(address, zip, email, phone, companyNameTextField.getText()));
                } else {
                    showMessageDialog(null, manageCustomer.registerPrivateCustomer(address, zip, email, phone, lastNameTextField.getText(), firstNameTextField.getText()));
                }
                manageCustomer.stopConnection();
                mainForm.loadCustomersTab();
                dispose();
            }
        });
    }
}
