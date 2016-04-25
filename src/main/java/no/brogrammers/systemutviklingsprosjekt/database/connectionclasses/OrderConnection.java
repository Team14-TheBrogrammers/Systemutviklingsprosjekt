package no.brogrammers.systemutviklingsprosjekt.database.connectionclasses;

import no.brogrammers.systemutviklingsprosjekt.database.DatabaseConnection;

import no.brogrammers.systemutviklingsprosjekt.order.Order;
import no.brogrammers.systemutviklingsprosjekt.recipe.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Knut on 11.03.2016.
 */
public abstract class OrderConnection extends DatabaseConnection {

    /**
     * Method for checking if the delibery- date and time is correct. That means that the delivery time is between 7 am and 9 pm.
     * @param deliveryDate is the date that the delivery is placed. This is a java.sql.Date object. A correct date is 3 or more days before now.
     * @param deliveryTime is the delivery time in the day, and it is correct if it is between 7.0 and 21.0
     * @return true if the delivery date is inbetween the accepted time and a correct date.
     */
    private boolean checkCorrectOrderDates(java.sql.Date deliveryDate, double deliveryTime, boolean takeAway) { //TODO: using own class to do this?
        if(deliveryTime <= 21 && deliveryTime >= 7) {
            long MILLISECONDS_IN_DAY = 24 * 60 * 60 * 1000;
            int days = (int) ((deliveryDate.getTime() - new Date(Calendar.getInstance().getTimeInMillis()).getTime()) / MILLISECONDS_IN_DAY);
            if(days < 0) {
                return false;
            } else {
                if(takeAway) {
                    if(days == 0) {
                        return true;
                    }
                } else {
                    if(days >= 3) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * This method is for adding a new order to the database.
     * @param customerID is the ID of the customer in the Customer table.
     * @param paymentStatus is the status of the payment, wheter it is paid or not.
     * @param orderDate is the date the order is placed. This variable is a java.sql.Date object.
     * @param deliveryDate is the date the delivery has to be delivered. (java.sql.Date object)
     * @param deliveryTime is the time of the day the delivery is placed.
     * @param address is the address to the delivery.
     * @param zipCode is the zip code for the address.
     * @param recipes is a ArrayList of Recipe objects. This is a list of what the customer has purchased, and want to be delivered at the date and time.
     * @return -1 if the customer with the given customerID is not in the database. Return -2 if the orderDate and time is not accepted.
     * Otherwise the method return the orderID of the placed order.
     */
    public int addOrder(int customerID, boolean paymentStatus, java.sql.Date orderDate, java.sql.Date deliveryDate, double deliveryTime, String address, int zipCode, boolean takeAway, String otherRequests, ArrayList<Recipe> recipes, int[] quantity) { // TODO: clean connection properly and do fix this code
        if(checkCorrectOrderDates(deliveryDate, deliveryTime, takeAway)) {
            if(checkCustomerId(customerID)) {
                int newNumber = 1; //If there is no other numbers before this number will be set as the id
                boolean finished = false;
                ResultSet resultSet = null;
                PreparedStatement selectStatement = null;
                PreparedStatement insertStatement = null;

                String selectCommand = "SELECT MAX(order_id) AS c FROM Orders;";
                String insertCommand = "INSERT INTO Orders(payment_status, order_date, delivery_date, delivery_time, address, zip, take_away, other_request, customer_id)\n" +
                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";

                while(!finished) {
                    try {
                        selectStatement = getConnection().prepareStatement(selectCommand);
                        resultSet = selectStatement.executeQuery();

                        resultSet.next();
                        newNumber = resultSet.getInt("c") + 1;
                        insertStatement = getConnection().prepareStatement(insertCommand);
                        insertStatement.setBoolean(1, paymentStatus);
                        insertStatement.setDate(2, orderDate);
                        insertStatement.setDate(3, deliveryDate);
                        insertStatement.setDouble(4, deliveryTime);
                        insertStatement.setString(5, address);
                        insertStatement.setInt(6, zipCode);
                        insertStatement.setBoolean(7, takeAway);
                        insertStatement.setString(8, otherRequests);
                        insertStatement.setInt(9, customerID);

                        for(int i = 0; i < recipes.size(); i++) {
                            String sqlCommand = "INSERT INTO Order_recipe(order_id, recipe_name, quantity) VALUES(" + newNumber + ", " + recipes.get(i).getRecipeName() + ", " + quantity[i] + ");";
                            checkUpdated(sqlCommand);
                        }

                        insertStatement.executeUpdate();
                        finished = true;
                    } catch (SQLException sqle) {
                        writeError(sqle.getMessage());
                    } catch (Exception e) {
                        writeError(e.getMessage());
                    } finally {
                        getCleaner().closePreparedStatement(insertStatement);
                        getCleaner().closePreparedStatement(selectStatement);
                        getCleaner().closeResultSet(resultSet);
                    }
                }
                return newNumber;
            } else {
                return -1;
            }
        }
        return -2;
    }

    /**
     * Method for retrieving the price for a given order with a orderID. This method does a query for all recipes that match an order.
     * @param orderID is the ID for the order.
     * @return -1.0 if something wrong happend. If not -1.0 is return the total price to the order is returned.
     */
    public double getOrderPrice(int orderID) {
        String sqlCommand = "SELECT * FROM Order_recipe NATURAL JOIN Recipe WHERE order_id = " + orderID + ";";
        double totalPrice = -1.0;

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sqlCommand);

            while (resultSet.next()) {
                totalPrice += (resultSet.getInt("quantity") * resultSet.getDouble("price"));
            }
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        } finally {
            getCleaner().closeResultSet(resultSet);
            getCleaner().closeStatement(statement);
        }
        return totalPrice;
    }

    /**
     * Method for checking if a customer exists with the given customerID.
     * @param id is the id that we check if exists in the database in the Customer table.
     * @return false if the customer does not exist in the databae. If it does, it returns true.
     */
    private boolean checkCustomerId(int id) {
        String sqlCommand = "SELECT * FROM Customer WHERE customer_id = " + id + ";";
        return checkExists(sqlCommand);
    }

    /**
     * Method for changing delivery- date and time for a given order with orderID.
     * @param orderID is the ID that is updated with new delivery date and time.
     * @param deliveryDate is a new delivery date for the order.
     * @param deliveryTime is a new delivery time for the order.
     * @return false if the order was not updated. Otherwise the order was updated and the method returns true.
     */
    public boolean changeOrderDeliveryTime(int orderID, java.sql.Date deliveryDate, double deliveryTime) {
        String sqlCommand = "UPDATE Orders SET delivery_date = " + deliveryDate + ", delivery_time = " + deliveryTime + " WHERE order_id = " + orderID + ";";
        return checkUpdated(sqlCommand);
    }

    /**
     * Method for retrieving all ingredients to a given recipe with a recipe name as a String.//TODO: fix more
     * @param recipeName
     * @return
     */
    private ArrayList<Ingredient> getIngredientsToRecipe(String recipeName) {
        ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
        String sqlCommand = "SELECT * FROM Recipe_ingredient WHERE recipe_name = '" + recipeName + "';";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sqlCommand);
            while(resultSet.next()) {
                String name = resultSet.getString("ingredient_name");
                double quantity = resultSet.getDouble("quantity");
                ingredients.add(new Ingredient(name, quantity));
            }
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        } finally {
            getCleaner().closeResultSet(resultSet);
            getCleaner().closeStatement(statement);
        }
        return ingredients;
    }

