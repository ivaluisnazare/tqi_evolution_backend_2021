package com.userssecurity.autenticsecurityusersdemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DetailsAlreadyRegisteredException extends  Exception{

    public DetailsAlreadyRegisteredException(String email) {
        super(String.format("Details with email %s already registered in the system.", email));
}}
