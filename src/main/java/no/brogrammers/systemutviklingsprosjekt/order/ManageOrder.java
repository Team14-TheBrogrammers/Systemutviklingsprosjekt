/**
 * Created by Ingunn on 10.03.2016.
 * Class for order management.
 */

package no.brogrammers.systemutviklingsprosjekt.order;
import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.*;

import java.util.ArrayList;

public class ManageOrder extends OrderConnection implements ViewOrderInterface, ChangeOrderInterface  {

    public ManageOrder() {
        super();
    }

    //Add order for a spesific customer. Not later than three days in advance.
    public int addOrder(Order order) {
        //Order order2 = new Order();
/*
        if(super.addOrder(order) >= 0) {
            return super.addOrder(order);
        }*/
        return -1;
    }

    public boolean checkCorrectTimeStamp(java.sql.Date orderDate, java.sql.Date deliveryDate) {
        long days = (deliveryDate.getTime()-orderDate.getTime())/86400000;
        //(currentTime - (currentTime % 86400000));
        if(days >= 3) {
            return true;
        }
        return false;
    }

    //View all orders that has not been delivered yet.
    public ArrayList<Order> viewAllActiveOrders() {
        return null;
    }

    //View orders for a spesific customer with customer ID.
    public ArrayList<Order> viewOrder(int customerID) {
        return null;
        //sort by date
    }

    public Order viewOrderByOrderID(int orderID) {
        return null;
    }

    public boolean deleteOrder(int orderID) {
        return false;
    }

    //Change order up to 24 hours before delivery.
    public boolean changeOrder(int orderID) {
        return false;
    }

    //List all ingredients missing for the three next days.
    /*public ArrayList<Ingredient> listMissingIngredients() {
        return null;
    }*/

    //All deliveries for today
    public ArrayList<Order> deliveriesToday() {
        return null;
    }

    //Spesific time: 0 = "8to12", 1 = "12to15", 2 = "15to18", 3 = "18to21"
    public ArrayList<Order> deliveriesOnDay(int deliveryTime) {

        return null;
    }

}
