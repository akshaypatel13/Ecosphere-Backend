package com.example.Ecosphere.databaseConfiguration;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class StoredProcedure {

    /*
    The StoredProcedure provides the persistence class to perform CRUD operations on the data using the
    stored procedures present in the database that helps avoid any layer violations and utilize the
    computation resources of the database server and after every data retrieval the cleanup is performed
    to any unwanted congestion in the network.
     */

    private String procedureName;
    private Connection connection;
    private CallableStatement statement;

    public StoredProcedure(String name) throws SQLException {
        this.procedureName = name;
        connection = null;
        statement = null;
        getDBConnection();
        callStoredProcedure();
    }

    public Connection getDBConnection() throws SQLException{
        DatabaseConnection dbConnection = new DatabaseConnection();
        connection = dbConnection.getDB();
        return connection;
    }

    public void cleanUp(){
        try{
            if(connection.isClosed() != true || statement != null){
                statement.close();
                connection.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void setParameter(int colIndex, String value) throws SQLException {
        statement.setString(colIndex,value);
    }

    public void setParameter(int colIndex, Long value) throws SQLException{
        statement.setLong(colIndex,value);
    }

    public void setParameter(int colIndex, int value) throws SQLException{
        statement.setInt(colIndex,value);
    }

    public ResultSet resultSetExecution() throws SQLException{
        if(statement.execute()){
            return statement.getResultSet();
        }
        else{
            return null;
        }
    }

    public void statementExecute() throws SQLException{
        statement.execute();
    }

    private void callStoredProcedure() throws SQLException
    {
        statement = connection.prepareCall("{call " + procedureName + "}");
    }

    public void registerOutputParameterLong(int paramIndex) throws SQLException
    {
        statement.registerOutParameter(paramIndex, java.sql.Types.BIGINT);
    }




}
