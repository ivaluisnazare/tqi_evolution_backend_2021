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
import java.util.List;

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
    @ResponseStatus(HttpStatus.CREATED)
    public void  createTest(@RequestBody User user) throws UserAlreadyRegisteredException {
        userService.createTest(user);
    }

    @GetMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable @Valid String email){
        return userService.getUser(email);
    }

    @GetMapping("/getAll/manager")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public User putUser(@RequestBody User user, @PathVariable @Valid Integer id){
        return userService.putUser(user, id);
    }

    @DeleteMapping("/deleteById/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer id) throws UserNotFoundException{
        userService.deleteById(id);
    }

    @DeleteMapping("/deleteAll")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllUsers(){
        userService.deleteAllUsers();
    }

    @GetMapping("/findById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User findById(@PathVariable Integer id) throws UserNotFoundException{
        return userService.findById(id);
    }
}







