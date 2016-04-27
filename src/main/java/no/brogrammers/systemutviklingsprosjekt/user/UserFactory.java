/**
 * Created by Ingunn on 01.04.2016.
 * Class for easier use of interfaces
 */

package no.brogrammers.systemutviklingsprosjekt.user;

public class UserFactory {

    public ChangeUserInterface managerUser(Manager manager) {
        return manager.newManageUserChange();
    }

    public ViewUserInterface cashierUser(Cashier cashier) {
        return cashier.newManageUserView();
    }

    public ViewUserInterface cookUser(Cook cook) {
        return cook.newManageUserView();
    }

    public ViewUserInterface driverUser(Driver driver) {
        return driver.newManageUserView();
    }

}
