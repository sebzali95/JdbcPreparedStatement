package org.example.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection = null;


    public static Connection getConnetion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project-jdbc","root","admin");
            System.out.println("Connection to database has successfully !");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Database connection close  successfully !");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
