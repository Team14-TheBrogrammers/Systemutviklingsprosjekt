package no.brogrammers.systemutviklingsprosjekt.database.connectionclasses;

import no.brogrammers.systemutviklingsprosjekt.customer.Customer;
import no.brogrammers.systemutviklingsprosjekt.customer.PrivateCustomer;
import no.brogrammers.systemutviklingsprosjekt.customer.Company;
import no.brogrammers.systemutviklingsprosjekt.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Knut on 06.04.2016.
 */
public abstract class CustomerConnection extends DatabaseConnection {

    private boolean customerExists(int customerID) {
        String sqlCommand = "SELECT * FROM Customer WHERE " + customerID + ";";
        return checkExists(sqlCommand);
    }

    private boolean phoneNumberIsUsed(int phone) {
        String sqlCommand = "SELECT phone FROM Customer WHERE phone = " + phone + ";";
        return checkExists(sqlCommand);
    }

    public int regPrivateCustomer(String address, int zip, int phone, String email, String lastName, String firstName) {
        if(phoneNumberIsUsed(phone)) {
            return -1;
        }

        int customerID = 1;
        boolean customerFinished = false;
        ResultSet resultSet = null;
        PreparedStatement selectStatement = null;
        PreparedStatement insertStatement = null;


        String customerSelect = "SELECT COUNT(customer_id) AS c FROM Customer;";
        String privateCSelect = "SELECT COUNT(private_id) AS c FROM Private_customer";
        String customerInsert = "INSERT INTO Customer(address, zip, phone, email_address) VALUES(?, ?, ?, ?);";
        String privateCInsert = "INSERT INTO Private_customer(last_name, first_name, customer_id)\n" +
                " VALUES(?, ?, ?, ?, ?);";

        while(!customerFinished) {
            try {
                selectStatement = getConnection().prepareStatement(customerSelect);
                resultSet = selectStatement.executeQuery();
                resultSet.next();

                customerID = resultSet.getInt("c") + 1;
                insertStatement = getConnection().prepareStatement(customerInsert);
                insertStatement.setString(1, address);
                insertStatement.setInt(2, zip);
                insertStatement.setInt(3, phone);
                insertStatement.setString(4, email);

                int privateID = 1;
                boolean privateFinished = false;
                ResultSet resultSet1 = null;
                PreparedStatement privateSelect = null;
                PreparedStatement privateInsert = null;

                while(!privateFinished) {
                    try {
                        privateSelect = getConnection().prepareStatement(privateCSelect);
                        resultSet1 = privateSelect.executeQuery();
                        resultSet1.next();

                        privateID = resultSet1.getInt("c") + 1;
                        privateInsert = getConnection().prepareStatement(privateCInsert);
                        privateInsert.setString(1, lastName);
                        privateInsert.setString(2, firstName);
                        privateInsert.setInt(3, customerID);

                        privateInsert.executeUpdate();
                        privateFinished = true;
                    } catch (SQLException sqle) {
                        writeError(sqle.getMessage()); //// FIXME: 14.04.2016
                    } catch (Exception e) {
                        writeError(e.getMessage()); // TODO : SHIT
                    } finally {
                        getCleaner().closePreparedStatement(privateInsert);
                        getCleaner().closeResultSet(resultSet1);
                        getCleaner().closePreparedStatement(privateSelect);
                    }
                }

                insertStatement.executeUpdate();
                customerFinished = true;

            } catch (SQLException sqle) {
                writeError(sqle.getMessage());
            } catch (Exception e) {
                writeError(e.getMessage());
            } finally {
                getCleaner().closePreparedStatement(insertStatement);
                getCleaner().closeResultSet(resultSet);
                getCleaner().closePreparedStatement(selectStatement);
            }
        }
        return customerID;
    }

