package no.brogrammers.systemutviklingsprosjekt.database.connectionclasses;

import no.brogrammers.systemutviklingsprosjekt.database.DatabaseConnection;
import no.brogrammers.systemutviklingsprosjekt.user.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Knut on 05.04.2016.
 * UserConnection class
 */
public abstract class UserConnection extends DatabaseConnection {

    public UserConnection() {
        super();
    }

    /**
     * Method that register a new user in the database.
     * @param lastName
     * @param firstName
     * @param phone
     * @param mail
     * @param dateOfEmployment
     * @param username
     * @param password
     * @param employeeType is an int where the type of employee is stored in the database
     * @return int 1 if the user was registered, -2 if not
     */
    private int regUser(String lastName, String firstName, int phone, String mail, java.sql.Date dateOfEmployment, String username, String password, int employeeType) {
        int check = checkExistingDetails(username, phone);
        if(check == 1) {
            String sqlCommand = "INSERT INTO Employee(last_name, first_name, phone, date_of_employment, position_id, username, password, email_address) \n" +
                    "  VALUES('" + lastName + "', '" + firstName + "', " + phone + ", '" + dateOfEmployment + "', " + employeeType + ", '" + username + "', '" + password + "', '" + mail + "');";
            return checkRegistered(sqlCommand); // 1 if updated -2 if not
        } else {
            return check;
        }
    }

    /**
     * Method that checks if something was registered/updated in the database.
     * @param command is an sql query
     * @return int 1 if something was updated in the database, -2 if not
     */

    private int checkRegistered(String command) {
        if(checkUpdated(command)) {
            return 1;
        } else {
            return -2;
        }
    }

    private boolean phoneNumberExist(int phone) {
        String sqlCommand = "SELECT * FROM Employee WHERE phone = " + phone + ";";
        return checkExists(sqlCommand);
    }

    private int checkExistingDetails(String username, int phone) {
        if(usernameExists(username)) {
            return -1;
        }

        if(phoneNumberExist(phone)) {
            return -3;
        }
        return 1; //ok
    }

    public int regManager(String lastName, String firstName, int phone, String mail, java.sql.Date dateOfEmployment, String username, String password) {
        return regUser(lastName, firstName, phone, mail, dateOfEmployment, username, password, 1);
        /*String sqlCommand = "INSERT INTO Employee(last_name, first_name, phone, date_of_employment, position_id, username, password, email_address) \n" +
                "  VALUES('" + lastName + "', '" + firstName + "', " + phone + ", '" + new Date(Calendar.getInstance().getTimeInMillis()) + "', 1, '" + username + "', '" + password + "', '" + mail + "');";
        return checkRegistered(sqlCommand);*/
    }

    public int regCashier(String lastName, String firstName, int phone, String mail, java.sql.Date dateOfEmployment, String username, String password) {
        return regUser(lastName, firstName, phone, mail, dateOfEmployment, username, password, 2);
        /*if(usernameExists(username)) {
            return -1;
        }
        String sqlCommand = "INSERT INTO Employee(last_name, first_name, phone, date_of_employment, position_id, username, password, email_address) \n" +
                "  VALUES('" + lastName + "', '" + firstName + "', " + phone + ", '" + new Date(Calendar.getInstance().getTimeInMillis()).getTime() + "', 1, '" + username + "', '" + password + "', '" + mail + "');";
        return checkRegistered(sqlCommand);*/
    }

    public int regCook(String lastName, String firstName, int phone, String mail, java.sql.Date dateOfEmployment, String username, String password) {
        return regUser(lastName, firstName, phone, mail, dateOfEmployment, username, password, 3);
        /*if(usernameExists(username)) {
            return -1;
        }
        String sqlCommand = "INSERT INTO Employee(last_name, first_name, phone, date_of_employment, position_id, username, password, email_address) \n" +
                "  VALUES('" + lastName + "', '" + firstName + "', " + phone + ", '" + new Date(Calendar.getInstance().getTimeInMillis()).getTime() + "', 1, '" + username + "', '" + password + "', '" + mail + "');";
        return checkRegistered(sqlCommand);*/
    }

