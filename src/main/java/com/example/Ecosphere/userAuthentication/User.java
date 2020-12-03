package com.example.Ecosphere.userAuthentication;

import java.sql.Timestamp;
import java.util.Date;

public class User {

    private long userId;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Date createdAt;
    private Timestamp lastUpdated;
    private long roleID;
    private Encryption encryption;

    User() {
        userId = -1;
        password = "";
        firstName = "";
        lastName = "";
        createdAt = null;
        lastUpdated = null;
        encryption = new Encryption();
        roleID = -1;
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

    public long getRoleID() {
        return roleID;
    }

    public void setRoleID(long roleID) {
        this.roleID = roleID;
    }

    public boolean createUserProfile(UserPersistence userDB) {
        this.password = encryption.passwordEncryption(getPassword());
        return userDB.createUser(this);
    }

    public String userLogin(UserPersistence userDB) {
        User userDup = userDB.loadUser(this.email);
        String receivedPassword = userDup.getPassword();
        String role = "";
        boolean result = encryption.passwordComparator(this.password, receivedPassword);
        System.out.println(receivedPassword);
        if (result) {
            if (userDup.getRoleID() == 2) {
                role = "user";
            } else {
                role = "admin";
            }
        }
        return role;
    }

}
