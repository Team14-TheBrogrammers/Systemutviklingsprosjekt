package no.brogrammers.systemutviklingsprosjekt.testing;

import org.junit.runners.Suite;
import org.junit.runner.RunWith;

/**
 * Created by Knut on 27.04.2016.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ManageUserTest.class, LoginConnectionTest.class, OrderTest.class, CheckTimeDifferenceTest.class, DateConverterTest.class})
public class TestSuite {

}
