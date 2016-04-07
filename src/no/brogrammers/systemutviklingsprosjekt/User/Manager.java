
/**
 * Created by Ingunn on 10.03.2016.
 */

package no.brogrammers.systemutviklingsprosjekt.User;

import no.brogrammers.systemutviklingsprosjekt.Orders.ChangeOrderInterface;
import no.brogrammers.systemutviklingsprosjekt.Orders.ManageOrder;

public class Manager extends EmployeeChange {
    public Manager(int ID, String lastName, String firstName, int phoneNumber, String eMail, String dateOfEmployment) {
        super(ID, lastName, firstName, phoneNumber, eMail, dateOfEmployment);
    }

    public int test() {
        return 0;
    }

    public UserInterface newManageUser() {
        return new ManageUser();
    }

}
