package no.brogrammers.systemutviklingsprosjekt.gui.userforms;

import no.brogrammers.systemutviklingsprosjekt.gui.MainForm;
import no.brogrammers.systemutviklingsprosjekt.user.ManageUser;
import no.brogrammers.systemutviklingsprosjekt.user.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        setTitle("Hei");
        setSize(400, 500);
        setVisible(true);
        setLocationRelativeTo(null);

        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
