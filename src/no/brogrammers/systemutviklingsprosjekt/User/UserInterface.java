package no.brogrammers.systemutviklingsprosjekt.User;

/**
 * Created by Ingunn on 01.04.2016.
 */
public interface UserInterface {
    boolean regManager();
    boolean regCashier();
    boolean regCook();
    boolean regDriver();
    boolean deleteUser();
    boolean changeUser();
    User viewUser();
}
