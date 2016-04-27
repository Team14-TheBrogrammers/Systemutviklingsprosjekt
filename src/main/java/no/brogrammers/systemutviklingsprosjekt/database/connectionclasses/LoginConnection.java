package no.brogrammers.systemutviklingsprosjekt.database.connectionclasses;

import no.brogrammers.systemutviklingsprosjekt.database.DatabaseConnection;
import no.brogrammers.systemutviklingsprosjekt.user.*;

import java.sql.PreparedStatement;
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

        String sqlCommand = "SELECT * FROM Employee WHERE username = ? AND password = ?;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = getConnection().prepareStatement(sqlCommand);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int employeeID = resultSet.getInt("emp_id");
                String lastName = resultSet.getString("last_name");
                String firstName = resultSet.getString("first_name");
                int phone = resultSet.getInt("phone");
                java.sql.Date dateOfEmplyment = resultSet.getDate("date_of_employment");
                int posistionID = resultSet.getInt("position_id");
                String email = resultSet.getString("email_address");
                switch (posistionID) {
                    case 1:
                        //Manager
                        return new Manager(employeeID, lastName, firstName, phone, email, dateOfEmplyment, username, password);
                    case 2:
                        //Cashier
                        return new Cashier(employeeID, lastName, firstName, phone, email, dateOfEmplyment, username, password);
                    case 3:
                        //Cook
                        return new Cook(employeeID, lastName, firstName, phone, email, dateOfEmplyment, username, password);
                    case 4:
                        //Driver
                        return new Driver(employeeID, lastName, firstName, phone, email, dateOfEmplyment, username, password);
                }
            }
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        } finally {
            getCleaner().closeResultSet(resultSet);
            getCleaner().closePreparedStatement(preparedStatement);
        }
        return null;
    }
}
