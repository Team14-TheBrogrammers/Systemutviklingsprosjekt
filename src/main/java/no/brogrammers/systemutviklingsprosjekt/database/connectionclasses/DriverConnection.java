package no.brogrammers.systemutviklingsprosjekt.database.connectionclasses;

import no.brogrammers.systemutviklingsprosjekt.database.DatabaseConnection;
import no.brogrammers.systemutviklingsprosjekt.order.Order;
import no.brogrammers.systemutviklingsprosjekt.recipe.Ingredient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Knut on 20.04.2016.
 */
public class DriverConnection extends OrderConnection {//TODO: abstract?

    public ArrayList<Order> deliveriesToday() {
        String sqlCommand = "SELECT * FROM Orders WHERE delivery_date = CURDATE() AND take_away = 0 ORDER BY delivery_time ASC;";// + now + ";";
        return getOrders(sqlCommand);
    }

    public ArrayList<Order> deliveriesTomorrow() {
        String sqlCommand = "SELECT * FROM Orders WHERE delivery_date = CURDATE() + INTERVAL 1 DAY AND take_away = 0;";
        return getOrders(sqlCommand);
    }

    public ArrayList<Order> deliveriesThisWeek() {
        String sqlCommand = "SELECT * FROM Orders WHERE WEEK(delivery_date) = WEEK(CURDATE()) AND YEAR(delivery_date) = YEAR(CURDATE()) AND delivery_date >= CURDATE() AND take_away = 0;";
        return getOrders(sqlCommand);
    }

    public ArrayList<Order> deliveriesOnDay(java.sql.Date deliveryDate) {
        String sqlCommand = "SELECT * FROM Orders WHERE delivery_date = '" + deliveryDate + "' AND take_away = 0;";
        return getOrders(sqlCommand);
    }


    public ArrayList<String> getAdresses(String sqlCommand) {
        ArrayList<String> addresses = new ArrayList<String>();
        PreparedStatement selectStatement = null;
        ResultSet resultSet = null;

        try {
            selectStatement = getConnection().prepareStatement(sqlCommand);
            resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
                int zip = resultSet.getInt("zip");
                String address = resultSet.getString("address");

                String zipaddress = address + ", " + zip;
                addresses.add(zipaddress);
            }
            return addresses;
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        } finally {
            getCleaner().closePreparedStatement(selectStatement);
            getCleaner().closeResultSet(resultSet);
        }
        return addresses;
    }


    public ArrayList<String> getAddressesFromToday() {
        String sqlCommand = "SELECT zip, address FROM Orders WHERE delivery_date = CURDATE() AND take_away = 0;";

        return null;
    }

    public ArrayList<ArrayList<Order>> splitDeliveriesToday() {
        ArrayList<ArrayList<Order>> allDeliveries = new ArrayList<ArrayList<Order>>();
        ArrayList<Order> orders = new ArrayList<Order>();
        ArrayList<Order> tmp = new ArrayList<Order>();
        tmp = deliveriesToday();
        for(int i = 0; i < tmp.size(); i++) {
            orders.add(tmp.get(i));
        }

        ArrayList<Order> deliveriesInterval = new ArrayList<Order>();
        if(orders.get(0) != null) {
            deliveriesInterval.add(orders.get(0));
            if (orders.size() == 1) {
                allDeliveries.add(deliveriesInterval);
            }

            double count = orders.get(0).getDeliveryTime();

            for (int i = 1; i < orders.size(); i++) {
                if ((orders.get(i).getDeliveryTime() - count) < 1.0) {
                    deliveriesInterval.add(orders.get(i));
                } else {
                    count = orders.get(i).getDeliveryTime();
                    allDeliveries.add(deliveriesInterval);
                    deliveriesInterval = new ArrayList<Order>();
                    deliveriesInterval.add(orders.get(i));
                    if(i == orders.size()-1) {
                        allDeliveries.add(deliveriesInterval);
                    }
                }
            }
        }
        return allDeliveries;
    }

    public int setDeliveriesAsDelivered(ArrayList<Order> orders) {
        return 1;
    }


    //did this in gui
    public ArrayList<ArrayList<String>> splittedAddressesAndTimeToday() {
        ArrayList<ArrayList<Order>> splittedDeliveriesToday = splitDeliveriesToday();
        ArrayList<ArrayList<String>> addresses = new ArrayList<ArrayList<String>>();
        ArrayList<String> addressList;// = new ArrayList<String>();

        for(ArrayList<Order> innerList : splittedDeliveriesToday) {
            addressList = new ArrayList<String>();
            for(Order order : innerList) {
                addressList = new ArrayList<String>();
                String orderAddress = order.getDeliveryTime() + "|" + order.getAddress() + ", " + order.getZipCode();
                addressList.add(orderAddress);
            }
            addresses.add(addressList);
        }
        return addresses;
    }




}