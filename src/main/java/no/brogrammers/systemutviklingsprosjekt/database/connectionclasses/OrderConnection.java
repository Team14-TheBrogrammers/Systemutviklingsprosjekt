package no.brogrammers.systemutviklingsprosjekt.database.connectionclasses;

import no.brogrammers.systemutviklingsprosjekt.database.DatabaseConnection;

import no.brogrammers.systemutviklingsprosjekt.order.Order;
import no.brogrammers.systemutviklingsprosjekt.recipe.Ingredient;
import no.brogrammers.systemutviklingsprosjekt.recipe.Recipe;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Knut on 11.03.2016.
 */
public abstract class OrderConnection extends DatabaseConnection {
    public OrderConnection() {
        super();
    }

    private boolean checkCorrectOrderDates(java.sql.Date deliveryDate, double deliveryTime) {
        if(deliveryTime <= 21 && deliveryTime >= 7) {
            long MILLISECONDS_IN_DAY = 24 * 60 * 60 * 1000;
            int days = (int) ((deliveryDate.getTime() - new Date(Calendar.getInstance().getTimeInMillis()).getTime()) / MILLISECONDS_IN_DAY);
            if(days >= 3) {
                System.out.println(days);
                return true;
            }
        }
        return false;
    }

    /**
     * This method is for adding a new order to the database.
     * @param customerID
     * @param paymentStatus
     * @param orderDate
     * @param deliveryDate
     * @param deliveryTime
     * @param address
     * @param zipCode
     * @param recipes
     * @return
     */
    public int addOrder(int customerID, boolean paymentStatus, java.sql.Date orderDate, java.sql.Date deliveryDate, double deliveryTime, String address, int zipCode, ArrayList<Recipe> recipes, int[] quantity) { // TODO: clean connection properly and do fix this code
        if(checkCorrectOrderDates(deliveryDate, deliveryTime)) {
            if(checkCustomerId(customerID)) {
                int newNumber = 1; //If there is no other numbers before this number will be set as the id
                boolean finished = false;
                ResultSet resultSet = null;
                PreparedStatement selectStatement = null;
                PreparedStatement insertStatement = null;

                String selectCommand = "SELECT MAX(order_id) AS c FROM Orders;";
                String insertCommand = "INSERT INTO Orders(payment_status, order_date, delivery_date, delivery_time, address, zip, customer_id)\n" +
                        "VALUES(?, ?, ?, ?, ?, ?, ?);";

                while(!finished) {
                    try {
                        selectStatement = getConnection().prepareStatement(selectCommand);
                        resultSet = selectStatement.executeQuery();

                        resultSet.next();
                        newNumber = resultSet.getInt("c") + 1;
                        insertStatement = getConnection().prepareStatement(insertCommand);
                        insertStatement.setBoolean(1, paymentStatus);
                        insertStatement.setDate(2, orderDate);
                        insertStatement.setDate(3, deliveryDate);
                        insertStatement.setDouble(4, deliveryTime);
                        insertStatement.setString(5, address);
                        insertStatement.setInt(5, zipCode);
                        insertStatement.setInt(5, customerID);

                        for(int i = 0; i < recipes.size(); i++) {
                            String sqlCommand = "INSERT INTO Order_recipe(order_id, recipe_name, quantity) VALUES(" + newNumber + ", " + recipes.get(i).getRecipeName() + ", " + quantity[i] + ");";
                            checkUpdated(sqlCommand);
                        }

                        insertStatement.executeUpdate();
                        finished = true;
                    } catch (SQLException sqle) {
                        writeError(sqle.getMessage());
                    } catch (Exception e) {
                        writeError(e.getMessage());
                    } finally {
                        getCleaner().closePreparedStatement(insertStatement);
                        getCleaner().closePreparedStatement(selectStatement);
                        getCleaner().closeResultSet(resultSet);
                    }
                }
                return newNumber;
            } else {
                return -1;
            }
        }
        return -2;
    }

    /** //TODO: FIX THIS documentationSHIT
     * Method for generating price for a order. This method does a query for all recipes that match an order.
     * @param orderID is the ID for the order.
     * @return -1.0 if something wrong happend. etc
     */
    private double getOrderPrice(int orderID) {
        String sqlCommand = "SELECT * FROM Order_recipe NATURAL JOIN Recipe WHERE order_id = " + orderID + ";";
        double totalPrice = -1.0;

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sqlCommand);

