package no.brogrammers.systemutviklingsprosjekt.Database.ConnectionClasses;

import no.brogrammers.systemutviklingsprosjekt.Database.DatabaseConnection;

import no.brogrammers.systemutviklingsprosjekt.Orders.Order;

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

    public boolean addOrder(Order order) {

        return true;
    }

    /*public boolean addUser(Order order) {
        return true;
    }*/

    public ArrayList<Order> getOrders() {
        ArrayList<Order> orders  = new ArrayList<>();
        return orders;
    }

    public Order getOrder(int orderId) {
        Order order = null;
        String sqlCommand = "SELECT payment_status, delivery_date, delivery_time, address, total_price FROM orders WHERE order_id = " + orderId + ";"
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while(resultSet.next()) {

            }
        } catch (SQLException sqle) {

        } catch (Exception e) {

        }

        return new Order(0,0,false,0,0,0.0,"asd",0);
    }

    public boolean changeOrder(int orderId) {
        return true;
    }

    public boolean deleteOrder(int orderId) {
        return true;
    }

    //Legge alle metodene for ManageOrder her ??
}
