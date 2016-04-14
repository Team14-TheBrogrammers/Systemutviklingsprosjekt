package no.brogrammers.systemutviklingsprosjekt.customer;

/**
 * Created by Ingunn on 12.04.2016.
 */
public class PrivateCustomer extends Customer {
    private int privateID;
    private String lastName;
    private String firstName;

    public PrivateCustomer(int ID, String address, int zip, String email, int phone, int privateID, String lastName, String firstName) {
        super(ID, address, zip, email, phone);
        this.privateID = privateID;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public int getPrivateID() {
        return privateID;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setPrivateID(int privateID) {
        this.privateID = privateID;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String toString() {
        return "Private customer: \n" + super.toString() + "\nPrivate Customer ID: " + privateID + "\nName: " + lastName + ", " + firstName;
    }
}