    public int regDriver(String lastName, String firstName, int phone, String mail, java.sql.Date dateOfEmployment, String username, String password) {
        return regUser(lastName, firstName, phone, mail, dateOfEmployment, username, password, 4);
        /*if(usernameExists(username)) {
            return -1;
        }
        String sqlCommand = "INSERT INTO Employee(last_name, first_name, phone, date_of_employment, position_id, username, password, email_address) \n" +
                "  VALUES('" + lastName + "', '" + firstName + "', " + phone + ", '" + new Date(Calendar.getInstance().getTimeInMillis()).getTime() + "', 1, '" + username + "', '" + password + "', '" + mail + "');";
        return checkRegistered(sqlCommand);*/
    }

    public int deleteUser(int employeeId) {
        if(!(userExists(employeeId))) {
            return -1;
        }
        String sqlCommand = "DELETE FROM Employee WHERE emp_id = " + employeeId + ";";
        return checkRegistered(sqlCommand); //Method name may be changed
    }

    private boolean usernameExists(String username) {
        String sqlCommand = "SELECT username FROM Employee WHERE username = '" + username + "';";
        return checkExists(sqlCommand);
    }

    private boolean userExists(int userID) {
        String sqlCommand = "SELECT emp_id FROM Employee WHERE emp_id = " + userID + ";";
        return checkExists(sqlCommand);
    }

    public int changeLastName(int userID, String lastName) {
        if(!(userExists(userID))) {
            return -1;
        }
        String sqlCommand = "UPDATE Employee SET last_name = '" + lastName + "' WHERE emp_id = " + userID + ";";
        return checkRegistered(sqlCommand); //Change method name
    }

    public int changeFirstName(int userID, String firstName) {
        if(!(userExists(userID))) {
            return -1;
        }
        String sqlCommand = "UPDATE Employee SET first_name = '" + firstName + "' WHERE emp_id = " + userID + ";";
        return checkRegistered(sqlCommand); //change method name
    }

    /**
     * Method foor changing the phone name for a user with a given userID.
     * @param userID
     * @param phone
     * @return -1 if not a user exist with the given userID. Return -3 if the phone number already exist. Return -2 if//TODO:FIX
     */
    public int changePhone(int userID, int phone) {
        if(!(userExists(userID))) {
          return -1;
        }
        if(phoneNumberExist(phone)) {
            return -3;
        }
        String sqlCommand = "UPDATE Employee SET phone = " + phone + " WHERE emp_id = " + userID + ";";
        return checkRegistered(sqlCommand); //change method name
    }

    public int changeMail(int userID, String email) {
        if(!(userExists(userID))) {
            return -1;
        }
        String sqlCommand = "UPDATE Employee SET email_address = '" + email + "' WHERE emp_id = " + userID + ";";
        return checkRegistered(sqlCommand); //change method name
    }

    public int changeUsername(int userID, String username) {
        if(!(userExists(userID))) {
            return -1;
        } else if(usernameExists(username)) {
            return -3;
        } else {
            String sqlCommand = "UPDATE Employee SET username = '" + username + "' WHERE emp_id = " + userID + ";";
            return checkRegistered(sqlCommand); //change method name
        }

        //1: ok (brukernavn endret)
        //-1: user id eksiterer ikke
        //-2: vanlig feil
        //-3: det nye brukernavnet er allerede i bruk
    }

    public int changePassword(int userID, String password) {
        if(userExists(userID)) {
            String sqlCommand = "UPDATE Employee SET password = '" + password + "' WHERE emp_id = " + userID + ";";
            return checkRegistered(sqlCommand); //change method name
        } else {
            return -1;
        }
    }

