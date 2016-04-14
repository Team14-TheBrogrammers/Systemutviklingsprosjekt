package no.brogrammers.systemutviklingsprosjekt.user;

/**
 * Created by Ingunn on 10.03.2016.
 */
public class Cashier extends EmployeeChange {
    public Cashier(int ID, String lastName, String firstName, int phoneNumber, String eMail, java.sql.Date dateOfEmployment, String username, String password) {
        super(ID, lastName, firstName, phoneNumber, eMail, dateOfEmployment, username, password);
    }

    public ViewUserInterface newManageUserView() {
        return new ManageUser();
    }
}
