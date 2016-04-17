package no.brogrammers.systemutviklingsprosjekt.database.connectionclasses;


import no.brogrammers.systemutviklingsprosjekt.recipe.Ingredient;
import no.brogrammers.systemutviklingsprosjekt.recipe.Instruction;
import no.brogrammers.systemutviklingsprosjekt.recipe.Recipe;
import no.brogrammers.systemutviklingsprosjekt.recipe.RecipeType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Nicole on 07.04.2016.
 */
public class RecipeConnection {

    Connection connection;

    public RecipeConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean create(String recipeName, RecipeType recipeType, List<Ingredient> ingredients, List<Instruction> instructions, double price) {
        if (!addRecipe(recipeName, recipeType, price)) {
            return false;
        }
        if (!addIngredients(recipeName, ingredients)) {
            return false;
        }
        return addInstructions(recipeName, instructions);
    }

    private boolean addRecipe(String recipeName, RecipeType recipeType, double price) {
        try {
            PreparedStatement pStatement = connection.prepareStatement(
                    "INSERT INTO recipe(recipe_name, recipe_type, price) VALUES (?,?,?)"
            );
            pStatement.setString(1, recipeName);
            pStatement.setString(2, recipeType.name());
            pStatement.setDouble(3, price);
            pStatement.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }

    }

    private boolean addIngredients(String recipeName, List<Ingredient> ingredients) {
        for (Ingredient ingredient : ingredients) {
            try {
                PreparedStatement pStatement = connection.prepareStatement(
                        "INSERT INTO ingredients(ingredient_name) VALUES (?)"
                );
                pStatement.setString(1, ingredient.getIngredientName());
                pStatement.execute();
            } catch (SQLException e) {
                System.out.println(e);
            }

            try {
                PreparedStatement pStatement = connection.prepareStatement(
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
                PreparedStatement pStatement = connection.prepareStatement(
                        "INSERT INTO recipe_instructions(recipe_name, step_number, description) VALUES (?,?,?)"
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
            PreparedStatement pStatement = connection.prepareStatement(
                    "SELECT * from recipe_ingredients WHERE recipe_name = (?)"
            );
            pStatement.setString(1, recipeName);
            pStatement.execute();

            ResultSet rs = pStatement.getResultSet();

            while (rs.next()) {
                ingredients.add(new Ingredient(rs.getString("ingredient_name"), rs.getString("quantity")));
            }

            pStatement = connection.prepareStatement(
                    "SELECT * from recipe_instructions WHERE recipe_name = (?)"
            );

            pStatement.setString(1, recipeName);
            pStatement.execute();

            rs = pStatement.getResultSet();

            while (rs.next()) {
                instructions.add(new Instruction(rs.getInt("step_number"), rs.getString("description")));
            }

            pStatement = connection.prepareStatement(
                    "SELECT recipe_type from recipe WHERE recipe_name = (?)"
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
