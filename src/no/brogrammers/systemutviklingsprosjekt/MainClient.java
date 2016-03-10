package no.brogrammers.systemutviklingsprosjekt;

import no.brogrammers.systemutviklingsprosjekt.Database.DatabaseConnection;
import no.brogrammers.systemutviklingsprosjekt.Database.DatabaseInformationReader;

/**
 * Created by Ingunn on 09.03.2016.
 */
public class MainClient {
    public static void main(String[] args) {
        //Reading database inforamtion
        String fileLocation = "C:\\SystemutviklingsProsjekt\\databaseInformation.txt";
        DatabaseInformationReader infoReader = new DatabaseInformationReader(fileLocation);

        String databaseDriver = "com.mysql.jdbc.Driver";
        String databaseName = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/ingunsu?user=ingunsu&password=knrdoB4w";//infoReader.readInformation();
                //"jdbc:mysql://mysql.stud.iie.ntnu.no:3306/";

        String errorFileLocation = "C:\\SystemutviklingsProsjekt\\errorLog.txt";

        DatabaseConnection databaseConnection = new DatabaseConnection(databaseDriver, databaseName, errorFileLocation);
        databaseConnection.endConnection();
    }
}
