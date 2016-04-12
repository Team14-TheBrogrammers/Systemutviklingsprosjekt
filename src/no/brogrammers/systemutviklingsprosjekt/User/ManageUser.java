package no.brogrammers.systemutviklingsprosjekt.User;
import no.brogrammers.systemutviklingsprosjekt.Database.ConnectionClasses.UserConnection;
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
        if(super.deleteUser(id)) {
            return true;
        }
        return false;
    }

    public boolean changeLastName(int id, String lastName) {
        if(super.changeLastName(id, lastName)) {
            return true;
        }
        return false;
    }

    public boolean changeFirstName(int id, String firstName) {
        if(super.changeFirstName(id, firstName)) {
            return true;
        }
        return false;
    }

    public boolean changePhone(int id, int phone) {
        if(super.changePhone(id, phone)) {
            return true;
        }
        return false;
    }

    public boolean changeMail(int id, String mail) {
        if(super.changeMail(id, mail)) {
            return true;
        }
        return false;
    }

    public boolean changeUsername(int id, String username) {
        if(super.changeUsername(id, username)) {
            return true;
        }
        return false;
    }

    public boolean changePassword(int id, String password) {
        if(super.changePassword(id, password) {
            return true;
        }
        return false;
    }

    public User viewUser(int id) { //id
        return super.viewUser(id);
    }

    public User viewUser(String username) { //username
        return super.viewUser(username);
    }

    public ArrayList<User> viewAllUsers() {
        return super.viewAllUsers();
    }
}
