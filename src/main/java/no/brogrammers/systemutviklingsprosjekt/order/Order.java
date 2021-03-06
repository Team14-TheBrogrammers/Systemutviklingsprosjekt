/**
 * Created by Ingunn on 10.03.2016.
 * Order class
 */

package no.brogrammers.systemutviklingsprosjekt.order;
import no.brogrammers.systemutviklingsprosjekt.recipe.*;

import java.util.ArrayList;

public class Order {
    private int orderID;
    private int customerID;
    private boolean paymentStatus;
    private java.sql.Date orderDate;    // Date when the order was placed
    private java.sql.Date deliveryDate;
    private double deliveryTime;        //From 7 to 21 (time written like: 15.5 = 15:30)
    private String address;
    private int zipCode;
    private boolean takeAway;
    private String otherRequests;
    private boolean made;
    private boolean ingredientsPurchased;
    private boolean delivered;
    private ArrayList<Recipe> recipes = new ArrayList<Recipe>();

    public Order(int orderID, int customerID, boolean paymentStatus, java.sql.Date orderDate, java.sql.Date deliveryDate, double deliveryTime, String address, int zipCode, boolean takeAway, String otherRequests, boolean made, boolean ingredientsPurchased, boolean delivered, ArrayList<Recipe> recipes) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.paymentStatus = paymentStatus;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.deliveryTime = deliveryTime;
        this.address = address;
        this.zipCode = zipCode;
        this.takeAway = takeAway;
        this.otherRequests = otherRequests;
        this.made = made;
        this.ingredientsPurchased = ingredientsPurchased;
        this.delivered = delivered;
        this.recipes = recipes;
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public int getOrderID() {
        return orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public java.sql.Date getOrderDate() {
        return orderDate;
    }

    public java.sql.Date getDeliveryDate() {
        return deliveryDate;
    }

    public double getDeliveryTime() {
        return deliveryTime;
    }

    public String getAddress() {
        return address;
    }

    public int getZipCode() {
        return zipCode;
    }

    public boolean isTakeAway() {
        return takeAway;
    }

    public String getOtherRequests() {
        return otherRequests;
    }

    public boolean isMade() {
        return made;
    }

    public boolean isIngredientsPurchased() {
        return ingredientsPurchased;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setDeliveryDate(java.sql.Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setDeliveryTime(double deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public void setTakeAway(boolean takeAway) {
        this.takeAway = takeAway;
    }

    public void setOtherRequests(String otherRequests) {
        this.otherRequests = otherRequests;
    }

    public void setMade(boolean made) {
        this.made = made;
    }

    public void setIngredientsPurchased(boolean ingredientsPurchased) {
        this.ingredientsPurchased = ingredientsPurchased;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public String toString() {
        String orderList = "";
        for(Recipe rec : recipes) {
            orderList += rec.toString() + "\n";
        }
        String pay = paymentStatus ? "Paid" : "Not paid";
        String ta = takeAway ? "Take away" : "Delivery";
        String md = made ? "Made" : "Not Made";
        String ip = ingredientsPurchased ? "Purchased" : "Not Purchased";
        String del = delivered ? "Delivered" : "Not delivered";
        return "Order ID: " + orderID + "\nCustomer ID: " + customerID + "\nPayment status: " + pay + "\nOrder date: " + orderDate + "\nDelivery date: " + deliveryDate + "\nDelivery time: " + deliveryTime + "\nAddress, zip: " + address + ", " + zipCode + "\n" + ta + "\nOther requests: " + otherRequests + "\nMade: " + made + "\nIngredients Purchased: " + ip + "\nDelivered: " + del + "\nOrder: \n" + orderList;
    }
}
