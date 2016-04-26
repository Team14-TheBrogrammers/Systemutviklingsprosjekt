package no.brogrammers.systemutviklingsprosjekt.gui;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import no.brogrammers.systemutviklingsprosjekt.customer.Customer;
import no.brogrammers.systemutviklingsprosjekt.customer.ManageCustomer;
import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.CookConnection;
import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.DriverConnection;
import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.IngredientConnection;
import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.RecipeConnection;
import no.brogrammers.systemutviklingsprosjekt.gui.employeeforms.AddNewEmployeeForm;
import no.brogrammers.systemutviklingsprosjekt.gui.ingredientforms.AddNewIngredientForm;
import no.brogrammers.systemutviklingsprosjekt.gui.orderforms.AddNewOrderForm;
import no.brogrammers.systemutviklingsprosjekt.gui.recipeforms.AddNewRecipeForm;
import no.brogrammers.systemutviklingsprosjekt.gui.userforms.ChangeUserDetailsForm;
import no.brogrammers.systemutviklingsprosjekt.miscellaneous.*;
import no.brogrammers.systemutviklingsprosjekt.order.ManageOrder;
import no.brogrammers.systemutviklingsprosjekt.order.Order;
import no.brogrammers.systemutviklingsprosjekt.recipe.Ingredient;
import no.brogrammers.systemutviklingsprosjekt.recipe.Recipe;
import no.brogrammers.systemutviklingsprosjekt.recipe.RecipeType;
import no.brogrammers.systemutviklingsprosjekt.statistics.CustomerTypeDiagram;
import no.brogrammers.systemutviklingsprosjekt.statistics.MonthlyIncomeDiagram;
import no.brogrammers.systemutviklingsprosjekt.statistics.MostPopularRecipesDiagram;
import no.brogrammers.systemutviklingsprosjekt.statistics.PopularWeekdayDiagram;
import no.brogrammers.systemutviklingsprosjekt.user.*;

import org.jfree.chart.ChartPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.util.ArrayList;

//import static java.awt.event.KeyEvent.VK_ENTER;
import static javax.swing.JOptionPane.showConfirmDialog;

/**
 * Created by Knut on 19.04.16.
 */
public class MainForm extends JFrame{
    private JTabbedPane tabbedPane1;
    private JPanel mainPanel;
    private JPanel orderTab;
    private JButton addNewOrderButton;
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
    private JTable recipeTable;
    private JButton addRecipeButton;
    private JTabbedPane tabbedPane4;
    private JTable takeAwayTable;
    private JButton addNewIngredientButton;
    private JTable ingredientsTable;
    private JButton deleteSelectedIngredientSButton;
    private JTable table4;
    private JButton button5;
    private JButton button6;
    private ChartPanel chartPanel1;
    private ChartPanel chartPanel2;
    private ChartPanel chartPanel3;
    private ChartPanel chartPanel4;
    private JPanel mapPanel;
    private JTabbedPane tabbedPane5;
    private JTable privateCustomersTable;
    private JTable table2;
    private JTabbedPane tabbedPane6;
    private JTable ordersTodayTable;
    private JTable deliveriesIngredientsTable;
    private JButton buyAllIngredientsForButton;
    private JButton buyIngredientsButton;
    private JTextField buyAllIngredientsForTextField;
    private BrowserView browserView1;
    private JTable table1;
    private JTable table3;
    private JTable table5;
    private JTable table6;
    private JScrollPane DeliveriesTodayTable;
    private BrowserView test12345;
    private BrowserView testassdasd;
    private JPanel incomePanel;
    private JTable able4;

    private ManageOrder manageOrder;// = new ManageOrder();
    private ManageCustomer manageCustomer;// = new ManageCustomer(); //TODO: How to use interfaces instead of these?
    private DriverConnection driverConnection;
    private ManageUser manageUser;// = new ManageUser();
    private RecipeConnection recipeConnection;// = new RecipeConnection();
    private IngredientConnection ingredientConnection;// = new IngredientConnection();
    private CookConnection cookConnection;//= new CookConnection();


