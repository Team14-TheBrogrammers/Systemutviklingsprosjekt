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

    /**
     * Method for view all ingredients in an ArrayList with Ingredients objects.
     * @return an ArrayList of Ingredient objects. It is a empty list if there is no Ingredients availible in the database.
     */
    public ArrayList<Ingredient> viewAllIngredients() {
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        String sqlCommand = "SELECT ingredient_name, quantity FROM Ingredient NATURAL JOIN Stock;";

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sqlCommand);

            while(resultSet.next()) {
                String name = resultSet.getString("ingredient_name");
                double quantity = resultSet.getInt("quantity");
                ingredients.add(new Ingredient(name, quantity));
            }
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        } finally {
            getCleaner().closeResultSet(resultSet);
            getCleaner().closeStatement(statement);
        }
        return ingredients;
    }

    /**
     * Method for getting all measurements for all ingredients.
     * @return a ArrayList of String objects which contain the measurement for the current ingredient.
     */
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
            getCleaner().closeResultSet(resultSet);
            getCleaner().closeStatement(statement);
        }
        return measurements;
    }

    /**
     * Method for adding a ingredient to the database with quantity and measurement.
     * @param ingredientName is the name of the ingredient.
     * @param quantity is the quantity of the ingredient in stock.
     * @param measurement is the measurement of the ingredient.
     * @return
     */
    public int addIngredient(String ingredientName, double quantity, String measurement) {
        String sqlIngredientsCommand = "INSERT INTO Ingredient(ingredient_name) VALUES ('" + ingredientName + "');";
        String sqlStockCommand = "INSERT INTO Stock(ingredient_name, quantity, measurement) VALUES ('" + ingredientName + "', " + quantity + ", '" + measurement + "');";
        if(checkUpdated(sqlIngredientsCommand)) {
            if(checkUpdated(sqlStockCommand)) { //If successfully added to the stock table.
                return 1;
            } else {
                return -1;
            }
        }
        return -2;
    }


}
