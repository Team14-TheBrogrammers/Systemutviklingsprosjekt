package no.brogrammers.systemutviklingsprosjekt.database.connectionclasses;

import no.brogrammers.systemutviklingsprosjekt.database.DatabaseConnection;
import no.brogrammers.systemutviklingsprosjekt.order.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Calendar;

/**
 * Created by Knut on 20.04.2016.
 */
public class DriverConnection extends OrderConnection {

    public ArrayList<Order> deliveriesToday() {
        //java.sql.Date now = new Date(Calendar.getInstance().getTimeInMillis());
        //LocalDate localDate = LocalDate.now();
        String sqlCommand = "SELECT * FROM Orders WHERE delivery_date = CURDATE();";// + now + ";";
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<Order> orders = new ArrayList<Order>();

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

    public ArrayList<Order> deliveriesOnDay(java.sql.Date deliveryTime) {
        return null;
    }

}
