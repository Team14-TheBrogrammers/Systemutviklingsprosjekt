
/**
 * Created by Ingunn on 10.03.2016.
 */

package no.brogrammers.systemutviklingsprosjekt.user;

public class Manager extends EmployeeChange {
    public Manager(int ID, String lastName, String firstName, int phoneNumber, String eMail, java.sql.Date dateOfEmployment, String username, String password) {
        super(ID, lastName, firstName, phoneNumber, eMail, dateOfEmployment, username, password);
    }

    public int test() {
        return 0;
    }

    public ChangeUserInterface newManageUserChange() {
        return new ManageUser();
    }
}
