package no.brogrammers.systemutviklingsprosjekt.gui.ingredientforms;

import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.IngredientConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Knut on 24.04.2016.
 */
public class AddNewIngredientForm extends JFrame{
    private JButton addNewIngredientButton;
    private JPanel mainPanel;
    private JTextField ingredientNameTextField;
    private JTextField measurementTextField;
    private JTextField quantityTextField;

    private IngredientConnection ingredientConnection = new IngredientConnection();

    public AddNewIngredientForm() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(500, 350);
        setTitle("Add New Ingredient");
        setVisible(true);
        setLocationRelativeTo(null);

        addNewIngredientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ingredientConnection.addIngredient(ingredientNameTextField.getText(), Double.parseDouble(quantityTextField.getText()), measurementTextField.getText());
            }
        });
    }
}
