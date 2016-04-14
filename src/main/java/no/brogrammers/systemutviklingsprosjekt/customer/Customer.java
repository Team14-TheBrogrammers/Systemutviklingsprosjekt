package no.brogrammers.systemutviklingsprosjekt.customer;

/**
 * Created by Ingunn on 12.04.2016.
 */
public abstract class Customer {
    private int ID;
    private String address;
    private int zip;
    private String email;
    private int phone;

    public Customer(int ID, String address, int zip, String email, int phone) {
        this.ID = ID;
        this.address = address;
        this.zip = zip;
        this.email = email;
        this.phone = phone;
    }

    public int getID() {
        return ID;
    }

    public String getAddress() {
        return address;
    }

    public int getZip() {
        return zip;
    }

    public String getEmail() {
        return email;
    }

    public int getPhone() {
        return phone;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String toString() {
        return "ID: " + ID + "\nAddress: " + address + "\nZip: " + zip + "\nEmail address: " + email + "\nPhone number: " + phone;
    }
}
