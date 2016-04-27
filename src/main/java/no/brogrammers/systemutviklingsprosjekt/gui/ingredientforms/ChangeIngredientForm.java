package no.brogrammers.systemutviklingsprosjekt.gui.ingredientforms;

import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.IngredientConnection;
import no.brogrammers.systemutviklingsprosjekt.gui.MainForm;
import no.brogrammers.systemutviklingsprosjekt.recipe.Ingredient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by Knut on 27.04.2016.
 */
public class ChangeIngredientForm extends JFrame {
    private JTextField quantityTextField;
    private JButton changeIngredientButton;
    private JTextField measurementTextField;
    private JPanel mainPanel;
    private JLabel ingredientNameLabel;

    private MainForm mainForm;
    private Ingredient ingredient;
    private IngredientConnection ingredientConnection;

    public ChangeIngredientForm(final MainForm mainForm, final Ingredient ingredient) {
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
                ingredientConnection = new IngredientConnection();
                /*if(ingredientConnection.addIngredient(nameTextField.getText(), Double.parseDouble(quantityTextField.getText()), measurementTextField.getText()) != 1) {

                } else {
                    //Delete ingredient here??
                }*/
                if(ingredientConnection.changeQuantity(ingredient.getIngredientName(), Double.parseDouble(quantityTextField.getText())) == 1) {
                    if(ingredientConnection.changeMeasurement(ingredient.getIngredientName(), measurementTextField.getText()) == 1) {
                        showMessageDialog(null, "Ingredient Was Updated With Given Information.", "Information Updated!", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        showMessageDialog(null, "Could Not Update Ingredient With Given Information.", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    if(ingredientConnection.changeMeasurement(ingredient.getIngredientName(), measurementTextField.getText()) == 1) {
                        showMessageDialog(null, "Updated Information With Some Information.", "Information Updated!", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        showMessageDialog(null, "Could Not Update Ingredient With All Given Information.", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }
                mainForm.loadIngredientsTab();
                dispose();
            }
        });
    }

    private void loadTextFields() {
        ingredientConnection = new IngredientConnection();
        ingredientNameLabel.setText(ingredient.getIngredientName());
        quantityTextField.setText(String.valueOf(ingredient.getQuantity()));
        System.out.println(ingredientConnection.viewMeasurement(ingredient.getIngredientName()));
        if(ingredientConnection.viewMeasurement(ingredient.getIngredientName()) != null) {
            measurementTextField.setText(ingredientConnection.viewMeasurement(ingredient.getIngredientName()));
        }
        ingredientConnection.stopConnection();
    }
}
