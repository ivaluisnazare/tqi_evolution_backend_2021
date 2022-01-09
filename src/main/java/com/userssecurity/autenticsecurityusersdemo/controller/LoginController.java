package com.userssecurity.autenticsecurityusersdemo.controller;

import com.userssecurity.autenticsecurityusersdemo.dtos.Login;
import com.userssecurity.autenticsecurityusersdemo.dtos.Section;
import com.userssecurity.autenticsecurityusersdemo.models.User;
import com.userssecurity.autenticsecurityusersdemo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LoginController {

    private UserRepository repository;
    private PasswordEncoder encoder;

    @PostMapping("/login")
    public Section logInto(@RequestBody Login login){
        User user = repository.findByEmail(login.getEmail());
        if (user != null){
            boolean passwordOK = encoder.matches(login.getPassword(), user.getPassword());
            if (!passwordOK){
                throw new RuntimeException("Invalid password for email: " + login.getEmail());
            }
            Section section = new Section();
            section.setLogin(user.getEmail());
            section.setLogin(user.getPassword());
            return section;
        }else {
            throw new RuntimeException("Error trying to login");
        }
    }
}
