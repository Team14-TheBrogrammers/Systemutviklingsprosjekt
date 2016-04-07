package no.brogrammers.systemutviklingsprosjekt.User;

import no.brogrammers.systemutviklingsprosjekt.Orders.*;

/**
 * Created by Ingunn on 10.03.2016.
 */
public abstract class EmployeeView extends User {
    public EmployeeView(int ID, String lastName, String firstName, int phoneNumber, String eMail, java.sql.Date dateOfEmployment, String username, String password) {
        super(ID, lastName, firstName, phoneNumber, eMail, dateOfEmployment, username, password);
    }

    public ViewOrderInterface newManageOrderView() {
        return new ManageOrder();
    }

    public ViewUserInterface newManageUserView() {
        return new ManageUser();
    }
}