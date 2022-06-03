package com.example.testDB.connection;

import com.example.testDB.repository.impl.PersonDAOImpl;


public class Test {
    public static void main(String[] args) {
        JDBCConnector jdbcConnector = new JDBCConnector();
        PersonDAOImpl personDAO = new PersonDAOImpl(jdbcConnector);





    }
}
