package no.brogrammers.systemutviklingsprosjekt.Database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Knut on 10.03.2016.
 */
public class ErrorLog {

    private File file;

    public ErrorLog(String fileLocation) {
        file = new File(fileLocation);
    }

    public boolean writeError(String errorMessage) {
        String errorString = "\n" + errorMessage; //Add a new line to the log file, but it does not work
        try {
            FileOutputStream outputStream = new FileOutputStream(file, true);
            outputStream.write(String.valueOf(errorString).getBytes());
            outputStream.close();
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
