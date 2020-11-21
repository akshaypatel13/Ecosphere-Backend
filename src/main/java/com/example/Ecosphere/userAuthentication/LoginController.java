package com.example.Ecosphere.userAuthentication;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
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

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public boolean userRegistration(@RequestParam(name = USERNAME) String username,
                                    @RequestParam(name = PASSWORD) String password,
                                    @RequestParam(name = FIRST_NAME) String firstName,
                                    @RequestParam(name = LAST_NAME) String lastName,
                                    @RequestParam(name = EMAIL) String email){
        System.out.println("fsdfsdfsdf");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        Boolean status = user.createUserProfile(userPersistence);
        return status;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public boolean userlogin(@RequestParam(name = USERNAME) String username,
                             @RequestParam(name = PASSWORD) String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        boolean status = user.userLogin(userPersistence);
        return status;
    }

}
