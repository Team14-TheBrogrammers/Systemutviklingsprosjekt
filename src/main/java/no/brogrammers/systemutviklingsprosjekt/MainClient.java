package no.brogrammers.systemutviklingsprosjekt;


import no.brogrammers.systemutviklingsprosjekt.driverroute.Route;
import no.brogrammers.systemutviklingsprosjekt.gui.LoginForm;
import no.brogrammers.systemutviklingsprosjekt.gui.MainForm;
import no.brogrammers.systemutviklingsprosjekt.gui.customerforms.AddNewCustomerForm;
import no.brogrammers.systemutviklingsprosjekt.gui.orderforms.AddNewOrderForm;

import javax.swing.*;


/**
 * Created by Ingunn on 09.03.2016.
 */
public class MainClient {
    public static void main(String[] args) {


        //This code is just temporary, there will be code for starting up a GUI form with login etc. here.

        //System.out.println(Encryption.encryptPassword("asdsad"));

        //test();
        setJFrameLookAndFeel();
        //Route route = new Route();

        LoginForm loginForm = new LoginForm();

        //AddNewOrderForm addNewOrderForm = new AddNewOrderForm();
        //AddNewEmployeeForm addNewEmployeeForm = new AddNewEmployeeForm();
        //System.out.println(new Date(Calendar.getInstance().getTimeInMillis()));
        //setJFrameLookAndFeel();
        //LoginForm loginForm = new LoginForm();
        //AddNewEmployeeForm addNewEmployeeForm = new AddNewEmployeeForm();
        //DateConverter dc = new DateConverter();
        //Manager manager = new Manager(2, "hei", "hei", 91919191, "hehe", dc.stringToSqlDate("2014-02-01"), "hei", "hei");
        //MainForm mainForm = new MainForm(manager);
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
