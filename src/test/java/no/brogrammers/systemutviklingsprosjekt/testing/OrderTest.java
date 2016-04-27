package no.brogrammers.systemutviklingsprosjekt.testing;

import no.brogrammers.systemutviklingsprosjekt.miscellaneous.DateConverter;
import no.brogrammers.systemutviklingsprosjekt.order.Order;
import no.brogrammers.systemutviklingsprosjekt.recipe.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Knut on 27.04.2016.
 */
public class OrderTest {

    int orderID = 710;
    int customerID = 13;
    boolean paymentStatus = false;
    DateConverter dateConverter = new DateConverter();
    java.sql.Date orderDate = dateConverter.stringToSqlDate("2016-03-20");
    java.sql.Date deliveryDate = dateConverter.stringToSqlDate("2016-04-25");
    double deliveryTime = 10.0;
    String address = "Asd veien 1";
    int zip = 7010;
    boolean takeAway = false;
    String otherRequsts = "This is a other request";
    boolean made = true;
    boolean ingredientsPurchased = true;
    ArrayList<Recipe> recipes = new ArrayList<Recipe>();
    boolean delivered = false;

    private Order order;

    @Before
    public void setUp() throws Exception {
        //Setup Ingredient array list that is needed for adding a recipe to a order:
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        ingredients.add(new Ingredient("Pasta", 1.0));
        ingredients.add(new Ingredient("Meat", 5.0));
        ingredients.add(new Ingredient("Salt", 0.1));
        //Setuo Instruction array list that is needed for adding a recipe to a order:
        ArrayList<Instruction> instructions = new ArrayList<Instruction>();
        instructions.add(new Instruction(1, "Find food"));
        instructions.add(new Instruction(2, "Put food together"));
        instructions.add(new Instruction(3, "Make food and eat"));
        recipes.add(new Recipe("Spaghetti", RecipeType.MEATLOVER, DietType.GLUTEN_FREE, ingredients, instructions, 120));
        order = new Order(orderID, customerID, paymentStatus, orderDate, deliveryDate, deliveryTime, address, zip, takeAway, otherRequsts, made, ingredientsPurchased, delivered, recipes);
    }

    /*@After
    public void tearDown() throws Exception {
        manageUser.stopConnection();
    }*/

    /*@Test
    public void addRecipe() throws Exception {

    }*/

    @Test
    public void getOrderID() throws Exception {
        int expectedResult = 710;
        int result = order.getOrderID();
        assertEquals(expectedResult, result);
    }

    @Test
    public void getCustomerID() throws Exception {
        int expectedResult = 13;
        int result = order.getCustomerID();
        assertEquals(expectedResult, result);
    }

    @Test
    public void isPaymentStatus() throws Exception {
        boolean expectedResult = false;
        boolean result = order.isPaymentStatus();
        assertEquals(expectedResult, result);
    }

    @Test
    public void getOrderDate() throws Exception {
        java.sql.Date expectedResult = dateConverter.stringToSqlDate("2016-03-20");
        java.sql.Date result = order.getOrderDate();
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDeliveryDate() throws Exception {
        java.sql.Date expectedResult = dateConverter.stringToSqlDate("2016-04-25");
        java.sql.Date result = order.getDeliveryDate();
        assertEquals(expectedResult, result);
    }

    @Test
    public void getDeliveryTime() throws Exception {
        double delta = 0.01;
        double expectedResult = 10.0;
        double result = order.getDeliveryTime();
        assertEquals(expectedResult, result, delta);
    }

    @Test
    public void getAddress() throws Exception {
        String expectedResult = "Asd veien 1";
        String result = order.getAddress();
        assertEquals(expectedResult, result);
    }

    @Test
    public void getZipCode() throws Exception {

    }

    @Test
    public void isTakeAway() throws Exception {

    }

    @Test
    public void getOtherRequests() throws Exception {

    }

    @Test
    public void isMade() throws Exception {

    }

    @Test
    public void isIngredientsPurchased() throws Exception {

    }

    @Test
    public void isDelivered() throws Exception {

    }

    @Test
    public void setPaymentStatus() throws Exception {

    }

    @Test
    public void setDeliveryDate() throws Exception {

    }

    @Test
    public void setDeliveryTime() throws Exception {

    }

    @Test
    public void setAddress() throws Exception {

    }

    @Test
    public void setZipCode() throws Exception {

    }

    @Test
    public void setTakeAway() throws Exception {

    }

    @Test
    public void setOtherRequests() throws Exception {

    }

    @Test
    public void setMade() throws Exception {

    }

    @Test
    public void setIngredientsPurchased() throws Exception {

    }

    @Test
    public void setDelivered() throws Exception {

    }

}