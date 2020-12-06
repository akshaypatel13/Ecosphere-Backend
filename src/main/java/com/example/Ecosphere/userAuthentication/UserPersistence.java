package com.example.Ecosphere.userAuthentication;

import com.example.Ecosphere.databaseConfiguration.StoredProcedure;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserPersistence {

    public boolean createUser(User user){
        StoredProcedure proc = null;
        try {
            proc = new StoredProcedure("spUser(?, ?, ?, ?, ?)");
            proc.setParameter(1,user.getEmail());
            proc.setParameter(2, user.getPassword());
            proc.setParameter(3, user.getFirstName());
            proc.setParameter(4, user.getLastName());
            proc.setParameter(5, 2);
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

    public User loadUser(String email) {
        StoredProcedure proc = null;
        User user = new User();
        System.out.print("Entered:"+email);
        try {

            proc = new StoredProcedure("spCheckUser(?)");
            proc.setParameter(1, email);
            ResultSet results = proc.resultSetExecution();
            if (null != results) {
                while (results.next()) {
                    System.out.println("Email:"+results.getString(1));
                    System.out.println("Password:"+results.getString(2));
                    user.setEmail( results.getString(1));
                    user.setPassword( results.getString(2));
                    user.setRoleID(results.getLong(3));
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
