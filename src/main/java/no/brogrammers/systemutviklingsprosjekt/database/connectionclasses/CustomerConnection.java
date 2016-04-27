package no.brogrammers.systemutviklingsprosjekt.database.connectionclasses;

import no.brogrammers.systemutviklingsprosjekt.customer.Customer;
import no.brogrammers.systemutviklingsprosjekt.customer.PrivateCustomer;
import no.brogrammers.systemutviklingsprosjekt.customer.Company;
import no.brogrammers.systemutviklingsprosjekt.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Knut on 06.04.2016.
 * This class is made for setting up a connection to the database and read and write data about customers in the database.
 * The CustomerConnection class Extends DatabaseConnection class and for easier conneciton and coding to the database.
 */
public abstract class CustomerConnection extends DatabaseConnection {

    /**
     * Method for checking if a given customer with customerID allready is registered in the database.
     * @param customerID is the ID that will be checked in the database.
     * @return true if the customer allready exists, if not the method returns false.
     */
    private boolean customerExists(int customerID) {
        String sqlCommand = "SELECT * FROM Customer WHERE customer_id = " + customerID + ";";
        return checkExists(sqlCommand);
    }

    /**
     * Method for checking whether a customer is a Private Customer or a Company.
     * @param customerID is the customer ID that will be checked in the database.
     * @return true if the Customer is either Private Customer or Company. It returns false if none of these matches.
     */
    private boolean isPrivateCustomerOrCompany(int customerID) { //customer exists as a private customer or a company
        String sqlPrivateCustomer = "SELECT * FROM Private_customer WHERE customer_id = " + customerID + ";";
        if(checkExists(sqlPrivateCustomer)) {
            return true;
        } else {
            String sqlCompany = "SELECT * FROM Company WHERE customer_id = " + customerID + ";";
            if (checkExists(sqlCompany)) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Method for checking if a phone number is allready in use by an other customer.
     * @param phone is the phone number that is checked in the databse.
     * @return true if the phone number is is use. It return false if not.
     */
    private boolean phoneNumberIsUsed(int phone) {
        String sqlCommand = "SELECT phone FROM Customer WHERE phone = " + phone + ";";
        return checkExists(sqlCommand);
    }

    /**
     * Method for registering a Private Customer to the database.
     * @param address is the address to the customer.
     * @param zip is the zip code for the address to the customer.
     * @param email is the email for the customer.
     * @param phone is the phone address to the customer.
     * @param lastName is the last name to the customer
     * @param firstName is the first name to the customer.
     * @return -1 if the phone number is allready in use by someone else, or something else happened.
     * Otherwise the registeredcustomer ID is returned.
     */
    public int regPrivateCustomer(String address, int zip, String email, int phone, String lastName, String firstName) {
        if(phoneNumberIsUsed(phone)) {
            return -1;
        }

        int customerID = 1;
        //boolean customerFinished = false;
        ResultSet resultSet = null;
        PreparedStatement selectStatement = null;
        PreparedStatement insertStatement = null;

        String customerSelect = "SELECT MAX(customer_id) AS m FROM Customer;";
        String customerInsert = "INSERT INTO Customer(address, zip, phone, email_address) VALUES(?, ?, ?, ?);";

        String privateCSelect = "SELECT MAX(private_id) AS m FROM Private_customer;";
        String privateCInsert = "INSERT INTO Private_customer(last_name, first_name, customer_id) VALUES(?, ?, ?);";

        //while(!customerFinished) {
            try {
                getConnection().setAutoCommit(false);

                selectStatement = getConnection().prepareStatement(customerSelect);
                resultSet = selectStatement.executeQuery();
                resultSet.next();
                customerID = resultSet.getInt("m") + 1;

                insertStatement = getConnection().prepareStatement(customerInsert);
                insertStatement.setString(1, address);
                insertStatement.setInt(2, zip);
                insertStatement.setInt(3, phone);
                insertStatement.setString(4, email);

                if(insertStatement.executeUpdate() != 0) {
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

                            privateID = resultSet1.getInt("m") + 1;
                            privateInsert = getConnection().prepareStatement(privateCInsert);
                            privateInsert.setString(1, lastName);
                            privateInsert.setString(2, firstName);
                            privateInsert.setInt(3, customerID);

                            if(privateInsert.executeUpdate() != 0) {
                                System.out.println(privateID);
                                getConnection().commit();
                                return customerID;
                            } else {
                                return -1;
                            }
                            //privateFinished = true;
                        } catch (SQLException sqle) {
                            writeError(sqle.getMessage());
                        } catch (Exception e) {
                            writeError(e.getMessage());
                        } finally {
                            getCleaner().closePreparedStatement(privateInsert);
                            getCleaner().closeResultSet(resultSet1);
                            getCleaner().closePreparedStatement(privateSelect);
                            getCleaner().setAutoCommit(getConnection());
                        }
                    }
                } else {
                    return -1;
                }

                //customerFinished = true;

            } catch (SQLException sqle) {
                writeError(sqle.getMessage());
            } catch (Exception e) {
                writeError(e.getMessage());
            } finally {
                getCleaner().closePreparedStatement(insertStatement);
                getCleaner().closeResultSet(resultSet);
                getCleaner().closePreparedStatement(selectStatement);
                getCleaner().setAutoCommit(getConnection());
            }
        //}
        return customerID;
    }

