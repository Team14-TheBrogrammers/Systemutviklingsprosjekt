package no.brogrammers.systemutviklingsprosjekt.gui.employeeforms;

import no.brogrammers.systemutviklingsprosjekt.converter.DateConverter;
import no.brogrammers.systemutviklingsprosjekt.converter.DatePickerFormatter;
import no.brogrammers.systemutviklingsprosjekt.user.ManageUser;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
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
    private JPanel datePickerPanel;
    private JDatePickerImpl datePickerTest;
    private JPasswordField passwordField1;
    private JTextField emailTextField;

    private ManageUser manageUser = new ManageUser();

    public AddNewEmployeeForm() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add New Employee");
        setSize(450, 500);
        setVisible(true);
        setLocationRelativeTo(null);

        //loadDatePicker();

        addNewEmployeeToButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });
    }

    private void performLogin() {
        String lastName = lastNameTextField.getText();
        String firstName = firstNameTextField.getText();
        int phone = Integer.parseInt(phoneTextField.getText());
        String email = emailTextField.getText();
        DateConverter dateConverter = new DateConverter();
        java.sql.Date dateOfEmployment = dateConverter.utilDateToSqlDate((java.util.Date)datePickerTest.getModel().getValue());
        String username = usernameTextField.getText();
        String password = passwordField1.getText();

        switch (userTypeComboBox.getSelectedIndex()) {
            case 0:
                System.out.println(manageUser.registerManager(lastName, firstName, phone, email, dateOfEmployment, username, password));
                break;
            case 1:
                //manageUser.registerCashier()//TODO:FIX more code
                break;
            case 2:
                //manageUser.registerCook()
                break;
            case 3:
                //manageUser.registerDriver()
                break;
        }
    }

    private void loadDatePicker() {
        UtilDateModel utilDateModel = new UtilDateModel();
        Calendar calendar = Calendar.getInstance();
        utilDateModel.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
        utilDateModel.setSelected(true);

        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");

        JDatePanelImpl datePanel = new JDatePanelImpl(utilDateModel, properties);
        //JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DatePickerFormatter());

        datePickerTest = new JDatePickerImpl(datePanel, new DatePickerFormatter());
        //mainPanel.add(datePicker);
        //add(datePicker);
    }

    private void createUIComponents() {
        loadDatePicker();
    }
}
