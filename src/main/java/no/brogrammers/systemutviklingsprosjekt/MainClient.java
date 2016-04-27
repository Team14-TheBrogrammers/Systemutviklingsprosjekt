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

        setJFrameLookAndFeel();
        //Route route = new Route();
        LoginForm loginForm = new LoginForm();
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
