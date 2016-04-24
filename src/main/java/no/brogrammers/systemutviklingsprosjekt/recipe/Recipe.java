package no.brogrammers.systemutviklingsprosjekt.recipe;

import java.util.ArrayList;

/**
 * Created by Nicole on 07.04.2016.
 */
public class Recipe {


    private String recipeName;
    private RecipeType recipeType;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<Instruction> instructions;
    private double price;

    public Recipe(String recipeName, RecipeType recipeType, ArrayList<Ingredient> ingredients, ArrayList<Instruction> instructions, double price) {
        this.recipeName = recipeName;
        this.recipeType = recipeType;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.price = price;
    }

    public String getRecipeName() { return recipeName; }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public RecipeType getRecipeType() {
        return recipeType;
    }

    public void setRecipeType(RecipeType recipeType) {
        this.recipeType = recipeType;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(ArrayList<Instruction> instructions) {
        this.instructions = instructions;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return recipeName + " (" + recipeType.name() + "):\n" + ingredients + "\n" + instructions + "\n" + price;

    }

}
