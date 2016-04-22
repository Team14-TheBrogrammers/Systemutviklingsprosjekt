/**
 * Created by Ingunn on 10.03.2016.
 */

package no.brogrammers.systemutviklingsprosjekt.order;

import java.util.ArrayList;

public interface ViewOrderInterface {
    ArrayList<Order> viewAllActiveOrders();
    //ArrayList<Ingredient> listMissingIngredents();
    ArrayList<Order> deliveriesToday();
    ArrayList<Order> deliveriesOnDay(double deliveryTime);
}
