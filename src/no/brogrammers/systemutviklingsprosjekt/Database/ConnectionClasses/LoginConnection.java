package no.brogrammers.systemutviklingsprosjekt.database.connectionclasses;

import no.brogrammers.systemutviklingsprosjekt.database.DatabaseConnection;
import no.brogrammers.systemutviklingsprosjekt.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Knut on 05.04.2016.
 */
public class LoginConnection extends DatabaseConnection{
    public LoginConnection() {
        super();
    }

    public User checkLoginDetails(String username, String password) {

        String sqlCommand = "SELECT * FROM Employee WHERE username = '" + username + "' AND password = '" + password + "';";


        return true;
    }
}
