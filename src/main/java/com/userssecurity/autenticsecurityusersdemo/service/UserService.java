package com.userssecurity.autenticsecurityusersdemo.service;

import com.userssecurity.autenticsecurityusersdemo.exceptions.DetailsAlreadyRegisteredException;
import com.userssecurity.autenticsecurityusersdemo.exceptions.DetailsNotFoundException;
import com.userssecurity.autenticsecurityusersdemo.exceptions.UserAlreadyRegisteredException;
import com.userssecurity.autenticsecurityusersdemo.exceptions.UserNotFoundException;
import com.userssecurity.autenticsecurityusersdemo.models.LoanDetails;
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








