package no.brogrammers.systemutviklingsprosjekt.testing;

import no.brogrammers.systemutviklingsprosjekt.user.ManageUser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Knut on 15.04.2016.
 */
public class ManageUserTest {

    ManageUser manageUser = new ManageUser();

    //MangagerVariabler under her:
    private final String lastName = ""; //LAST_NAME = "";
    private final String firstName = ""; //FIRST_NAME = "";
    private final int phoneAddress = 123; //PHONE_ADDRESS = 123;
    private final String emailAddress = ""; //EMAIL_ADDRESS = "";


    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void registerManager() throws Exception {
        //manageUser.registerManager(lastName, firstName, phoneAddress, emailAddress, )
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

    @Test
    public void deleteUserByID() throws Exception {
        manageUser.deleteUserByID(1000);
        manageUser.deleteUserByID(10);
        manageUser.deleteUserByID(1);
        manageUser.deleteUserByID(-100);
    }

    @Test
    public void changeLastNameByID() throws Exception {

    }

    @Test
    public void changeFirstNameByID() throws Exception {

    }

    @Test
    public void changePhoneByID() throws Exception {

    }

    @Test
    public void changeMailByID() throws Exception {

    }

    @Test
    public void changeUsernameByID() throws Exception {

    }

    @Test
    public void changePasswordByID() throws Exception {

    }

    @Test
    public void viewUser() throws Exception {

    }

    @Test
    public void viewUser1() throws Exception {

    }

    @Test
    public void viewAllUsers() throws Exception {

    }

}