package com.example.Ecosphere.userAuthentication;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    private final String USERNAME = "username";
    private final String PASSWORD = "password";
    private final String FIRST_NAME = "firstName";
    private final String LAST_NAME = "lastName";
    private final   String EMAIL = "email";
    UserPersistence userPersistence;

    LoginController(){
        userPersistence = new UserPersistence();
    }

    @GetMapping("/")
    public String greeting()
    {
        return "dcfa";
    }

    @PostMapping(value = "/abc",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean userRegistration(@RequestBody User user){
        System.out.println("fsdfsdfsdf");
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setEmail(user.getLastName());
        Boolean status = user.createUserProfile(userPersistence);
        return status;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public boolean userLogin(@RequestBody User user){
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());
        boolean status = user.userLogin(userPersistence);
        return status;
    }

}
