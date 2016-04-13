package no.brogrammers.systemutviklingsprosjekt.database.connectionclasses;

import no.brogrammers.systemutviklingsprosjekt.database.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Knut on 06.04.2016.
 */
public abstract class CustomerConnection extends DatabaseConnection {

    private boolean customerExists(int customerID) {
        String sqlCommand = "SELECT * FROM Customer WHERE " + customerID + ";";
        return checkExists(sqlCommand);
    }

    public boolean regPrivateCustomer() {
        int customerID = 1;
        boolean finished = false;
        ResultSet resultSet = null;
        PreparedStatement selectStatement;
        PreparedStatement insertStatement;

        String selectCommand = "SELECT COUNT(customer_id) AS c FROM Customer;";
        String insertCommand = "INSERT INTO Customer SET ";
        String insertCommand2 = "";

        while(!finished) {
            try {
                selectStatement = getConnection().prepareStatement(selectCommand);
            } catch (SQLException sqle) {
                writeError(sqle.getMessage());
            } catch (Exception e) {
                writeError(e.getMessage());
            }
        }
    }

    public boolean regCompany() {

    }

    public boolean UpdateCompany() {

    }
    public boolean UpdatePrivateCustomer() {

    }

    public Customer viewCustomer(int customerID) {
        if(customerExists(customerID)) {
            boolean isCompany = false;
            String sqlCommandCompany = "SELECT * FROM Company WHERE customer_id = " + customerID + ";";

            try {
                Statement statement = getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery(sqlCommandCompany);
                while (resultSet.next()) {
                    String address = resultSet.getString("");
                    int zipAddress = resultSet.getInt("zip");
                    //PHONE ? INT? STRING?
                    String email = resultSet.getString("");


                    return new Company();
                }

                if(!isCompany) {
                    String sqlCommandPrivate = "SELECT * FROM Private_customer WHERE customer_id = " + customerID + ";";

                    Statement statement1 = getConnection().createStatement();
                    ResultSet resultSet1 = statement1.executeQuery(sqlCommandPrivate);

                    while (resultSet1.next()) {
                        String address = resultSet.getString("");
                        int zipAddress = resultSet.getInt("zip");
                        //PHONE ? INT? STRING?
                        String email = resultSet.getString("");

                        return new PrivateCustomer();
                    }
                }
            } catch (SQLException sqle) {
                writeError(sqle.getMessage());
            } catch (Exception e) {
                writeError(e.getMessage());
            }
        } else {
            return null;
        }
    }

    public ArrayList<Customer> viewAllCustomers() {

    }

    private boolean isCompany(int customerID) {
        String sqlCompany = "SELECT * FROM Company WHRE customer_id = " + customerID + ";";
        return checkExists(sqlCompany);
    }

    public boolean deleteCustomer(int customerID) {
        if(customerExists(customerID)) {
            if(isCompany(customerID)) {
                String sqlDelete = "DELETE FROM Company WHERE customer_id = " + customerID + ";";
                if(!(checkUpdated(sqlDelete))) {
                    return false;
                }
            } else {
                String sqlDelete = "DELETE FROM Private_customer WHERE customer_id = " + customerID + ";";
                if(!(checkUpdated(sqlDelete))) {
                    return false;
                }
            }
            String sqlCommand = "DELETE FROM Customer WHERE customer_id = " + customerID + ";";
            return checkUpdated(sqlCommand);
        } else {
            return false;
        }
    }
}
