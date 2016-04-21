package no.brogrammers.systemutviklingsprosjekt.gui.orderforms;

import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.RecipeConnection;
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

    private AddNewOrderForm addNewOrderForm;

    public SelectRecipeForm(AddNewOrderForm asdf) {
        addNewOrderForm  = asdf;

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(720, 500);
        setTitle("test");
        setContentPane(panel1);
        setLocationRelativeTo(null);
        setVisible(true);

        loadTables();
        addRecipesToOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(allRecipesTable.getSelectedRowCount());
                addRowToTable(defaultTableMode4, recipes.get(allRecipesTable.getSelectedRowCount() - 1));
            }
        });
        completeChoosingRecipesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] asd = new int[recipes.size()];
                addNewOrderForm.setRecipeData(recipes, new int[]{1});
                dispose();

            }
        });
    }

    private DefaultTableModel defaultTableMode4;
    ArrayList<Recipe> recipes;

    private void addRowToTable(DefaultTableModel defaultTableModel, Recipe recipe) {
        /*String name = recipes.get(i).getRecipeName();
        RecipeType recipeType = recipes.get(i).getRecipeType();
        double price = recipes.get(i).getPrice();
        Object[] objects = {name, recipeType, price};
        defaultTableModel3.addRow(objects);*/
        String name = recipe.getRecipeName();
        RecipeType recipeType = recipe.getRecipeType();
        double price = recipe.getPrice();
        Object[] objects = {name, recipeType, price};
        defaultTableModel.addRow(objects);
    }

    private void loadTables() {
        //Load Recipe table:
        RecipeConnection recipeConnection = new RecipeConnection();
        recipes = recipeConnection.viewAllRecipes();
        String recipeColumns[] = {"Name", "Type", "Price"};
        DefaultTableModel defaultTableModel3 = new DefaultTableModel(recipeColumns, 0);
        allRecipesTable.setModel(defaultTableModel3);
        for(int i = 0; i < recipes.size(); i++) {
            addRowToTable(defaultTableModel3, recipes.get(i));
        }

        //Set modeltable for Recipe table:
        String recipeColumns2[] = {"Name", "Type", "Price for each", "Total Price", "Quantity"};
        defaultTableMode4 = new DefaultTableModel(recipeColumns2, 0);
        orderRecipesTable.setModel(defaultTableMode4);

    }
}