            while (resultSet.next()) {
                totalPrice += (resultSet.getInt("quantity") * resultSet.getDouble("price"));
            }
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        } finally {
            getCleaner().closeResultSet(resultSet);
            getCleaner().closeStatement(statement);
        }
        return totalPrice;
    }

    private boolean checkCustomerId(int id) {
        String sqlCommand = "SELECT * FROM Customer WHERE customer_id = " + id + ";";
        return checkExists(sqlCommand);
    }

    public boolean changeOrderDeliveryTime(int orderID, java.sql.Date deliveryDate, double deliveryTime) {
        String sqlCommand = "UPDATE order SET delivery_date = " + deliveryDate + ", delivery_time = " + deliveryTime + " WHERE order_id = " + orderID + ";";
        return checkUpdated(sqlCommand);
    }

    public ArrayList<Recipe> getRecipesToOrder(int orderID) {
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        String sqlCommand = "SELECT * FROM Order_recipe NATURAL JOIN Recipe WHERE order_id = " + orderID + ";";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sqlCommand);
            while (resultSet.next()) {
                String name = resultSet.getString("recipe_name");
                String type = resultSet.getString("recipe_type");
                double price = resultSet.getDouble("price");
                //recipes.add(new Recipe(name, type, price)); //TODO: FIX
            }
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        } finally {
            getCleaner().closeResultSet(resultSet);
            getCleaner().closeStatement(statement);
        }
        return recipes;
    }

    public Order viewOrderByID(int orderID) {
        //String sqlCommand = "SELECT payment_status, delivery_date, delivery_time, address, total_price FROM orders WHERE order_id = " + orderID + ";";

        String sqlCommand = "SELECT * FROM Orders WHERE order_id = " + orderID + ";";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sqlCommand);
            while(resultSet.next()) {

                boolean paymentStatus = resultSet.getBoolean("payment_status");
                java.sql.Date orderDate = resultSet.getDate("order_date");
                java.sql.Date deliveryDate = resultSet.getDate("delivery_date");
                double deliveryTime = resultSet.getDouble("delivery_time");
                String address = resultSet.getString("address");
                int zip = resultSet.getInt("zip");
                int customerID = resultSet.getInt("customer_id");

                return new Order(orderID, customerID, paymentStatus, orderDate, deliveryDate, deliveryTime, address, zip, getRecipesToOrder(orderID));
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

    public boolean deleteOrder(int id) {
        String sqlCommand = "DELETE FROM order WHERE order_id = " + id + ";";
        return checkUpdated(sqlCommand);
    }

    public ArrayList<Order> getOrders(String sqlCommand) {
        ArrayList<Order> orders  = new ArrayList<Order>();
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
                orders.add(new Order(orderID, customerID, paymentStatus, orderDate, deliveryDate, deliveryTime, address, zip, getRecipesToOrder(orderID)));
            }

        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        } finally {
            getCleaner().closeResultSet(resultSet);
            getCleaner().closeStatement(statement);
        }
        return orders;
    }

    public ArrayList<Order> viewPreviousOrders() {//viewAllOrders() {
        String sqlCommand = "SELECT * FROM Orders WHERE delivery_date < CURDATE();";//"SELECT * FROM Orders;";
        return getOrders(sqlCommand);
    }

    public ArrayList<Order> viewOrdersToCustomer(int customerID) {
        ArrayList<Order> orders = new ArrayList<Order>();
        String sqlOrder = "SELECT * FROM Customer NATURAL JOIN Orders WHERE customer_id = " + customerID + ";";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sqlOrder);
            while (resultSet.next()) {
                int orderID = resultSet.getInt("order_id");
                boolean paymentStatus = resultSet.getBoolean("payment_status");
                java.sql.Date orderDate = resultSet.getDate("order_date");
                java.sql.Date deliveryDate = resultSet.getDate("delivery_date");
                double deliveryTime = resultSet.getDouble("delivery_time");
                String address = resultSet.getString("address");
                int zip = resultSet.getInt("zip");

                orders.add(new Order(orderID, customerID, paymentStatus, orderDate, deliveryDate, deliveryTime, address, zip, getRecipesToOrder(orderID)));
            }
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        } finally {
            getCleaner().closeResultSet(resultSet);
            getCleaner().closeStatement(statement);
        }
        return orders;
    }

    //TODO: FIX THIS METHOD
    public ArrayList<Order> viewActiveOrders() {
        String sqlCommand = "SELECT * FROM Orders WHERE delivery_date >= CURDATE();";
        return getOrders(sqlCommand);
    }
        /*ArrayList<Order> orders  = new ArrayList<Order>();
        String sqlCommand = "SELECT * FROM Orders;";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sqlCommand);
            while(resultSet.next()) {
                if(resultSet.getDate("delivery_date").getTime() - new Date(Calendar.getInstance().getTimeInMillis()).getTime()) {
                    int orderID = resultSet.getInt("order_id");
                    int customerID = resultSet.getInt("customer_id");
                    boolean paymentStatus = resultSet.getBoolean("payment_status");
                    java.sql.Date orderDate = resultSet.getDate("order_date");
                    java.sql.Date deliveryDate = resultSet.getDate("delivery_date");
                    double deliveryTime = resultSet.getDouble("delivery_time");
                    String address = resultSet.getString("address");
                    int zip = resultSet.getInt("zip");

                    orders.add(new Order(orderID, customerID, paymentStatus, orderDate, deliveryDate, deliveryTime, address, zip, getRecipesToOrder(orderID)));
                }

                if(resultSet.getInt("delivery_date") - new Date(Calendar.getInstance().getTimeInMillis())) {

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
        return orders;
    }*/

    public ArrayList<Ingredient> listMissingIngredients() {
        return null;
    }


    //Legge alle metodene for ManageOrder her ??
}
