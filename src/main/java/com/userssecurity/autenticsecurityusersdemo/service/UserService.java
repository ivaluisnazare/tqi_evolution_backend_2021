package com.userssecurity.autenticsecurityusersdemo.service;

import com.userssecurity.autenticsecurityusersdemo.exceptions.UserNotFoundException;
import com.userssecurity.autenticsecurityusersdemo.models.LoanDetails;
import com.userssecurity.autenticsecurityusersdemo.models.User;
import com.userssecurity.autenticsecurityusersdemo.repository.LoanDetailsRepository;
import com.userssecurity.autenticsecurityusersdemo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))

public class UserService{

    private UserRepository repository;
    private PasswordEncoder encoder;


    public void createUser(User user){
        String pass = user.getPassword();
        user.setPassword(encoder.encode(pass));
        user.setTotalToPay(user.getLoanAmount()*(Math.pow(1+user.getFeesCharged(), user.getNumberOfInstallments())));
        user.setPortionAmount(user.getTotalToPay()/user.getNumberOfInstallments());
        repository.save(user);
    }

}
