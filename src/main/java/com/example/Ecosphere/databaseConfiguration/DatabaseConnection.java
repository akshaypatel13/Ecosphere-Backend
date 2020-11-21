package com.example.Ecosphere.databaseConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance = null;
    private String url = "jdbc:mysql://ti-5193.czlhweioasi8.ca-central-1.rds.amazonaws.com/sys?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String user = "admin";
    private String password = "admin1234";

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
