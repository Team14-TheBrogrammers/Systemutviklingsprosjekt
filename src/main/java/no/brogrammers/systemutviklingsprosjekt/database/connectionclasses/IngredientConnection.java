package no.brogrammers.systemutviklingsprosjekt.database.connectionclasses;

import com.teamdev.jxbrowser.chromium.internal.ipc.message.EvaluateXPathMessage;
import no.brogrammers.systemutviklingsprosjekt.database.DatabaseConnection;
import no.brogrammers.systemutviklingsprosjekt.recipe.Ingredient;

import java.sql.PreparedStatement;
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

    public String viewMeasurement(String ingredientName) {
        String sqlCommand = "SELECT measurement FROM Stock WHERE ingredient_name = ?;";

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = getConnection().prepareStatement(sqlCommand);
            preparedStatement.setString(1, ingredientName);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                return resultSet.getString("measurement");
            }
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        } finally {
            getCleaner().closeResultSet(resultSet);
            getCleaner().closePreparedStatement(preparedStatement);
        }
        return null;
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
     * @return 1 if a ingredient is added to the database. -1 or -2 if something wrong happened.
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

    public int changeStock(ArrayList<Ingredient> ingredients) {
        for(int i = 0; i < ingredients.size(); i++) {
            String quantityTmp = getIngredientQuantity(ingredients.get(i).getIngredientName());
            double quantity;
            if(!quantityTmp.equals("Error")) {
                quantity = Double.parseDouble(quantityTmp);
                double currentIngredientQuantity = ingredients.get(i).getQuantity();
                double newQuantity = -1.0;
                //Check if quantity is positive or negative:
                if(currentIngredientQuantity < 0) {
                    //is negative:
                    newQuantity = quantity + Math.abs(currentIngredientQuantity);
                } else if (currentIngredientQuantity > 0) {
                    //is positive:
                    newQuantity = quantity - Math.abs(currentIngredientQuantity);
                }

                if(newQuantity != -1.0) {
                    changeQuantity(ingredients.get(i).getIngredientName(), newQuantity);
                }
            } else {
                return -1;
            }
        }
        return 1;
    }

    private String getIngredientQuantity(String ingredientName) {//TODO:fix later
        String sqlCommand = "SELECT quantity FROM Stock WHERE ingredient_name = '" + ingredientName + "';";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sqlCommand);
            while(resultSet.next()) {
                return "" + resultSet.getDouble("quantity");
            }
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        } finally {
            getCleaner().closeResultSet(resultSet);
            getCleaner().closeStatement(statement);
        }
        return "Error";
    }

    public int changeQuantity(String ingredientName, double newQuantity) {
        String sqlCommand = "UPDATE Stock SET quantity = ? WHERE ingredient_name = ?;";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = getConnection().prepareStatement(sqlCommand);
            preparedStatement.setDouble(1, newQuantity);
            preparedStatement.setString(2, ingredientName);
            if(preparedStatement.executeUpdate() != 0) {
                return 1;
            }

        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        } finally {
            getCleaner().closePreparedStatement(preparedStatement);
        }
        return -1;
    }

    public int changeMeasurement(String ingredientName, String newMeasurement) {
        String sqlCommand = "UPDATE Stock SET measurement = ? WHERE ingredient_name = ?;";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = getConnection().prepareStatement(sqlCommand);
            preparedStatement.setString(1, newMeasurement);
            preparedStatement.setString(2, ingredientName);
            if(preparedStatement.executeUpdate() != 0) {
                return 1;
            }

        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        } finally {
            getCleaner().closePreparedStatement(preparedStatement);
        }
        return -1;
    }
}
