package no.brogrammers.systemutviklingsprosjekt.user;

import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.UserConnection;
import java.util.ArrayList;

/**
 * Created by Ingunn on 01.04.2016.
 */

public class ManageUser extends UserConnection implements ChangeUserInterface, ViewUserInterface {

    public ManageUser() {
        super();
    }

    public String registerManager(String lastName, String firstName, int phone, String mail, java.sql.Date doe, String username, String password) {
        int reg = super.regManager(lastName, firstName, phone, mail, doe, username, password);
        if(reg == 1) {
            return "A new manager was registered";
        } else if(reg == -1) {
            return "Username already exists";
        } else {
            return "An error occurred";
        }
    }

    public String registerCashier(String lastName, String firstName, int phone, String mail, java.sql.Date doe, String username, String password) {
        int reg = super.regCashier(lastName, firstName, phone, mail, doe, username, password);
        if(reg == 1) {
            return "A new cashier was registered";
        } else if(reg == -1) {
            return "Username already exists";
        } else {
            return "An error occurred";
        }
    }

    public String registerCook(String lastName, String firstName, int phone, String mail, java.sql.Date doe, String username, String password) {
        int reg = super.regCook(lastName, firstName, phone, mail, doe, username, password);
        if(reg == 1) {
            return "A new cook was registered";
        } else if(reg == -1) {
            return "Username already exists";
        } else {
            return "An error occurred";
        }
    }

    public String registerDriver(String lastName, String firstName, int phone, String mail, java.sql.Date doe, String username, String password) {
        int reg = super.regDriver(lastName, firstName, phone, mail, doe, username, password);
        if(reg == 1) {
            return "A new driver was registered";
        } else if(reg == -1) {
            return "Username already exists";
        } else {
            return "An error occurred";
        }
    }

    public String deleteUserByID(int id) {
        int delete = super.deleteUser(id);
        if(delete == 1) {
            return "User was deleted";
        } else if(delete == -1) {
            return "User does not exist";
        } else {
            return "An error occurred";
        }
    }

    public String changeLastNameByID(int id, String lastName) {
        int change = super.changeLastName(id, lastName);
        if(change == 1) {
            return "Last name was changed to " + lastName;
        } else if(change == -1) {
            return "User does not exist";
        } else {
            return "An error occurred";
        }
    }

    public String changeFirstNameByID(int id, String firstName) {
        int change = super.changeFirstName(id, firstName);
        if(change == 1) {
            return "First name was changed to " + firstName;
        } else if(change == -1) {
            return "User does not exist";
        } else {
            return "An error occurred";
        }
    }

    public String changePhoneByID(int id, int phone) {
        int change = super.changePhone(id, phone);
        if(change == 1) {
            return "Phone number was changed to " + phone;
        } else if(change == -1) {
            return "User does not exist";
        } else {
            return "An error occurred";
        }
    }

    public String changeMailByID(int id, String mail) {
        int change = super.changeMail(id, mail);
        if(change == 1) {
            return "Email address was changed to " + mail;
        } else if(change == -1) {
            return "User does not exist";
        } else {
            return "An error occurred";
        }
    }

    public String changeUsernameByID(int id, String username) {
        int change = (super.changeUsername(id, username));
        if(change == 1) {
            return "Username was changed to " + username;
        } else if(change == -1) {
            return "User does not exist";
        } else if(change == -3) {
            return "New username is already in use";
        } else {
            return "An error occurred";
        }
    }

    public String changePasswordByID(int id, String password) {
        int change = super.changePassword(id, password);
        if(change == 1) {
            return "Password was changed";
        } else if(change == -1) {
            return "User does not exist";
        } else {
            return "An error occurred";
        }
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
