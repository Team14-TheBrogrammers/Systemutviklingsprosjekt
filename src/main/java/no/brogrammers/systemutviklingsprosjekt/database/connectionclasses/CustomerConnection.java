package no.brogrammers.systemutviklingsprosjekt.database.connectionclasses;

import no.brogrammers.systemutviklingsprosjekt.customer.Customer;
import no.brogrammers.systemutviklingsprosjekt.customer.PrivateCustomer;
import no.brogrammers.systemutviklingsprosjekt.customer.Company;
import no.brogrammers.systemutviklingsprosjekt.database.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

    /*public boolean UpdateCompany() {

    }*/

    public int updateCustomerAddressAndZip(int customerID, String newAddress, int newZip) {
        if(customerExists(customerID)) {
            if(checkZipExists(newZip)) {
                String sqlCommand = "UPDATE Customer SET address = " + newAddress + " AND zip = " + newZip + " WHERE customer_id = " + customerID + ";";
                if(checkUpdated(sqlCommand)) {
                    return 1; //Information is updated
                } else {
                    return -2; //Information is not updated
                }
            } else {
                //Zip address does not exist in the database
                return -3;
            }
        } else {
            //CustomerID is invalid and does not exist in the database
            return -1;
        }
    }

    public int updateCustomerAddress(int customerID, String newAddress) {
        if(customerExists(customerID)) {
            String sqlCommand = "UPDATE Customer SET address = " + newAddress + " WHERE customer_id = " + customerID + ";";
            if(checkUpdated(sqlCommand)) {
                return 1;
            } else {
                return -2;
            }
        } else {
            return -1;
        }
    }

    //LEGGE TIL NY ZIP-METODE

    private boolean checkZipExists(int zip) {

    }

    /*public boolean UpdatePrivateCustomer() {

    }*/

    public Customer viewCustomer(int customerID) {
        if(customerExists(customerID)) {
            if(isCompany(customerID)) {
                String sqlCommand = "SELECT * FROM Company NATURAL JOIN Customer WHERE customer_id  = ?;\n";
                try {
                    Statement statement = getConnection().createStatement();
                    ResultSet resultSet = statement.executeQuery(sqlCommand);
                    while (resultSet.next()) {
                        String address = resultSet.getString("address");
                        int zip = resultSet.getInt("zip");
                        int phone = resultSet.getInt("phone");
                        String email = resultSet.getString("email_address");
                        int companyID = resultSet.getInt("company_id");
                        String name = resultSet.getString("name");

                        return new Company(customerID, address, zip, email, phone, companyID, name);
                    }
                } catch (SQLException sqle) {
                    writeError(sqle.getMessage());
                } catch (Exception e) {
                    writeError(e.getMessage());
                }
            } else {
                String sqlCommand = "SELECT * FROM Private_customer NATURAL JOIN Customer WHERE customer_id = ?;";
                try {
                    Statement statement = getConnection().createStatement();
                    ResultSet resultSet = statement.executeQuery(sqlCommand);
                    while (resultSet.next()) {
                        String address = resultSet.getString("address");
                        int zip = resultSet.getInt("zip");
                        int phone = resultSet.getInt("phone");
                        String email = resultSet.getString("email_address");
                        int privateID = resultSet.getInt("private_id");
                        String lastName = resultSet.getString("last_name");
                        String firstName = resultSet.getString("first_name");

                        return new PrivateCustomer(customerID, address, zip, email, phone, privateID, lastName, firstName);
                    }
                } catch (SQLException sqle) {
                    writeError(sqle.getMessage());
                } catch (Exception e) {
                    writeError(e.getMessage());
                }
            }
        }
        return null;
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
