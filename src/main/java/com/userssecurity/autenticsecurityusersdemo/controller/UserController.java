package com.userssecurity.autenticsecurityusersdemo.controller;

import com.userssecurity.autenticsecurityusersdemo.exceptions.UserAlreadyRegisteredException;
import com.userssecurity.autenticsecurityusersdemo.exceptions.UserNotFoundException;
import com.userssecurity.autenticsecurityusersdemo.models.User;
import com.userssecurity.autenticsecurityusersdemo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping("/test")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUserTest(@RequestBody User user) {
        userService.create(user);
    }

    @PostMapping("/user/test")
    public void  createTest(@RequestBody User user) throws UserAlreadyRegisteredException {
        userService.createTest(user);
    }

    @GetMapping("/{email}")
    public User getUser(@PathVariable @Valid String email){
        return userService.getUser(email);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public User putUser(@RequestBody User user, @PathVariable @Valid Integer id){
        return userService.putUser(user, id);
    }

    @GetMapping("/findById/{id}")
    public User findById(Integer id) throws UserNotFoundException{
        return userService.findById(id);
    }
}







