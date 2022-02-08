package com.userssecurity.autenticsecurityusersdemo.service;
import com.userssecurity.autenticsecurityusersdemo.builder.UserBuilder;
import com.userssecurity.autenticsecurityusersdemo.dtos.UserDTO;
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
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
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
        UserDTO expectedUserDTO = UserBuilder.builder().build().toUserDTO();
        User expectedSavedUser = userMapper.toModel(expectedUserDTO);

        lenient().when(userRepository.findByEmail(expectedUserDTO.getEmail())).thenReturn(expectedSavedUser);
        lenient().when(userRepository.save(expectedSavedUser)).thenReturn(expectedSavedUser);

        assertThat(expectedSavedUser.getId(), is(equalTo(expectedUserDTO.getId())));
        assertThat(expectedSavedUser.getEmail(), is(equalTo(expectedUserDTO.getEmail())));
        assertThat(expectedSavedUser.getName(), is(equalTo(expectedUserDTO.getName())));
        assertThat(expectedSavedUser.getPassword(), is(equalTo(expectedUserDTO.getPassword())));
        assertThat(expectedSavedUser.getRoles(), is(equalTo(expectedUserDTO.getRoles())));
        assertThat(expectedSavedUser.getCpf(), is(equalTo(expectedUserDTO.getCpf())));
        assertThat(expectedSavedUser.getRg(), is(equalTo(expectedUserDTO.getRg())));
        assertThat(expectedSavedUser.getAddress(), is(equalTo(expectedUserDTO.getAddress())));
    }

    @Test
    void whenAlreadyRegisteredUserInformedThenAnExceptionShouldBeThrown() {
        UserDTO expectedUserDTO = UserBuilder.builder().build().toUserDTO();
        User duplicatedUser = userMapper.toModel(expectedUserDTO);

        lenient().when(userRepository.findByEmail(expectedUserDTO.getEmail())).thenReturn(duplicatedUser);
        lenient().when(userRepository.save(duplicatedUser)).thenReturn(duplicatedUser);

        Assertions.assertThrows(Exception.class, () -> userService.createUser(duplicatedUser));
    }
}
