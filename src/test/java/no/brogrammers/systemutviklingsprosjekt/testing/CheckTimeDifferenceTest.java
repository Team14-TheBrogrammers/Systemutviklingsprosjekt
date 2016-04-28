package no.brogrammers.systemutviklingsprosjekt.testing;

import no.brogrammers.systemutviklingsprosjekt.miscellaneous.CheckTimeDifference;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Created by Knut on 27.04.2016.
 */
public class CheckTimeDifferenceTest {

    private java.sql.Date dateFrom = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    private java.sql.Date dateTo = new java.sql.Date(Calendar.getInstance().getTime().getTime());

    private java.sql.Date dateFrom2 = java.sql.Date.valueOf("2016-04-24");
    private java.sql.Date dateTo2 = java.sql.Date.valueOf("2016-04-27");
    private CheckTimeDifference checkTimeDifference = new CheckTimeDifference();

    @Test
    public void testCheckDifferenceInDays() throws Exception {
        int expectedResut = 0;
        int result = checkTimeDifference.checkDifferenceInDays(dateFrom, dateTo);
        assertEquals(expectedResut, result);
    }

    @Test
    public void testCheckDifference() throws Exception {
        int expectedResult = 3;
        int result = checkTimeDifference.checkDifferenceInDays(dateFrom2, dateTo2);
        assertEquals(expectedResult, result);
    }
}