package no.brogrammers.systemutviklingsprosjekt.Recipe;

/**
 * Created by Nicole on 07.04.2016.
 *
 */
public class Ingredient {

    private String ingredientName;
    private String quantity;

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
