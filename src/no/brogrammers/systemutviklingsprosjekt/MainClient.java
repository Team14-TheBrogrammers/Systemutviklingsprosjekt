package no.brogrammers.systemutviklingsprosjekt;

import no.brogrammers.systemutviklingsprosjekt.Database.DatabaseConnection;
import no.brogrammers.systemutviklingsprosjekt.Database.DatabaseInformationReader;

/**
 * Created by Ingunn on 09.03.2016.
 */
public class MainClient {
    public static void main(String[] args) {
        String fileLocation = "C:\\SystemutviklingsProsjekt\\databaseInformation.txt";
        DatabaseInformationReader infoReader = new DatabaseInformationReader(fileLocation);
        String databaseDriver = "com.mysql.jdbc.Driver";
        String databaseName = infoReader.readInformation();
                //"jdbc:mysql://mysql.stud.iie.ntnu.no:3306/";

        DatabaseConnection databaseConnection = new DatabaseConnection(databaseDriver, databaseName);

    }
}
