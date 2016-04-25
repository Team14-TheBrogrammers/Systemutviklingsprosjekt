package no.brogrammers.systemutviklingsprosjekt.gui.orderforms;

import no.brogrammers.systemutviklingsprosjekt.miscellaneous.DateConverter;
import no.brogrammers.systemutviklingsprosjekt.miscellaneous.DatePickerFormatter;
import no.brogrammers.systemutviklingsprosjekt.order.ManageOrder;
import no.brogrammers.systemutviklingsprosjekt.recipe.Recipe;
import no.brogrammers.systemutviklingsprosjekt.recipe.RecipeType;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;


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
    private JTextField textField1;
    private JDatePickerImpl deliveryDatePicker;

    private ArrayList<Recipe> recipes = new ArrayList<Recipe>();
    private int[] quantity;
    private int customerID;
    private ManageOrder manageOrder = new ManageOrder();

    public AddNewOrderForm() {
        setTitle("Add New Order");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(400, 450);
        setContentPane(mainPanel);
        setVisible(true);
        setLocationRelativeTo(null);
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
            }
        });
        addNewOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DateConverter dateConverter = new DateConverter();
                //manageOrder.addOrder(customerID, false, dateConverter.utilDateToSqlDate((java.util.Date)deliveryDatePicker.getModel().getValue()), )
            }
        });
    }

    public void setRecipeData(ArrayList<Recipe> recipes, int[] quantity) {
        this.recipes = recipes;
        this.quantity = quantity;
        String recipeColumns2[] = {"Name", "Type", "Price for each", "Total Price", "Quantity"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(recipeColumns2, 0);
        table1.setModel(defaultTableModel);
        for(int i = 0; i < recipes.size(); i++) {
            addRowToTable(defaultTableModel, recipes.get(i));
        }
    }

    private void addRowToTable(DefaultTableModel defaultTableModel, Recipe recipe) {

        String name = recipe.getRecipeName();
        RecipeType recipeType = recipe.getRecipeType();
        //double price = recipe.getPrice();
        Object[] objects = {name, recipeType};//, price};//TODO: FIX
        defaultTableModel.addRow(objects);
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
        this.customerID = customerID;
    }
}
