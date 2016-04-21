package no.brogrammers.systemutviklingsprosjekt.database.connectionclasses;


import no.brogrammers.systemutviklingsprosjekt.database.DatabaseConnection;
import no.brogrammers.systemutviklingsprosjekt.recipe.Ingredient;
import no.brogrammers.systemutviklingsprosjekt.recipe.Instruction;
import no.brogrammers.systemutviklingsprosjekt.recipe.Recipe;
import no.brogrammers.systemutviklingsprosjekt.recipe.RecipeType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Nicole on 07.04.2016.
 *
 */
public class RecipeConnection extends DatabaseConnection{


    public ArrayList<Recipe> viewAllRecipes() {
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        String sqlCommand = "SELECT * FROM Recipe";
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sqlCommand);
            while(resultSet.next()) {
                recipes.add(read(resultSet.getString("recipe_name")));
            }
        } catch(SQLException sqle) {
            writeError(sqle.getMessage());
        } catch(Exception e) {
            writeError(e.getMessage());
        } finally {
            getCleaner().closeResultSet(resultSet);
            getCleaner().closeStatement(statement);
        }
        return recipes;
    }


    public Recipe read(String recipeName) {
        List<Ingredient> ingredients = new ArrayList<>();
        List<Instruction> instructions = new ArrayList<>();
        RecipeType recipeType = null;
        double price = 0;
        try {
            PreparedStatement pStatement = getConnection().prepareStatement(
                    "SELECT * from Recipe_ingredient WHERE recipe_name = (?)"
            );
            pStatement.setString(1, recipeName);
            pStatement.execute();

            ResultSet rs = pStatement.getResultSet();

            while (rs.next()) {
                ingredients.add(new Ingredient(rs.getString("ingredient_name"), rs.getString("quantity")));
            }

            pStatement = getConnection().prepareStatement(
                    "SELECT * from Recipe_instruction WHERE recipe_name = (?)"
            );

            pStatement.setString(1, recipeName);
            pStatement.execute();

            rs = pStatement.getResultSet();

            while (rs.next()) {
                instructions.add(new Instruction(rs.getInt("step_number"), rs.getString("description")));
            }

            pStatement = getConnection().prepareStatement(
                    "SELECT * from Recipe WHERE recipe_name = (?)"
            );

            pStatement.setString(1, recipeName);
            pStatement.execute();

            rs = pStatement.getResultSet();

            while (rs.next()) {
                recipeType = RecipeType.valueOf(rs.getString("recipe_type"));
                price = Double.parseDouble((rs.getString("price")));
            }

        } catch (SQLException e) {
            System.err.println(e);
        }

        return new Recipe(recipeName, recipeType, ingredients, instructions, price);
    }

    public boolean update() {
        return false;
    }

    public boolean delete() {
        return false;
    }


}
