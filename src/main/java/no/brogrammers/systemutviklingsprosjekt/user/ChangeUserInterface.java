package no.brogrammers.systemutviklingsprosjekt.user;

/**
 * Created by Ingunn on 01.04.2016.
 */
public interface ChangeUserInterface extends ViewUserInterface{
    String registerManager(String lastName, String firstName, int phone, String mail, java.sql.Date doe, String username, String password);
    String registerCashier(String lastName, String firstName, int phone, String mail, java.sql.Date doe, String username, String password);
    String registerCook(String lastName, String firstName, int phone, String mail, java.sql.Date doe, String username, String password);
    String registerDriver(String lastName, String firstName, int phone, String mail, java.sql.Date doe, String username, String password);
    String deleteUserByID(int id);
    String changeLastNameByID(int id, String lastName);
    String changeFirstNameByID(int id, String firstName);
    String changePhoneByID(int id, int phone);
    String changeMailByID(int id, String mail);

}