    /**
     * Method for registering a new Company in the customer database.
     * @param address is the address to the customer.
     * @param zip is the zip code for the address to the customer.
     * @param email is the email to the customer.
     * @param phone is the phone address to the customer.
     * @param name is the name for the Company that will be registered in the database.
     * @return -1 if phone number is allready in use by another customer or something else wrong happened.
     * Otherwise it returs -2 if the customer was not registered in the database.
     * The method returns the customer ID for the new Company if everything went great.
     */
    public int regCompany(String address, int zip, String email, int phone, String name) {
        if(phoneNumberIsUsed(phone)) {
            return -1;
        }

        int customerID = 1;
        //boolean customerFinished = false;
        ResultSet resultSet = null;
        PreparedStatement selectStatement = null;
        PreparedStatement insertStatement = null;


        String customerSelect = "SELECT MAX(customer_id) AS m FROM Customer;";
        String customerInsert = "INSERT INTO Customer(address, zip, phone, email_address) VALUES(?, ?, ?, ?);";

        String companyCSelect = "SELECT MAX(company_id) AS m FROM Company;";
        String companyCInsert = "INSERT INTO Company(company_name, customer_id) VALUES(?, ?);";

        //while(!customerFinished) {
            try {
                getConnection().setAutoCommit(false);

                selectStatement = getConnection().prepareStatement(customerSelect);
                resultSet = selectStatement.executeQuery();
                resultSet.next();
                customerID = resultSet.getInt("m") + 1;

                insertStatement = getConnection().prepareStatement(customerInsert);
                insertStatement.setString(1, address);
                insertStatement.setInt(2, zip);
                insertStatement.setInt(3, phone);
                insertStatement.setString(4, email);
                if(insertStatement.executeUpdate() != 0){
                    int companyID = 1;
                    boolean companyFinished = false;
                    ResultSet resultSet1 = null;
                    PreparedStatement companySelect = null;
                    PreparedStatement companyInsert = null;

                    while(!companyFinished) {
                        try {
                            companySelect = getConnection().prepareStatement(companyCSelect);
                            resultSet1 = companySelect.executeQuery();
                            resultSet1.next();

                            companyID = resultSet1.getInt("m") + 1;
                            companyInsert = getConnection().prepareStatement(companyCInsert);
                            companyInsert.setString(1, name);
                            companyInsert.setInt(2, customerID);

                            if(companyInsert.executeUpdate() != 0) {
                                System.out.println(companyID);
                                getConnection().commit();
                                return customerID;
                            } else {
                                return -1;
                            }

                            //privateFinished = true;
                        } catch (SQLException sqle) {
                            writeError(sqle.getMessage());
                        } catch (Exception e) {
                            writeError(e.getMessage());
                        } finally {
                            getCleaner().closePreparedStatement(companyInsert);
                            getCleaner().closeResultSet(resultSet1);
                            getCleaner().closePreparedStatement(companySelect);
                            getCleaner().setAutoCommit(getConnection());
                        }
                    }
                } else {
                    return -2;
                }

                //insertStatement.executeUpdate();
                //customerFinished = true;

            } catch (SQLException sqle) {
                writeError(sqle.getMessage());
            } catch (Exception e) {
                writeError(e.getMessage());
            } finally {
                getCleaner().closePreparedStatement(insertStatement);
                getCleaner().closeResultSet(resultSet);
                getCleaner().closePreparedStatement(selectStatement);
                getCleaner().setAutoCommit(getConnection());
            }
        //}
        return customerID;
    }

