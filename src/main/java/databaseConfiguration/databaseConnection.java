package databaseConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnection {

    private static databaseConnection instance = null;
    private String url = "";
    private String user = "";
    private String password = "";

    public static databaseConnection instance(){
        if(instance == null){
            instance = new databaseConnection();
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
