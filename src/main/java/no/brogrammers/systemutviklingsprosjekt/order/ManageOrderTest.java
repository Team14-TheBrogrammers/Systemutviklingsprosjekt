/**
 * Created by Ingunn on 10.03.2016.
 * Testing interfaces
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

    }
}
