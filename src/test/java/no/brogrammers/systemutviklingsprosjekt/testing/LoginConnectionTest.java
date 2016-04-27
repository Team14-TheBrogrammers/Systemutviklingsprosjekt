package no.brogrammers.systemutviklingsprosjekt.testing;

import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.LoginConnection;
import no.brogrammers.systemutviklingsprosjekt.miscellaneous.DateConverter;
import no.brogrammers.systemutviklingsprosjekt.user.ManageUser;
import no.brogrammers.systemutviklingsprosjekt.user.Manager;
import no.brogrammers.systemutviklingsprosjekt.user.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Knut on 27.04.2016.
 */
public class LoginConnectionTest {

    private LoginConnection loginConnection;
    private String username = "ingunnsu";
    private String password = "ingunnIzc00l";

    @Before
    public void setUp() throws Exception {
        loginConnection = new LoginConnection();
    }

    @After
    public void tearDown() throws Exception {
        loginConnection.stopConnection();
    }

    @Test
    public void checkLoginDetails() throws Exception {
        User user = loginConnection.checkLoginDetails(username, password);
        DateConverter dateConverter = new DateConverter();
        Manager expectedResult = new Manager(1, "Sund", "Ingunn", 98765432, "ingunnsu@yahoo.no", dateConverter.stringToSqlDate("2015-03-04"), "ingunnsu", "ingunnIzc00l");
        assertEquals(user, expectedResult);
    }

    @Test
    public void checkWrongLoginDetails() throws Exception {

    }

}