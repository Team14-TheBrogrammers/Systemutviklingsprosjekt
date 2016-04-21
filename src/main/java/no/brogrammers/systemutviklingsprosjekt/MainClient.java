package no.brogrammers.systemutviklingsprosjekt;

import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.UserConnection;
import no.brogrammers.systemutviklingsprosjekt.database.DatabaseConnection;
import no.brogrammers.systemutviklingsprosjekt.gui.LoginForm;
import no.brogrammers.systemutviklingsprosjekt.gui.MainForm;
import no.brogrammers.systemutviklingsprosjekt.gui.orderforms.AddNewOrderForm;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Ingunn on 09.03.2016.
 */
public class MainClient {
    public static void main(String[] args) {


        //This code is just temporary, there will be code for starting up a GUI form with login etc. here.

        //System.out.println(Encryption.encryptPassword("asdsad"));

        //test();
        setJFrameLookAndFeel();
        LoginForm loginForm = new LoginForm();
        //AddNewOrderForm addNewOrderForm = new AddNewOrderForm();/
    }

    private static void setJFrameLookAndFeel() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}
