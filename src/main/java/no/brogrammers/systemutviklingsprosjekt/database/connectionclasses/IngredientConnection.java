package no.brogrammers.systemutviklingsprosjekt.database.connectionclasses;

import no.brogrammers.systemutviklingsprosjekt.database.DatabaseConnection;
import no.brogrammers.systemutviklingsprosjekt.recipe.Ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Knut on 24.04.2016.
 */
public class IngredientConnection extends DatabaseConnection {
    //viewAllIngredients()
    //add
    //delete
    //++ og -- quantity
    //set quantity(int quantity)
    //makeOrder (cook remove ingredient(s))

    public ArrayList<Ingredient> viewAllIngredients() {
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        String sqlCommand = "SELECT ingredient_name, quantity FROM Ingredient NATURAL JOIN Stock;";

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sqlCommand);

            while(resultSet.next()) {
                String name = resultSet.getString("recipe_name");
                int quantity = resultSet.getInt("quantity");
                //String measurement = resultSet.getString("measurement");

            }
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {

        } finally {

        }
        return ingredients;
    }

    public ArrayList<String> viewAllMeasurements() {
        ArrayList<String> measurements = new ArrayList<String>();
        String sqlCommand = "SELECT measurement FROM Ingredient NATURAL JOIN Stock;";

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sqlCommand);

            while(resultSet.next()) {
                String measurement = resultSet.getString("measurement");
                measurements.add(measurement);
            }
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        } finally {
            //TODO: // FIXME: 24.04.2016
        }
        return measurements;
    }

    public int addIngredient() {
       return 0;
    }


}
