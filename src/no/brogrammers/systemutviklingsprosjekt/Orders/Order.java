/**
 * Created by Ingunn on 10.03.2016.
 */

package no.brogrammers.systemutviklingsprosjekt.Orders;

public class Order {
    private int orderID;
    private int customerID;
    private boolean paymentStatus;
    private int orderDate; //Date when the order was placed:
    private int deliveryDate;
    private double deliveryTime; //From 8 to 21 (time written like: 18.45 = 18.75 and 15.30 = 15.5
    private String address;
    private int zipCode;
    //Recipes tabell??

    public Order(int orderID, int customerID, boolean paymentStatus, int orderDate, int deliveryDate, double deliveryTime, String address, int zipCode) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.paymentStatus = paymentStatus;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.deliveryTime = deliveryTime;
        this.address = address;
        this.zipCode = zipCode;
    }

    public Order(int orderID, int customerID, boolean paymentStatus, int orderDate, int deliveryDate, double deliveryTime) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.paymentStatus = paymentStatus;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.deliveryTime = deliveryTime;
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

    public int getOrderDate() {
        return orderDate;
    }

    public int getDeliveryDate() {
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



    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setDeliveryDate(int deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public double convertTime() {
        String time = Double.toString(deliveryTime).substring(2);
        return 0.0;
    }

    public String toString() {
        String pay = paymentStatus ? "Paid" : "Not paid";
        return "Order ID: " + orderID + "\nCustomer ID: " + customerID + "\nPayment status: " + pay + "\nOrder date: " + orderDate + "\nDelivery date: " + deliveryDate + "\nDelivery time: " + deliveryTime + "\nAddress, zip: " + address + ", " + zipCode;
    }
}
