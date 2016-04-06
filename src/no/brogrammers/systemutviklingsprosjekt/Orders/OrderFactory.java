package no.brogrammers.systemutviklingsprosjekt.Orders;
import no.brogrammers.systemutviklingsprosjekt.User.*;

/**
 * Created by Ingunn on 01.04.2016.
 */

public class OrderFactory {

    public ChangeOrderInterface managerOrder(Manager manager) {
        ChangeOrderInterface manager2 = manager.newManageOrderChange();
        return manager2;
    }

    public ChangeOrderInterface cashierOrder(Cashier cashier) {
        ChangeOrderInterface cashier = ca.newManageOrderChange();
    }
    //Make new Manager
    //Employee ID, last name, first name, phone number, email, date of employment


    //Make new Cashier
    Cashier ca = new Cashier(4, "C", "C", 92929292, "cashier@email.com", 20101010);
    ChangeOrderInterface cashier = ca.newManageOrderChange();

    //Make new Cook
    Cook co = new Cook(3, "", "", 93939393, "cook@email.com", 20160311);
    ViewOrderInterface cook = co.newManageOrderView();

    //Make new Driver
    Driver d = new Driver(1, "", "hei", 90909090, "hehe", 20041011);
    ViewOrderInterface driver = d.newManageOrderView();


    driver.deleteOrder(1);

    System.out.println(manager.deliveriesToday());
    System.out.println(manager.deleteOrder(1));



    System.out.println(driver.deliveriesToday());


    //voi.deleteOrder(1);

    //deliveriesToday;

    //ChangeOrderInterface eci1 = new Manager(1, "hei", "hei", 123, "hehe", 12423543);
    //ManageOrder mo = new ManageOrder();
}
