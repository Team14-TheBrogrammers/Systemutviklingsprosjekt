/**
 * Created by The Brogrammers on 10.03.2016.
 * Klasse user exists for better overview.
 */

package no.brogrammers.systemutviklingsprosjekt.user;

public abstract class User {
    private final int ID;
    private String lastName;
    private String firstName;
    private int phoneNumber;
    private String email;
    private java.sql.Date dateOfEmployment; //yyyy-mm-dd
    private String username;
    private String password;

    public User(int ID, String lastName, String firstName, int phoneNumber, String email, java.sql.Date dateOfEmployment, String username, String password) {
        this.ID = ID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dateOfEmployment = dateOfEmployment;
        this.username = username;
        this.password = password;
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

    public java.sql.Date getDateOfEmployment() {
        return dateOfEmployment;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "user ID: " + ID + "\nName: " + lastName + ", " + firstName + "\nPhone Number: " + phoneNumber + "\nEmail: " + email + "\nDate of Employment: " + dateOfEmployment;
    }
}
