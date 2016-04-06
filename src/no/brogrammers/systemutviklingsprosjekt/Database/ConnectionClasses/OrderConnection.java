package no.brogrammers.systemutviklingsprosjekt.Database.ConnectionClasses;

import no.brogrammers.systemutviklingsprosjekt.Database.DatabaseConnection;

import no.brogrammers.systemutviklingsprosjekt.Orders.Ingredient;
import no.brogrammers.systemutviklingsprosjekt.Orders.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
            boolean ok = false;
            ResultSet resultSet;
            PreparedStatement selectStatement;
            PreparedStatement insertStatement;

            String selectCommand = "SELECT MAX(order_id) AS count FROM Orders;";
            String insertCommand = "INSERT INTO Orders(delivery_date, delivery_time, adress, total_price, customer_id)\n" +
                    "VALUES(?, ?, ?, ?, ?);";

            while(!ok) {
                try {
                    selectStatement = getConnection().prepareStatement(selectCommand);
                    resultSet = selectStatement.executeQuery();

                    resultSet.next();
                    newNumber = resultSet.getInt("count") + 1;
                    insertStatement = getConnection().prepareStatement(insertCommand);
                    insertStatement.setInt(1, order.getDeliveryDate());
                    insertStatement.setDouble(2, order.getDeliveryTime());
                    insertStatement.setString(3, order.getAddress());
                    insertStatement.setDouble(4, order.getPrice());
                    insertStatement.setInt(5, order.getCustomerID());
                    insertStatement.executeUpdate();
                    ok = true;
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
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while(resultSet.next()) {
                return true;
            }
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        }
        return false;
    }

    public boolean changeOrderDeliveryTime(int orderID, java.sql.Date deliveryDate, double deliveryTime) {
        String sqlCommand = "UPDATE Orders SET delivery_date = " + deliveryDate + ", delivery_time = " + deliveryTime + " WHERE order_id = " + orderID + ";";
        try {
            Statement statement = getConnection().createStatement();
            if(statement.executeUpdate(sqlCommand) != 0) {
                return true;
            }
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        }
        return false;
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
        String sqlCommand = "DELETE FROM `Orders` WHERE order_id = 2;";
        try {
            Statement statement = getConnection().createStatement();
            if(statement.executeUpdate(sqlCommand) != 0) {
                return true;
            }
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        }
        return false;
    }

    public ArrayList<Order> getOrders() {
        ArrayList<Order> orders  = new ArrayList<>();
        return orders;
    }

    public ArrayList<Order> viewActiveOrders() {

    }

    public ArrayList<Order> viewOrdersToCustomer(int customerID) {

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
