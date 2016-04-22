package no.brogrammers.systemutviklingsprosjekt.gui.userforms;

import no.brogrammers.systemutviklingsprosjekt.gui.MainForm;
import no.brogrammers.systemutviklingsprosjekt.user.ManageUser;
import no.brogrammers.systemutviklingsprosjekt.user.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Knut on 21.04.2016.
 */
public class ChangeUserDetailsForm extends JFrame {
    private JButton saveChangesButton;
    private JPanel mainPanel;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField phoneTextField;
    private JTextField emailTextField;
    private JTextField usernameTextField;
    private JTextField passwordTextField;

    private MainForm mainForm;
    private User user;

    private boolean firstNameChanged = false;
    private boolean lastNameChanged = false;
    private boolean phoneChanged = false;
    private boolean emailChanged = false;
    private boolean usernameChanged = false;
    private boolean passwordChanged = false;

    private ManageUser manageUser = new ManageUser();

    public ChangeUserDetailsForm(MainForm mainForm) {
        this.mainForm = mainForm;
        user = mainForm.getUser();//TODO:FIX
        setContentPane(mainPanel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Change User Data");
        setSize(400, 500);
        setVisible(true);
        setLocationRelativeTo(null);
        setupTextFields();

        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Check booleans that is true (which means what text fields is changed)
                //Check if first name is changed
                if(firstNameChanged) {
                    manageUser.changeFirstName(user.getID(), firstNameTextField.getText());
                }

                //Check if last name is changed
                if(lastNameChanged) {
                    manageUser.changeLastNameByID(user.getID(), lastNameTextField.getText());
                }

                //Check if phone is changed
                if(phoneChanged) {
                    manageUser.changePhoneByID(user.getID(), Integer.parseInt(phoneTextField.getText()));
                }

                //Check if email is changed
                if(emailChanged) {

                }

                //Check if username is changed
                if(usernameChanged) {

                }

                //Check if password is changed
                if(passwordChanged) {

                }
            }
        });

        //Setting listeners to the text fields so that we can know what text fields is changed.
        firstNameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                firstNameChanged = true;
            }
        });
        lastNameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                lastNameChanged = true;
            }
        });
        phoneTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                phoneChanged = true;
            }
        });
        emailTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                emailChanged = true;
            }
        });
        usernameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                usernameChanged = true;
            }
        });
        passwordTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                passwordChanged = true;
            }
        });
    }

    private void setupTextFields() {
        firstNameTextField.setText(user.getFirstName());
        lastNameTextField.setText(user.getLastName());
        phoneTextField.setText(String.valueOf(user.getPhoneNumber()));
        emailTextField.setText(user.getEmail());
        usernameTextField.setText(user.getEmail());
        passwordTextField.setText(user.getPassword());
    }
}
