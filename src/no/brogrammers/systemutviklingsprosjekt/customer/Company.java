package no.brogrammers.systemutviklingsprosjekt.customer;

/**
 * Created by Ingunn on 12.04.2016.
 */
public class Company extends Customer {
    private int companyID;
    private String name;

    public Company(int ID, String address, int zip, String email, int phone, int companyID, String name) {
        super(ID, address, zip, email, phone);
        this.companyID = companyID;
        this.name = name;
    }

    public int getCompanyID() {
        return companyID;
    }

    public String getName() {
        return name;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return super.toString() + "\nCompany ID: " + companyID + "\nCompany name: " + name;
    }
}
