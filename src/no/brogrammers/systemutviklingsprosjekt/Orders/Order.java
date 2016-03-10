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
    private int deliveryTime; //From 8 to 21 (time written like: 18.45 = 18.75 and 15.30 = 15.5
    private String address;
    private int zipCode;

    public Order(int orderID, int customerID, boolean paymentStatus, int orderDate, int deliveryDate, String address, int zipCode) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.paymentStatus = paymentStatus;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.address = address;
        this.zipCode = zipCode;
    }

    public Order(int orderID, int customerID, boolean paymentStatus, int orderDate, int deliveryDate) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.paymentStatus = paymentStatus;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
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

    public void setAddress(String address) {
        this.address = address;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
}
