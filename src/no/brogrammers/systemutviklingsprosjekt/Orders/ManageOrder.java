/**
 * Created by Ingunn on 10.03.2016.
 */

package no.brogrammers.systemutviklingsprosjekt.Orders;

import no.brogrammers.systemutviklingsprosjekt.UserPackage.*;
import java.util.ArrayList;

public class ManageOrder {

    public ManageOrder() {

    }

    //Add order for a spesific customer. Not later than three days in advance.
    public boolean addOrder(Order order) {

    }

    //View all orders that has not been delivered yet.
    public ArrayList<Order> viewAllActiveOrders() {

    }

    //View orders for a spesific customer with customer ID.
    public ArrayList<Order> viewOrder(int customerID) {

        //sort by date
    }

    public Order viewOrderByOrderID(int orderID) {

    }

    public boolean deleteOrder(int orderID) {

    }

    //Change order up to 24 hours before delivery.
    public boolean changeOrder(int orderID) {

    }

    //List all ingredients missing for the three next days.
    public ArrayList<Ingredient> listMissingIngredents() {

    }

    //All deliveries for today
    public ArrayList<Order> deliveriesToday() {

    }

    //Spesific time: 0 = "8to12", 1 = "12to15", 2 = "15to18", 3 = "18to21"
    public ArrayList<Order> deliveriesOnDay(int deliveryTime) {

    }

}
