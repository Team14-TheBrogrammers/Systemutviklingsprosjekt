package no.brogrammers.systemutviklingsprosjekt.gui.ingredientforms;

import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.IngredientConnection;
import no.brogrammers.systemutviklingsprosjekt.gui.MainForm;
import no.brogrammers.systemutviklingsprosjekt.recipe.Ingredient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Knut on 27.04.2016.
 */
public class ChangeIngredientForm extends JFrame {
    private JTextField nameTextField;
    private JTextField quantityTextField;
    private JButton changeIngredientButton;
    private JTextField measurementTextField;
    private JPanel mainPanel;

    private MainForm mainForm;
    private Ingredient ingredient;
    private IngredientConnection ingredientConnection;

    public ChangeIngredientForm(MainForm mainForm, Ingredient ingredient) {
        this.mainForm = mainForm;
        this.ingredient = ingredient;
        setTitle("Change Ingredient Information");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(400, 500);
        setVisible(true);
        setLocationRelativeTo(null);

        loadTextFields();

        changeIngredientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void loadTextFields() {
        ingredientConnection = new IngredientConnection();
        nameTextField.setText(ingredient.getIngredientName());
        quantityTextField.setText(String.valueOf(ingredient.getQuantity()));
        System.out.println(ingredientConnection.viewMeasurement(ingredient.getIngredientName()));
        if(ingredientConnection.viewMeasurement(ingredient.getIngredientName()) != null) {
            measurementTextField.setText(ingredientConnection.viewMeasurement(ingredient.getIngredientName()));
        }
        ingredientConnection.stopConnection();
    }
}
