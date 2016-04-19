package no.brogrammers.systemutviklingsprosjekt.gui;

import no.brogrammers.systemutviklingsprosjekt.customer.Customer;
import no.brogrammers.systemutviklingsprosjekt.customer.ManageCustomer;
import no.brogrammers.systemutviklingsprosjekt.order.ManageOrder;
import no.brogrammers.systemutviklingsprosjekt.order.Order;
import no.brogrammers.systemutviklingsprosjekt.user.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by Knut on 19.04.16.
 */
public class MainForm extends JFrame{
    private JTabbedPane tabbedPane1;
    private JPanel mainPanel;
    private JTable table1;
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
    private JButton removeOrderSButton;
    private JTable table3;

    private ManageOrder manageOrder = new ManageOrder();
    private ManageCustomer manageCustomer = new ManageCustomer(); //TODO: How to use interfaces instead of these?

    public MainForm(User user) {
        setContentPane(mainPanel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(700, 450);
        setTitle("Healty Catering Ltd.");
        setLocationRelativeTo(null);
        setVisible(true);

        loadTabs();
    }

    private void loadTabs() {
        //scrollPane1.setViewportView(table2);

        String orderColumns[] = {"Order ID", "Customer ID", "Payment Status", "Order date", "Delivery Date", "Delivery Time", "Address", "Zip"};

        //Customer tab: //TODO: Private customer og company tab for seg sjøl?
        /*String customerColumns[] = {"", "", ""};
        DefaultTableModel defaultTableModel = new DefaultTableModel(customerColumns, 0);
        table1.setModel(defaultTableModel);*/

        //Employee:
        String employeeColumns[] = {"ID", "Last Name", "First Name", "Phone", "Date of Employment", "Position", "Username", "Password", "Email Address"};

        //Ingredients:
        ///toolBarTest.add("Test");
        String ingredientColumns[] = {"Name", "Quantity"}; //Measurment in own row?

        //Recipes:
        String recipeColumns[] = {"Name", "Type", "Price"};

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
            //table1.add();
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
