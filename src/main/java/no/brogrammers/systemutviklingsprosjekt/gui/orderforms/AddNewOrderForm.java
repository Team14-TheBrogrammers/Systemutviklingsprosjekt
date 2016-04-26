package no.brogrammers.systemutviklingsprosjekt.gui.orderforms;

import no.brogrammers.systemutviklingsprosjekt.customer.Company;
import no.brogrammers.systemutviklingsprosjekt.customer.Customer;
import no.brogrammers.systemutviklingsprosjekt.customer.ManageCustomer;
import no.brogrammers.systemutviklingsprosjekt.customer.PrivateCustomer;
import no.brogrammers.systemutviklingsprosjekt.gui.MainForm;
import no.brogrammers.systemutviklingsprosjekt.miscellaneous.DateConverter;
import no.brogrammers.systemutviklingsprosjekt.miscellaneous.DatePickerFormatter;
import no.brogrammers.systemutviklingsprosjekt.miscellaneous.NonEditTableModel;
import no.brogrammers.systemutviklingsprosjekt.order.ManageOrder;
import no.brogrammers.systemutviklingsprosjekt.recipe.DietType;
import no.brogrammers.systemutviklingsprosjekt.recipe.Recipe;
import no.brogrammers.systemutviklingsprosjekt.recipe.RecipeType;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

import static javax.swing.JOptionPane.showMessageDialog;


/**
 * Created by Knut on 20.04.2016.
 */
public class AddNewOrderForm extends JFrame {

    private JButton addNewOrderButton;
    private JPanel mainPanel;
    private JButton selectRecipesButton;
    private JButton selectCustomerButton;
    private JPanel recipePanel;
    private JPanel customerPanel;
    private JTable table1;
    private JDatePickerImpl deliveryDatePicker;
    private JLabel customerNameAndIDLabel;
    private JTextField deliveryTimeTextField;
    private JTextField addressTextField;
    private JTextField zipTextField;
    private JCheckBox orderIsTakeAwayCheckBox;
    private JTextArea otherRequestsTextField;

    private ArrayList<Recipe> recipes = new ArrayList<Recipe>();
    private int[] quantity;
    private int customerID;
    private Customer customer;

    private ManageOrder manageOrder;// = new ManageOrder();
    private ManageCustomer manageCustomer;// = new ManageCustomer();
    private MainForm mainForm1;

    public AddNewOrderForm(final MainForm mainForm) {
        this.mainForm1 = mainForm;
        setTitle("Add New Order");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(600, 450);
        setContentPane(mainPanel);
        setVisible(true);
        setLocationRelativeTo(null);

        documentListeners();

        selectRecipesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectRecipeForm selectRecipeForm = new SelectRecipeForm(AddNewOrderForm.this);
            }
        });
        selectCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectCustomerForm selectCustomerForm = new SelectCustomerForm(AddNewOrderForm.this);
                DateConverter dateConverter = new DateConverter();

                System.out.println(dateConverter.utilDateToSqlDate((java.util.Date)deliveryDatePicker.getModel().getValue()));

            }
        });
        addNewOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DateConverter dateConverter = new DateConverter();
                //System.out.println(DateConverter.utilDateToSqlDate((java.util.Date)deliveryDatePicker.getModel().getValue()));
                manageOrder = new ManageOrder();
                java.sql.Date currentDate = new Date(Calendar.getInstance().getTimeInMillis());
                java.sql.Date deliveryDate = dateConverter.utilDateToSqlDate((java.util.Date)deliveryDatePicker.getModel().getValue());
                double deliveryTime = Double.parseDouble(deliveryTimeTextField.getText());
                String address = addressTextField.getText();

                int zip = Integer.parseInt(zipTextField.getText());
                boolean takeAway = orderIsTakeAwayCheckBox.isSelected();
                String otherRequests = otherRequestsTextField.getText();

                showMessageDialog(null, manageOrder.addOrder(customerID, false, currentDate, deliveryDate, deliveryTime, address, zip, takeAway, otherRequests, recipes, quantity));
                manageOrder.stopConnection();
                mainForm1.loadOrdersTab();
                dispose();
            }
        });
    }

    private void documentListeners() {
        addNewOrderButton.setEnabled(true);
        //deliveryTimeTextField.getDocument().addDocumentListener();
        /*deliveryTimeTextField.addActionListener(textActionListener);
        addressTextField.addActionListener(textActionListener);
        zipTextField.addActionListener(textActionListener);*/
    }

    public void setRecipeData(ArrayList<Recipe> recipes, int[] quantity) {
        this.recipes = recipes;
        this.quantity = quantity;
        String recipeColumns2[] = {"Name", "Recipe Type", "Diet Type", "Quantity", "Price for each", "Total Price"};
        NonEditTableModel tableModel = new NonEditTableModel(recipeColumns2);
        table1.setModel(tableModel);
        addRowsToTable(tableModel);
    }

    private void checkEnableButton() {

    }

    private void addRowsToTable(DefaultTableModel defaultTableModel) {
        for(int i = 0; i < recipes.size(); i++) {
            String name = recipes.get(i).getRecipeName();
            RecipeType recipeType = recipes.get(i).getRecipeType();
            DietType dietType = recipes.get(i).getDietType();
            double priceEach = recipes.get(i).getPrice();
            double totalPrice = priceEach * quantity[i];
            Object[] objects = {name, recipeType, dietType, quantity[i], priceEach, totalPrice};//, price};//TODO: FIX
            defaultTableModel.addRow(objects);
        }
    }

    private void loadDatePicker() {
        UtilDateModel utilDateModel = new UtilDateModel();
        Calendar calendar = Calendar.getInstance();
        utilDateModel.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
        utilDateModel.setSelected(true);

        Properties properties = new Properties();
        properties.setProperty("text.today", "Year");
        properties.setProperty("text.month", "Month");
        properties.setProperty("text.year", "Year");

        JDatePanelImpl deliveryDatePanel = new JDatePanelImpl(utilDateModel, properties);

        deliveryDatePicker = new JDatePickerImpl(deliveryDatePanel, new DatePickerFormatter());
    }

    private void createUIComponents() {
        loadDatePicker();
    }

    public void setCustomerID(int customerID) {
        manageCustomer = new ManageCustomer();
        this.customerID = customerID;
        String name = "";
        Customer tmp = manageCustomer.viewCustomer(customerID);
        if(tmp instanceof Company) {
            name = ((Company) tmp).getName();
            //customer = (Company) tmp;
        } else if(tmp instanceof PrivateCustomer) {
            name = ((PrivateCustomer) tmp).getFirstName() + " " + ((PrivateCustomer) tmp).getLastName();
            //customer = (PrivateCustomer) tmp;
        }
        customerNameAndIDLabel.setText(name + " , ID: " + tmp.getID());
        manageCustomer.stopConnection();
    }
}
