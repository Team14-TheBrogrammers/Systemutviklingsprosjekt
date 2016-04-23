package no.brogrammers.systemutviklingsprosjekt.gui;

import no.brogrammers.systemutviklingsprosjekt.customer.Customer;
import no.brogrammers.systemutviklingsprosjekt.customer.ManageCustomer;
import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.DriverConnection;
import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.OrderConnection;
import no.brogrammers.systemutviklingsprosjekt.gui.employeeforms.AddNewEmployeeForm;
import no.brogrammers.systemutviklingsprosjekt.gui.orderforms.AddNewOrderForm;
import no.brogrammers.systemutviklingsprosjekt.gui.recipeforms.AddNewRecipeForm;
import no.brogrammers.systemutviklingsprosjekt.gui.userforms.ChangeUserDetailsForm;
import no.brogrammers.systemutviklingsprosjekt.order.ManageOrder;
import no.brogrammers.systemutviklingsprosjekt.order.Order;
import no.brogrammers.systemutviklingsprosjekt.statistics.CustomerTypeDiagram;
import no.brogrammers.systemutviklingsprosjekt.statistics.MonthlyIncomeDiagram;
import no.brogrammers.systemutviklingsprosjekt.statistics.MostPopularRecipesDiagram;
import no.brogrammers.systemutviklingsprosjekt.statistics.PopularWeekdayDiagram;
import no.brogrammers.systemutviklingsprosjekt.user.*;
import org.jfree.chart.ChartPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.sql.Date;
import java.util.ArrayList;

