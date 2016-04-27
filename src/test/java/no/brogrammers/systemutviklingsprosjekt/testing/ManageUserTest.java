package no.brogrammers.systemutviklingsprosjekt.testing;

import no.brogrammers.systemutviklingsprosjekt.miscellaneous.DateConverter;
import no.brogrammers.systemutviklingsprosjekt.user.ManageUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Knut on 27.04.2016.
 */
public class ManageUserTest {

    private String lastName = "Nordmann";
    private String firstName ="Ola";
    private int phone = 21345678;
    private String emailAddress = "test@test.no";
    DateConverter dateConverter = new DateConverter();
    java.sql.Date dateOfEmployment = dateConverter.stringToSqlDate("2016-04-27");
    String username = "usernametest123";
    String password = "passwordtest123";

    private ManageUser manageUser;

    @Before
    public void setUp() throws Exception {
        manageUser = new ManageUser();
    }

    @After
    public void tearDown() throws Exception {
        manageUser.stopConnection();
    }

    @Test
    public void registerManager() throws Exception {
        String result = manageUser.registerManager(lastName, firstName, phone, emailAddress, dateOfEmployment, username, password);
        String expectedResult = "A new manager was registered";
        assertEquals(expectedResult, result);
    }

    @Test
    public void registerCashier() throws Exception {

    }

    @Test
    public void registerCook() throws Exception {

    }

    @Test
    public void registerDriver() throws Exception {

    }

}