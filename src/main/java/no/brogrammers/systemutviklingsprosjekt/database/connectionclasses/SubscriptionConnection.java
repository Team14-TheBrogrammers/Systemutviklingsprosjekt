package no.brogrammers.systemutviklingsprosjekt.database.connectionclasses;

import no.brogrammers.systemutviklingsprosjekt.order.Subscription;
import no.brogrammers.systemutviklingsprosjekt.recipe.Recipe;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Ingunn on 24.04.2016.
 * SubscriptionConnection class
 */

public class SubscriptionConnection extends OrderConnection {

    /**
     * Method to obtain orderID from subscriptionID. Returns -1 if subscriptionID does not exist or an error occured.
     * @param subscriptionID is the subscription ID
     * @return orderID
     */

    public int getOrderIDFromSubID(int subscriptionID) {
        String sqlCommand = "SELECT order_id FROM Orders NATURAL JOIN Subscription WHERE subs_id = '" + subscriptionID + "';";
        PreparedStatement selectStatement = null;
        ResultSet resultSet = null;
        int orderID = -1;

        try {
            selectStatement = getConnection().prepareStatement(sqlCommand);
            resultSet = selectStatement.executeQuery();
            resultSet.next();
            orderID = resultSet.getInt("order_id");
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        } finally {
            getCleaner().closePreparedStatement(selectStatement);
            getCleaner().closeResultSet(resultSet);
        }
        return orderID;
    }

    /**
     * Method for registering a subscription. An order is first registered, then a subscription.
     * @param customerID is the customer ID
     * @param paymentStatus is a boolean where true means paid
     * @param orderDate is the date the order was placed
     * @param deliveryDate is the date the order will be delivered
     * @param deliveryTime is the time of the day the order will be delivered
     * @param address is the delivery address
     * @param zipCode is the zip code for the delivery address
     * @param takeAway is a boolean where true means take away and false means delivery
     * @param otherRequests is possible requests the customer may have
     * @param recipes is a list of recipes
     * @param quantity is a list of quantities for the recipes
     * @param frequencyID is the frequency ID
     * @return newNumber (new subscriptionID) if subscription was successfully registered, -1 if adding a new Order to the database failed, -2 if an error occurred
     */

    public int registerSubscription(int customerID, boolean paymentStatus, java.sql.Date orderDate, java.sql.Date deliveryDate, double deliveryTime, String address, int zipCode, boolean takeAway, String otherRequests, ArrayList<Recipe> recipes, int[] quantity, int frequencyID) {
        ResultSet resultSet = null;
        PreparedStatement selectStatement = null;
        PreparedStatement insertStatement = null;

        String sqlCommand = "SELECT MAX(subs_id) AS s FROM Subscription;";
        String sqlCommand2 = "INSERT INTO Subscription(frequency_id, order_id) VALUES(?, ?);";
        int newNumber = 1; //If there is no other numbers before this number will be set as the id

        try {
            int orderID = addOrder(customerID, paymentStatus, orderDate, deliveryDate, deliveryTime, address, zipCode, takeAway, otherRequests, recipes, quantity);
            if (orderID <= 1) {
                return -1;
            }

            selectStatement = getConnection().prepareStatement(sqlCommand);
            resultSet = selectStatement.executeQuery();

            resultSet.next();
            newNumber = resultSet.getInt("s") + 1;
            insertStatement = getConnection().prepareStatement(sqlCommand2);
            insertStatement.setInt(1, frequencyID);
            insertStatement.setInt(2, orderID);
            insertStatement.executeUpdate();

            return newNumber;
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        } finally {
            getCleaner().closePreparedStatement(insertStatement);
            getCleaner().closePreparedStatement(selectStatement);
            getCleaner().closeResultSet(resultSet);
        }
        return -2;
    }


    /**
     * Method for deleting a subscription. It tries to delete the subscription using the checkUpdated(String sqlCommand) method
     * from a super class. Next it converts the subscriptionID to the orderID (with getOrderIDFromSubID(int subscriptionID) method)
     * to delete the order connected to the subscription (using deleteOrder(int orderID) method from OrderConnection class).
     * @param subscriptionID is the subscription ID
     * @return true if the subscription was deleted, false if the subscription or the order could not be deleted, false if orderID could not be retrieved from subscriptionID
     */

    public boolean deleteSubscription(int subscriptionID) {
        String sqlCommand = "DELETE FROM Subsciption WHERE subs_id = '" + subscriptionID + "';";
        int orderID = getOrderIDFromSubID(subscriptionID);

        if(!(checkUpdated(sqlCommand))) {       // Return false if subscription is not deleted
            return false;
        }

        if(orderID != -1) {
            if (!(deleteOrder(orderID))) {      // Return false if order is not deleted
                return false;
            }
        } else {
            return false;                       // Return false if orderID = -1 (it could not be converted from subscriptionID)
        }
        return true;
    }

    /**
     * Method for returning an ArrayList of Subscription objects using a SQL query.
     * @param sqlCommand is the sql query
     * @return ArrayList<Subscription> subscriptions
     */

    public ArrayList<Subscription> getSubscriptions(String sqlCommand) {
        ArrayList<Subscription> subscriptions = new ArrayList<Subscription>();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sqlCommand);

