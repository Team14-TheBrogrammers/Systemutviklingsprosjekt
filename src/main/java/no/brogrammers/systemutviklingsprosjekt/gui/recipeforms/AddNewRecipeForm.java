package no.brogrammers.systemutviklingsprosjekt.gui.recipeforms;

import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.IngredientConnection;
import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.RecipeConnection;
import no.brogrammers.systemutviklingsprosjekt.gui.MainForm;
import no.brogrammers.systemutviklingsprosjekt.miscellaneous.NonEditTableModel;
import no.brogrammers.systemutviklingsprosjekt.recipe.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Knut on 22.04.2016.
 */
public class AddNewRecipeForm extends JFrame {
    private JPanel mainPanel;
    private JButton addNewRecipeButton;
    private JTextField recipeNameTextField;
    private JTable allRecipesTable;
    private JTable instructionTable;
    private JComboBox recipeTypeComboBox;
    private JComboBox dietTypeComboBox;
    private JTextArea textArea1;
    private JButton addInstructionButton;
    private JButton deleteInstructionButton;
    private JTable recipeIngredientsTable;
    private JTable allIngredientsTable;
    private JButton addIngredientButton;
    private JButton removeIngredientButton;
    private JTextField priceTextField;

    RecipeConnection recipeConnection = new RecipeConnection();
    private ArrayList<String> instructions = new ArrayList<String>();
    private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
    private IngredientConnection ingredientConnection = new IngredientConnection();
    private MainForm parentForm;

    public AddNewRecipeForm(MainForm mainForm) {
        parentForm = mainForm;
        setContentPane(mainPanel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(600, 550);
        setVisible(true);
        setLocationRelativeTo(null);

        //loadAllRecipes();
        setUpTable();
        loadIngredientTable();

        addInstructionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addInstructionToTable(instructions.size(), textArea1.getText());
            }
        });

        deleteInstructionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = instructionTable.getSelectedRows();
                for(int i = 0; i < selectedRows.length; i++) {
                    instructions.remove(selectedRows[i]);
                    tableModel.removeRow(selectedRows[i]);
                }
                //instructions.remove(selectedRows);
                reloadTable();
            }
        });
        addNewRecipeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = recipeNameTextField.getText();
                RecipeType recipeType = RecipeType.valueOf(recipeTypeComboBox.getSelectedItem().toString());
                DietType dietType = DietType.valueOf(dietTypeComboBox.getSelectedItem().toString());
                ArrayList<Instruction> instructionsTmp = new ArrayList<Instruction>();
                for(int i = 0; i < instructions.size(); i++) {
                    instructionsTmp.add(new Instruction(i, instructions.get(i)));
                }
                double price = Double.parseDouble(priceTextField.getText());
                recipeConnection.create(new Recipe(name, recipeType, dietType, ingredients, instructionsTmp, price));
                parentForm.loadRecipesTab();
                dispose();
            }
        });
    }

    private NonEditTableModel tableModel;

    private void setUpTable() {
        String columns[] = {"Step Number", "Description"};
        tableModel = new NonEditTableModel(columns);
        instructionTable.setModel(tableModel);
    }

    private void addInstructionToTable(int stepNumber, String instruction) {
        instructions.add(instruction);
        Object objects[] = {stepNumber + 1, instruction};
        tableModel.addRow(objects);
        textArea1.setText("");
    }

    private void reloadTable() {//load table
        String columns[] = {"Step Number", "Description"};
        tableModel = new NonEditTableModel(columns);
        instructionTable.setModel(tableModel);
        for(int i = 0; i < instructions.size(); i++) {
            addInstructionToTable(i, instructions.get(i));
        }
    }

    NonEditTableModel ingredientTableModel;
    private void loadIngredientTable() {
        String ingredientColumns[] = {"Name"};
        ingredientTableModel = new NonEditTableModel(ingredientColumns);
        allIngredientsTable.setModel(ingredientTableModel);
        ingredientConnection = new IngredientConnection();
        ArrayList<Ingredient> ingredients = ingredientConnection.viewAllIngredients();
        for(int i = 0; i < ingredients.size(); i++) {
            String name = ingredients.get(i).getIngredientName();
            Object objects[] = {name};
            ingredientTableModel.addRow(objects);
        }
        ingredientConnection.stopConnection();
    }
}
