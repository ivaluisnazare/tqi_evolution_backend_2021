package com.userssecurity.autenticsecurityusersdemo.controller;

import com.userssecurity.autenticsecurityusersdemo.builder.UserBuilder;
import com.userssecurity.autenticsecurityusersdemo.models.User;
import com.userssecurity.autenticsecurityusersdemo.repository.UserRepository;
import com.userssecurity.autenticsecurityusersdemo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import static com.userssecurity.autenticsecurityusersdemo.utils.JsonConvertionUtilsUser.asJsonString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@DisplayName("Service's class test")
public class UserControllerTest {


    private static final String USERS_API_URL_PATH = "/users";
    private static final String USER_DELETE_BY_ID_API_URL_PATH = "/users/deleteById";

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenPOSTIsCalledThenAUserIsCreated() throws Exception {

        User user = UserBuilder.builder().build().toUser();
        when(userService.createUser(user)).thenReturn(user);

        this.mockMvc.perform(post(USERS_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isCreated());
    }

    @Test
    void whenPOSTUserIsCalledWithoutRequiredFieldThenAnErrorIsReturned() throws Exception{

        User user = UserBuilder.builder().build().toUser();
        user.setEmail(null);

        this.mockMvc.perform(post(USERS_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user.getEmail())))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenUserIsCalledWithValidEmailThenOkStatusIsReturned() throws Exception {

        User user = UserBuilder.builder().build().toUser();
        lenient().when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

        this.mockMvc.perform(MockMvcRequestBuilders.get(USERS_API_URL_PATH + "/" + user.getEmail())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isOk());
    }
    @Test
    void whenUserCalledWithValidIdThenNoContentStatusIsReturnedAndDelete() throws Exception {
        User user = UserBuilder.builder().build().toUser();

        lenient().doNothing().when(userRepository).deleteById(user.getId());

        this.mockMvc.perform(MockMvcRequestBuilders.delete(USER_DELETE_BY_ID_API_URL_PATH + "/" + user.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
