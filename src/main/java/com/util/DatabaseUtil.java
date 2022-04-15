package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {


    public static Connection connectionToDatabase(){
    String dbURL = "jdbc:mysql://localhost:3306/TestDB";
    String username = "root";
    String password = "password";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbURL, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connected");
        return conn;
        }

    }

