package no.brogrammers.systemutviklingsprosjekt.gui;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserContext;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import no.brogrammers.systemutviklingsprosjekt.customer.Company;
import no.brogrammers.systemutviklingsprosjekt.customer.Customer;
import no.brogrammers.systemutviklingsprosjekt.customer.ManageCustomer;
import no.brogrammers.systemutviklingsprosjekt.customer.PrivateCustomer;
import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.CookConnection;
import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.DriverConnection;
import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.IngredientConnection;
import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.RecipeConnection;
import no.brogrammers.systemutviklingsprosjekt.gui.customerforms.AddNewCustomerForm;
import no.brogrammers.systemutviklingsprosjekt.gui.employeeforms.AddNewEmployeeForm;
import no.brogrammers.systemutviklingsprosjekt.gui.ingredientforms.AddNewIngredientForm;
import no.brogrammers.systemutviklingsprosjekt.gui.orderforms.AddNewOrderForm;
import no.brogrammers.systemutviklingsprosjekt.gui.recipeforms.AddNewRecipeForm;
import no.brogrammers.systemutviklingsprosjekt.gui.userforms.ChangeUserDetailsForm;
import no.brogrammers.systemutviklingsprosjekt.miscellaneous.NonEditTableModel;
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
    private JTable allCompaniesTable;
    private JTabbedPane tabbedPane6;
    private JTable ordersTodayTable;
    private JTable deliveriesIngredientsTable;
    private JButton buyAllIngredientsForButton;
    private JButton buyButton;
    private JTextField buyIngredientsForDeliveriesTextField;
    private JTable allCustomersTable;
    private BrowserView testassdasd;
    private JPanel incomePanel;
    private JTable able4;

    private ManageOrder manageOrder;// = new ManageOrder();
    private ManageCustomer manageCustomer;// = new ManageCustomer(); //TODO: How to use interfaces instead of these?
    private DriverConnection driverConnection;// = new DriverConnection();
    private ManageUser manageUser;// = new ManageUser();
    private RecipeConnection recipeConnection;// = new RecipeConnection();
    private IngredientConnection ingredientConnection;// = new IngredientConnection();

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
                AddNewCustomerForm addNewCustomerForm = new AddNewCustomerForm(MainForm.this, true);
            }
        });
        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNewEmployeeForm addNewEmployeeForm = new AddNewEmployeeForm(MainForm.this);
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
                AddNewIngredientForm addNewIngredientForm = new AddNewIngredientForm(MainForm.this);
            }
        });
        buyAllIngredientsForButton.addActionListener(new ActionListener() {
            CookConnection cookConnection = new CookConnection();
            @Override
            public void actionPerformed(ActionEvent e) {
                cookConnection.buyTakeAwayIngredientsForYesterday();
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

    NonEditTableModel recipeTableModel;
    private void loadRecipesTab() {
        //asd
        recipeConnection = new RecipeConnection();

        //Recipes:
        String recipeColumns[] = {"Name", "Type", "Price"};
        recipeTableModel = new NonEditTableModel(recipeColumns, 0);
        recipeTable.setModel(recipeTableModel);
        ArrayList<Recipe> recipes = recipeConnection.viewAllRecipes();
        for(int i = 0; i < recipes.size(); i++) {
            String name = recipes.get(i).getRecipeName();
            RecipeType recipeType = recipes.get(i).getRecipeType();
            double price = recipes.get(i).getPrice();
            Object objects[] = {name, recipeType, price};
            recipeTableModel.addRow(objects);
        }
        recipeConnection.stopConnection();
    }

    NonEditTableModel ingredientTableModel;
    public void loadIngredientsTab() {
        ingredientConnection = new IngredientConnection();
        String ingredientColumns[] = {"Name", "In Stock"};
        ingredientTableModel = new NonEditTableModel(ingredientColumns, 0);
        ingredientsTable.setModel(ingredientTableModel);
        ArrayList<Ingredient> ingredients = ingredientConnection.viewAllIngredients();
        ArrayList<String> measurements = ingredientConnection.viewAllMeasurements();
        for(int i = 0; i < ingredients.size(); i++) {
            System.out.println("asd");
            String name = ingredients.get(i).getIngredientName();
            String measurement = ingredients.get(i).getQuantity() + " " + measurements.get(i);
            Object objects[] = {name, measurement};
            ingredientTableModel.addRow(objects);
        }
    }

    private NonEditTableModel allCustomersTableModel;
    private NonEditTableModel privateCustomerTableModel;
    private NonEditTableModel companiesTableModel;

    public void loadCustomersTab() {
        //Customer tab:
        String allCustomersColumns[] = {"ID", "Name", "Address", "Zip", "Email Address", "Phone"};
        String privateCustomerColumns[] = {"Customer ID", "Private Customer ID", "First Name", "Last Name", "Address", "Zip", "Email Address", "Phone"};
        String companyColumns[] = {"Customer ID", "Company ID", "Name", "Address", "Zip", "Email Address", "Phone"};
        manageCustomer = new ManageCustomer();

        //All customers tab:
        allCustomersTableModel = new NonEditTableModel(allCustomersColumns, 0);
        allCustomersTable.setModel(allCustomersTableModel);
        ArrayList<Customer> customers = manageCustomer.viewAllCustomers();
        for(int i = 0; i < customers.size(); i++) {
            int customerID = customers.get(i).getID();
            String name = "";
            if(customers.get(i) instanceof Company) {
                name = ((Company) customers.get(i)).getName();
            } else if(customers.get(i) instanceof PrivateCustomer) {
                name = ((PrivateCustomer) customers.get(i)).getFirstName() + " " + ((PrivateCustomer) customers.get(i)).getLastName();
            }
            String address = customers.get(i).getAddress();
            int zip = customers.get(i).getZip();
            String emailAddress = customers.get(i).getEmail();
            int phone = customers.get(i).getPhone();

            Object objects[] = {customerID, name, address, zip, emailAddress, phone};
            allCustomersTableModel.addRow(objects);
        }


        //Private Customers tab:
        privateCustomerTableModel = new NonEditTableModel(privateCustomerColumns, 0);
        privateCustomersTable.setModel(privateCustomerTableModel);
        ArrayList<PrivateCustomer> privateCustomers = manageCustomer.viewAllPrivateCustomers();
        for(int i = 0; i < privateCustomers.size(); i++) {
            int customerID = privateCustomers.get(i).getID();
            int privateCustomerID = privateCustomers.get(i).getPrivateID();
            String firstName = privateCustomers.get(i).getFirstName();
            String lastName = privateCustomers.get(i).getLastName();
            String address = privateCustomers.get(i).getAddress();
            int zipAddress = privateCustomers.get(i).getZip();
            String emailAddress = privateCustomers.get(i).getEmail();
            int phone = privateCustomers.get(i).getPhone();

            Object objects[] = {customerID, privateCustomerID, firstName, lastName, address, zipAddress, emailAddress, phone};
            privateCustomerTableModel.addRow(objects);
        }

        //Companies tab:
        companiesTableModel = new NonEditTableModel(companyColumns, 0);
        allCompaniesTable.setModel(companiesTableModel);
        ArrayList<Company> companies = manageCustomer.viewAllCompanies();
        for(int i = 0; i < companies.size(); i++) {
            int customerID = companies.get(i).getID();
            int companyID = companies.get(i).getCompanyID();
            String companyName = companies.get(i).getName();
            String address = companies.get(i).getAddress();
            int zip = companies.get(i).getZip();
            String emailAddress = companies.get(i).getEmail();
            int phone = companies.get(i).getPhone();

            Object objects[] = {customerID, companyID, companyName, address, zip, emailAddress, phone};
            companiesTableModel.addRow(objects);
        }
        manageCustomer.stopConnection();
    }

    private void loadEmployeesTab() {
        //Employee (user):
        manageUser = new ManageUser();
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
        manageUser.stopConnection();
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

    }

    private void createUIComponents() {
        //Browser browser = new Browser();
        //test123 = new BrowserView(browser);
        //browser.loadURL("http://www.google.com");
        //test123.getBrowser().loadURL("google.com");

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
        CookConnection cookConnection = new CookConnection();
        String cookColumns[] = {"Order ID", "Delivery Date", "Delivery Time", "Delivery/Take Away", "Other Requests", "Make Order"};
        NonEditTableModel nonEditTableModel = new NonEditTableModel(cookColumns, 0);
        ordersTodayTable.setModel(nonEditTableModel);
        ArrayList<Order> orders = cookConnection.deliveriesToday();
        for(int i = 0; i < orders.size(); i++) {
            int id = orders.get(i).getOrderID();
            java.sql.Date date = orders.get(i).getDeliveryDate();
            double time = orders.get(i).getDeliveryTime();
            if(orders.get(i).isTakeAway()) {
                ta = "Take Away";
            } else {
                ta = "Delivery";
            }
            String otherRequests = orders.get(i).getOtherRequests();
            Object objects[] = {id, date, time, ta, otherRequests, true};
            nonEditTableModel.addRow(objects);
        }

        String cookColumns2[] = {"Order ID", "Delivery Date", "Delivery Time", "Other Requests", "Buy Ingredients"};
        NonEditTableModel nonEditTableModel2 = new NonEditTableModel(cookColumns2, 0);
        takeAwayTable.setModel(nonEditTableModel2);
        ArrayList<Order> orders2 = cookConnection.takeAwayToday();
        for(int i = 0; i < orders2.size(); i++) {
            int id = orders2.get(i).getOrderID();
            java.sql.Date date = orders2.get(i).getDeliveryDate();
            double time = orders2.get(i).getDeliveryTime();
            String otherRequests = orders2.get(i).getOtherRequests();
            Object objects[] = {id, date, time, otherRequests};
            nonEditTableModel2.addRow(objects);
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
    }

    private void loadTabs() {
        loadOrdersTab();
        //subscriptions
        loadRecipesTab();//TODO:DOES NOT WORKSSSS
        //loadCustomersTab();
        loadEmployeesTab();
        loadIngredientsTab();

        loadCustomersTab();

        loadCookTab();

        //loadStatisticsTab();
        //scrollPane1.setViewportView(customersTable);
        //loadDriverRouteTab();

        //Subscription:

        //Maps:


    }


}
