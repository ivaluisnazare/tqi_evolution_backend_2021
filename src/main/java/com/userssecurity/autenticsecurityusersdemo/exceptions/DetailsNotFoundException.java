package com.userssecurity.autenticsecurityusersdemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DetailsNotFoundException extends Exception{

    public DetailsNotFoundException(Integer id) {
        super(String.format("Details with id %s not found in the system.", id));
    }
    public DetailsNotFoundException(String email) {
        super(String.format("Details with email %s not found in the system.", email));
    }
}
