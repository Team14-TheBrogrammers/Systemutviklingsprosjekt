package no.brogrammers.systemutviklingsprosjekt.testing;

import no.brogrammers.systemutviklingsprosjekt.miscellaneous.DateConverter;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Created by Knut on 27.04.2016.
 */
public class DateConverterTest {

    private DateConverter dateConverter = new DateConverter();
    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void testStringToSqlDate() throws Exception {
        java.sql.Date expectedResult = java.sql.Date.valueOf("2016-04-27");
        java.sql.Date result = dateConverter.stringToSqlDate("2016-04-27");
        assertEquals(expectedResult, result);
    }

    @Test
    public void testUtilDateToSqlDate() throws Exception {
        java.sql.Date expectedResult = dateConverter.stringToSqlDate("2016-04-27");
        java.util.Date temp = simpleDateFormat.parse("2016-04-27");
        java.sql.Date result = dateConverter.utilDateToSqlDate(temp);
        assertEquals(expectedResult, result);
    }
}