    //Current user object
    private final User user;

    //Setup all array lists
    private ArrayList<Order> orders = new ArrayList<Order>();
    private ArrayList<Customer> customers = new ArrayList<Customer>();
    private ArrayList<User> users = new ArrayList<User>();
    //private ArrayList<> //TODO: driverroute:?

    //DefaultListModels for using in tables
    NonEditTableModel acticeOrdersTableModel;
    NonEditTableModel previousOrdersTableModel;


    public MainForm(User user) {
        this.user = user;
        setContentPane(mainPanel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(1300, 800);
        setTitle("Healty Catering Ltd.");
        setLocationRelativeTo(null);
        setVisible(true);

        loadTabMenu();

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
                    System.out.println("test");//TODO:// FIXME: 25.04.2016 asd
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
        addNewIngredientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNewIngredientForm addNewIngredientForm = new AddNewIngredientForm();
            }
        });
        buyAllIngredientsForButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("TAKE AWAY");
                cookConnection = new CookConnection();
                cookConnection.buyAllTakeAwayIngredientsForToday();
                loadCookTab();
                cookConnection.stopConnection();
            }
        });
        buyIngredientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cookConnection = new CookConnection();
                cookConnection.buyIngredientsTwoDaysFromTomorrow();
                loadCookTab();
                cookConnection.stopConnection();
            }
        });
    }

    private void loadTabMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("test123");
        menu.setMnemonic(KeyEvent.VK_ALT);
        menuBar.add(menu);
        //Add subItems etc.
        setJMenuBar(menuBar);
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
        //Start Connection for ManageOrder object
        manageOrder = new ManageOrder();

        //Orders
        String orderColumns[] = {"Order ID", "Customer ID", "Payment Status", "Order date", "Delivery Date", "Delivery Time", "Address", "Zip"};

        //Active orders:
        acticeOrdersTableModel = new NonEditTableModel(orderColumns, 0);
        activeOrdersTable.setModel(acticeOrdersTableModel);
        ArrayList<Order> activeOrders = manageOrder.viewActiveOrders();
        addRowsToOrderTab(acticeOrdersTableModel, activeOrders);

        //Previous orders:
        previousOrdersTableModel = new NonEditTableModel(orderColumns, 0);
        previousOrdersTable.setModel(previousOrdersTableModel);
        ArrayList<Order> previousOrders = manageOrder.viewPreviousOrders();
        addRowsToOrderTab(previousOrdersTableModel, previousOrders);

        manageOrder.stopConnection();
    }

    private void loadRecipesTab() {
        //asd
        recipeConnection = new RecipeConnection();

        //Recipes:
        String recipeColumns[] = {"Name", "Type", "Price"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(recipeColumns, 0);
        recipeTable.setModel(defaultTableModel);
        ArrayList<Recipe> recipes = recipeConnection.viewAllRecipes();
        for(int i = 0; i < recipes.size(); i++) {
            String name = recipes.get(i).getRecipeName();
            RecipeType recipeType = recipes.get(i).getRecipeType();
            double price = recipes.get(i).getPrice();
            Object objects[] = {name, recipeType, price};
            defaultTableModel.addRow(objects);
        }
        recipeConnection.stopConnection();
    }

    private void loadIngredientsTab() {
        String ingredientColumns[] = {"Name", "In Stock"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(ingredientColumns, 0);
        ingredientsTable.setModel(defaultTableModel);
        ArrayList<Ingredient> ingredients = ingredientConnection.viewAllIngredients();
        ArrayList<String> measurements = ingredientConnection.viewAllMeasurements();
        for(int i = 0; i < ingredients.size(); i++) {
            String name = ingredients.get(i).getIngredientName();
            String measurement = ingredients.get(i).getQuantity() + " " + measurements.get(i);
            Object objects[] = {name, measurement};
            defaultTableModel.addRow(objects);
        }
    }

    private void loadCustomersTab() {
        //Customer tab:
        String customerColumns[] = {"ID", "Name", "Address", "Zip Address", "Email Address", "Phone"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(customerColumns, 0);
        privateCustomersTable.setModel(defaultTableModel);
        ArrayList<Customer> customers = manageCustomer.viewAllCustomers();
        for(int i = 0; i < customers.size(); i++) {
            int id = customers.get(i).getID();
            //String name = customers.get(i).get

        }
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
        /*String orderColumns[] = {"Order ID", "Customer ID", "Payment Status", "Order date", "Delivery Date", "Delivery Time", "Address", "Zip"};
        DefaultTableModel defaultTableModel2 = new DefaultTableModel(orderColumns, 0);
        deliveriesTodayTable.setModel(defaultTableModel2);
        ArrayList<Order> orders = driverConnection.deliveriesToday();
        addRowsToOrderTab(defaultTableModel2, orders);*/

        //Browser browser = new Browser();
        //test123 = new BrowserView(browser);
        //view = new BrowserView(browser);

        //mapPanel.add(test123);
        driverConnection = new DriverConnection();
        String deliveriesColumns1[] = {"Route", "Time", "Show Route", "Drive"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(deliveriesColumns1, 0);
        deliveriesTodayTable.setModel(defaultTableModel);
        ArrayList<ArrayList<Order>> orders = driverConnection.splitDeliveriesToday();

        ArrayList<ArrayList<String>> addresses = new ArrayList<ArrayList<String>>();
        ArrayList<String> addressList;// = new ArrayList<String>();

        for(ArrayList<Order> innerList : orders) {
            //addressList = new ArrayList<String>();

            String time = String.valueOf(innerList.get(0).getDeliveryTime());
            for(int i = 1; i < innerList.size(); i++) {//(Order order : innerList) {
                //Make address list for mapmethod: uses button as listner

                double time2 = innerList.get(i).getDeliveryTime();

                if(!(time2 == (innerList.get(i-1).getDeliveryTime()))) {//TODO: fix format osv
                    time += " - " + time2;
                }
            }




            System.out.println(time);
            Object objects[] = {"Route", time, "Show Route", "Drive"};
            defaultTableModel.addRow(objects);
            //addresses.add(addressList);
            driverConnection.stopConnection();
        }





    }

    private void createUIComponents() {
        Browser browser = new Browser();
        browserView1 = new BrowserView(browser);
        browser.loadURL(getClass().getResource("/Driver map/Map.html").toString());//"http://www.google.com");
        //test123.getBrowser().loadURL("google.com");
        //browser.j

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

    private void loadCookTab() {
        //ordersTodayTable
        //deliveriesToday() {//order_id, delivery_date, delivery_time, take_away, other_request
        String ta = "";
        cookConnection = new CookConnection();
        final String cookColumns[] = {"Order ID", "Delivery Date", "Delivery Time", "Delivery/Take Away", "Other Requests", "Make Order"};
        //NonEditTableModel nonEditTableModel = new NonEditTableModel(cookColumns, 0);
        DefaultTableModel nonEditTableModel = new DefaultTableModel(cookColumns, 0);
        ordersTodayTable.setModel(nonEditTableModel);
        ArrayList<Order> orders = cookConnection.deliveriesToday();

        /*ActionListener actionListener123 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("test");
            }
        };*/

        for(int i = 0; i < orders.size(); i++) {
            int id = orders.get(i).getOrderID();
            java.sql.Date date = orders.get(i).getDeliveryDate();
            double time = orders.get(i).getDeliveryTime();
            if(orders.get(i).isTakeAway()) {
                ta = "Take Away";
            } else {
                ta = "Delivery";
            }
            ButtonRenderer buttonRenderer = new ButtonRenderer();
            //buttonRenderer.addActionListener(actionListener123);
            ordersTodayTable.getColumn("Make Order").setCellRenderer(buttonRenderer);//new ButtonRenderer());
            ordersTodayTable.getColumn("Make Order").setCellEditor(
                    new ButtonEditor(new JCheckBox()){
                        @Override
                        public Object getCellEditorValue() {
                            if (isPushed()) {
                                cookConnection = new CookConnection();
                                int make = cookConnection.makeOrder(Integer.parseInt(ordersTodayTable.getValueAt(ordersTodayTable.getSelectedRow(), 0).toString()));
                                if(make == 1) {
                                    System.out.println("order made");
                                }
                                cookConnection.stopConnection();
                                loadCookTab();
                                button.setEnabled(false);
                            }
                            //setPushed(false);
                            return new String(getLabel());
                        }
                    });
            //TODO: set all made = button.setEnabled(false)
            String otherRequests = orders.get(i).getOtherRequests();
            Object objects[] = {id, date, time, ta, otherRequests, "Make Order"};
            nonEditTableModel.addRow(objects);
            cookConnection.stopConnection();
        }

        String cookColumns3[] = {"Ingredient Name", "Quantity missing"};
        NonEditTableModel nonEditTableModel3 = new NonEditTableModel(cookColumns3, 0);
        deliveriesIngredientsTable.setModel(nonEditTableModel3);
        ArrayList<Ingredient> ingredients = cookConnection.missingIngredientsTwoDaysFromTomorrow();
        for (int i = 0; i < ingredients.size(); i++) {
            String name = ingredients.get(i).getIngredientName();
            double quantity = -(ingredients.get(i).getQuantity());
            Object objects[] = {name, quantity};
            nonEditTableModel3.addRow(objects);
        }

        String cookColumns2[] = {"Order ID", "Delivery Date", "Delivery Time", "Other Requests", "Buy Ingredients"};
        DefaultTableModel nonEditTableModel2 = new DefaultTableModel(cookColumns2, 0);
        takeAwayTable.setModel(nonEditTableModel2);
        ArrayList<Order> orders2 = cookConnection.takeAwayToday();
        for(int i = 0; i < orders2.size(); i++) {
            int id = orders2.get(i).getOrderID();
            java.sql.Date date = orders2.get(i).getDeliveryDate();
            double time = orders2.get(i).getDeliveryTime();
            String otherRequests = orders2.get(i).getOtherRequests();
            Object objects[] = {id, date, time, otherRequests, "Buy Ingredients"};
            takeAwayTable.getColumn("Buy Ingredients").setCellRenderer(new ButtonRenderer());
            takeAwayTable.getColumn("Buy Ingredients").setCellEditor(
                    new ButtonEditor(new JCheckBox()){
                        @Override
                        public Object getCellEditorValue() {
                            if (isPushed()) {
                                cookConnection = new CookConnection();
                                int purchase = cookConnection.buyIngredientsTakeAway(Integer.parseInt(takeAwayTable.getValueAt(takeAwayTable.getSelectedRow(), 0).toString()));
                                if(purchase == 1) {
                                    System.out.println("ingredients purchased");
                                }
                                cookConnection.stopConnection();
                                loadCookTab();
                                button.setEnabled(false);
                            }
                            setPushed(false);
                            return new String(getLabel());
                        }
                    });
            nonEditTableModel2.addRow(objects);
        }
    }


    private void loadTabs() {
        loadOrdersTab();
        //subscriptions
        loadRecipesTab();//TODO:DOES NOT WORKSSSS
        //loadCustomersTab();
        //loadEmployeesTab();
        //loadIngredientsTab();

        //loadCustomersTab();

        loadCookTab();

        //loadStatisticsTab();
        //scrollPane1.setViewportView(customersTable);
        loadDriverRouteTab();

        //Subscription:

        //Maps:


    }


}
