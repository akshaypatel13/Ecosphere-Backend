package com.example.Ecosphere.databaseConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance = null;
    private String url = System.getenv("URL");
    private String user = System.getenv("USER");
    private String password = System.getenv("PASSWORD");

    public static DatabaseConnection instance(){
        if(instance == null){
            instance = new DatabaseConnection();
        }
        else{
            return instance;
        }
        return instance;
    }

    public Connection getDB() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }

}
