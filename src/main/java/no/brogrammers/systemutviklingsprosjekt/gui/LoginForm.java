package no.brogrammers.systemutviklingsprosjekt.gui;

import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.LoginConnection;
import no.brogrammers.systemutviklingsprosjekt.user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by Knut on 19.04.16.
 */
public class LoginForm extends JFrame {
    private JButton logInButton;
    private JTextField usernameTextField;
    private JPasswordField passwordPasswordField;
    private JPanel mainPanel;
    private JLabel backgroundLabel;

    private LoginConnection loginConnection = new LoginConnection();

    public LoginForm() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(420, 400);
        setLocationRelativeTo(null);
        setTitle("Login");
        setContentPane(mainPanel);

        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/login_background.png"));
        backgroundLabel.setIcon(new ImageIcon(image));

        setVisible(true);

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logIn();
            }
        });
        passwordPasswordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER) { //Enter key is pressed in the password field
                    logIn();
                }
            }
        });
        usernameTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                usernameTextField.selectAll();
            }
        });
        passwordPasswordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                passwordPasswordField.selectAll();
            }
        });
    }

    private void logIn() {
        logInButton.setEnabled(false);
        User user = loginConnection.checkLoginDetails("ingunnsu", "ingunnIzc00l");
        if (user != null) {//usernameTextField.getText(), passwordPasswordField.getText()) != null) { //TODO: Change .getText()
            MainForm mainForm = new MainForm(user);
        } else {
            showMessageDialog(null, "Invalid login details.");
        }
        logInButton.setEnabled(true);
    }
}
