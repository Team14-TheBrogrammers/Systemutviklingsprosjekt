package no.brogrammers.systemutviklingsprosjekt.converter;

/**
 * Created by Ingunn on 16.04.2016.
 */
public class CheckTimeDifference {

    public int checkDifferenceInDays(java.sql.Date orderDate, java.sql.Date deliveryDate) {
        long days = (deliveryDate.getTime()-orderDate.getTime())/86400000;
        return (int) days;
    }
}
