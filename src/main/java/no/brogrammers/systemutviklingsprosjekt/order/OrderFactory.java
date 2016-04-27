package no.brogrammers.systemutviklingsprosjekt.order;
import no.brogrammers.systemutviklingsprosjekt.user.*;

/**
 * Created by Ingunn on 01.04.2016.
 * OrderFactory for easier use of interfaces
 */

public class OrderFactory {

    public ChangeOrderInterface managerOrder(Manager manager) {
        ChangeOrderInterface manager2 = manager.newManageOrderChange();
        return manager2;
    }

    public ChangeOrderInterface cashierOrder(Cashier cashier) {
        return cashier.newManageOrderChange();
    }
    //Make new Manager
    //Employee ID, last name, first name, phone number, email, date of employment

    public ViewOrderInterface cookOrder(Cook cook) {
        return cook.newManageOrderView();
    }

    public ViewOrderInterface driverOrder(Driver driver) {
        return driver.newManageOrderView();
    }

}
