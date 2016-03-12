
/**
 * Created by Ingunn on 10.03.2016.
 */

package no.brogrammers.systemutviklingsprosjekt.UserPackage;

public class Manager extends EmployeeChange {
    public Manager(int ID, String lastName, String firstName, int phoneNumber, String eMail, int dateOfEmployment) {
        super(ID, lastName, firstName, phoneNumber, eMail, dateOfEmployment);
    }

    public int test() {
        return 0;
    }

    //ChangeOrderInterface ee = new ManageOrder();
}
