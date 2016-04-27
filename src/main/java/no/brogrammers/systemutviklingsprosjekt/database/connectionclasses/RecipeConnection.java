package no.brogrammers.systemutviklingsprosjekt.database.connectionclasses;


import no.brogrammers.systemutviklingsprosjekt.recipe.*;
import no.brogrammers.systemutviklingsprosjekt.database.DatabaseConnection;

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

    public boolean create(Recipe recipe) {
        String recipeName = recipe.getRecipeName();
        RecipeType recipeType = recipe.getRecipeType();
        DietType dietType = recipe.getDietType();
        ArrayList<Ingredient> ingredients = recipe.getIngredients();
        ArrayList<Instruction> instructions = recipe.getInstructions();
        double price = recipe.getPrice();
        if (!addRecipe(recipeName, recipeType, dietType, price)) {
            return false;
        }
        if (!addIngredientRecipeConnection(recipeName, ingredients)) {
            return false;
        }
        return addInstructions(recipeName, instructions);
    }

    private boolean addRecipe(String recipeName, RecipeType recipeType, DietType dietType, double price) {
        try {
            PreparedStatement pStatement = getConnection().prepareStatement("INSERT INTO Recipe(recipe_name, recipe_type, diet_type, price) VALUES (?,?,?,?);");
            pStatement.setString(1, recipeName);
            pStatement.setString(2, recipeType.name());
            pStatement.setString(3, dietType.name());
            pStatement.setDouble(4, price);
            pStatement.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

    private boolean addIngredientRecipeConnection(String recipeName, ArrayList<Ingredient> ingredients) {
        for(int i = 0; i < ingredients.size(); i++) {
            String sqlCommand = "INSERT INTO Recipe_ingredient(recipe_name, ingredient_name, quantity) VALUES (?,?,?);";
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = getConnection().prepareStatement(sqlCommand);
                preparedStatement.setString(1, recipeName);
                preparedStatement.setString(2, ingredients.get(i).getIngredientName());
                preparedStatement.setDouble(3, ingredients.get(i).getQuantity());
                if(preparedStatement.executeUpdate() == 0) {
                    return false;
                }
            } catch (SQLException sqle) {
                writeError(sqle.getMessage());
            } catch (Exception e) {
                writeError(e.getMessage());
            } finally {
                getCleaner().closePreparedStatement(preparedStatement);
            }
        }
        return true;
    }

    private boolean addIngredients(String recipeName, ArrayList<Ingredient> ingredients) {
        for (Ingredient ingredient : ingredients) {
            try {
                PreparedStatement pStatement = getConnection().prepareStatement("INSERT INTO Ingredient(ingredient_name) VALUES (?);");
                pStatement.setString(1, ingredient.getIngredientName());
                pStatement.execute();
            } catch (SQLException e) {
                System.out.println(e);
            }

            try {
                PreparedStatement pStatement = getConnection().prepareStatement("INSERT INTO Recipe_ingredient(recipe_name, ingredient_name, quantity) VALUES (?,?,?);");
                pStatement.setString(1, recipeName);
                pStatement.setString(2, ingredient.getIngredientName());
                pStatement.setDouble(3, ingredient.getQuantity());
                pStatement.execute();
            } catch (SQLException e) {
                System.err.println(e);
            }

        }
        return true;
    }

    private boolean addInstructions(String recipeName, ArrayList<Instruction> instructions) {
        for (Instruction instruction : instructions) {
            try {
                PreparedStatement pStatement = getConnection().prepareStatement(
                        "INSERT INTO Recipe_instruction(recipe_name, step_number, description) VALUES (?,?,?)"
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
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ArrayList<Instruction> instructions = new ArrayList<>();
        RecipeType recipeType = null;
        DietType dietType = null;
        double price = 0;
        try {
            PreparedStatement pStatement = getConnection().prepareStatement(
                    "SELECT * from Recipe_ingredient WHERE recipe_name = (?)"
            );
            pStatement.setString(1, recipeName);
            pStatement.execute();

            ResultSet rs = pStatement.getResultSet();

            while (rs.next()) {
                ingredients.add(new Ingredient(rs.getString("ingredient_name"), rs.getDouble("quantity")));
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
                dietType = DietType.valueOf(rs.getString("diet_type"));
                price = Double.parseDouble((rs.getString("price")));
            }

        } catch (SQLException e) {
            System.err.println(e);
        }

        return new Recipe(recipeName, recipeType, dietType, ingredients, instructions, price);
    }

    public boolean updateRecipe(String oldRecipeName, Recipe newRecipe) {
        delete(oldRecipeName);
        create(newRecipe);
        return true;
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
