package com.userssecurity.autenticsecurityusersdemo.controller;

import com.userssecurity.autenticsecurityusersdemo.exceptions.UserNotFoundException;
import com.userssecurity.autenticsecurityusersdemo.models.LoanDetails;
import com.userssecurity.autenticsecurityusersdemo.models.User;
import com.userssecurity.autenticsecurityusersdemo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postUser(@RequestBody User user){
        userService.createUser(user);
    }

//    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public void newRequest(@PathVariable @Valid Integer id, @RequestBody User user) throws UserNotFoundException {
//        userService.newRequest(id, user);
//    }
//
//    @GetMapping("/{email}")
//    public User findByEmail(@PathVariable @Valid String email){ return userService.findByEmail(email);
//    }
//
//    @PostMapping("/newRequest/{email}")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void userRequest(@PathVariable @Valid String email, @RequestBody  User user, @RequestBody LoanDetails loanDetails){
//        userService.userRequest(email, user, loanDetails);
//    }
//
//    @GetMapping("/view/{email}")
//    public List getQuery(@PathVariable @Valid String email){
//        return userService.getQuery(email);
//    }
//
//
//    @GetMapping("/findAll/{email}")
//    public void findAllFromEmail(@PathVariable @Valid String email){
//        userService.findAllFromEmail(email);
//    }




    }







