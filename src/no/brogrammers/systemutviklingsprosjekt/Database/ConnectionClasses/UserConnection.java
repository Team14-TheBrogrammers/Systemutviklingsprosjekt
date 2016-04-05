package no.brogrammers.systemutviklingsprosjekt.Database.ConnectionClasses;

import no.brogrammers.systemutviklingsprosjekt.Database.DatabaseConnection;
import no.brogrammers.systemutviklingsprosjekt.UserPackage.User;

import java.util.ArrayList;

/**
 * Created by Knut on 05.04.2016.
 */
public class UserConnection extends DatabaseConnection {

    public UserConnection() {
        super();
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();

        //get information from database here

        return users;
    }
}
