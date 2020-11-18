package databaseConfiguration;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class storedProcedure {

    private String procedureName;
    private Connection connection;
    private CallableStatement statement;

    public storedProcedure(String name) throws SQLException {
        this.procedureName = name;
        connection = null;
        statement = null;
        getDBConnection();
        callStoredProcedure();
    }

    public Connection getDBConnection() throws SQLException{
        databaseConnection dbConnection = new databaseConnection();
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

}
