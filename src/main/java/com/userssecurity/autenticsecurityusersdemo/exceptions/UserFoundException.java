package com.userssecurity.autenticsecurityusersdemo.exceptions;

public class UserFoundException extends Exception{
    public UserFoundException(String email){
        super(String.format("User with email %s found in the system.", email));
    }
}
