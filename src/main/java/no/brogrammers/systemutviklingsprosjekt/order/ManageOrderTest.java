/**
 * Created by Ingunn on 10.03.2016.
 */

package no.brogrammers.systemutviklingsprosjekt.order;
import no.brogrammers.systemutviklingsprosjekt.user.*;
import no.brogrammers.systemutviklingsprosjekt.miscellaneous.*;


public class ManageOrderTest {
    public static void main(String[] args) {

        String date = "2014-02-01";
        DateConverter dc = new DateConverter();
        java.sql.Date date1 = dc.stringToSqlDate(date);

        Manager man = new Manager(2, "hei", "hei", 91919191, "hehe", date1, "hei", "hei");
        OrderFactory of = new OrderFactory();

        (of.managerOrder(man)).deleteOrder(12);

        Driver dr = new Driver(2, "hei", "hei", 91919191, "hehe", dc.stringToSqlDate("2014-02-01"), "heeeee", "hesfdfsd");
        of.driverOrder(dr).deliveriesToday();
        of.managerOrder(man).deliveriesToday();
        /*
        //LocalDate localDate = new ;

        //Make new Manager
        //Employee ID, last name, first name, phone number, email, date of employment
        Manager m = new Manager(2, "hei", "hei", 91919191, "hehe", 20140201); //Date of employment: February 1, 2014
        ChangeOrderInterface manager = m.newManageOrderChange();

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
*/

    }
}