    private ArrayList<Instruction> getInstructionsToRecipe(String recipeName) {
        ArrayList<Instruction> instructions = new ArrayList<Instruction>();
        String sqlCommand = "SELECT * FROM Recipe_instruction WHERE recipe_name = '" + recipeName + "';";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sqlCommand);

            while(resultSet.next()) {
                int stepNumber = resultSet.getInt("step_number");
                String description = resultSet.getString("description");
                instructions.add(new Instruction(stepNumber, description));
            }
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        } finally {
            getCleaner().closeResultSet(resultSet);
            getCleaner().closeStatement(statement);
        }
        return instructions;
    }

    public ArrayList<Recipe> getRecipesToOrder(int orderID) {
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        String sqlCommand = "SELECT * FROM Order_recipe NATURAL JOIN Recipe WHERE order_id = " + orderID + ";";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sqlCommand);
            while (resultSet.next()) {
                String name = resultSet.getString("recipe_name");
                RecipeType recipeType = RecipeType.valueOf(resultSet.getString("recipe_type"));
                DietType dietType = DietType.valueOf(resultSet.getString("diet_type"));
                double price = resultSet.getDouble("price");
                recipes.add(new Recipe(name, recipeType, dietType, getIngredientsToRecipe(name), getInstructionsToRecipe(name), price)); //TODO: FIX
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

    public Order viewOrderByID(int orderID) {//TODO: DOES NOT NEED test subscription
        //String sqlCommand = "SELECT payment_status, delivery_date, delivery_time, address, total_price FROM orders WHERE order_id = " + orderID + ";";

        String sqlCommand = "SELECT * FROM Orders WHERE order_id = " + orderID + ";";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sqlCommand);
            while(resultSet.next()) {

                boolean paymentStatus = resultSet.getBoolean("payment_status");
                java.sql.Date orderDate = resultSet.getDate("order_date");
                java.sql.Date deliveryDate = resultSet.getDate("delivery_date");
                double deliveryTime = resultSet.getDouble("delivery_time");
                String address = resultSet.getString("address");
                int zip = resultSet.getInt("zip");
                int customerID = resultSet.getInt("customer_id");
                boolean isTakeaway = resultSet.getBoolean("takeaway");
                String otherRequests = resultSet.getString("other_request");

                return new Order(orderID, customerID, paymentStatus, orderDate, deliveryDate, deliveryTime, address, zip, isTakeaway, otherRequests, getRecipesToOrder(orderID));
            }
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        } finally {
            getCleaner().closeResultSet(resultSet);
            getCleaner().closeStatement(statement);
        }
        return null;
    }

    public boolean deleteOrder(int id) {
        String sqlCommand = "DELETE FROM Orders WHERE order_id = " + id + ";";
        return checkUpdated(sqlCommand);
    }

    /*private boolean orderIsSubscription() {

    }*///TODO:// FIXME: 25.04.2016 asd

    public ArrayList<Order> getOrders(String sqlCommand) {
        ArrayList<Order> orders  = new ArrayList<Order>();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sqlCommand);

            while(resultSet.next()) {
                int orderID = resultSet.getInt("order_id");
                boolean paymentStatus = resultSet.getBoolean("payment_status");
                java.sql.Date orderDate = resultSet.getDate("order_date");
                java.sql.Date deliveryDate = resultSet.getDate("delivery_date");
                double deliveryTime = resultSet.getDouble("delivery_time");
                String address = resultSet.getString("address");
                int zip = resultSet.getInt("zip");
                int customerID = resultSet.getInt("customer_id");
                boolean isTakeaway = resultSet.getBoolean("take_away");
                String otherRequests = resultSet.getString("other_request");
                orders.add(new Order(orderID, customerID, paymentStatus, orderDate, deliveryDate, deliveryTime, address, zip, isTakeaway, otherRequests, getRecipesToOrder(orderID)));
            }

        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        } finally {
            getCleaner().closeResultSet(resultSet);
            getCleaner().closeStatement(statement);
        }
        return orders;
    }

    public ArrayList<Order> viewPreviousOrders() {//viewAllOrders() {
        String sqlCommand = "SELECT * FROM Orders WHERE delivery_date < CURDATE();";//"SELECT * FROM Orders;";
        return getOrders(sqlCommand);
    }

    public ArrayList<Order> viewOrdersToCustomer(int customerID) {
        ArrayList<Order> orders = new ArrayList<Order>();
        String sqlOrder = "SELECT * FROM Customer NATURAL JOIN Orders WHERE customer_id = " + customerID + ";";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sqlOrder);
            while (resultSet.next()) {
                int orderID = resultSet.getInt("order_id");
                boolean paymentStatus = resultSet.getBoolean("payment_status");
                java.sql.Date orderDate = resultSet.getDate("order_date");
                java.sql.Date deliveryDate = resultSet.getDate("delivery_date");
                double deliveryTime = resultSet.getDouble("delivery_time");
                String address = resultSet.getString("address");
                int zip = resultSet.getInt("zip");
                boolean isTakeaway = resultSet.getBoolean("takeaway");
                String otherRequests = resultSet.getString("other_request");

                orders.add(new Order(orderID, customerID, paymentStatus, orderDate, deliveryDate, deliveryTime, address, zip, isTakeaway, otherRequests, getRecipesToOrder(orderID)));
            }
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        } finally {
            getCleaner().closeResultSet(resultSet);
            getCleaner().closeStatement(statement);
        }
        return orders;
    }

    //TODO: FIX THIS METHOD SOON
    public ArrayList<Order> viewActiveOrders() {
        String sqlCommand = "SELECT * FROM Orders WHERE delivery_date >= CURDATE();";
        return getOrders(sqlCommand);
    }
        /*ArrayList<Order> orders  = new ArrayList<Order>();
        String sqlCommand = "SELECT * FROM Orders;";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sqlCommand);
            while(resultSet.next()) {
                if(resultSet.getDate("delivery_date").getTime() - new Date(Calendar.getInstance().getTimeInMillis()).getTime()) {
                    int orderID = resultSet.getInt("order_id");
                    int customerID = resultSet.getInt("customer_id");
                    boolean paymentStatus = resultSet.getBoolean("payment_status");
                    java.sql.Date orderDate = resultSet.getDate("order_date");
                    java.sql.Date deliveryDate = resultSet.getDate("delivery_date");
                    double deliveryTime = resultSet.getDouble("delivery_time");
                    String address = resultSet.getString("address");
                    int zip = resultSet.getInt("zip");

                    orders.add(new Order(orderID, customerID, paymentStatus, orderDate, deliveryDate, deliveryTime, address, zip, getRecipesToOrder(orderID)));
                }

                if(resultSet.getInt("delivery_date") - new Date(Calendar.getInstance().getTimeInMillis())) {

                }

            }
        } catch (SQLException sqle) {
            writeError(sqle.getMessage());
        } catch (Exception e) {
            writeError(e.getMessage());
        } finally {
            getCleaner().closeResultSet(resultSet);
            getCleaner().closeStatement(statement);
        }
        return orders;
    }*/

    public ArrayList<Ingredient> listMissingIngredients() {
        return null;
    }


    //Legge alle metodene for ManageOrder her ??
}
