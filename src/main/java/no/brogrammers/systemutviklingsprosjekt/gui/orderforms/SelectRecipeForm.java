package no.brogrammers.systemutviklingsprosjekt.gui.orderforms;

import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.RecipeConnection;
import no.brogrammers.systemutviklingsprosjekt.miscellaneous.NonEditTableModel;
import no.brogrammers.systemutviklingsprosjekt.recipe.DietType;
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
        setSize(820, 500);
        setTitle("test");
        setContentPane(panel1);
        setLocationRelativeTo(null);
        setVisible(true);

        loadTables();
        addRecipesToOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] tableIndexes = allRecipesTable.getSelectedRows();
                for(int i = 0; i < tableIndexes.length; i++) {
                    Recipe recipe = recipes.get(tableIndexes[i]);

                    //addRowToTable(defaultTableMode4, recipes.get(tableIndexes[i]));//bruk annen måte siden det er total pris osv her
                }
            }
        });
        completeChoosingRecipesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < orderRecipesTable.getRowCount(); i++) {
                    //recipes.
                }

                int[] asd = new int[recipes.size()];
                //System.out.println();

                addNewOrderForm.setRecipeData(recipes, new int[]{1});
                dispose();

            }
        });
    }

    private DefaultTableModel defaultTableMode4;
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
        NonEditTableModel allRecipesTableModel = new NonEditTableModel(recipeColumns, 0);
        allRecipesTable.setModel(allRecipesTableModel);
        for(int i = 0; i < recipes.size(); i++) {
            addRowToTable(allRecipesTableModel, recipes.get(i));
        }

        //Set modeltable for Recipe table:
        String recipeColumns2[] = {"Name", "Recipe Type", "Diet Type", "Price for each", "Total Price", "Quantity"};
        defaultTableMode4 = new DefaultTableModel(recipeColumns2, 0);
        orderRecipesTable.setModel(defaultTableMode4);

    }
}
