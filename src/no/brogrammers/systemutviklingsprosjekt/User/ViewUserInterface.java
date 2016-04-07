package no.brogrammers.systemutviklingsprosjekt.User;

import java.util.ArrayList;

/**
 * Created by Ingunn on 07.04.2016.
 */
public interface ViewUserInterface {
    User viewUser(int id);
    User viewUser(String lastName);
    ArrayList<User> viewAllUsers();
}
