package com.accenture.jive.animalshelter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {


    public Connection getConnection() throws SQLException {
//throws SQLException means this is one kind of exception that we expect to happen in this class, it is a checked exception
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "root");
        connectionProps.put("password", "");

        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/animalshelter",
                connectionProps);

        System.out.println("\u001B[32m" + "Connection to Database successfull!" + "\u001B[0m");
        return conn;
    }
}
