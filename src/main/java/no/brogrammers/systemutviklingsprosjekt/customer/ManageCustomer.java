package no.brogrammers.systemutviklingsprosjekt.customer;

import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.*;

import java.util.ArrayList;

/**
 * Created by Ingunn on 12.04.2016.
 */
public class ManageCustomer extends CustomerConnection implements CustomerInteface {

    public String registerPrivateCustomer(String address, int zip, String email, int phone, String lastName, String firstName) {
        int reg = super.regPrivateCustomer(address, zip, email, phone, lastName, firstName);
        switch(reg) {
            case 1:
                return "Private customer was registered";
            case -1:
                return "Phone number already registered";
            default:
                return "Private customer was not registered";
        }
    }

    public String registerCompany(String address, int zip, String email, int phone, String name) {
        int reg = super.regCompany(address, zip, email, phone, name);
        switch(reg) {
            case 1:
                return "Company was registered";
            case -1:
                return "Phone number already registered";
            default:
                return "Company was not registered";
        }
    }


    public String changeAddressAndZip(int customerID, String address, int zip) {
        int change = super.updateCustomerAddressAndZip(customerID, address, zip);
        switch(change) {
            case 1:
                return "Address was changed to " + address + ", " + zip;
            case -1:
                return "Customer does not exist";
            case -3:
                return "The zip code does not exist in Trondheim";
            default:
                return "Address was not changed";
        }
    }

    public String changeAddress(int customerID, String address) {
        int change = super.updateCustomerAddress(customerID, address);
        switch(change) {
            case 1:
                return "Address was changed to " + address;
            case -1:
                return "Customer does not exist";
            default:
                return "Address was not changed";
        }
    }

    public ArrayList<Customer> viewAllCustomers() {
        return super.viewAllCustomers();
    }

    public Customer viewCustomer(int ID) {
        return super.viewCustomer(ID);
    }

}
