package com.userssecurity.autenticsecurityusersdemo.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAlreadyRegisteredException extends Exception{
    public UserAlreadyRegisteredException(Integer id) {
        super(String.format("User with id %s already registered in the system.", id));

}}
