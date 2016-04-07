package no.brogrammers.systemutviklingsprosjekt.Database.ConnectionClasses;

import no.brogrammers.systemutviklingsprosjekt.Database.DatabaseConnection;

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

    public boolean checkLoginDetails(String username, String password) {
        Statement statement = null;
        try {
            String sqlCommand = "";
            statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("");
        } catch (SQLException sqle) {

        } catch (Exception e) {

        } finally {
            /*if(statement != null) {
                statement.close();
            }*/
        }

        return true;
    }
}
