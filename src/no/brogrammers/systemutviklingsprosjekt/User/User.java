/**
 * Created by The Brogrammers on 10.03.2016.
 * Klasse User exists for better overview.
 */

package no.brogrammers.systemutviklingsprosjekt.User;

public abstract class User {
    private final int ID;
    private String lastName;
    private String firstName;
    private int phoneNumber;
    private String email;
    private String dateOfEmployment; //yyyy-mm-dd

    public User(int ID, String lastName, String firstName, int phoneNumber, String email, String dateOfEmployment) {
        this.ID = ID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dateOfEmployment = dateOfEmployment;
    }

    public int getID() {
        return ID;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void seteMail(String eMail) {
        this.email = eMail;
    }

    public String toString() {
        return "User ID: " + ID + "\nName: " + lastName + ", " + firstName + "\nPhone Number: " + phoneNumber + "\nEmail: " + email + "\nDate of Employment: " + dateOfEmployment;
    }
}
