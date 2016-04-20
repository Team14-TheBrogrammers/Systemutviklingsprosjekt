package no.brogrammers.systemutviklingsprosjekt.recipe;

import java.util.List;
import no.brogrammers.systemutviklingsprosjekt.recipe.*;

/**
 * Created by Nicole on 07.04.2016.
 */
public class Recipe {


    private String recipeName;
    private RecipeType recipeType;
    private List<Ingredient> ingredients;
    private List<Instruction> instructions;
    private double price;

    public Recipe(String recipeName, RecipeType recipeType, List<Ingredient> ingredients, List<Instruction> instructions, double price) {
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        return recipeName + " (" + recipeType.name() + "):\n" + ingredients + "\n" + instructions;
    }
}
