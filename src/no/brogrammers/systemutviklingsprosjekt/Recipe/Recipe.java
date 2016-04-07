package no.brogrammers.systemutviklingsprosjekt.Recipe;

/**
 * Created by Nicole on 07.04.2016.
 */
public class Recipe {


    private String recipeName;
    private RecipeType recipeType;
    private List<String> ingredients;
    private List<String> instructions;

    public Recipe(String recipeName, RecipeType recipeType, List<String> ingredients, List<String> instructions) {
        this.recipeName = recipeName;
        this.recipeType = recipeType;
        this.ingredients = ingredients;
        this.instructions = instructions;
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

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

    public String toString() {
        return recipeName + " (" + recipeType.name() + "):\n" + ingredients + "\n" + instructions;
    }

}
