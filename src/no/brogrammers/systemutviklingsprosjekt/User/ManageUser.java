package no.brogrammers.systemutviklingsprosjekt.User;

import java.util.ArrayList;

/**
 * Created by Ingunn on 01.04.2016.
 */
public class ManageUser extends UserConnection implements ChangeUserInterface, ViewUserInterface {

    public ManageUser() {
        super();
    }

    public boolean regManager(String lastName, String firstName, int phone, String mail, java.sql.Date doe, String username, String password) {
        if(super.regManager(lastName, firstName, phone, mail, doe, username, password)) {
            return true;
        }
        return false;
    }

    public boolean regCashier(String lastName, String firstName, int phone, String mail, java.sql.Date doe, String username, String password) {
        if(super.regCashier(lastName, firstName, phone, mail, doe, username, password)) {
            return true;
        }
        return false;
    }

    public boolean regCook(String lastName, String firstName, int phone, String mail, java.sql.Date doe, String username, String password) {
        if(super.regCook(lastName, firstName, phone, mail, doe, username, password)) {
            return true;
        }
        return false;
    }

    public boolean regDriver(String lastName, String firstName, int phone, String mail, java.sql.Date doe, String username, String password) {
        if(super.regDriver(lastName, firstName, phone, mail, doe, username, password)) {
            return true;
        }
        return false;
    }

    public boolean deleteUser(int id) {

        //sjekk om bruker eksisterer
        return false;
    }

    public boolean changeLastName(int id, String lastName) {
        return false;
    }

    public boolean changeFirstName(int id, String firstName) {
        return false;
    }

    public boolean changePhone(int id, int phone) {
        return false;
    }

    public boolean changeMail(int id, String mail) {
        return false;
    }


    public User viewUser(int id) { //id
        return null;
    }

    public User viewUser(String lastName) { //last name
        return null;
    }

    public ArrayList<User> viewAllUsers() {
        return null;
    }
}
