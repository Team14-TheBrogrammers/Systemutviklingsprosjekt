package no.brogrammers.systemutviklingsprosjekt.database.connectionclasses;

import no.brogrammers.systemutviklingsprosjekt.database.DatabaseConnection;
import no.brogrammers.systemutviklingsprosjekt.order.ManageOrder;
import no.brogrammers.systemutviklingsprosjekt.order.Order;
import no.brogrammers.systemutviklingsprosjekt.recipe.Ingredient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Ingunn on 25.04.2016.
 */
public class CookConnection extends IngredientConnection {


    //SELECT Stock.ingredient_name, Order_recipe.order_id, (Stock.quantity - (Recipe_ingredient.quantity*Order_recipe.quantity)) AS sum FROM Stock JOIN Recipe_ingredient ON (Stock.ingredient_name = Recipe_ingredient.ingredient_name) JOIN Order_recipe ON(Recipe_ingredient.recipe_name = Order_recipe.recipe_name) JOIN Orders ON(Order_recipe.order_id = Orders.order_id) WHERE take_away = 0 AND delivery_date >= (CURDATE() + INTERVAL 1 DAY) AND delivery_date <= (CURDATE() + INTERVAL 3 DAY) AND Orders.ingredients_purchased = 0 ORDER BY ingredient_name;    //CREATE VIEW stock_view AS (SELECT Stock.quantity, Stock.ingredient_name, Order_recipe.order_id FROM Stock JOIN Recipe_ingredient ON (Stock.ingredient_name = Recipe_ingredient.ingredient_name) JOIN Order_recipe ON(Recipe_ingredient.recipe_name = Order_recipe.recipe_name));
    public ArrayList<Ingredient> missingIngredientsTwoDaysFromTomorrow() { // For deliveries
        String sqlCommand = "SELECT Stock.ingredient_name, Order_recipe.order_id, (Stock.quantity - (Recipe_ingredient.quantity*Order_recipe.quantity)) AS sum " +
                "FROM Stock JOIN Recipe_ingredient ON (Stock.ingredient_name = Recipe_ingredient.ingredient_name) " +
                "JOIN Order_recipe ON(Recipe_ingredient.recipe_name = Order_recipe.recipe_name) JOIN Orders ON(Order_recipe.order_id = Orders.order_id) " +
                "WHERE take_away = 0 AND delivery_date >= (CURDATE() + INTERVAL 1 DAY) AND delivery_date <= (CURDATE() + INTERVAL 3 DAY) AND Orders.ingredients_purchased = 0 ORDER BY ingredient_name;";
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

    public int buyIngredientsTwoDaysFromTomorrow() {
        String sqlUpdate = "";
        String sqlSelect = "SELECT Stock.ingredient_name, Order_recipe.order_id, (Stock.quantity - (Recipe_ingredient.quantity*Order_recipe.quantity)) AS sum FROM Stock JOIN Recipe_ingredient ON (Stock.ingredient_name = Recipe_ingredient.ingredient_name) JOIN Order_recipe ON(Recipe_ingredient.recipe_name = Order_recipe.recipe_name) JOIN Orders ON(Order_recipe.order_id = Orders.order_id) WHERE take_away = 0 AND delivery_date >= (CURDATE() + INTERVAL 1 DAY) AND delivery_date <= (CURDATE() + INTERVAL 3 DAY) AND Orders.ingredients_purchased = 0 ORDER BY ingredient_name;    //CREATE VIEW stock_view AS (SELECT Stock.quantity, Stock.ingredient_name, Order_recipe.order_id FROM Stock JOIN Recipe_ingredient ON (Stock.ingredient_name = Recipe_ingredient.ingredient_name) JOIN Order_recipe ON(Recipe_ingredient.recipe_name = Order_recipe.recipe_name));";

        PreparedStatement selectStatement = null;
        PreparedStatement updateStatement = null;
        ResultSet resultSet = null;
        ArrayList<Integer> id = new ArrayList<Integer>();

        try {
            selectStatement = getConnection().prepareStatement(sqlSelect);
            resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
                int orderID = resultSet.getInt("order_id");
                double quantity = resultSet.getDouble("sum");

                if(quantity < 0) {
                    id.add(orderID);
                }
            }

            if(changeStock(missingIngredientsTwoDaysFromTomorrow()) == 1) {// && orderID != -1) {
                for (int i = 0; i < id.size(); i++) {
                    sqlUpdate = "UPDATE Orders SET ingredients_purchased = " + 1 + " WHERE order_id = " + i + ";";
                    updateStatement = getConnection().prepareStatement(sqlUpdate);

                    if(!(checkUpdated(sqlUpdate))) {
                        return -1;
                    }
                    getCleaner().closePreparedStatement(updateStatement);
                }
            } else {
                return -3;
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
        return -2;
    }


    public int buyAllTakeAwayIngredientsForToday() {
        double quantity = -1;
        String ingredientName = "";
        String sqlSelect = "SELECT Stock.ingredient_name, Order_recipe.order_id, (Recipe_ingredient.quantity*Order_recipe.quantity) AS sum FROM Stock JOIN Recipe_ingredient ON (Stock.ingredient_name = Recipe_ingredient.ingredient_name) JOIN Order_recipe ON(Recipe_ingredient.recipe_name = Order_recipe.recipe_name) JOIN Orders ON(Order_recipe.order_id = Orders.order_id) WHERE take_away = 1 AND delivery_date = CURDATE() ORDER BY ingredient_name;";
        String sqlUpdate = "";
        PreparedStatement selectStatement = null;
        PreparedStatement updateStatement = null;
        ResultSet resultSet = null;
        //ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

        try {
            selectStatement = getConnection().prepareStatement(sqlSelect);
            resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("ingredient_name");
                double sum = resultSet.getDouble("sum");

                sqlUpdate = "UPDATE Stock SET quantity = (quantity + " + sum + ") WHERE ingredient_name = " + name + ";";
                updateStatement = getConnection().prepareStatement(sqlUpdate);
                if(!(checkUpdated(sqlUpdate))) {
                    return -1;
                }
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
        return -2;
    }

    public ArrayList<Order> deliveriesToday() {//order_id, delivery_date, delivery_time, take_away, other_request
        String sqlCommand = "SELECT * FROM Orders WHERE delivery_date = CURDATE();";
        ManageOrder manageOrder = new ManageOrder();
        ArrayList<Order> orders = manageOrder.getOrders(sqlCommand);
        manageOrder.stopConnection();
        return orders;
    }

    public ArrayList<Order> takeAwayToday() {
        String sqlCommand = "SELECT * FROM Orders WHERE delivery_date = CURDATE() AND take_away = 1;";
        ManageOrder manageOrder = new ManageOrder();
        ArrayList<Order> orders = manageOrder.getOrders(sqlCommand);
        manageOrder.stopConnection();
        return orders;
    }

    public ArrayList<Ingredient> getIngredients(String sqlCommand) {
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        PreparedStatement selectStatement = null;
        ResultSet resultSet = null;

        try {
            selectStatement = getConnection().prepareStatement(sqlCommand);
            resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
                String ingredientName = resultSet.getString("ingredient_name");
                double quantity = resultSet.getDouble("sum");

                ingredients.add(new Ingredient(ingredientName, quantity));
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


    public int makeOrder(int orderID) {
        String sqlCommand = "SELECT Stock.ingredient_name, (Recipe_ingredient.quantity*Order_recipe.quantity) AS sum FROM Stock JOIN Recipe_ingredient ON (Stock.ingredient_name = Recipe_ingredient.ingredient_name) JOIN Order_recipe ON(Recipe_ingredient.recipe_name = Order_recipe.recipe_name) JOIN Orders ON(Order_recipe.order_id = Orders.order_id) WHERE Orders.order_id = " + orderID + " ORDER BY ingredient_name;";
        return changeStock(getIngredients(sqlCommand));
    }

    public int buyIngredientsTakeAway(int orderID) { // - before sum to work with changeStock method
        String sqlCommand = "SELECT Stock.ingredient_name, Order_recipe.order_id, -(Recipe_ingredient.quantity*Order_recipe.quantity) AS sum FROM Stock JOIN Recipe_ingredient ON (Stock.ingredient_name = Recipe_ingredient.ingredient_name) JOIN Order_recipe ON(Recipe_ingredient.recipe_name = Order_recipe.recipe_name) JOIN Orders ON(Order_recipe.order_id = Orders.order_id) WHERE take_away = 0 AND Orders.order_id = " + orderID + " ORDER BY ingredient_name;";
        return changeStock(getIngredients(sqlCommand));
    }


    /*public int buyBunch() {
        String sqlCommandSelect = "SELECT quantity+100 FROM Stock;";
        String sqlCommandUpdate = "UPDATE Stock SET quantity = (quantity+100) WHERE quantity < 100;";


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
