package no.brogrammers.systemutviklingsprosjekt.User;

import no.brogrammers.systemutviklingsprosjekt.Orders.*;

/**
 * Created by Ingunn on 10.03.2016.
 */
public abstract class EmployeeView extends User {
    public EmployeeView(int ID, String lastName, String firstName, int phoneNumber, String eMail, String dateOfEmployment) {
        super(ID, lastName, firstName, phoneNumber, eMail, dateOfEmployment);
    }

    public ViewOrderInterface newManageOrderView() {
        return new ManageOrder();
    }
}