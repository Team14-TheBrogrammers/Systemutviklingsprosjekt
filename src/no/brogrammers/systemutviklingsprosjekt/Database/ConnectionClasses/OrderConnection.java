package no.brogrammers.systemutviklingsprosjekt.Database.ConnectionClasses;

import no.brogrammers.systemutviklingsprosjekt.Database.DatabaseConnection;

/**
 * Created by Knut on 11.03.2016.
 */
public class OrderConnection extends DatabaseConnection {
    public OrderConnection(String databaseDriver, String databaseName, String errorFileLocation) {
        super(databaseDriver, databaseName, errorFileLocation);
    }

    public boolean addOrder(Order order) {
        return false;
    }
}
