package no.brogrammers.systemutviklingsprosjekt.gui.recipeforms;

import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.RecipeConnection;
import no.brogrammers.systemutviklingsprosjekt.miscellaneous.NonEditTableModel;
import no.brogrammers.systemutviklingsprosjekt.recipe.DietType;
import no.brogrammers.systemutviklingsprosjekt.recipe.Instruction;
import no.brogrammers.systemutviklingsprosjekt.recipe.Recipe;
import no.brogrammers.systemutviklingsprosjekt.recipe.RecipeType;

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
    private JTable table1;
    private JTable table2;
    private JButton addIngredientButton;
    private JButton removeIngredientButton;

    RecipeConnection recipeConnection = new RecipeConnection();
    private ArrayList<String> instructions = new ArrayList<String>();

    public AddNewRecipeForm() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(400, 550);
        setVisible(true);
        setLocationRelativeTo(null);

        //loadAllRecipes();
        setUpTable();

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
        String ingredientColumns[] = {"Name", ""};
        ingredientTableModel = new NonEditTableModel(ingredientColumns);
    }
}
