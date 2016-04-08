package no.brogrammers.systemutviklingsprosjekt.User;

/**
 * Created by Ingunn on 10.03.2016.
 */
public class Cook extends EmployeeView {
    public Cook(int ID, String lastName, String firstName, int phoneNumber, String eMail, java.sql.Date dateOfEmployment, String username, String password) {
        super(ID, lastName, firstName, phoneNumber, eMail, dateOfEmployment, username, password);
    }
}
