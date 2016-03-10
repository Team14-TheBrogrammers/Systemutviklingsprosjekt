package no.brogrammers.systemutviklingsprosjekt.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by The Brogrammers on 09.03.2016.
 */
public class DatabaseConnection {

    private Connection connection;
    private Statement statement;
    private String databaseDriver;
    private String databaseName;
    private ErrorLog errorLogger;

    public DatabaseConnection(String databaseDriver, String databaseName, String errorFileLocation) {
        this.databaseDriver = databaseDriver;
        this.databaseName = databaseName;
        errorLogger = new ErrorLog(errorFileLocation);
        startConnection();
    }

    /*
    Method for starting connection and setting the objects so that they can be used in later methods.
     */
    private void startConnection() {
        try {
            Class.forName(databaseDriver);
            connection = DriverManager.getConnection(databaseName);
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            errorLogger.writeError(e.getMessage());
        } catch (SQLException sqle) {
            errorLogger.writeError(sqle.getMessage());
        } catch (Exception e) {
            errorLogger.writeError(e.getMessage());
        }

    }

    private void endConnection() {

    }
}
