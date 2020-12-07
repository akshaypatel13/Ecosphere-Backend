package com.example.Ecosphere.databaseConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    /*
    The DatabaseConnection performs the functionality to direct connect BackEnd application to the MySQL
    database stored on AWS RDS. The database credentials are stored in the heroku environment variables to
    avoid public access in the github repository but the sql queries for the table structure and stored
    procedures are available in the SQL folder to perform local implementation of the project.
     */

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
