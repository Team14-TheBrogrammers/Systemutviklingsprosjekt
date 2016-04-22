package no.brogrammers.systemutviklingsprosjekt.gui.orderforms;

import no.brogrammers.systemutviklingsprosjekt.order.ManageOrder;
import no.brogrammers.systemutviklingsprosjekt.recipe.Recipe;
import no.brogrammers.systemutviklingsprosjekt.recipe.RecipeType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


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
                //manageOrder.addOrder(customerID, payment)
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

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}
