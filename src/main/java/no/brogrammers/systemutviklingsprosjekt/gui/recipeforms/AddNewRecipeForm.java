package no.brogrammers.systemutviklingsprosjekt.gui.recipeforms;

import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.IngredientConnection;
import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.RecipeConnection;
import no.brogrammers.systemutviklingsprosjekt.gui.MainForm;
import no.brogrammers.systemutviklingsprosjekt.miscellaneous.ChooseEditTableModel;
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
    private ArrayList<Ingredient> allIngredients = new ArrayList<Ingredient>();
    private ArrayList<Ingredient> currentIngredients = new ArrayList<Ingredient>();
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
        loadIngredientTables();

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
                reloadInstructionTable();
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
                recipeConnection.create(new Recipe(name, recipeType, dietType, currentIngredients, instructionsTmp, price));
                parentForm.loadRecipesTab();
                dispose();
            }
        });
        addIngredientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] seletedRows = allIngredientsTable.getSelectedRows();
                for(int i = 0; i < seletedRows.length; i++) {
                    currentIngredients.add(allIngredients.get(seletedRows[i]));
                    Object objects[] = {allIngredients.get(seletedRows[i]).getIngredientName(), 1.0};
                    recipeIngredientsTableModel.addRow(objects);
                    allIngredients.remove(seletedRows[i]);
                    //allIngredientsTable.remove(seletedRows[i]);
                    allIngredientTableModel.removeRow(seletedRows[i]);

                }
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

    private void reloadInstructionTable() {//load table
        String columns[] = {"Step Number", "Description"};
        tableModel = new NonEditTableModel(columns);
        instructionTable.setModel(tableModel);
        for(int i = 0; i < instructions.size(); i++) {
            addInstructionToTable(i, instructions.get(i));
        }
    }

    private void reloadIngredientTable() {

    }

    NonEditTableModel allIngredientTableModel;
    ChooseEditTableModel recipeIngredientsTableModel;
    private void loadIngredientTables() {
        //Setup all ingredients in the database:
        String ingredientColumns[] = {"Name"};
        allIngredientTableModel = new NonEditTableModel(ingredientColumns);
        allIngredientsTable.setModel(allIngredientTableModel);
        ingredientConnection = new IngredientConnection();
        allIngredients = ingredientConnection.viewAllIngredients();
        for(int i = 0; i < allIngredients.size(); i++) {
            String name = allIngredients.get(i).getIngredientName();
            Object objects[] = {name};
            allIngredientTableModel.addRow(objects);
        }
        ingredientConnection.stopConnection();

        //Set up current ingredients for this recipe
        String ingredientColumns2[] = {"Name", "Quantity"};
        recipeIngredientsTableModel = new ChooseEditTableModel(ingredientColumns2, 1);
        recipeIngredientsTable.setModel(recipeIngredientsTableModel);

    }
}
