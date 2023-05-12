package com.example.eksheflyproject;
import java.sql.*;

public class Connector {
    public static Connection getConnection(String path) {
        String jdbcURL = "jdbc:sqlite:".concat(path);
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcURL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static ResultSet execute(String query, String path) throws SQLException {
        Connection conn = getConnection(path);
        Statement stm = conn.createStatement();
        conn.setAutoCommit(true); // if you want the changes to commit to the db file as fast as it's ready.
        ResultSet resultSet = stm.executeQuery(query);
        return resultSet;
    }
    public static void executeWithoutResults(String query, String path) throws SQLException {
        Connection conn = getConnection(path);
        Statement stm = conn.createStatement();
        conn.setAutoCommit(true); // if you want the changes to commit to the db file as fast as it's ready.
        stm.executeUpdate(query);
    }
}
