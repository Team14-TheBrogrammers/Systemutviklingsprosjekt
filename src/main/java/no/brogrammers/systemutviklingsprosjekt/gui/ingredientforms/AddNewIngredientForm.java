package no.brogrammers.systemutviklingsprosjekt.gui.ingredientforms;

import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.IngredientConnection;
import no.brogrammers.systemutviklingsprosjekt.gui.MainForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Knut on 24.04.2016.
 */
public class AddNewIngredientForm extends JFrame{
    private JButton addNewIngredientButton;
    private JPanel mainPanel;
    private JTextField ingredientNameTextField;
    private JTextField measurementTextField;
    private JTextField quantityTextField;

    private IngredientConnection ingredientConnection; // = new IngredientConnection();
    private boolean nameOk = false;
    private boolean quantityOk = false;
    private boolean measurementOk = false;

    private MainForm mainForm;

    public AddNewIngredientForm(final MainForm mainForm) {
        this.mainForm = mainForm;
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(500, 350);
        setTitle("Add New Ingredient");
        setVisible(true);
        setLocationRelativeTo(null);

        addNewIngredientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ingredientConnection = new IngredientConnection();
                ingredientConnection.addIngredient(ingredientNameTextField.getText(), Double.parseDouble(quantityTextField.getText()), measurementTextField.getText());
                ingredientConnection.stopConnection();
                mainForm.loadIngredientsTab();
                dispose();
            }
        });

        //Key typed:
        ingredientNameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(ingredientNameTextField.getText().equals("")) {
                    nameOk = false;
                } else {
                    nameOk = true;
                    checkFieldsOk();
                }
            }
        });
        quantityTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(quantityTextField.getText().equals("")) {
                    quantityOk = false;
                } else {
                    quantityOk = true;
                    checkFieldsOk();
                }
            }
        });
        measurementTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(measurementTextField.getText().equals("")) {
                    measurementOk = false;
                } else {
                    measurementOk = true;
                    checkFieldsOk();
                }
            }
        });
    }

    private void checkFieldsOk() {
        if(nameOk && quantityOk && measurementOk) {
            addNewIngredientButton.setEnabled(true);
        } else {
            addNewIngredientButton.setEnabled(false);
        }
    }
}
