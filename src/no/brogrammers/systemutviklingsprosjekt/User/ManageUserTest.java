package no.brogrammers.systemutviklingsprosjekt.user;

import no.brogrammers.systemutviklingsprosjekt.Converter.*;

/**
 * Created by Ingunn on 07.04.2016.
 */
public class ManageUserTest {
    public static void main(String[] args) {


        DateConverter dc = new DateConverter();

        Manager man = new Manager(2, "hei", "hei", 91919191, "hehe", dc.stringToSqlDate("2014-02-01"), "hei", "hei");

        UserFactory uf = new UserFactory();
        uf.managerUser(man).viewAllUsers();

        Driver dri = new Driver(2, "hei", "hei", 91919191, "hehe", dc.stringToSqlDate("2014-02-01"), "hei", "hei");

        uf.driverUser(dri).viewAllUsers();
    }
}
