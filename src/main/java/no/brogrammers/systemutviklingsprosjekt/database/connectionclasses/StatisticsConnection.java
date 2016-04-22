package no.brogrammers.systemutviklingsprosjekt.database.connectionclasses;

import no.brogrammers.systemutviklingsprosjekt.database.DatabaseConnection;

import java.sql.*;

/**
 * Created by Ingunn on 20.04.2016.
 *
 */
public class StatisticsConnection extends DatabaseConnection{
    //what weekday is most popular: orderdate and deliverydate (most popular time?)
    //most popular recipes
    //pie chart: what type of customer
    //income month
    //customer with most orders/gjennomsnittlig antall ordre pr bruker
    //what zip is most popular
    //pie chart: which user

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
     * ArrayList<Integer> popularWeekdaysDeliveryDate()
     * percent
     * @return
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

            for (int i = 0; i < weekdays.length; i++) {
                String test = "";
                test += weekdays[i] + "\n";
                System.out.println(test);
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
     *
     * View for the method: CREATE VIEW price_view AS (SELECT order_id, SUM(price*quantity) AS order_price FROM Orders NATURAL JOIN Order_recipe NATURAL JOIN Recipe GROUP BY order_id);
     * @return
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

            /*for (int i = 0; i < month.length; i++) {
                String test = "";
                test += month[i] + "\n";
                System.out.println(test);
            }*/

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
     * By times ordered
     * @return
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

            String test = "";
            for (int i = 0; i < recipes.length; i++) {
                test += (recipes[i][0] + recipes[i][1] + "\n");

            }
            System.out.println(test);

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