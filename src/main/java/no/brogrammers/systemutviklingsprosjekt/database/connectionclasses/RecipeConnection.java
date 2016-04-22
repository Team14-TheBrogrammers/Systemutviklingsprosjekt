package no.brogrammers.systemutviklingsprosjekt.database.connectionclasses;


import no.brogrammers.systemutviklingsprosjekt.recipe.Recipe;
import no.brogrammers.systemutviklingsprosjekt.database.DatabaseConnection;
import no.brogrammers.systemutviklingsprosjekt.recipe.Ingredient;
import no.brogrammers.systemutviklingsprosjekt.recipe.Instruction;
import no.brogrammers.systemutviklingsprosjekt.recipe.RecipeType;

import java.sql.*;
import java.util.ArrayList;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


/**
 * Created by Nicole on 07.04.2016.
 *
 */
public class RecipeConnection extends DatabaseConnection {

    public ArrayList<Recipe> viewAllRecipes() {
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        String sqlCommand = "SELECT * FROM Recipe";
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sqlCommand);
            while (resultSet.next()) {
                recipes.add(read(resultSet.getString("recipe_name")));
            }
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        } finally {
            getCleaner().closeResultSet(resultSet);
            getCleaner().closeStatement(statement);
        }
        return recipes;
    }

    public boolean create(String recipeName, List<Ingredient> ingredients) {
        if( !addRecipe(recipeName) ) {
            return false;
        }
        if( !addIngredients(recipeName, ingredients) ) {
            return false;
        }
        return addInstructions(recipeName);
    }

    private boolean addInstructions(String recipeName) {
        return false;
    }

    private boolean addRecipe(String recipeName) {
        try {
            PreparedStatement pStatement = getConnection().prepareStatement(
                    "INSERT INTO recipe(recipe_name) VALUES (?)"
            );
            pStatement.setString(1, recipeName);
            pStatement.execute();
            return true;
        } catch(SQLException e) {
            System.err.println(e);
            return false;
        }

    }

    private boolean addIngredients(String recipeName, List<Ingredient> ingredients) {
        for (Ingredient ingredient : ingredients) {
            try {
                PreparedStatement pStatement = getConnection().prepareStatement(
                        "INSERT INTO ingredients(ingredient_name) VALUES (?)"
                );
                pStatement.setString(1, ingredient.getIngredientName());
                pStatement.execute();
            } catch (SQLException e) {
                System.out.println(e);
            }

            try {
                PreparedStatement pStatement = getConnection().prepareStatement(
                        "INSERT INTO recipe_ingredients(recipe_name, ingredient_name, quantity) VALUES (?,?,?)"
                );
                pStatement.setString(1, recipeName);
                pStatement.setString(2, ingredient.getIngredientName());
                pStatement.setString(3, ingredient.getQuantity());
                pStatement.execute();
            } catch (SQLException e) {
                System.err.println(e);
            }

        }
        return true;
    }

    private boolean addInstructions(String recipeName, List<Instruction> instructions) {
        for (Instruction instruction : instructions) {
            try {
                PreparedStatement pStatement = getConnection().prepareStatement(
                        "INSERT INTO recipe_intructions(recipe_name, step_number, description) VALUES (?,?,?)"
                );
                pStatement.setString(1, recipeName);
                pStatement.setInt(2, instruction.getStepNumber());
                pStatement.setString(3, instruction.getDescription());
                pStatement.execute();
            } catch (SQLException e) {
                System.err.println(e);
            }

        }
        return true;
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
                    "SELECT recipe_type from Recipe WHERE recipe_name = (?)"
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

    public Recipe read() {
        return null;
    }

    public boolean update() {
        return false;
    }

    public boolean delete(String recipeName) {
        try {
            PreparedStatement pStatement = getConnection().prepareStatement(
                    "DELETE FROM Recipe WHERE recipe_name = (?) "
            );
            pStatement.setString(1, recipeName);
            pStatement.execute();
            return true;

        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

}
