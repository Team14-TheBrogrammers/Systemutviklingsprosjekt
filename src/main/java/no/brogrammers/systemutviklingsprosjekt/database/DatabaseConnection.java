package no.brogrammers.systemutviklingsprosjekt.database;

import java.io.*;
import java.sql.*;
import java.util.Calendar;

/**
 * Created by The Brogrammers on 09.03.2016.
 * This class is used for setting up a connection to the database. It locates local variables with database driver,
 * information and error log file and database inforamtion file.
 * This class is used to make more conneciton classes by extending it into sub classes.
 */
public abstract class DatabaseConnection {

    private Connection connection;

    private final String databaseDriver = "com.mysql.jdbc.Driver";
    private String databaseName; // = readInformation();
    private final String errorLocation = "C:\\SystemutviklingsProsjekt\\errorLog.txt";
    private final String informationLocation = "C:\\SystemutviklingsProsjekt\\databaseInformation.txt";

    private File errorFile;
    private File informationFile;

    private ConnectionCleaner cleaner = new ConnectionCleaner();

    public DatabaseConnection() {
        //Set location for local files
        errorFile = new File(errorLocation);
        informationFile = new File(informationLocation);

        databaseName = readInformation(); //Read information that is locally stored on the computer
        startConnection(); //Start connection to the database
    }

    /**
     * Method for starting connection and setting the objects so that they can be used in later methods.
     */
    private void startConnection() {
        try {
            Class.forName(databaseDriver);
            connection = DriverManager.getConnection(databaseName);
            //statement = connection.createStatement();
        } catch (ClassNotFoundException cnfe) {
            //e.printStackTrace();
            writeError(cnfe.getMessage());
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        }
    }

    /**
     * Read the information about the login details for the database.
     * Information includes database website, name, username and password for the connection.
     * @return a string contining the information for login details.
     */
    private String readInformation() {
        String information = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(informationFile));
            information = reader.readLine();
        } catch (FileNotFoundException e) {
            writeError(e.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        }
        return information;
    }

    /**
     * Do write a message in a log file, so the user can later view errors.
     * @param message the error message as a string to write in the file.
     * @return true if the method did write the message in the file. False if something wrong occoured.
     */
    public boolean writeError(String message) {
        try {
            if(message == null) {
                System.out.println("Message was null");
                return false;
            }
            FileWriter fileWriter = new FileWriter(errorFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("[" + Calendar.getInstance().getTime() + "] " + message);
            bufferedWriter.newLine();
            bufferedWriter.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Method for closing the connection properly.
     */
    public void stopConnection() {
        cleaner.closeConnection(connection);
    }

    /**
     * Method for checking if a sql statement does any update to the database.
     * @param sqlCommand The string that contains the sql statement that is used in this method.
     * @return true if there is any rows updated in the database. Otherwise false.
     */

    public boolean checkUpdated(String sqlCommand) {
        Statement statement = null;

        try {
            statement = getConnection().createStatement();
            if(statement.executeUpdate(sqlCommand) != 0) {
                return true;
            }
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        } finally {
            cleaner.closeStatement(statement);
        }
        return false;
    }

    /**
     * Method for checking if any rows is returned in a ResultSet with a sql query.
     * @param sqlCommand is the sql query that is used in this method.
     * @return true if the ResultSet returns any rows. Else false.
     */

    public boolean checkExists(String sqlCommand) {
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sqlCommand);
            while(resultSet.next()) {
                return true;
            }
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        } finally {
            cleaner.closeResultSet(resultSet);
            cleaner.closeStatement(statement);
        }
        return false;
    }

    /**
     * Method for returning the Connection object so one can easily access the connection in sub-classes.
     * @return the Connetiojn object.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Method for returning the ConnectionCleaner object so one can easily access the cleaner object in sub-classes.
     * @return the Connection Cleaner object.
     */
    public ConnectionCleaner getCleaner() {
        return cleaner;
    }
}