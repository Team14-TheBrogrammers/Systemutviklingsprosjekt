package no.brogrammers.systemutviklingsprosjekt.Database.ConnectionClasses;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ResultTreeType;
import no.brogrammers.systemutviklingsprosjekt.Database.DatabaseConnection;
import no.brogrammers.systemutviklingsprosjekt.User.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Knut on 05.04.2016.
 */
public class UserConnection extends DatabaseConnection {

    public UserConnection() {
        super();
    }

    /*private boolean regUser(int employeeType) {
        String sqlCommand = "INSERT INTO Employee(last_name, first_name, phone, hiredate, position_id, username, password, email_address) \n" +
                "  VALUES('" + lastName + "', '" + firstName + "', " + phone + ", '" + new Date(Calendar.getInstance().getTimeInMillis()).getTime() + "', 1, '" + username + "', '" + password + ", '" + mail + "');";
        return checkUpdated(sqlCommand);
    }*/

    public boolean regManager(String lastName, String firstName, int phone, String mail, java.sql.Date dateOfEmployment, String username, String password) {
        String sqlCommand = "INSERT INTO Employee(last_name, first_name, phone, hiredate, position_id, username, password, email_address) \n" +
                "  VALUES('" + lastName + "', '" + firstName + "', " + phone + ", '" + new Date(Calendar.getInstance().getTimeInMillis()).getTime() + "', 1, '" + username + "', '" + password + ", '" + mail + "');";
        return checkUpdated(sqlCommand);
    }

    public boolean regCashier(String lastName, String firstName, int phone, String mail, java.sql.Date dateOfEmployment, String username, String password) {
        String sqlCommand = "INSERT INTO Employee(last_name, first_name, phone, hiredate, position_id, username, password, email_address) \n" +
                "  VALUES('" + lastName + "', '" + firstName + "', " + phone + ", '" + new Date(Calendar.getInstance().getTimeInMillis()).getTime() + "', 1, '" + username + "', '" + password + ", '" + mail + "');";
        return checkUpdated(sqlCommand);
    }

    public boolean regCook(String lastName, String firstName, int phone, String mail, java.sql.Date dateOfEmployment, String username, String password) {
        String sqlCommand = "INSERT INTO Employee(last_name, first_name, phone, hiredate, position_id, username, password, email_address) \n" +
                "  VALUES('" + lastName + "', '" + firstName + "', " + phone + ", '" + new Date(Calendar.getInstance().getTimeInMillis()).getTime() + "', 1, '" + username + "', '" + password + ", '" + mail + "');";
        return checkUpdated(sqlCommand);
    }

    public boolean regDriver(String lastName, String firstName, int phone, String mail, java.sql.Date dateOfEmployment, String username, String password) {
        String sqlCommand = "INSERT INTO Employee(last_name, first_name, phone, hiredate, position_id, username, password, email_address) \n" +
                "  VALUES('" + lastName + "', '" + firstName + "', " + phone + ", '" + new Date(Calendar.getInstance().getTimeInMillis()).getTime() + "', 1, '" + username + "', '" + password + ", '" + mail + "');";
        return checkUpdated(sqlCommand);
    }

    public boolean deleteUser(int employeeId) {
        if(userExists(employeeId)) {
            return false;
        }
        String sqlCommand = "DELETE FROM Employee WHERE emp_id = " + employeeId + ";";
        return checkUpdated(sqlCommand);
    }

    private boolean usernameExists(String username) {
        String sqlCommand = "SELECT username FROM Employee WHERE username = '" + username + "';";
        return checkExists(sqlCommand);
    }

    private boolean userExists(int userID) {
        String sqlCommand = "SELECT emp_id FROM Employee WHERE emp_id = " + userID + ";";
        return checkExists(sqlCommand);
    }

    public boolean changeLastName(int userID, String lastName) {
        String sqlCommand = "UPDATE Employee SET last_name = '" + lastName + "' WHERE emp_id = " + userID + ";";
        return checkUpdated(sqlCommand);
    }

    public boolean changeFirstName(int userID, String firstName) {
        String sqlCommand = "UPDATE Employee SET first_name = '" + firstName + "' WHERE emp_id = " + userID + ";";
        return checkUpdated(sqlCommand);
    }

    public ArrayList<User> viewAllUsers() {
        ArrayList<User> users = new ArrayList<>();

        String sqlCommand = "SELECT * FROM Employee;";
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while(resultSet.next()) {
                int id = resultSet.getInt("emp_id");
                String lastName = resultSet.getString("last_name");
                String firstName = resultSet.getString("first_name");
                int phone = resultSet.getInt("phone");
                //hiredate
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email_adress");
                switch(resultSet.getInt("position_id")) {
                    case 1:
                        users.add(new Manager(id, lastName, firstName, phone, email, hiredate, username, password));
                        break;
                    case 2:
                        users.add(new Cashier(id, lastName, firstName, phone, email, hiredate, username, password));
                        break;
                    case 3:
                        users.add(new Cook(id, lastName, firstName, phone, email, hiredate, username, password));
                        break;
                    case 4:
                        users.add(new Driver(id, lastName, firstName, phone, email, hiredate, username, password));
                        break;
                }
            }
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        }
        //get information from database here

        return users;
    }
}
