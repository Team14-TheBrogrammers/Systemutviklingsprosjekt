package no.brogrammers.systemutviklingsprosjekt.database;

import java.sql.*;

/**
 * Created by Knut on 05.04.2016.
 * This class is used to properly close connections and other database objects.
 * These methods checks if the objects is null, and if not then the object is closed.
 * This class also handles setting auto-commit and rollback for a connection.
 */
public class ConnectionCleaner {

    /**
     * Method for closing a Statement.
     * @param statement is the statement that is closed.
     */
    public void closeStatement(Statement statement) {
        try {
            if(statement != null) {
                statement.close();
            }
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }

    /**
     * Method for closing a PreparedStatement
     * @param preparedStatement is the Prepared Statement that is closed.
     */
    public void closePreparedStatement(PreparedStatement preparedStatement) {
        try {
            if(preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }

    /**
     * Method for closing a ResultSet
     * @param resultSet is the Result Set that is closed.
     */
    public void closeResultSet(ResultSet resultSet) {
        try {
            if(resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }

    /**
     * Method for closing a Connection.
     * @param connection is the Connection that is closed.
     */
    public void closeConnection(Connection connection) {
        try {
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }

    /**
     * Method for doing a Rollback on a connection.
     * This method is used when something wrong happens with Prepared Statements and database connection,
     * and one need to set back the database to the same result as previously.
     * @param connection is the Connection that the Rollback is used on.
     */
    public void doRollback(Connection connection) {
        try {
            if(connection != null && !connection.getAutoCommit()) {
                connection.rollback();
            }
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }

    /**
     * Method for setting Auto Commit to true.
     * This method is used after one is finished with doing sql-commands and will be used in the "finally" in a try-catch-finally.
     * @param connection is the Connection that is set auto commit to true.
     */
    public void setAutoCommit(Connection connection) {
        try {
            if(connection != null && !connection.getAutoCommit()) {
                connection.setAutoCommit(true);
            }
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }
}
