package com.example.Ecosphere.databaseConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance = null;
    private String url = "";
    private String user = "";
    private String password = "";

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
