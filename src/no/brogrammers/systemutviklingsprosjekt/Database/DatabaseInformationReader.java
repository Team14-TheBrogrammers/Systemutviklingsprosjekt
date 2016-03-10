package no.brogrammers.systemutviklingsprosjekt.Database;

import java.io.*;

/**
 * Created by Knut on 10.03.2016.
 */
public class DatabaseInformationReader {

    private File file;

    public DatabaseInformationReader(String fileLocation) {
        this.file = new File(fileLocation);
    }

    /* Read the information about the login details for the database.
     Information includes website, database name, username and password for the connection.
    */
    public String readInformation() {
        String information = "";
        try {
            /*FileInputStream stream = new FileInputStream(file);
            information = stream.read();*/
            BufferedReader reader = new BufferedReader(new FileReader(file));
            information = reader.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {

        }
        return information;
    }

    //Tesing if the method prints out the information to log in to the database:
    public static void main(String[] args) {
        DatabaseInformationReader databaseInformationReader = new DatabaseInformationReader("C:\\test1.txt");
        System.out.println(databaseInformationReader.readInformation());
    }
}
