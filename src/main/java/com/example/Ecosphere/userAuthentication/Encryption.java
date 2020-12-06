package com.example.Ecosphere.userAuthentication;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encryption {

    private BCryptPasswordEncoder passwordEncoder;

    Encryption(){
        passwordEncoder = new BCryptPasswordEncoder();
    }

    public String passwordEncryption(String password){
        return passwordEncoder.encode(password);
    }

    public boolean passwordComparator(String password, String encodedPassword){
        return passwordEncoder.matches(password,encodedPassword);
    }

}
