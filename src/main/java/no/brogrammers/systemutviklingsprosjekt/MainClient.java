package no.brogrammers.systemutviklingsprosjekt;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import no.brogrammers.systemutviklingsprosjekt.converter.DateConverter;
import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.UserConnection;
import no.brogrammers.systemutviklingsprosjekt.database.DatabaseConnection;
import no.brogrammers.systemutviklingsprosjekt.gui.LoginForm;
import no.brogrammers.systemutviklingsprosjekt.gui.MainForm;
import no.brogrammers.systemutviklingsprosjekt.gui.employeeforms.AddNewEmployeeForm;
import no.brogrammers.systemutviklingsprosjekt.gui.orderforms.AddNewOrderForm;
import no.brogrammers.systemutviklingsprosjekt.user.Manager;

import javax.swing.*;
import java.awt.*;
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
        //setJFrameLookAndFeel();
        //LoginForm loginForm = new LoginForm();
        //AddNewEmployeeForm addNewEmployeeForm = new AddNewEmployeeForm();
        DateConverter dc = new DateConverter();
        Manager manager = new Manager(2, "hei", "hei", 91919191, "hehe", dc.stringToSqlDate("2014-02-01"), "hei", "hei");
        MainForm mainForm = new MainForm(manager);
        //AddNewOrderForm addNewOrderForm = new AddNewOrderForm();/

        /*Browser browser = new Browser();
        BrowserView browserView = new BrowserView(browser);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(browserView, BorderLayout.CENTER);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        browser.loadURL("http://www.google.com");*/
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
