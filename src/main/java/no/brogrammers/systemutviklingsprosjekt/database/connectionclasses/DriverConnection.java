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
        return getOrders(sqlCommand);
    }

    public ArrayList<Order> deliveriesOnDay(java.sql.Date deliveryTime) {
        return null;
    }

}
