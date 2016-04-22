package no.brogrammers.systemutviklingsprosjekt.order;

import no.brogrammers.systemutviklingsprosjekt.recipe.Recipe;

import java.util.ArrayList;

/**
 * Created by Ingunn on 22.04.2016.
 */
public class Subscription extends Order {
    private int frequency;

    public Subscription(int orderID, int customerID, boolean paymentStatus, java.sql.Date orderDate, java.sql.Date deliveryDate, double deliveryTime, String address, int zipCode, ArrayList<Recipe> recipes, int frequency) {
        super(orderID, customerID, paymentStatus, orderDate, deliveryDate, deliveryTime, address, zipCode, recipes);
        this.frequency = frequency;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return super.toString() + "\nFrequency: " + frequency;
    }
}
