package com.userssecurity.autenticsecurityusersdemo.exceptions;

public class DetailsNotFoundException extends Exception{
    public DetailsNotFoundException(Long id){
        super(String.format("Details with id %s not found in the system.", id));
    }
}
