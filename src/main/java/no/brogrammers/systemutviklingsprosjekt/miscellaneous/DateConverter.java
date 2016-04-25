package no.brogrammers.systemutviklingsprosjekt.miscellaneous;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Ingunn on 07.04.2016.
 */
public class DateConverter {//TODO:static??

    public java.sql.Date stringToSqlDate(String date) { //Striormang ft: yyyy-MM-dd
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//TODO: does not work?
        java.util.Date utilDate = null;
        try {
            utilDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        java.util.Calendar cal = Calendar.getInstance();
        cal.setTime(utilDate);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        java.sql.Date sqlDate = new java.sql.Date(cal.getTime().getTime());

        return sqlDate;
    }

    public java.sql.Date utilDateToSqlDate(java.util.Date date) {
        java.util.Calendar cal = Calendar.getInstance();

        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        java.sql.Date sqlDate = new java.sql.Date(cal.getTime().getTime());

        return sqlDate;
    }
}
