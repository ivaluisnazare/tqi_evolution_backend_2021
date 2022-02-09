package com.userssecurity.autenticsecurityusersdemo.service;
import com.userssecurity.autenticsecurityusersdemo.builder.UserBuilder;
import com.userssecurity.autenticsecurityusersdemo.dtos.UserDTO;
import com.userssecurity.autenticsecurityusersdemo.exceptions.DetailsAlreadyRegisteredException;
import com.userssecurity.autenticsecurityusersdemo.mapper.UserMapper;
import com.userssecurity.autenticsecurityusersdemo.models.User;
import com.userssecurity.autenticsecurityusersdemo.repository.UserRepository;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private UserMapper userMapper = UserMapper.INSTANCE;

    @Test
    void whenUserInformedThenItShouldBeCreated() {

        User user = UserBuilder.builder().build().toUserDTO();


        lenient().when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        lenient().when(userRepository.save(user)).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertThat(createdUser.getId(), is(equalTo(user.getId())));
        assertThat(createdUser.getEmail(), is(equalTo(user.getEmail())));
        assertThat(createdUser.getName(), is(equalTo(user.getName())));
        assertThat(createdUser.getPassword(), is(equalTo(user.getPassword())));
        assertThat(createdUser.getRoles(), is(equalTo(user.getRoles())));
        assertThat(createdUser.getCpf(), is(equalTo(user.getCpf())));
        assertThat(createdUser.getRg(), is(equalTo(user.getRg())));
        assertThat(createdUser.getAddress(), is(equalTo(user.getAddress())));
    }

    @Test
    void whenAlreadyRegisteredUserInformedThenAnExceptionShouldBeThrown(){
        User expectedUser = UserBuilder.builder().build().toUserDTO();
        lenient().when(userRepository.findByEmail(expectedUser.getEmail())).thenReturn(expectedUser);
        lenient().when(userRepository.save(expectedUser)).thenReturn(expectedUser);

//        assertThrows(DetailsAlreadyRegisteredException .class,  () -> userService.createUser(expectedUser));
    }
}
