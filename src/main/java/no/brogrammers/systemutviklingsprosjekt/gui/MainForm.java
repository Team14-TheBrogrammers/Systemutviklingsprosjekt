package no.brogrammers.systemutviklingsprosjekt.gui;

import no.brogrammers.systemutviklingsprosjekt.customer.Customer;
import no.brogrammers.systemutviklingsprosjekt.customer.ManageCustomer;
import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.DriverConnection;
import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.OrderConnection;
import no.brogrammers.systemutviklingsprosjekt.gui.employeeforms.AddNewEmployeeForm;
import no.brogrammers.systemutviklingsprosjekt.gui.orderforms.AddNewOrderForm;
import no.brogrammers.systemutviklingsprosjekt.gui.userforms.ChangeUserDetailsForm;
import no.brogrammers.systemutviklingsprosjekt.order.ManageOrder;
import no.brogrammers.systemutviklingsprosjekt.order.Order;
import no.brogrammers.systemutviklingsprosjekt.user.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by Knut on 19.04.16.
 */
public class MainForm extends JFrame{
    private JTabbedPane tabbedPane1;
    private JPanel mainPanel;
    private JPanel orderTab;
    private JTable customersTable;
    private JScrollPane scrollPane1;
    private JButton addNewOrderButton;
    private JToolBar toolBarTest;
    private JButton button1;
    private JPanel driverRouteTab;
    private JPanel statisticsTab;
    private JPanel aboutTab;
    private JPanel homeTab;
    private JTable employeesTable;
    private JTabbedPane tabbedPane2;
    private JTable deliveriesTodayTable;
    private JTabbedPane tabbedPane3;
    private JButton addOrderButton;
    private JButton deleteOrderSButton;
    private JTable activeOrdersTable;
    private JTable previousOrdersTable;
    private JButton addCustomerButton;
    private JButton deleteCustomerButton;
    private JButton addEmployeeButton;
    private JButton button3;
    private JButton changeMyProfileDataButton;
    private JLabel userIdLabel;
    private JLabel nameLabel;
    private JLabel phoneLabel;
    private JLabel emailLabel;
    private JLabel employmentLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTable able4;

    private ManageOrder manageOrder = new ManageOrder();
    private ManageCustomer manageCustomer = new ManageCustomer(); //TODO: How to use interfaces instead of these?
    private DriverConnection driverConnection = new DriverConnection();
    private ManageUser manageUser = new ManageUser();

    //Current user object
    private final User user;

    //Setup all array lists
    private ArrayList<Order> orders = new ArrayList<Order>();
    private ArrayList<Customer> customers = new ArrayList<Customer>();
    private ArrayList<User> users = new ArrayList<User>();
    //private ArrayList<> //TODO: driverroute:?

