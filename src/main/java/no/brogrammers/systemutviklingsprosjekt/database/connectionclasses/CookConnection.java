package no.brogrammers.systemutviklingsprosjekt.database.connectionclasses;

import no.brogrammers.systemutviklingsprosjekt.database.DatabaseConnection;
import no.brogrammers.systemutviklingsprosjekt.recipe.Ingredient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Ingunn on 25.04.2016.
 */
public class CookConnection extends DatabaseConnection {

    //SELECT Stock.ingredient_name, Order_recipe.order_id, (Stock.quantity - (Recipe_ingredient.quantity*Order_recipe.quantity)) AS sum FROM Stock JOIN Recipe_ingredient ON (Stock.ingredient_name = Recipe_ingredient.ingredient_name) JOIN Order_recipe ON(Recipe_ingredient.recipe_name = Order_recipe.recipe_name) JOIN Orders ON(Order_recipe.order_id = Orders.order_id) WHERE take_away = 0 AND delivery_date >= (CURDATE() + INTERVAL 1 DAY) AND delivery_date <= (CURDATE() + INTERVAL 3 DAY) ORDER BY ingredient_name;
    public int removeIngredientsForDeliveriesToday() {
        String sqlCommand = "SELECT Stock.ingredient_name, Order_recipe.order_id, (Stock.quantity - (Recipe_ingredient.quantity*Order_recipe.quantity)) AS sum \" +\n" +
                "                \"FROM Stock JOIN Recipe_ingredient ON (Stock.ingredient_name = Recipe_ingredient.ingredient_name) \" +\n" +
                "                \"JOIN Order_recipe ON(Recipe_ingredient.recipe_name = Order_recipe.recipe_name) JOIN Orders ON(Order_recipe.order_id = Orders.order_id) \" +\n" +
                "                \"WHERE take_away = 0 AND delivery_date >= (CURDATE() + INTERVAL 1 DAY) AND delivery_date <= (CURDATE() + INTERVAL 3 DAY) ORDER BY ingredient_name;";
    }

    //SELECT Stock.ingredient_name, Order_recipe.order_id, (Stock.quantity - (Recipe_ingredient.quantity*Order_recipe.quantity)) AS sum FROM Stock JOIN Recipe_ingredient ON (Stock.ingredient_name = Recipe_ingredient.ingredient_name) JOIN Order_recipe ON(Recipe_ingredient.recipe_name = Order_recipe.recipe_name) JOIN Orders ON(Order_recipe.order_id = Orders.order_id) WHERE take_away = 0 AND delivery_date >= (CURDATE() + INTERVAL 1 DAY) AND delivery_date <= (CURDATE() + INTERVAL 3 DAY) ORDER BY ingredient_name;
    //CREATE VIEW stock_view AS (SELECT Stock.quantity, Stock.ingredient_name, Order_recipe.order_id FROM Stock JOIN Recipe_ingredient ON (Stock.ingredient_name = Recipe_ingredient.ingredient_name) JOIN Order_recipe ON(Recipe_ingredient.recipe_name = Order_recipe.recipe_name));
    public ArrayList<Ingredient> missingIngredientsTwoDaysFromTomorrow() { // For deliveries
        String sqlCommand = "SELECT Stock.ingredient_name, Order_recipe.order_id, (Stock.quantity - (Recipe_ingredient.quantity*Order_recipe.quantity)) AS sum " +
                "FROM Stock JOIN Recipe_ingredient ON (Stock.ingredient_name = Recipe_ingredient.ingredient_name) " +
                "JOIN Order_recipe ON(Recipe_ingredient.recipe_name = Order_recipe.recipe_name) JOIN Orders ON(Order_recipe.order_id = Orders.order_id) " +
                "WHERE take_away = 0 AND delivery_date >= (CURDATE() + INTERVAL 1 DAY) AND delivery_date <= (CURDATE() + INTERVAL 3 DAY) ORDER BY ingredient_name;";
        PreparedStatement selectStatement = null;
        ResultSet resultSet = null;
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

        try {
            selectStatement = getConnection().prepareStatement(sqlCommand);
            resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
                String ingredientName = resultSet.getString("ingredient_name");
                double quantity = resultSet.getDouble("sum");

                if(quantity < 0) {
                    ingredients.add(new Ingredient(ingredientName, quantity));
                }
            }
            return ingredients;
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        } finally {
            getCleaner().closePreparedStatement(selectStatement);
            getCleaner().closeResultSet(resultSet);
        }
        return ingredients;
    }

    public int buyTakeAwayIngredients() {
        double quantity = -1;
        String ingredientName = "";
        String sqlSelect = "SELECT Stock.ingredient_name, Order_recipe.order_id, (Recipe_ingredient.quantity*Order_recipe.quantity) AS sum FROM Stock JOIN Recipe_ingredient ON (Stock.ingredient_name = Recipe_ingredient.ingredient_name) JOIN Order_recipe ON(Recipe_ingredient.recipe_name = Order_recipe.recipe_name) JOIN Orders ON(Order_recipe.order_id = Orders.order_id) WHERE take_away = 1 AND delivery_date = CURDATE() ORDER BY ingredient_name;";
        String sqlUpdate = "";
        PreparedStatement selectStatement = null;
        PreparedStatement updateStatement = null;
        ResultSet resultSet = null;
        ResultSet resultSet2 = null;
        //ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

        try {
            selectStatement = getConnection().prepareStatement(sqlSelect);
            resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("ingredient_name");
                double sum = resultSet.getDouble("sum");

                sqlUpdate = "UPDATE Stock SET quantity = (quantity + " + sum + ") WHERE ingredient_name = " + name + ";";
                updateStatement = getConnection().prepareStatement(sqlUpdate);
                resultSet2 = updateStatement.executeQuery();
                getCleaner().closeResultSet(resultSet2);
                getCleaner().closePreparedStatement(updateStatement);
            }
            return 1;

        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        } finally {
            getCleaner().closePreparedStatement(selectStatement);
            getCleaner().closeResultSet(resultSet);
        }
        return -1;
    }

    /*public makeTakeAway() {

    }*/

    /*public int buyBunch() {
        String sqlCommandSelect = "SELECT quantity+100 FROM Stock;";
        String sqlCommandUpdate = "UPDATE Stock SET quantity = (quantity+1) WHERE quantity < 10000000;";


        PreparedStatement selectStatement = null;
        ResultSet resultSet = null;

        try {
            selectStatement = getConnection().prepareStatement(sqlCommandUpdate);
            resultSet = selectStatement.executeQuery();


        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        } finally {
            getCleaner().closePreparedStatement(selectStatement);
            getCleaner().closeResultSet(resultSet);
        }
    }*/

}
