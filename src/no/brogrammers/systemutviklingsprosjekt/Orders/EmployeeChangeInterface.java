/**
 * Created by Ingunn on 10.03.2016.
 */

package no.brogrammers.systemutviklingsprosjekt.Orders;

import java.util.ArrayList;

public interface EmployeeChangeInterface extends EmployeeViewInterface{
    boolean addOrder(Order order);
    ArrayList<Order> viewOrder(int customerID);
    Order viewOrderByOrderID(int orderID);
    boolean deleteOrder(int orderID);
    boolean changeOrder(int orderID);
}
