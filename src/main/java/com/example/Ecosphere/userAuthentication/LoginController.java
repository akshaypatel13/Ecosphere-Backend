package com.example.Ecosphere.userAuthentication;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    UserPersistence userPersistence;

    LoginController() {
        userPersistence = new UserPersistence();
    }

    @GetMapping("/")
    public String greeting() {
        return "dcfa";
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean userRegistration(@RequestBody User user) {
        System.out.println("fsdfsdfsdf");
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setEmail(user.getLastName());
        return user.createUserProfile(userPersistence);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String userLogin(@RequestBody User user) {
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());
        String status = user.userLogin(userPersistence);
        String result = null;
        if (status.equals("admin") || status.equals("user")) {
            result =  status;
        } else if (status.equals("")) {
            result = "Bad Credentials";
        }
        return result;
    }

}
