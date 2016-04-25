package no.brogrammers.systemutviklingsprosjekt.gui.recipeforms;

import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.RecipeConnection;
import no.brogrammers.systemutviklingsprosjekt.miscellaneous.NonEditTableModel;
import no.brogrammers.systemutviklingsprosjekt.recipe.DietType;
import no.brogrammers.systemutviklingsprosjekt.recipe.Recipe;
import no.brogrammers.systemutviklingsprosjekt.recipe.RecipeType;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Knut on 22.04.2016.
 */
public class AddNewRecipeForm extends JFrame {
    private JPanel mainPanel;
    private JButton addNewRecipeButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTable allRecipesTable;
    private JTable newOrderRecipesTable;

    RecipeConnection recipeConnection = new RecipeConnection();

    public AddNewRecipeForm() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(400, 550);
        setVisible(true);
        setLocationRelativeTo(null);

        loadAllRecipes();
    }

    private void loadAllRecipes() {//load table
        String[] columnNames = {"Name", "Recipe Type", "Diet Type", "Price"};
        NonEditTableModel tableModel = new NonEditTableModel(columnNames, 0);
        allRecipesTable.setModel(tableModel);
        ArrayList<Recipe> recipes = recipeConnection.viewAllRecipes();
        for(int i = 0; i < recipes.size(); i++) {
            String name = recipes.get(i).getRecipeName();
            RecipeType recipeType = recipes.get(i).getRecipeType();
            DietType dietType = recipes.get(i).getDietType();
            double price = recipes.get(i).getPrice();
            Object objects[] = {name, recipeType, dietType, price};
            tableModel.addRow(objects);
        }
    }
}
