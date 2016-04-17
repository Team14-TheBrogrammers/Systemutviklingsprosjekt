package no.brogrammers.systemutviklingsprosjekt;

import com.google.common.collect.ImmutableList;
import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.RecipeConnection;
import no.brogrammers.systemutviklingsprosjekt.database.connectionclasses.UserConnection;
import no.brogrammers.systemutviklingsprosjekt.database.DatabaseConnection;
import no.brogrammers.systemutviklingsprosjekt.recipe.Ingredient;
import no.brogrammers.systemutviklingsprosjekt.recipe.Instruction;
import no.brogrammers.systemutviklingsprosjekt.recipe.RecipeType;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Ingunn on 09.03.2016.
 */
public class MainClient {
    public static void main(String[] args) {


        //This code is just temporary, there will be code for starting up a GUI form with login etc. here.

        //System.out.println(Encryption.encryptPassword("asdsad"));

        //test();



    }

    private static void test() {
        DatabaseConnection databaseConnection = new UserConnection(); //databaseDriver, databaseName, errorFileLocation);

        try {
            Statement statement = databaseConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM test;");
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
