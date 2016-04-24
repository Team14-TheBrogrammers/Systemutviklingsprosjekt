package no.brogrammers.systemutviklingsprosjekt.recipe;

import no.brogrammers.systemutviklingsprosjekt.recipe.DietType;
import no.brogrammers.systemutviklingsprosjekt.recipe.Ingredient;
import no.brogrammers.systemutviklingsprosjekt.recipe.Instruction;
import no.brogrammers.systemutviklingsprosjekt.recipe.RecipeType;

import java.util.List;

/**
 * Created by Nicole on 07.04.2016.
 */
public class Recipe {


    private String recipeName;
    private RecipeType recipeType;
    private DietType dietType;
    private List<Ingredient> ingredients;
    private List<Instruction> instructions;
    private double price;

    public Recipe(String recipeName, RecipeType recipeType, DietType dietType, List<Ingredient> ingredients, List<Instruction> instructions, double price) {
        this.recipeName = recipeName;
        this.recipeType = recipeType;
        this.dietType = dietType;
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

    public DietType getDietType() { return dietType; }

    public void setDietType(DietType dietType) { this.dietType = dietType; }

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

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }


    public String toString() {
        return recipeName + " (" + recipeType.name() + ", " + dietType.name() + "):\n" + ingredients + "\n" + instructions + "\n" + price;

    }

}
