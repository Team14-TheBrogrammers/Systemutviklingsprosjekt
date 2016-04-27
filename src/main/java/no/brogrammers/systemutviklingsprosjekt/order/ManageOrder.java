/**
 * Created by Ingunn on 10.03.2016.
 * Class for order management.
 */

package no.brogrammers.systemutviklingsprosjekt.order;
import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.*;
import no.brogrammers.systemutviklingsprosjekt.recipe.Recipe;

import java.util.ArrayList;

public class ManageOrder extends OrderConnection implements ViewOrderInterface, ChangeOrderInterface  {

    public ManageOrder() {
        super();
    }

    //Add order for a spesific customer. Not later than three days in advance.
    public int addOrder(int customerID, boolean paymentStatus, java.sql.Date orderDate, java.sql.Date deliveryDate, double deliveryTime, String address, int zipCode, boolean takeAway, String otherRequests, ArrayList<Recipe> recipes, int[] quantity) {
        return super.addOrder(customerID, paymentStatus, orderDate, deliveryDate, deliveryTime, address, zipCode, takeAway, otherRequests, recipes, quantity);
    }

    //View all orders that has not been delivered yet.
    public ArrayList<Order> viewAllActiveOrders() {
        return super.viewActiveOrders();
    }

    //View orders for a spesific customer with customer ID.
    public ArrayList<Order> viewOrder(int customerID) {
        return super.viewOrdersToCustomer(customerID);
    }

    public Order viewOrderByOrderID(int orderID) {
        return super.viewOrderByID(orderID);
    }

    public boolean deleteOrder(int orderID) {
        return deleteOrder(orderID);
    }

    //Change order up to 24 hours before delivery.
    public boolean changeOrder(int orderID) {
        return changeOrder(orderID);
    }


    //All deliveries for today
    public ArrayList<Order> deliveriesToday() {
        return deliveriesToday();
    }

    public ArrayList<Order> deliveriesOnDay(double deliveryTime) {
        return deliveriesOnDay(deliveryTime);
    }
}
