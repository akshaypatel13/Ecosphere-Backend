package com.example.Ecosphere.userAuthentication;

import com.example.Ecosphere.databaseConfiguration.StoredProcedure;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserPersistence {

    public boolean createUser(User user){
        StoredProcedure proc = null;
        try {
            proc = new StoredProcedure("spUser(?, ?, ?, ?, ?)");
            proc.setParameter(1, user.getUsername());
            proc.setParameter(2, user.getPassword());
            proc.setParameter(3,user.getEmail());
            proc.setParameter(4, user.getFirstName());
            proc.setParameter(5, user.getLastName());
            proc.statementExecute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (null != proc) {
                proc.cleanUp();
            }
        }
        return true;
    }

    public User loadUser(String username) {
        StoredProcedure proc = null;
        User user = new User();
        try {

            proc = new StoredProcedure("spCheckUser(?)");
            proc.setParameter(1, username);
            ResultSet results = proc.resultSetExecution();
            if (null != results) {
                while (results.next()) {
                    user.setUsername( results.getString(2));
                    user.setPassword( results.getString(3));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (null != proc) {
                proc.cleanUp();
            }
        }
        return user;
    }
}
