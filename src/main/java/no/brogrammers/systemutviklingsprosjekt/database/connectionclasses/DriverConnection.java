package no.brogrammers.systemutviklingsprosjekt.database.connectionclasses;

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
        return getOrders(sqlCommand);
    }

    public ArrayList<Order> deliveriesOnDay(java.sql.Date deliveryTime) {
        return null;
    }

}
