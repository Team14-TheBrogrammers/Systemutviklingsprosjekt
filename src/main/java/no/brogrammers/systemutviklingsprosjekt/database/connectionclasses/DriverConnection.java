package no.brogrammers.systemutviklingsprosjekt.database.connectionclasses;

import com.sun.org.apache.xpath.internal.operations.Or;
import no.brogrammers.systemutviklingsprosjekt.order.Order;

import java.util.ArrayList;

/**
 * Created by Knut on 20.04.2016.
 */
public class DriverConnection extends OrderConnection {//TODO: abstract?

    public ArrayList<Order> deliveriesToday() {
        //java.sql.Date now = new Date(Calendar.getInstance().getTimeInMillis());
        //LocalDate localDate = LocalDate.now();
        String sqlCommand = "SELECT * FROM Orders WHERE delivery_date = CURDATE();";// + now + ";";
        return null;//getOrders(sqlCommand);
    }

    public ArrayList<Order> deliveriesTomorrow() {
        String sqlCommand = "SELECT * FROM Orders WHERE delivery_date = CURDATE() + INTERVAL 1 DAY;";

        return null;
    }

    public ArrayList<Order> deliveriesThisWeek() { //TODO: test delivery_date >= curdate()???
        String sqlCommand = "SELECT * FROM Orders WHERE WEEK(delivery_date) = WEEK(CURDATE()) AND YEAR(delivery_date) = YEAR(CURDATE()) AND delivery_date >= CURDATE();";
        return null;
    }

    public ArrayList<Order> deliveriesOnDay(java.sql.Date deliveryDate) {
        String sqlCommand = "SELECT * FROM Orders WHERE delivery_date = '" + deliveryDate + "';";
        return null;
    }
    
}