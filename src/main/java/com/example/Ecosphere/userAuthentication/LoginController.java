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
        return "hello";
    }

    @RequestMapping(value = "/register", consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"},method = RequestMethod.POST)
    public boolean userRegistration(@RequestBody User user) {
        user.setEmail(user.getEmail());
        System.out.println(user.getPassword());
        user.setPassword(user.getPassword());
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        return user.createUserProfile(userPersistence);
    }

    @RequestMapping(value = "/login",  consumes = {"application/json;charset=UTF-8"}, produces = {"application/json;charset=UTF-8"},method = RequestMethod.POST)
    public String userLogin(@RequestBody User user) {
        user.setEmail(user.getEmail());
        System.out.println("Controller"+user.getEmail());

        user.setPassword(user.getPassword());
        String status = user.userLogin(userPersistence);
        String result = null;
        System.out.print(status);
        if (status.equals("admin") || status.equals("user")) {
            result =  status;
        } else{
            result = "Bad Credentials";
        }
        System.out.print("status");
        System.out.print(result);
        return result;
    }

}
