package com.userssecurity.autenticsecurityusersdemo.exceptions;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(Integer id){
        super(String.format("User with id %s not found in the system.", id));
    }
}