    public MainForm(User user) {
        this.user = user;
        setContentPane(mainPanel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(700, 450);
        setTitle("Healty Catering Ltd.");
        setLocationRelativeTo(null);
        setVisible(true);

        loadTabs();
        addOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNewOrderForm addNewOrderForm = new AddNewOrderForm();
            }
        });
        addCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNewEmployeeForm addNewEmployeeForm = new AddNewEmployeeForm();
            }
        });
        changeMyProfileDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeUserDetailsForm changeUserDetailsForm = new ChangeUserDetailsForm(MainForm.this);
            }
        });
    }

    public User getUser() {
        return user;
    }

    private void checkUserType() {

    }

    private void loadTabs() {
        //scrollPane1.setViewportView(customersTable);

        //Driver tab:
        String orderColumns[] = {"Order ID", "Customer ID", "Payment Status", "Order date", "Delivery Date", "Delivery Time", "Address", "Zip"};
        DefaultTableModel defaultTableModel2 = new DefaultTableModel(orderColumns, 0);
        deliveriesTodayTable.setModel(defaultTableModel2);
        ArrayList<Order> orders = driverConnection.deliveriesToday();

        for(int i = 0; i < orders.size(); i++) {
            int orderID = orders.get(i).getOrderID();
            int customerID = orders.get(i).getCustomerID();
            boolean paymentStatus = orders.get(i).isPaymentStatus();
            java.sql.Date orderDate = orders.get(i).getOrderDate();
            java.sql.Date deliveryDate = orders.get(i).getDeliveryDate();
            double deliveryTime = orders.get(i).getDeliveryTime();
            String address = orders.get(i).getAddress();
            int zip = orders.get(i).getZipCode();

            Object[] objects = {orderID, customerID, paymentStatus, orderDate, deliveryDate, deliveryTime, address, zip};
            defaultTableModel2.addRow(objects);
        }


        //Customer tab:
        String customerColumns[] = {"ID", "Name", "Address", "Zip Address", "Email Address", "Phone"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(customerColumns, 0);
        customersTable.setModel(defaultTableModel);
        //ArrayList<Customer> customers

        //Employee (user):
        String employeeColumns[] = {"ID", "Last Name", "First Name", "Phone", "Date of Employment", "Position", "Username", "Password", "Email Address"};
        DefaultTableModel employeesTableModel = new DefaultTableModel(employeeColumns, 0);
        employeesTable.setModel(employeesTableModel);
        ArrayList<User> users = manageUser.viewAllUsers();
        for(int i = 0; i < users.size(); i++) {
            int id = users.get(i).getID();
            String lastName = users.get(i).getLastName();
            String firstName = users.get(i).getFirstName();
            int phone = users.get(i).getPhoneNumber();
            Date dateOfEmployment = users.get(i).getDateOfEmployment();
            /*if (users.get(i) instanceof Manager) {

            } else if (users.get(i) instanceof Cashier) {

            } else if (users.get(i) instanceof Cook) {

            } else if (users.get(i) instanceof Driver) {

            }*/
            int pos = 1;
            String username = users.get(i).getUsername();
            String password = users.get(i).getPassword();
            String emailAddress = users.get(i).getEmail();
            Object objects[] = {id, lastName, firstName, phone, dateOfEmployment, pos, username, password, emailAddress};
            employeesTableModel.addRow(objects);
        }

        //Ingredients:
        ///toolBarTest.add("Test");
        String ingredientColumns[] = {"Name", "Quantity"}; //Measurment in own row?

        //Recipes:
        String recipeColumns[] = {"Name", "Type", "Price"};

        //Orders
        String orderrColumns[] = {"Order ID", "Customer ID", "Payment Status", "Order date", "Delivery Date", "Delivery Time", "Address", "Zip"};
        DefaultTableModel defaultTableModel3 = new DefaultTableModel(orderColumns, 0);
        previousOrdersTable.setModel(defaultTableModel3);
        ArrayList<Order> previousOrders = manageOrder.viewPreviousOrders();
        for (int i = 0; i < previousOrders.size(); i++) {
            int orderID = previousOrders.get(i).getOrderID();
            int customerID = previousOrders.get(i).getCustomerID();
            boolean paymentStatus = previousOrders.get(i).isPaymentStatus();
            java.sql.Date orderDate = previousOrders.get(i).getOrderDate();
            java.sql.Date deliveryDate = previousOrders.get(i).getDeliveryDate();
            double deliveryTime = previousOrders.get(i).getDeliveryTime();
            String address = previousOrders.get(i).getAddress();
            int zip = previousOrders.get(i).getZipCode();

            Object[] objects = {orderID, customerID, paymentStatus, orderDate, deliveryDate, deliveryTime, address, zip};
            defaultTableModel3.addRow(objects);
        }

        DefaultTableModel defaultTableModel4 = new DefaultTableModel(orderColumns, 0);
        activeOrdersTable.setModel(defaultTableModel4);
        ArrayList<Order> activeOrders = manageOrder.viewActiveOrders();
        for (int i = 0; i < activeOrders.size(); i++) {
            int orderID = activeOrders.get(i).getOrderID();
            int customerID = activeOrders.get(i).getCustomerID();
            boolean paymentStatus = activeOrders.get(i).isPaymentStatus();
            java.sql.Date orderDate = activeOrders.get(i).getOrderDate();
            java.sql.Date deliveryDate = activeOrders.get(i).getDeliveryDate();
            double deliveryTime = activeOrders.get(i).getDeliveryTime();
            String address = activeOrders.get(i).getAddress();
            int zip = activeOrders.get(i).getZipCode();

            Object[] objects = {orderID, customerID, paymentStatus, orderDate, deliveryDate, deliveryTime, address, zip};
            defaultTableModel4.addRow(objects);
        }


        //Subscription:

        //Maps:

        //Load "my profile":
        userIdLabel.setText("User ID: " + String.valueOf(user.getID()));
        nameLabel.setText("Name: " + user.getFirstName() + " " + user.getLastName());
        phoneLabel.setText("Phone Address: " + String.valueOf(user.getPhoneNumber()));
        emailLabel.setText("Email Address: " + user.getEmail());
        employmentLabel.setText("Date of Employment: " + user.getDateOfEmployment().toString());
        usernameLabel.setText("Username: " + user.getUsername());
        passwordLabel.setText("Password: " + user.getPassword());



        /*ArrayList<Order> orders = manageOrder.viewAllOrders();
        for(int i = 0; i < orders.size(); i++) {
            showMessageDialog(null, orders.get(i).toString());
        }*/

        /*ArrayList<Customer> customers = manageCustomer.viewAllCustomers();
        for(int i = 0; i < customers.size(); i++) {
            /*if(customers.get(i) != null) {
                showMessageDialog(null, customers.get(i).toString());
            }
            //activeOrdersTable.add();
            int id = customers.get(i).getID();
            String address = customers.get(i).getAddress();
            String emailAddress = customers.get(i).getEmail();
            Object[] objects = {id, address, emailAddress};
            defaultTableModel.addRow(objects);
        }*/


        /*for(int i = 0; i < orders.size(); i++) {
            orderTab.add(orders.toArray());
        }*/

    }


}