    /**
     * Method for changing address and zip for a customer in the database
     * @param customerID is the customer id
     * @param newAddress is the new address
     * @param newZip is the zip code (in Trondheim)
     * @return int 1 if successful, -2 if the information was not updated, -3 if the zip address does not exist in the database, -1 if customer id is invalid
     */
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

    /**
     * Update address for a customer in the database
     * @param customerID is the customer id
     * @param newAddress is the new address
     * @return int 1 if the database was updated, -2 if not, -1 if the customer does not exist in the database
     */

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

    /**
     * Method for checking if the zip code exist in the database (all zip codes for Trondheim should exist in the database).
     * @param zip is the zip code
     * @return true if it exists, false if not
     */

    private boolean checkZipExists(int zip) {
        String sqlCommand = "SELECT * FROM Postal WHERE zip = " + zip + ";";
        return checkExists(sqlCommand);
    }

    /**
     * Method for returning a customer object from the database with customer id.
     * @param customerID is the customer id
     * @return Company object if the customer is a company, private customer object if the customer is a private customer, null if the customer id does not exist
     */

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
                        String name = resultSet.getString("company_name");

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

    /**
     * Method that returns an arraylist of all customers
     * @return ArrayList<Customer>
     */

    public ArrayList<Customer> viewAllCustomers() {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        String sqlAllCustomers = "SELECT * FROM Customer";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sqlAllCustomers);
            while (resultSet.next()) {
                int customerID = resultSet.getInt("customer_id");
                if(isPrivateCustomerOrCompany(customerID)) {
                    customers.add(viewCustomer(customerID));
                }
            }
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
            System.out.println("viewCustomer sql error");
        } catch (Exception e) {
            writeError(e.getMessage());
            System.out.println("viewCustomer error");
        } finally {
            getCleaner().closeResultSet(resultSet);
            getCleaner().closeStatement(statement);
        }
        return customers;
    }

    /**
     * Method that returns an arraylist of all companies in the database.
     * @return ArrayList<Company>
     */

    public ArrayList<Company> viewAllCompanies() {
        ArrayList<Company> companies = new ArrayList<Company>();
        String sqlCommand = "SELECT * FROM Customer NATURAL JOIN Company;";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sqlCommand);

            while(resultSet.next()) {
                int id = resultSet.getInt("customer_id");
                String address = resultSet.getString("address");
                int zip = resultSet.getInt("zip");
                String emailAddress = resultSet.getString("email_address");
                int phone = resultSet.getInt("phone");
                int companyID = resultSet.getInt("company_id");
                String companyName = resultSet.getString("company_name");
                companies.add(new Company(id, address, zip, emailAddress, phone, companyID, companyName));
            }
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        } finally {
            getCleaner().closeResultSet(resultSet);
            getCleaner().closeStatement(statement);
        }
        return companies;
    }

    /**
     * Method that returns an arraylist of all private customers in the database.
     * @return ArrayList<PrivateCustomer>
     */

    public ArrayList<PrivateCustomer> viewAllPrivateCustomers() {
        ArrayList<PrivateCustomer> privateCustomers = new ArrayList<PrivateCustomer>();
        String sqlCommand = "SELECT * FROM Customer NATURAL JOIN Private_customer;";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sqlCommand);
            while(resultSet.next()) {
                int customerID = resultSet.getInt("customer_id");
                String address = resultSet.getString("address");
                int zip = resultSet.getInt("zip");
                String emailAddress = resultSet.getString("email_address");
                int phone = resultSet.getInt("phone");
                int privateCustomerID = resultSet.getInt("private_id");
                String lastName = resultSet.getString("last_name");
                String firstName = resultSet.getString("first_name");
                privateCustomers.add(new PrivateCustomer(customerID, address, zip, emailAddress, phone, privateCustomerID, lastName, firstName));
            }
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        } finally {
            getCleaner().closeResultSet(resultSet);
            getCleaner().closeStatement(statement);
        }
        return privateCustomers;
    }

    /**
     * Method that checks if the customer belonging to the customer id is a company, the method uses the method checkExists(sqlCommand)
     * @param customerID is the customer id
     * @return true if it is a company, false if not
     */

    private boolean isCompany(int customerID) {
        String sqlCompany = "SELECT * FROM Company WHERE customer_id = " + customerID + ";";
        return checkExists(sqlCompany);
    }

    /**
     * Method that deletes a customer from the database, using a customer id
     * @param customerID is the customer id
     * @return true if the customer was deleted from both the company/private customer- and the customer-table, false if not
     */

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