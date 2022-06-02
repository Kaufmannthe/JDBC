package com.example.testDB.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnector {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/testDB";
    private static final String JDBC_NAME = "root";
    private static final String JDBC_PASSWORD = "170299Artem_";
    private Connection connection;

    public Connection getConnection(){
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(JDBC_URL, JDBC_NAME, JDBC_PASSWORD);
                return connection;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }return connection;
    }
}
