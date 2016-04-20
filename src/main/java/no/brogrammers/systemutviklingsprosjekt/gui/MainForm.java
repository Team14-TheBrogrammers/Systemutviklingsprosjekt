package no.brogrammers.systemutviklingsprosjekt.gui;

import no.brogrammers.systemutviklingsprosjekt.customer.ManageCustomer;
import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.DriverConnection;
import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.OrderConnection;
import no.brogrammers.systemutviklingsprosjekt.order.ManageOrder;
import no.brogrammers.systemutviklingsprosjekt.order.Order;
import no.brogrammers.systemutviklingsprosjekt.user.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by Knut on 19.04.16.
 */
public class MainForm extends JFrame{
    private JTabbedPane tabbedPane1;
    private JPanel mainPanel;
    private JPanel orderTab;
    private JList list1;
    private JTable table2;
    private JScrollPane scrollPane1;
    private JButton addNewOrderButton;
    private JToolBar toolBarTest;
    private JButton button1;
    private JPanel driverRouteTab;
    private JPanel statisticsTab;
    private JPanel aboutTab;
    private JPanel homeTab;
    private JTable table3;
    private JTabbedPane tabbedPane2;
    private JTable deliveriesTodayTable;
    private JTabbedPane tabbedPane3;
    private JButton addOrderButton;
    private JButton deleteOrderSButton;
    private JTable activeOrdersTable;
    private JTable previousOrdersTable;
    private JTable able4;

    private ManageOrder manageOrder = new ManageOrder();
    private ManageCustomer manageCustomer = new ManageCustomer(); //TODO: How to use interfaces instead of these?
    private DriverConnection driverConnection = new DriverConnection();
    private OrderConnection orderConnection = new OrderConnection();

    public MainForm(User user) {
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

            }
        });
    }

    private void loadTabs() {
        //scrollPane1.setViewportView(table2);

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


        //Customer tab: //TODO: Private customer og company tab for seg sjøl?
        /*String customerColumns[] = {"", "", ""};
        DefaultTableModel defaultTableModel = new DefaultTableModel(customerColumns, 0);
        activeOrdersTable.setModel(defaultTableModel);*/

        //Employee:
        String employeeColumns[] = {"ID", "Last Name", "First Name", "Phone", "Date of Employment", "Position", "Username", "Password", "Email Address"};

        //Ingredients:
        ///toolBarTest.add("Test");
        String ingredientColumns[] = {"Name", "Quantity"}; //Measurment in own row?

        //Recipes:
        String recipeColumns[] = {"Name", "Type", "Price"};

        //Orders
        String orderrColumns[] = {"Order ID", "Customer ID", "Payment Status", "Order date", "Delivery Date", "Delivery Time", "Address", "Zip"};
        DefaultTableModel defaultTableModel3 = new DefaultTableModel(orderColumns, 0);
        previousOrdersTable.setModel(defaultTableModel3);
        ArrayList<Order> previousOrders = orderConnection.viewPreviousOrders();
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
        ArrayList<Order> activeOrders = orderConnection.viewActiveOrders();
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
