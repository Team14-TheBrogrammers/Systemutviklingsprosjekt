package no.brogrammers.systemutviklingsprosjekt.order;

import no.brogrammers.systemutviklingsprosjekt.recipe.Recipe;

import java.util.ArrayList;

/**
 * Created by Ingunn on 22.04.2016.
 */
public class Subscription extends Order {
    private int subscriptionID;
    private int frequency;

    public Subscription(int orderID, int customerID, boolean paymentStatus, java.sql.Date orderDate, java.sql.Date deliveryDate, double deliveryTime, String address, int zipCode, boolean takeaway, String otherRequests, ArrayList<Recipe> recipes, int subscriptionID, int frequency) {
        super(orderID, customerID, paymentStatus, orderDate, deliveryDate, deliveryTime, address, zipCode, takeaway, otherRequests, recipes);
        this.subscriptionID = subscriptionID;
        this.frequency = frequency;
    }

    public int getSubscriptionID() {
        return subscriptionID;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setSubscriptionID(int subscriptionID) {
        this.subscriptionID = subscriptionID;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return super.toString() + "\nSubscription ID: " + subscriptionID + "\nFrequency: " + frequency;
    }
}
