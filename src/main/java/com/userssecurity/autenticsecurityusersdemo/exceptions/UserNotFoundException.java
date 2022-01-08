package com.userssecurity.autenticsecurityusersdemo.exceptions;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(String email){
        super(String.format("User with id %s not found in the system.", email));
    }
}
