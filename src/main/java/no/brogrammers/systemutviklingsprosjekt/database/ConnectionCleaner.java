package no.brogrammers.systemutviklingsprosjekt.database;

import java.sql.*;

/**
 * Created by Knut on 05.04.2016.
 * This class is used to properly close connections and other database objects.
 */
public abstract class ConnectionCleaner {

    public void closeStatement(Statement statement) {
        try {
            if(statement != null) {
                statement.close();
            }
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }

    public void closePreparedStatement(PreparedStatement preparedStatement) {
        try {
            if(preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }

    public void closeResultSet(ResultSet resultSet) {
        try {
            if(resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }

    public void closeConnection(Connection connection) {
        try {
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }

    public void doRollback(Connection connection) {
        try {
            if(connection != null && !connection.getAutoCommit()) {
                connection.rollback();
            }
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }

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
