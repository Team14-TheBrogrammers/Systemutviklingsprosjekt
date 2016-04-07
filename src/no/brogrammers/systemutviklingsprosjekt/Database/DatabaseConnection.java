package no.brogrammers.systemutviklingsprosjekt.Database;

import java.io.*;
import java.sql.*;

/**
 * Created by The Brogrammers on 09.03.2016.
 */
public abstract class DatabaseConnection {

    private Connection connection;
    //private Statement statement; //skal vi bruke PreparedStatement her isteden og heller legge det inn i hver enkelt metode.

    private final String databaseDriver = "com.mysql.jdbc.Driver";
    private String databaseName; // = readInformation();
    private final String errorLocation = "C:\\SystemutviklingsProsjekt\\errorLog.txt";
    private final String informationLocation = "C:\\SystemutviklingsProsjekt\\databaseInformation.txt";

    private File errorFile;
    private File informationFile;

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
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            writeError(e.getMessage());
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
            FileWriter fileWriter = new FileWriter(errorFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(message);
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
    public /*abstract*/ void stopConnection() {
        try {
            //statement.close();
            connection.close();
        } catch (SQLException e) {
            writeError(e.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        }
    }

    public boolean checkUpdated(String sqlCommand) {
        try {
            Statement statement = getConnection().createStatement();
            if(statement.executeUpdate(sqlCommand) != 0) {
                return true;
            }
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        }
        return false;
    }

    public boolean checkExists(String sqlCommand) {
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while(resultSet.next()) {
                return true;
            }
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        }
        return false;
    }

    public Connection getConnection() {
        return connection;
    }

    //Unødvendig under her???

    /*public Statement getStatement() {
        return statement;
    }*/

    //UPDATE - INSERT

    //DELETE (DROP?)

    //SELECT
}