package no.brogrammers.systemutviklingsprosjekt.User;

/**
 * Created by Ingunn on 01.04.2016.
 */
public class ManageUser implements UserInterface {

    public boolean regManager() {
        //legg til i database
        return true;
    }

    public boolean regCashier() {
        return true;
    }

    public boolean regCook() {
        return true;
    }

    public boolean regDriver() {
        return true;
    }

    public boolean deleteUser() {
        return true;
    }

    public boolean changeUser() {
        return true;
    }

    public User viewUser() {
        return null;
    }
}
