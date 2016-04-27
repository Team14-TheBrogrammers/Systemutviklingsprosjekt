package no.brogrammers.systemutviklingsprosjekt.gui;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import no.brogrammers.systemutviklingsprosjekt.customer.Company;
import no.brogrammers.systemutviklingsprosjekt.customer.Customer;
import no.brogrammers.systemutviklingsprosjekt.customer.ManageCustomer;
import no.brogrammers.systemutviklingsprosjekt.customer.PrivateCustomer;
import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.*;
import no.brogrammers.systemutviklingsprosjekt.driverroute.Route;
import no.brogrammers.systemutviklingsprosjekt.gui.customerforms.AddNewCustomerForm;
import no.brogrammers.systemutviklingsprosjekt.gui.employeeforms.AddNewEmployeeForm;
import no.brogrammers.systemutviklingsprosjekt.gui.ingredientforms.AddNewIngredientForm;
import no.brogrammers.systemutviklingsprosjekt.gui.orderforms.AddNewOrderForm;
import no.brogrammers.systemutviklingsprosjekt.gui.recipeforms.AddNewRecipeForm;
import no.brogrammers.systemutviklingsprosjekt.gui.userforms.ChangeUserDetailsForm;
import no.brogrammers.systemutviklingsprosjekt.miscellaneous.*;
import no.brogrammers.systemutviklingsprosjekt.order.ManageOrder;
import no.brogrammers.systemutviklingsprosjekt.order.Order;
import no.brogrammers.systemutviklingsprosjekt.order.Subscription;
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
    private JButton buyIngredientsButton;
    private JTextField buyAllIngredientsForTextField;
    private BrowserView browserView1;
    private JTable table1;
    private JTable table3;
    private JTable table5;
    private JTable table6;
    private JScrollPane DeliveriesTodayTable;
    private JTable subscrptionsTab;
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
    private SubscriptionConnection subscriptionConnection;


    //Current user object
    private final User user;

    //Setup all array lists
    private ArrayList<Order> orders = new ArrayList<Order>();
    private ArrayList<Customer> customers = new ArrayList<Customer>();
    private ArrayList<User> users = new ArrayList<User>();

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

        checkUserType();

        //loadTabs();
        addOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNewOrderForm addNewOrderForm = new AddNewOrderForm(MainForm.this);
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
                AddNewRecipeForm addNewRecipeForm = new AddNewRecipeForm(MainForm.this);
            }
        });
        addNewIngredientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNewIngredientForm addNewIngredientForm = new AddNewIngredientForm(MainForm.this);
            }
        });
        buyAllIngredientsForButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("TAKE AWAY");
                cookConnection = new CookConnection();
                System.out.println(cookConnection.buyAllTakeAwayIngredientsForToday());
                cookConnection.stopConnection();
                loadCookTab();
            }
        });
        buyIngredientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cookConnection = new CookConnection();
                cookConnection.buyIngredientsTwoDaysFromTomorrow();
                cookConnection.stopConnection();
                loadCookTab();
            }
        });
    }

    private void loadTabMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_ALT);
        menuBar.add(menu);
        //Add subItems etc.
        setJMenuBar(menuBar);
    }

    public User getUser() {
        return user;
    }

    private void checkUserType() {
        if(user instanceof Manager) {
            loadOrdersTab();
            //tabbedPane1.removeTabAt(2); //2 is the index of subscription tab
            loadSubscriptionTab();
            loadRecipesTab();
            loadEmployeesTab();
            loadIngredientsTab();
            loadCustomersTab();
            loadCookTab();
            loadMyProfileTab();
            loadStatisticsTab();
            loadDriverRouteTab();
        } else if (user instanceof Cashier) {
            loadOrdersTab();
            //tabbedPane1.removeTabAt(2); //2 is the index of subscription tab
            loadStatisticsTab();
            loadRecipesTab();
            loadCustomersTab();
            loadCookTab();
            loadMyProfileTab();
            loadStatisticsTab();
        } else if (user instanceof Cook) {
            loadOrdersTab();
            //tabbedPane1.removeTabAt(2);
            loadRecipesTab();
            loadIngredientsTab();
            loadCookTab();
            loadMyProfileTab();
        } else if (user instanceof Driver) {
            loadMyProfileTab();
            loadDriverRouteTab();
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

    public void loadOrdersTab() {
        //Start Connection for ManageOrder object
        manageOrder = new ManageOrder();

        //Orders
        String orderColumns[] = {"Order ID", "Customer ID", "Payment Status", "Order date", "Delivery Date", "Delivery Time", "Address", "Zip"};

        //Active orders:
        acticeOrdersTableModel = new NonEditTableModel(orderColumns);
        activeOrdersTable.setModel(acticeOrdersTableModel);
        ArrayList<Order> activeOrders = manageOrder.viewActiveOrders();
        addRowsToOrderTab(acticeOrdersTableModel, activeOrders);

        //Previous orders:
        previousOrdersTableModel = new NonEditTableModel(orderColumns);
        previousOrdersTable.setModel(previousOrdersTableModel);
        ArrayList<Order> previousOrders = manageOrder.viewPreviousOrders();
        addRowsToOrderTab(previousOrdersTableModel, previousOrders);

        manageOrder.stopConnection();
    }

    NonEditTableModel recipeTableModel;
    public void loadRecipesTab() {
        //asd
        recipeConnection = new RecipeConnection();

        //Recipes:
        String recipeColumns[] = {"Name", "Type", "Price"};
        recipeTableModel = new NonEditTableModel(recipeColumns);
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
        ingredientTableModel = new NonEditTableModel(ingredientColumns);
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
        allCustomersTableModel = new NonEditTableModel(allCustomersColumns);
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
        privateCustomerTableModel = new NonEditTableModel(privateCustomerColumns);
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
        companiesTableModel = new NonEditTableModel(companyColumns);
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

    public void loadEmployeesTab() {
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
        driverConnection = new DriverConnection();
        String deliveriesColumns1[] = {"Route", "Time", "Show Route", "Drive"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(deliveriesColumns1, 0);
        deliveriesTodayTable.setModel(defaultTableModel);
        ArrayList<ArrayList<Order>> orders = driverConnection.splitDeliveriesToday();

        ArrayList<ArrayList<String>> addresses = new ArrayList<ArrayList<String>>();
        ArrayList<String> addressList;// = new ArrayList<String>();

        for(ArrayList<Order> innerList : orders) {
            //addressList = new ArrayList<String>();
            String time = "";
            if(innerList.size() > 0 ) {
                time = String.valueOf(innerList.get(0).getDeliveryTime());
                if(time.contains(".5")) {
                    time = time.substring(0, time.indexOf("."));
                    time = time + ":30";
                } else {
                    time = time.substring(0, time.indexOf("."));
                    time = time + ":00";
                }


                for (int i = 1; i < innerList.size(); i++) {//(Order order : innerList) {
                    double time2 = innerList.get(i).getDeliveryTime();

                    if (!(time2 == (innerList.get(i - 1).getDeliveryTime()))) {
                        String time3 = String.valueOf(time2);
                        if(time3.contains(".5")) {
                            time3 = time3.substring(0, time3.indexOf(".")) + ":30";
                        } else {
                            time3 = time3.substring(0, time3.indexOf(".")) + ":00";
                        }
                        time += " - " + time3;
                    }
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
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("olav tryggvasons gate 24, 7011");
        strings.add("sverdrupsvei 33, 7020");
        //strings.add("olav tryggvasons gate 40, 7011");
        //strings.add("munkegata 34, 7011");

        Route route = new Route(strings);
        browser.loadURL("C:\\SystemutviklingsProsjekt\\Driver map\\Map.html");//getClass().getResource("/Driver map/Map.html").toString());//"http://www.google.com");
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

    private NonEditTableModel subscriptionTableModel;
    private void loadSubscriptionTab() {
        subscriptionConnection = new SubscriptionConnection();
        String subscriptionolumns[] = {"Order ID", "Subscription ID", "Customer ID", "Payment Status", "Order Date", "Delivery Date", "Delivery Time", "Address", "Zip", "Other Requests", "Frequency"};
        subscriptionTableModel = new NonEditTableModel(subscriptionolumns);
        subscrptionsTab.setModel(subscriptionTableModel);
        ArrayList<Subscription> subscriptions = subscriptionConnection.viewAllSubscriptions();
        for(int i = 0; i < subscriptions.size(); i++) {
            int orderID = subscriptions.get(i).getOrderID();
            int subsID = subscriptions.get(i).getSubscriptionID();
            int custID = subscriptions.get(i).getCustomerID();
            boolean paymentStatus = subscriptions.get(i).isPaymentStatus();
            java.sql.Date orderDate = subscriptions.get(i).getOrderDate();
            java.sql.Date deliveryDate = subscriptions.get(i).getDeliveryDate();
            double deliveryTime = subscriptions.get(i).getDeliveryTime();
            String address = subscriptions.get(i).getAddress();
            int zip = subscriptions.get(i).getZipCode();
            //boolean takeAway = subscriptions.get(i).isTakeAway()
            String otherRequests = subscriptions.get(i).getOtherRequests();
            int frequency = subscriptions.get(i).getFrequency();

            Object objects[] = {orderID, subsID, custID, paymentStatus, orderDate, deliveryDate, deliveryTime, address, zip, otherRequests, frequency};
            subscriptionTableModel.addRow(objects);
        }
        subscriptionConnection.stopConnection();
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

        }



        String cookColumns3[] = {"Ingredient Name", "Quantity missing"};
        NonEditTableModel nonEditTableModel3 = new NonEditTableModel(cookColumns3);
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
        cookConnection.stopConnection();
    }


    private void loadTabs() {
        loadOrdersTab();
        //subscriptions
        loadRecipesTab();//TODO:DOES NOT WORKSSSS
        //loadCustomersTab();
        loadEmployeesTab();
        loadIngredientsTab();
        loadSubscriptionTab();
        loadCustomersTab();

        loadCookTab();
        loadMyProfileTab();
        //loadStatisticsTab();
        //scrollPane1.setViewportView(customersTable);
        loadDriverRouteTab();

        //Subscription:

        //Maps:


    }


}
