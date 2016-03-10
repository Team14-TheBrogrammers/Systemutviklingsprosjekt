/**
 * Created by The Brogrammers on 10.03.2016.
 */

package no.brogrammers.systemutviklingsprosjekt.UserPackage;

public abstract class User {
    private final int ID;
    private String lastName;
    private String firstName;
    private int phoneNumber;
    private String eMail;
    private int dateOfEmployment; //yyyymmdd

    public User(int ID, String lastName, String firstName, int phoneNumber, String eMail, int dateOfEmployment) {
        this.ID = ID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.eMail = eMail;
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

    public String geteMail() {
        return eMail;
    }

    public int getDateOfEmployment() {
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
        this.eMail = eMail;
    }
}
