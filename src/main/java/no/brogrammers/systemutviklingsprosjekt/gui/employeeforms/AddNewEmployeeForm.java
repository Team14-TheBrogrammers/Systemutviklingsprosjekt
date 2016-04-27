package no.brogrammers.systemutviklingsprosjekt.gui.employeeforms;

import no.brogrammers.systemutviklingsprosjekt.gui.MainForm;
import no.brogrammers.systemutviklingsprosjekt.miscellaneous.DateConverter;
import no.brogrammers.systemutviklingsprosjekt.miscellaneous.DatePickerFormatter;
import no.brogrammers.systemutviklingsprosjekt.user.ManageUser;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Properties;

import static javax.swing.JOptionPane.showMessageDialog;

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

    private ManageUser manageUser;
    private MainForm mainForm;

    public AddNewEmployeeForm(MainForm mainForm) {
        this.mainForm = mainForm;
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
                performRegister();
            }
        });
    }

    private void performRegister() {
        manageUser = new ManageUser();

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
                showMessageDialog(null, manageUser.registerManager(lastName, firstName, phone, email, dateOfEmployment, username, password));
                break;
            case 1:
                showMessageDialog(null, manageUser.registerCashier(lastName, firstName, phone, email, dateOfEmployment, username, password));
                break;
            case 2:
                showMessageDialog(null, manageUser.registerCook(lastName, firstName, phone, email, dateOfEmployment, username, password));
                break;
            case 3:
                showMessageDialog(null, manageUser.registerDriver(lastName, firstName, phone, email, dateOfEmployment, username, password));
                break;
        }
        mainForm.loadEmployeesTab();
        manageUser.stopConnection();
        dispose();
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
