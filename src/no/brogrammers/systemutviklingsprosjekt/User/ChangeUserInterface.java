package no.brogrammers.systemutviklingsprosjekt.User;

/**
 * Created by Ingunn on 01.04.2016.
 */
public interface ChangeUserInterface extends ViewUserInterface{
    boolean regManager(String lastName, String firstName, int phone, String mail, java.sql.Date doe, String username, String password);
    boolean regCashier(String lastName, String firstName, int phone, String mail, java.sql.Date doe, String username, String password);
    boolean regCook(String lastName, String firstName, int phone, String mail, java.sql.Date doe, String username, String password);
    boolean regDriver(String lastName, String firstName, int phone, String mail, java.sql.Date doe, String username, String password);
    boolean deleteUser(int id);
    boolean changeLastName(int id, String lastName);
    boolean changeFirstName(int id, String firstName);
    boolean changePhone(int id, int phone);
    boolean changeMail(int id, String mail);

}
