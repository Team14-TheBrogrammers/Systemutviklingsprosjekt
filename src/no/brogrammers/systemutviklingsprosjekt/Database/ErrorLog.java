package no.brogrammers.systemutviklingsprosjekt.Database;

import java.io.*;

/**
 * Created by Knut on 10.03.2016.
 */
public class ErrorLog {

    private File file;

    public ErrorLog(String fileLocation) {
        file = new File(fileLocation);
    }


    /**
     * Do write a message in a log file, so the user can later view errors.
     * @param errorMessage the error message as a string to write in the file.
     * @return true if the method did write the message in the file. False if something wrong occoured.
     */

    public boolean writeError(String errorMessage) {
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(errorMessage);
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

    public static void main(String[] args) {
        ErrorLog errorLog = new ErrorLog("C:\\SystemutviklingsProsjekt\\errorLog.txt");
        errorLog.writeError("test123");
    }
}
