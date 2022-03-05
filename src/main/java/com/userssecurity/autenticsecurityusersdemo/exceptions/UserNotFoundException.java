package com.userssecurity.autenticsecurityusersdemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends Exception{

    public UserNotFoundException(Integer id) {
        super(String.format("User with id %s not found in the system.", id));
    }

}
