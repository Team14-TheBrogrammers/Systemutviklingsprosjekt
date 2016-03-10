package no.brogrammers.systemutviklingsprosjekt;

/**
 * Created by Ingunn on 09.03.2016.
 */
public class MainClient {
    public static void main(String[] args) {
        DatabaseInformationReader infoReader = new DatabaseInformationReader("fileLocationHere");
        String databaseDriver = "com.mysql.jdbc.Driver";
        String databaseName = infoReader.readInformation();
                //"jdbc:mysql://mysql.stud.iie.ntnu.no:3306/";

        DatabaseConnection databaseConnection = new DatabaseConnection(databaseDriver, databaseName);

    }
}
