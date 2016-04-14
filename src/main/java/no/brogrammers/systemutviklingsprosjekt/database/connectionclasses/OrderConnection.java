package no.brogrammers.systemutviklingsprosjekt.database.connectionclasses;

import no.brogrammers.systemutviklingsprosjekt.database.DatabaseConnection;

import no.brogrammers.systemutviklingsprosjekt.order.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Knut on 11.03.2016.
 */
public class OrderConnection extends DatabaseConnection {
    public OrderConnection() {
        super();
    }

    public int addOrder(Order order) {
        if(checkCustomerId(order.getCustomerID())) {
            int newNumber = 1; //If there is no other numbers before this number will be set as the id
            boolean finished = false;
            ResultSet resultSet;
            PreparedStatement selectStatement;
            PreparedStatement insertStatement;

            String selectCommand = "SELECT MAX(order_id) AS c FROM Orders;";
            String insertCommand = "INSERT INTO Orders(delivery_date, delivery_time, adress, total_price, customer_id)\n" +
                    "VALUES(?, ?, ?, ?, ?);";

            while(!finished) {
                try {
                    selectStatement = getConnection().prepareStatement(selectCommand);
                    resultSet = selectStatement.executeQuery();

                    resultSet.next();
                    newNumber = resultSet.getInt("c") + 1;
                    insertStatement = getConnection().prepareStatement(insertCommand);
                    insertStatement.setInt(1, order.getDeliveryDate());
                    insertStatement.setDouble(2, order.getDeliveryTime());
                    insertStatement.setString(3, order.getAddress());
                    insertStatement.setDouble(4, order.getPrice());
                    insertStatement.setInt(5, order.getCustomerID());
                    insertStatement.executeUpdate();
                    finished = true;
                } catch (SQLException sqle) {
                    writeError(sqle.getMessage());
                } catch (Exception e) {
                    writeError(e.getMessage());
                }
            }
            return newNumber;
        }
        return -1;
    }

    private boolean checkCustomerId(int id) {
        String sqlCommand = "SELECT * FROM Customer WHERE customer_id = " + id + ";";
        return checkExists(sqlCommand);
    }

    public boolean changeOrderDeliveryTime(int orderID, java.sql.Date deliveryDate, double deliveryTime) {
        String sqlCommand = "UPDATE order SET delivery_date = " + deliveryDate + ", delivery_time = " + deliveryTime + " WHERE order_id = " + orderID + ";";
        return checkUpdated(sqlCommand);
    }

    public Order viewOrderByID(int orderID) {
        Order order = null;
        String sqlCommand = "SELECT payment_status, delivery_date, delivery_time, address, total_price FROM orders WHERE order_id = " + orderID + ";";
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while(resultSet.next()) {

            }
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        }

        return new Order(0,0,false,0,0,0.0,"asd",0);
    }

    public boolean deleteOrder(int id) {
        String sqlCommand = "DELETE FROM order WHERE order_id = " + id + ";";
        return checkUpdated(sqlCommand);
    }

    public ArrayList<Order> getOrders() {
        ArrayList<Order> orders  = new ArrayList<Order>();
        String sqlCommand = "SELECT * FROM Orders;";
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while(resultSet.next()) {
                int id = resultSet.getInt("order_id");
                int customerId = resultSet.getInt("customer_id");

                orders.add(new Order(id, customerId, ));
            }
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        }
        return orders;
    }

    public ArrayList<Order> viewActiveOrders() {
        ArrayList<Order> orders  = new ArrayList<Order>();
        String sqlCommand = "SELECT * FROM Orders;";
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while(resultSet.next()) {
                if(resultSet.getDate("delivery_date").getTime() - new Date(Calendar.getInstance().getTimeInMillis()).getTime()) {
                    int id = resultSet.getInt("order_id");
                    int customerId = resultSet.getInt("customer_id");
                    boolean paymentStatus = resultSet.getBoolean("payment_status");
                    int orderDate = resultSet.getInt("order_date");
                    int deliveryDate = resultSet.getInt("delivery_date");
                    int deliveryTime = resultSet.getInt("");

                    orders.add(new Order(id, customerId, paymentStatus, orderDate, deliveryDate, deliveryTime, adress, ));
                }

                if(resultSet.getInt("delivery_date") - new Date(Calendar.getInstance().getTimeInMillis())) {

                }

            }
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        }
        return orders;
    }

    public ArrayList<Order> viewOrdersToCustomer(int customerID) {
        ArrayList<Order> orders = new ArrayList<>();
        String sqlOrder = "SELECT * FROM Customer NATURAL JOIN Orders WHERE customer_id = " + customerID + ";";
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sqlOrder);
            while (resultSet.next()) {
                int orderID = resultSet.getInt("order_id");
                boolean paymentStatus = resultSet.getBoolean("payment_status");
                java.sql.Date orderDate = resultSet.getDate("order_date");
                java.sql.Date deliveryDate = resultSet.getDate("delivery_date");
                double deliveryTime = resultSet.getDouble("delivery_time");
                String address = resultSet.getString("address");
                int zip = resultSet.getInt("zip");

                ArrayList<Recipe> recipes = new ArrayList<>();
                String sqlRecipe = "SELECT * FROM Recipe NATURAL JOIN Order_recipe WHERE Order_recipe.order_id = " + orderID + ";";
                Statement statement1 = getConnection().createStatement();
                ResultSet resultSet1 = statement1.executeQuery(sqlRecipe);
                while (resultSet1.next()) {
                    String recipeName  = resultSet1.getString("recipe_name");
                    double price = resultSet1.getDouble("price");
                    orders.add(new Recipe(recipeName, price));
                }


                orders.add(new Order(orderID, customerID, paymentStatus, orderDate, deliveryDate, deliveryTime, address, zip, recipes));
            }
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        }
        return orders;
    }

    public ArrayList<Ingredient> listMissingIngredients() {
        return null;
    }

    public ArrayList<Order> deliveriesToday() {
        return null;
    }

    public ArrayList<Order> deliveriesOnDay(int deliveryTime) {
        return null;
    }

    //Legge alle metodene for ManageOrder her ??
}