            while(resultSet.next()) {
                int orderID = resultSet.getInt("order_id");
                boolean paymentStatus = resultSet.getBoolean("payment_status");
                java.sql.Date orderDate = resultSet.getDate("order_date");
                java.sql.Date deliveryDate = resultSet.getDate("delivery_date");
                double deliveryTime = resultSet.getDouble("delivery_time");
                String address = resultSet.getString("address");
                int zip = resultSet.getInt("zip");
                int customerID = resultSet.getInt("customer_id");
                boolean takeAway = resultSet.getBoolean("take_away");
                String otherRequests = resultSet.getString("other_request");
                boolean made = resultSet.getBoolean("made");
                boolean ingredientsPurchased = resultSet.getBoolean("ingredients_purchased");
                int subscriptionID = resultSet.getInt("subs_id");
                int frequency = resultSet.getInt("frequency_id");
                subscriptions.add(new Subscription(orderID, customerID, paymentStatus, orderDate, deliveryDate, deliveryTime, address, zip, takeAway, otherRequests, made, ingredientsPurchased, getRecipesToOrder(orderID), subscriptionID, frequency));
            }
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        } finally {
            getCleaner().closeResultSet(resultSet);
            getCleaner().closeStatement(statement);
        }
        return subscriptions;
    }

    /**
     * Method for returning an ArrayList of all subscriptions using the getSubscriptions(sqlCommand) method.
     * @return ArrayList<Subscription>
     */

    public ArrayList<Subscription> viewAllSubscriptions() {
        String sqlCommand = "SELECT * FROM Orders NATURAL JOIN Subscription;";
        return getSubscriptions(sqlCommand);
    }

    /**
     * Method for returning an ArrayList of all subscriptions for one customer using the getSubscriptions(String sqlCommand) method.
     * @param customerID is the customer ID
     * @return ArrayList<Subscription>
     */

    public ArrayList<Subscription> viewSubscriptionsByCustomerID(int customerID) {
        String sqlCommand = "SELECT * FROM Orders NATURAL JOIN Subscription WHERE customer_id = " + customerID + ";";
        return getSubscriptions(sqlCommand);
    }

    /**
     * Method for returning a Subscription object using an subscriptionID.
     * @param subscriptionID is the subscription ID
     * @return Subscription object if the method was successful, null if the ID does not exist or an error occured
     */

    public Subscription viewSubscriptionByID(int subscriptionID) {
        String sqlCommand = "SELECT * FROM Orders NATURAL JOIN Subscription WHERE subs_id = " + subscriptionID + ";";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sqlCommand);
            while(resultSet.next()) {

                int orderID = resultSet.getInt("order_id");
                boolean paymentStatus = resultSet.getBoolean("payment_status");
                java.sql.Date orderDate = resultSet.getDate("order_date");
                java.sql.Date deliveryDate = resultSet.getDate("delivery_date");
                double deliveryTime = resultSet.getDouble("delivery_time");
                String address = resultSet.getString("address");
                int zip = resultSet.getInt("zip");
                int customerID = resultSet.getInt("customer_id");
                boolean isTakeaway = resultSet.getBoolean("takeaway");
                String otherRequests = resultSet.getString("other_request");
                boolean made = resultSet.getBoolean("made");
                boolean ingredientsPurchased = resultSet.getBoolean("ingredients_purchased");
                int frequency = resultSet.getInt("frequency_id");

                return new Subscription(orderID, customerID, paymentStatus, orderDate, deliveryDate, deliveryTime, address, zip, isTakeaway, otherRequests, made, ingredientsPurchased, getRecipesToOrder(orderID), subscriptionID, frequency);
            }

        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        } finally {
            getCleaner().closeResultSet(resultSet);
            getCleaner().closeStatement(statement);
        }
        return null;
    }

    /**
     * Method for returning a list of recipes to the subscription using the getRecipesToOrder(int OrderID) method (from the
     * OrderConnection class) and the getOrderIDFromSubID(int subscriptionID) method.
     * @param subscriptionID is the subscription ID
     * @return ArrayList<Recipe>
     */

    public ArrayList<Recipe> getRecipesToSubscription(int subscriptionID) {
        return getRecipesToOrder(getOrderIDFromSubID(subscriptionID));
    }


    /**
     * Method for changing the delivery time using the changeOrderDeliveryTime(int OrderID) method (from the
     * OrderConnection class) and the getOrderIDFromSubID(int subscriptionID) method.
     * @param subscriptionID is the subscription ID
     * @param deliveryDate is the date the order will be delivered
     * @param deliveryTime is the time of the day the order will be delivered
     * @return true if changeOrderDeliveryTime(getOrderIDFromSubID(subscriptionID), deliveryDate, deliveryTime) was successfull
     */
    public boolean changeSubscriptionDeliveryTime(int subscriptionID, java.sql.Date deliveryDate, double deliveryTime) {
        return changeOrderDeliveryTime(getOrderIDFromSubID(subscriptionID), deliveryDate, deliveryTime);
    }

    /**
     * Method for returning the subscription price using the getOrderPrice(int OrderID) method (from the
     * OrderConnection class) and the getOrderIDFromSubID(int subscriptionID) method.
     * @param subscriptionID is the subscription ID
     * @return the subscription price if the method is successfull
     */

    public double getSubscriptionPrice(int subscriptionID) {
        return getOrderPrice(getOrderIDFromSubID(subscriptionID));
    }
}