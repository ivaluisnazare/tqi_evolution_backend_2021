package com.userssecurity.autenticsecurityusersdemo.service;

import com.userssecurity.autenticsecurityusersdemo.builder.UserBuilder;
import com.userssecurity.autenticsecurityusersdemo.exceptions.UserAlreadyRegisteredException;
import com.userssecurity.autenticsecurityusersdemo.exceptions.UserNotFoundException;
import com.userssecurity.autenticsecurityusersdemo.models.User;
import com.userssecurity.autenticsecurityusersdemo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void whenUserInformedThenItShouldBeCreated() throws UserAlreadyRegisteredException {

        User user = UserBuilder.builder().build().toUser();

        lenient().when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

        User createdUser = userService.create(user);
        assertThat(createdUser.getId(), is(equalTo(user.getId())));
        assertThat(createdUser.getEmail(), is(equalTo(user.getEmail())));
        assertThat(createdUser.getPassword(), is(equalTo(user.getPassword())));
        assertThat(createdUser.getName(), is(equalTo(user.getName())));
        assertThat(createdUser.getCpf(), is(equalTo(user.getCpf())));
        assertThat(createdUser.getRg(), is(equalTo(user.getRg())));
        assertThat(createdUser.getAddress(), is(equalTo(user.getAddress())));
        assertThat(createdUser.getRoles(), is(equalTo(user.getRoles())));
        verify(userRepository, atLeast(1)).save(user);
    }

    @Test
    void whenAlreadyRegisteredUserInformedThenAnExceptionShouldBeThrown() {

        User expectUser = UserBuilder.builder().build().toUser();

        when(userRepository.findById(expectUser.getId())).thenReturn(Optional.of(expectUser));

        assertThrows(UserAlreadyRegisteredException.class, () -> userService.createTest(expectUser));
        verify(userRepository, atLeast(1)).findById(expectUser.getId());
    }

    @Test
    void whenValidIdIsGivenThenReturnUser() throws UserNotFoundException {

        User findUser = UserBuilder.builder().build().toUser();

        lenient().when(userRepository.findById(findUser.getId())).thenReturn(Optional.of(findUser));

        User foundUser = userService.findById(findUser.getId());

        assertThat(foundUser, is(equalTo(findUser)));
        verify(userRepository, atLeast(1)).findById(findUser.getId());
    }

    @Test
    void whenInvalidIdIsGivenThenReturnThrows() throws UserNotFoundException {

        User findUser = UserBuilder.builder().build().toUser();

        when(userRepository.findById(findUser.getId())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.findById(findUser.getId()));
        verify(userRepository, times(1)).findById(findUser.getId());
    }
}
