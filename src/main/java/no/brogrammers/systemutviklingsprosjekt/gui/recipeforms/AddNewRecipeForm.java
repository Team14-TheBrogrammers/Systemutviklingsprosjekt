package no.brogrammers.systemutviklingsprosjekt.gui.recipeforms;

import javax.swing.*;

/**
 * Created by Knut on 22.04.2016.
 */
public class AddNewRecipeForm extends JFrame {
    private JPanel mainPanel;
    private JButton addNewRecipeButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTable table1;
    private JTable table2;

    public AddNewRecipeForm() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(400, 550);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
