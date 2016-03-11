/**
 * Created by Ingunn on 10.03.2016.
 */

package no.brogrammers.systemutviklingsprosjekt.Orders;

import java.util.ArrayList;

public class ManageOrder implements ViewOrderInterface, ChangeOrderInterface {


    //Add order for a spesific customer. Not later than three days in advance.
    public boolean addOrder(Order order) {
        int count;
        "SELECT ID from PrivateCustomer WHERE id = '" + order.getCustomerID() + "'";
        if(count > 0) {//Sjekk om customerID finnes og om leveringstid er innenfor riktig intervall
            if(order.get)
            //Inkrementer ordreID (antall ordre + 1)
            return true
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
    public ArrayList<Ingredient> listMissingIngredents() {
        return null;
    }

    //All deliveries for today
    public ArrayList<Order> deliveriesToday() {
        return null;
    }

    //Spesific time: 0 = "8to12", 1 = "12to15", 2 = "15to18", 3 = "18to21"
    public ArrayList<Order> deliveriesOnDay(int deliveryTime) {

        return null;
    }

}
