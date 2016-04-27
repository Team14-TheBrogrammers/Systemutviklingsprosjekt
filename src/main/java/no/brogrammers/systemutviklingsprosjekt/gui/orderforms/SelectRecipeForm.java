package no.brogrammers.systemutviklingsprosjekt.gui.orderforms;

import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.IngredientConnection;
import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.RecipeConnection;
import no.brogrammers.systemutviklingsprosjekt.miscellaneous.NonEditTableModel;
import no.brogrammers.systemutviklingsprosjekt.recipe.DietType;
import no.brogrammers.systemutviklingsprosjekt.recipe.Ingredient;
import no.brogrammers.systemutviklingsprosjekt.recipe.Recipe;
import no.brogrammers.systemutviklingsprosjekt.recipe.RecipeType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by Knut on 20.04.2016.
 */
public class SelectRecipeForm extends JFrame {
    private JButton addRecipesToOrderButton;
    private JPanel panel1;
    private JTable allRecipesTable;
    private JTable orderRecipesTable;
    private JButton completeChoosingRecipesButton;

    private AddNewOrderForm parentForm;
    private ArrayList<Recipe> orderRecipes = new ArrayList<Recipe>();

    public SelectRecipeForm(AddNewOrderForm addNewOrderForm) {
        parentForm = addNewOrderForm;

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(820, 500);
        setTitle("Add Recipes To Order");
        setContentPane(panel1);
        setLocationRelativeTo(null);
        setVisible(true);

        loadTables();
        addRecipesToOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] tableIndexes = allRecipesTable.getSelectedRows();
                for(int i = 0; i < tableIndexes.length; i++) {
                    //Get object from selected index
                    Recipe recipe = recipes.get(tableIndexes[i]);
                    orderRecipes.add(recipe);
                    //Set up objects
                    String name = recipe.getRecipeName();
                    RecipeType recipeType = recipe.getRecipeType();
                    DietType dietType = recipe.getDietType();
                    double price = recipe.getPrice();

                    //add objects
                    Object[] objects = {name, recipeType, dietType, 1, price, price};
                    orderTableModel.addRow(objects);
                    //addRowToTable(defaultTableMode4, recipes.get(tableIndexes[i]));//bruk annen måte siden det er total pris osv her
                }
            }
        });
        completeChoosingRecipesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] quantity = new int[orderRecipes.size()];
                for(int i = 0; i < quantity.length; i++) {
                    //int quantityTmp = Integer.parseInt((String)orderRecipesTable.getValueAt(i, 4));
                    int quantityTmp = Integer.parseInt(orderRecipesTable.getValueAt(i, 3).toString());//Integer.parseInt();
                    quantity[i] = quantityTmp;
                }
                parentForm.setRecipeData(orderRecipes, quantity);
                dispose();

            }
        });
    }

    private NonEditTableModel allRecipesTableModel;
    private DefaultTableModel orderTableModel;
    ArrayList<Recipe> recipes;

    private void addRowToTable(DefaultTableModel defaultTableModel, Recipe recipe) {
        String name = recipe.getRecipeName();
        RecipeType recipeType = recipe.getRecipeType();
        DietType dietType = recipe.getDietType();
        double price = recipe.getPrice();
        Object[] objects = {name, recipeType, dietType, price};
        defaultTableModel.addRow(objects);
    }

    private void loadTables() {
        //Load Recipe table:
        RecipeConnection recipeConnection = new RecipeConnection();
        recipes = recipeConnection.viewAllRecipes();
        String recipeColumns[] = {"Name", "Recipe Type", "Diet Type", "Price"};
        allRecipesTableModel = new NonEditTableModel(recipeColumns);
        allRecipesTable.setModel(allRecipesTableModel);
        for(int i = 0; i < recipes.size(); i++) {
            addRowToTable(allRecipesTableModel, recipes.get(i));
        }

        //Set modeltable for Recipe table:
        String recipeColumns2[] = {"Name", "Recipe Type", "Diet Type", "Quantity", "Price for each", "Total Price"};
        orderTableModel = new DefaultTableModel(recipeColumns2, 0);
        orderRecipesTable.setModel(orderTableModel);
        //TODO: more code

    }
}