    public User viewUser(int userID) {
        if(userExists(userID)) {
            String sqlCommand = "SELECT * FROM Employee WHERE emp_id = " + userID + ";";
            Statement statement = null;
            ResultSet resultSet = null;

            try {
                statement = getConnection().createStatement();
                resultSet = statement.executeQuery(sqlCommand);
                while (resultSet.next()) {
                    String lastName = resultSet.getString("last_name");
                    String firstName = resultSet.getString("first_name");
                    int phone = resultSet.getInt("phone");
                    java.sql.Date dateOfEmployment = resultSet.getDate("date_of_employment");
                    int position = resultSet.getInt("position_id");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    String email = resultSet.getString("email_address");

                    switch (position) {
                        case 1:
                            return new Manager(userID, lastName, firstName, phone, email, dateOfEmployment, username, password);
                        case 2:
                            return new Cashier(userID, lastName, firstName, phone, email, dateOfEmployment, username, password);
                        case 3:
                            return new Cook(userID, lastName, firstName, phone, email, dateOfEmployment, username, password);
                        case 4:
                            return new Driver(userID, lastName, firstName, phone, email, dateOfEmployment, username, password);
                    }
                }
            } catch (SQLException sqle) {
                writeError(sqle.getMessage());
            } catch (Exception e) {
                writeError(e.getMessage());
            } finally {
                getCleaner().closeResultSet(resultSet);
                getCleaner().closeStatement(statement);
            }
        }
        return null;
    }

    public User viewUser(String username) {
        if(usernameExists(username)) {
            String sqlCommand = "SELECT * FROM Employee WHERE username = '" + username + "';";
            Statement statement = null;
            ResultSet resultSet = null;

            try {
                statement = getConnection().createStatement();
                resultSet = statement.executeQuery(sqlCommand);
                while (resultSet.next()) {
                    int userID = resultSet.getInt("emp_id");
                    String lastName = resultSet.getString("last_name");
                    String firstName = resultSet.getString("first_name");
                    int phone = resultSet.getInt("phone");
                    java.sql.Date dateOfEmployment = resultSet.getDate("date_of_employment");
                    int position = resultSet.getInt("position_id");
                    String password = resultSet.getString("password");
                    String email = resultSet.getString("email_address");

                    switch (position) {
                        case 1:
                            return new Manager(userID, lastName, firstName, phone, email, dateOfEmployment, username, password);
                        case 2:
                            return new Cashier(userID, lastName, firstName, phone, email, dateOfEmployment, username, password);
                        case 3:
                            return new Cook(userID, lastName, firstName, phone, email, dateOfEmployment, username, password);
                        case 4:
                            return new Driver(userID, lastName, firstName, phone, email, dateOfEmployment, username, password);
                    }
                }
            } catch (SQLException sqle) {
                writeError(sqle.getMessage());
            } catch (Exception e) {
                writeError(e.getMessage());
            } finally {
                getCleaner().closeResultSet(resultSet);
                getCleaner().closeStatement(statement);
            }
        }
        return null;
    }

    public ArrayList<User> viewAllUsers() {
        ArrayList<User> users = new ArrayList<User>();

        String sqlCommand = "SELECT * FROM Employee;";
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sqlCommand);
            while(resultSet.next()) {
                int id = resultSet.getInt("emp_id");
                String lastName = resultSet.getString("last_name");
                String firstName = resultSet.getString("first_name");
                int phone = resultSet.getInt("phone");
                java.sql.Date dateOfEmployment = resultSet.getDate("date_of_employment");
                int position = resultSet.getInt("position_id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email_address");
                switch(position) {
                    case 1:
                        users.add(new Manager(id, lastName, firstName, phone, email, dateOfEmployment, username, password));
                        break;
                    case 2:
                        users.add(new Cashier(id, lastName, firstName, phone, email, dateOfEmployment, username, password));
                        break;
                    case 3:
                        users.add(new Cook(id, lastName, firstName, phone, email, dateOfEmployment, username, password));
                        break;
                    case 4:
                        users.add(new Driver(id, lastName, firstName, phone, email, dateOfEmployment, username, password));
                        break;
                }
            }
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        } finally {
            getCleaner().closeResultSet(resultSet);
            getCleaner().closeStatement(statement);
        }
        //get information from database here

        return users;
    }
}