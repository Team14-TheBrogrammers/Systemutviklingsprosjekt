/**
 * Created by Ingunn on 10.03.2016.
 */

package no.brogrammers.systemutviklingsprosjekt.order;

import no.brogrammers.systemutviklingsprosjekt.recipe.Recipe;

import java.util.ArrayList;

public interface ChangeOrderInterface extends ViewOrderInterface {
    //ManageOrder(String databaseDriver, String databaseName, String errorFileLocation);
    int addOrder(int customerID, boolean paymentStatus, java.sql.Date orderDate, java.sql.Date deliveryDate, double deliveryTime, String address, int zipCode, boolean takeAway, String otherRequests, ArrayList<Recipe> recipes, int[] quantity);
    ArrayList<Order> viewOrder(int customerID);
    Order viewOrderByOrderID(int orderID);
    boolean deleteOrder(int orderID);
    boolean changeOrder(int orderID);
}