    public int regCompany(String address, int zip, int phone, String email, String name) {
        if(phoneNumberIsUsed(phone)) {
            return -1;
        }

        int customerID = 1;
        boolean customerFinished = false;
        ResultSet resultSet = null;
        PreparedStatement selectStatement = null;
        PreparedStatement insertStatement = null;


        String customerSelect = "SELECT COUNT(customer_id) AS c FROM Customer;";
        String privateCSelect = "SELECT COUNT(private_id) AS c FROM Company";
        String customerInsert = "INSERT INTO Customer(address, zip, phone, email_address) VALUES(?, ?, ?, ?);";
        String privateCInsert = "INSERT INTO Company(company_name, customer_id) VALUES(?, ?);";

        while(!customerFinished) {
            try {
                selectStatement = getConnection().prepareStatement(customerSelect);
                resultSet = selectStatement.executeQuery();
                resultSet.next();

                customerID = resultSet.getInt("c") + 1;
                insertStatement = getConnection().prepareStatement(customerInsert);
                insertStatement.setString(1, address);
                insertStatement.setInt(2, zip);

                int privateID = 1;
                boolean privateFinished = false;
                ResultSet resultSet1 = null;
                PreparedStatement privateSelect = null;
                PreparedStatement privateInsert = null;

                while(!privateFinished) {
                    try {
                        privateSelect = getConnection().prepareStatement(privateCSelect);
                        resultSet1 = privateSelect.executeQuery();
                        resultSet1.next();

                        privateID = resultSet1.getInt("c") + 1;
                        privateInsert = getConnection().prepareStatement(privateCInsert);
                        privateInsert.setString(1, name);
                        privateInsert.setInt(2, customerID);

                        privateInsert.executeUpdate();
                        privateFinished = true;
                    } catch (SQLException sqle) {
                        writeError(sqle.getMessage()); //// FIXME: 14.04.2016
                    } catch (Exception e) {
                        writeError(e.getMessage()); // TODO : SHIT
                    } finally {
                        getCleaner().closePreparedStatement(privateInsert);
                        getCleaner().closeResultSet(resultSet1);
                        getCleaner().closePreparedStatement(privateSelect);
                    }
                }

                insertStatement.executeUpdate();
                customerFinished = true;

            } catch (SQLException sqle) {
                writeError(sqle.getMessage());
            } catch (Exception e) {
                writeError(e.getMessage());
            } finally {
                getCleaner().closePreparedStatement(insertStatement);
                getCleaner().closeResultSet(resultSet);
                getCleaner().closePreparedStatement(selectStatement);
            }
        }
        return customerID;
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
        String sqlCommand = "SELECT * FROM Postal WHERE zip = " + zip + ";";
        return checkExists(sqlCommand);
    }

    /*public boolean UpdatePrivateCustomer() {

    }*/

    public Customer viewCustomer(int customerID) {
        if(customerExists(customerID)) {
            if(isCompany(customerID)) {
                String sqlCommand = "SELECT * FROM Company NATURAL JOIN Customer WHERE customer_id  = " + customerID + ";";
                Statement statement = null;
                ResultSet resultSet = null;
                try {
                    statement = getConnection().createStatement();
                    resultSet = statement.executeQuery(sqlCommand);
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
                } finally {
                    getCleaner().closeResultSet(resultSet);
                    getCleaner().closeStatement(statement);
                }
            } else {
                String sqlCommand = "SELECT * FROM Private_customer NATURAL JOIN Customer WHERE customer_id = " + customerID + ";";
                Statement statement = null;
                ResultSet resultSet = null;
                try {
                    statement = getConnection().createStatement();
                    resultSet = statement.executeQuery(sqlCommand);
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
                } finally {
                    getCleaner().closeResultSet(resultSet);
                    getCleaner().closeStatement(statement);
                }
            }
        }
        return null;
    }

    public ArrayList<Customer> viewAllCustomers() {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        String sqlAllCustomers = "SELECT * FROM Customers";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sqlAllCustomers);
            while (resultSet.next()) {
                int customerID = resultSet.getInt("customer_id");
                customers.add(viewCustomer(customerID));
            }
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        } finally {
            getCleaner().closeResultSet(resultSet);
            getCleaner().closeStatement(statement);
        }
        return customers;
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