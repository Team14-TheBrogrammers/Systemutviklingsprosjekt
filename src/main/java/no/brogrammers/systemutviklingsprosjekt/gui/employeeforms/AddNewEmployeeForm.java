package no.brogrammers.systemutviklingsprosjekt.gui.employeeforms;

import no.brogrammers.systemutviklingsprosjekt.converter.DatePickerFormatter;
import no.brogrammers.systemutviklingsprosjekt.user.ManageUser;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

/**
 * Created by Knut on 21.04.2016.
 */
public class AddNewEmployeeForm extends JFrame{
    private JButton addNewEmployeeToButton;
    private JComboBox userTypeComboBox;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField phoneTextField;
    private JPanel mainPanel;
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JTextField emailAddressTextField;
    private JPanel datePickerPanel;

    private ManageUser manageUser = new ManageUser();

    public AddNewEmployeeForm() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("test");
        setSize(350, 450);
        setVisible(true);
        setLocationRelativeTo(null);

        loadDatePicker();

        addNewEmployeeToButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (userTypeComboBox.getSelectedIndex()) {
                    case 0:
                        //manageUser.registerManager(lastNameTextField.getText(), firstNameTextField.getText(), 1, "", "", "", "");phoneTextField.getText(), "",)
                        break;
                    case 1:
                        //manageUser.registerCashier()
                        break;
                    case 2:
                        //manageUser.registerCook()
                        break;
                    case 3:
                        //manageUser.registerDriver()
                        break;
                }
            }
        });
    }

    private void loadDatePicker() {
        UtilDateModel utilDateModel = new UtilDateModel();
        utilDateModel.setDate(2016, 4, 22);
        utilDateModel.setSelected(true);

        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");

        JDatePanelImpl datePanel = new JDatePanelImpl(utilDateModel, properties);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DatePickerFormatter());

        mainPanel.add(datePicker);
        //add(datePicker);
    }
}