import static javax.swing.JOptionPane.OPTION_TYPE_PROPERTY;
import static javax.swing.JOptionPane.showConfirmDialog;
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
    private JButton deleteCustomerSButton;
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
    private JTable table1;
    private JButton addRecipeButton;
    private JTabbedPane tabbedPane4;
    private ChartPanel chartPanel1;
    private ChartPanel chartPanel2;
    private ChartPanel chartPanel3;
    private ChartPanel chartPanel4;
    private JPanel incomePanel;
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

    //DefaultListModels for using in tables
    DefaultTableModel acticeOrdersTableModel;
    DefaultTableModel previousOrdersTableModel;


    public MainForm(User user) {
        this.user = user;
        setContentPane(mainPanel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(1300, 800);
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
        deleteOrderSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = "Deleting order";
                String message = "Are you sure you want to delete the selected order";
                if(activeOrdersTable.getSelectedRowCount() <= 2) {
                    message += "s";
                    title += "s";
                }
                message += "?";
                title += "?";

                if(showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 1) {
                    System.out.println("test");
                }

            }
        });

        //activeOrdersTable.addListSelectionListener(new ListSelectionListener() {


        addRecipeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNewRecipeForm addNewRecipeForm = new AddNewRecipeForm();
            }
        });
    }

    public User getUser() {
        return user;
    }

    private void checkUserType() { //TODO: // FIXME: 22.04.2016
        if(user instanceof Manager) {

        }
    }

    /**
     * Method for adding rows of orders to a order table by selecting DefaultTableModel and the ArrayList.
     * @param defTabModel is the DefaultTableModel where the rows is added.
     * @param orders is the ArrayList of Order objects, which is added to the table.
     */
    private void addRowsToOrderTab(DefaultTableModel defTabModel, ArrayList<Order> orders) {
        for(int i = 0; i < orders.size(); i++) {
            int orderID = orders.get(i).getOrderID();
            int customerID = orders.get(i).getCustomerID();
            boolean paymentStatus = orders.get(i).isPaymentStatus();
            java.sql.Date orderDate = orders.get(i).getOrderDate();
            java.sql.Date deliveryDate = orders.get(i).getDeliveryDate();
            double deliveryTime = orders.get(i).getDeliveryTime();
            String address = orders.get(i).getAddress();
            int zip = orders.get(i).getZipCode();

            //Adding objects to array, and then to the table
            Object[] objects = {orderID, customerID, paymentStatus, orderDate, deliveryDate, deliveryTime, address, zip};
            defTabModel.addRow(objects); //Add the row to the table
        }
    }

    private void loadOrdersTab() {
        //Orders
        String orderColumns[] = {"Order ID", "Customer ID", "Payment Status", "Order date", "Delivery Date", "Delivery Time", "Address", "Zip"};
/*
        //Active orders:
        acticeOrdersTableModel = new DefaultTableModel(orderColumns, 0);
        activeOrdersTable.setModel(acticeOrdersTableModel);
        ArrayList<Order> activeOrders = manageOrder.viewActiveOrders();
        addRowsToOrderTab(acticeOrdersTableModel, activeOrders);

        //Previous orders:
        DefaultTableModel previousOrdersTableModel = new DefaultTableModel(orderColumns, 0);
        previousOrdersTable.setModel(previousOrdersTableModel);
        ArrayList<Order> previousOrders = manageOrder.viewPreviousOrders();
        addRowsToOrderTab(previousOrdersTableModel, previousOrders);*/
    }

    private void loadRecipesTab() {
        //Recipes:
        String recipeColumns[] = {"Name", "Type", "Price"};
    }

    private void loadCustomersTab() {
        //Customer tab:
        String customerColumns[] = {"ID", "Name", "Address", "Zip Address", "Email Address", "Phone"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(customerColumns, 0);
        customersTable.setModel(defaultTableModel);
        //ArrayList<Customer> customers
    }

    private void loadEmployeesTab() {
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
    }

    private void loadDriverRouteTab() {
        //Driver tab:
        String orderColumns[] = {"Order ID", "Customer ID", "Payment Status", "Order date", "Delivery Date", "Delivery Time", "Address", "Zip"};
        DefaultTableModel defaultTableModel2 = new DefaultTableModel(orderColumns, 0);
        deliveriesTodayTable.setModel(defaultTableModel2);
        ArrayList<Order> orders = driverConnection.deliveriesToday();
        addRowsToOrderTab(defaultTableModel2, orders);
    }

    private void createUIComponents() {
        loadStatisticsTab();
    }

    private void loadStatisticsTab() {
        MonthlyIncomeDiagram monthlyIncomeDiagram = new MonthlyIncomeDiagram();
        CustomerTypeDiagram customerTypeDiagram = new CustomerTypeDiagram();
        PopularWeekdayDiagram popularWeekdayDiagram = new PopularWeekdayDiagram();
        MostPopularRecipesDiagram mostPopularRecipesDiagram = new MostPopularRecipesDiagram();
        //ChartPanel chartPanel = monthlyIncomeDiagram.createChartPanel();
        chartPanel1 = monthlyIncomeDiagram.createChartPanel();
        chartPanel2 = customerTypeDiagram.createChartPanel();
        chartPanel3 = popularWeekdayDiagram.createChartPanel();
        chartPanel4 = mostPopularRecipesDiagram.createChartPanel();
        //incomePanel.add(chartPanel);
    }

    private void loadMyProfileTab() {
        //Load "my profile":
        userIdLabel.setText("User ID: " + String.valueOf(user.getID()));
        nameLabel.setText("Name: " + user.getFirstName() + " " + user.getLastName());
        phoneLabel.setText("Phone Address: " + String.valueOf(user.getPhoneNumber()));
        emailLabel.setText("Email Address: " + user.getEmail());
        employmentLabel.setText("Date of Employment: " + user.getDateOfEmployment().toString());
        usernameLabel.setText("Username: " + user.getUsername());
        passwordLabel.setText("Password: " + user.getPassword());
    }

    private void subssscriptionssstuffhere() {

    }

    private void loadTabs() {
        loadOrdersTab();
        loadCustomersTab();
        //loadStatisticsTab();
        //scrollPane1.setViewportView(customersTable);





        //Ingredients:
        ///toolBarTest.add("Test");
        String ingredientColumns[] = {"Name", "Quantity"}; //Measurment in own row?







        //Subscription:

        //Maps:


    }


}
