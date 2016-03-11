package no.brogrammers.systemutviklingsprosjekt;

import no.brogrammers.systemutviklingsprosjekt.Database.DatabaseConnection;
import no.brogrammers.systemutviklingsprosjekt.Database.DatabaseInformationReader;

import java.sql.ResultSet;

/**
 * Created by Ingunn on 09.03.2016.
 */
public class MainClient {
    public static void main(String[] args) {
        //Reading database inforamtion
        String fileLocation = "C:\\SystemutviklingsProsjekt\\databaseInformation.txt";
        DatabaseInformationReader infoReader = new DatabaseInformationReader(fileLocation);

        String databaseDriver = "com.mysql.jdbc.Driver";
        String databaseName = infoReader.readInformation();
                //"jdbc:mysql://mysql.stud.iie.ntnu.no:3306/";
        String errorFileLocation = "C:\\SystemutviklingsProsjekt\\errorLog.txt";

        DatabaseConnection databaseConnection = new DatabaseConnection(databaseDriver, databaseName, errorFileLocation);

        try {
            ResultSet resultSet = databaseConnection.getStatement().executeQuery("SELECT * FROM test;");
            while(resultSet.next()) {
                String name = resultSet.getString("navn");
                System.out.println(name);
            }
        } catch (Exception e) {
            System.out.println("Error occoured.");
            System.out.println(e.getMessage());
        }

        databaseConnection.stopConnection();
    }
}
