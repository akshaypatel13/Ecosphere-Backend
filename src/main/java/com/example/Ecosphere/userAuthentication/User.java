package com.example.Ecosphere.userAuthentication;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {


    private long userId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Date createdAt;
    private Timestamp lastUpdated;
    private Encryption encryption;
    User(){
        userId = -1;
        username = "";
        password = "";
        firstName = "";
        lastName = "";
        createdAt = null;
        lastUpdated = null;
        encryption = new Encryption();

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public boolean createUserProfile(UserPersistence userDB) {
        this.password = encryption.passwordEncryption(getPassword());
        Boolean result = userDB.createUser(this);
        return result;
    }

    public boolean userLogin(UserPersistence userDB) {
        User userDup = userDB.loadUser(this.username);
        String receivedPassword = userDup.getPassword();
        String receivedEmail = userDup.getEmail();
        boolean result = encryption.passwordComparator(receivedPassword,this.password);
        if(result && receivedEmail.equals(this.email)){
            return true;
        }
        return false;
    }

}
