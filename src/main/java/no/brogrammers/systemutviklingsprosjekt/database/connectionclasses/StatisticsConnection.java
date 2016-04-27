package no.brogrammers.systemutviklingsprosjekt.database.connectionclasses;

import no.brogrammers.systemutviklingsprosjekt.database.DatabaseConnection;

import java.sql.*;

/**
 * Created by Ingunn on 20.04.2016.
 * StatisticsConnection class
 */
public class StatisticsConnection extends DatabaseConnection{

    /**
     * Method that calculates the percent of private customers.
     * @return double percent of private customers
     */

    public double percentOfPrivateCustomers() {
        String sqlCommand = "SELECT COUNT(*) AS c FROM Customer;";
        String sqlCommand2 = "SELECT COUNT(*) AS pc FROM Private_customer";
        Statement statement = null;
        Statement statement2 = null;
        ResultSet resultSet = null;
        ResultSet resultSet2 = null;

        try {
            statement = getConnection().createStatement();
            statement2 = getConnection().createStatement();
            resultSet = statement.executeQuery(sqlCommand);
            resultSet2 = statement2.executeQuery(sqlCommand2);

            resultSet.next();
            resultSet2.next();

            int customers = resultSet.getInt("c");
            int privateCustomers = resultSet2.getInt("pc");
            double percent = (((double)privateCustomers/(double)customers)*100);
            return percent;
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            getCleaner().closeResultSet(resultSet2);
            getCleaner().closeStatement(statement2);
            getCleaner().closeResultSet(resultSet);
            getCleaner().closeStatement(statement);
        }
        return -1;
    }

    /**
     * Method that finds the number of deliveries on each weekday.
     * @return int[] weekdays with the total number of orders on each weekday
     */

    public int[] popularWeekdaysDeliveryDate() {
        String sqlCommand = "SELECT WEEKDAY(delivery_date) AS wday FROM Orders;";
        Statement statement = null;
        ResultSet resultSet = null;
        int[] weekdays = new int[7];

        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sqlCommand);

            while(resultSet.next()) {
                weekdays[resultSet.getInt("wday")]++;
            }

            return weekdays;

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            getCleaner().closeResultSet(resultSet);
            getCleaner().closeStatement(statement);
        }
        return null;
    }

    /**
     * The method returns an dobble array of monthly income for the 12 last months.
     * View for the method: CREATE VIEW price_view AS (SELECT order_id, SUM(price*quantity) AS order_price FROM Orders NATURAL JOIN Order_recipe NATURAL JOIN Recipe GROUP BY order_id);
     * @return double[] of monthly income in the last 12 months
     */

    public double[] monthlyIncome() { //last 12 months
        String sqlCommand = "SELECT order_price, MONTH(order_date) AS month FROM price_view NATURAL JOIN Orders WHERE YEAR(order_date) = YEAR(CURDATE()) OR (YEAR(order_date) = (YEAR(CURDATE())-1) AND MONTH(order_date) > MONTH(CURDATE()));";
        Statement statement = null;
        ResultSet resultSet = null;
        double[] month = new double[12];

        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sqlCommand);

            while(resultSet.next()) {
                month[(resultSet.getInt("month"))-1] += resultSet.getDouble("order_price");
            }
            return month;

        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            getCleaner().closeResultSet(resultSet);
            getCleaner().closeStatement(statement);
        }
        return null;
    }

    /**
     * Method that finds the ten most ordered recipes. If there is less than ten recipes in the database, the method
     * will only show the number of recipes in the database.
     * @return String[][] where it stores recipe name and quantity
     */

    public String[][] top10Recipes() {
        String sqlCommand = "SELECT recipe_name, SUM(quantity) AS quantity FROM Recipe NATURAL JOIN Order_recipe GROUP BY recipe_name ORDER BY quantity DESC LIMIT 10;";
        Statement statement = null;
        ResultSet resultSet = null;
        String[][] recipes = new String[10][2];

        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sqlCommand);

            int count = 0;

            while(resultSet.next()) {
                recipes[count][0] = resultSet.getString("recipe_name");
                recipes[count][1] = String.valueOf(resultSet.getInt("quantity"));
                count++;
            }

            return recipes;
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            getCleaner().closeResultSet(resultSet);
            getCleaner().closeStatement(statement);
        }
        return null;
    }
}