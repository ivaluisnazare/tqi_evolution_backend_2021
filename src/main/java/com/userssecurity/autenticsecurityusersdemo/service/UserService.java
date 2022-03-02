package com.userssecurity.autenticsecurityusersdemo.service;

import com.userssecurity.autenticsecurityusersdemo.exceptions.UserAlreadyRegisteredException;
import com.userssecurity.autenticsecurityusersdemo.exceptions.UserNotFoundException;
import com.userssecurity.autenticsecurityusersdemo.models.User;
import com.userssecurity.autenticsecurityusersdemo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
@PersistenceContext
public class UserService{

    private UserRepository repository;
    private PasswordEncoder encoder;
    EntityManager entityManager;

    public User createUser(User user) {
        String pass = user.getPassword();
        user.setPassword(encoder.encode(pass));
        repository.save(user);
        return user;
    }

    public User create(User user){
        repository.save(user);
        return user;
    }

    public User createTest(User user) throws UserAlreadyRegisteredException {
        verifyIfAlreadyRegistered(user.getId());
        repository.save(user);
        return user;
    }

    public User getUser(String email){
        User user =  repository.findByEmail(email);
        return user;
    }

    public User putUser(User user, Integer id) {
        Optional<User> oldUser = repository.findById(id);
        if (oldUser.isPresent()) {
            User newUser = oldUser.get();
            newUser.setId(user.getId());
            newUser.setEmail(user.getEmail());
            newUser.setPassword(encoder.encode(user.getPassword()));
            newUser.setName(user.getName());
            newUser.setCpf(user.getCpf());
            newUser.setRg(user.getRg());
            newUser.setAddress(user.getAddress());
            newUser.setRoles(user.getRoles());
            repository.save(newUser);
        }
        return user;
    }

    public User findById(Integer id) throws UserNotFoundException {
        User foundUser = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return foundUser;
    }

    private void verifyIfAlreadyRegistered(Integer id) throws UserAlreadyRegisteredException {
        Optional<User> existUser = repository.findById(id);
        if(existUser.isPresent()){
            throw new UserAlreadyRegisteredException(id);
        }
    }
}








