package no.brogrammers.systemutviklingsprosjekt.recipe;

/**
 * Created by Nicole on 07.04.2016.
 *
 */
public class Ingredient {

    private String ingredientName;
    private String quantity;
    /**
     * Quantity depends on where is it used.
     * If used in orders the quantity is the number of ingredients in a recipe.
     * If it is used in a table it is the number of ingredients in the stock.
     */

    public Ingredient(String ingredientName, String quantity){
        this.ingredientName = ingredientName;
        this.quantity = quantity;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

}
