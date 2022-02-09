package com.userssecurity.autenticsecurityusersdemo.builder;

import com.userssecurity.autenticsecurityusersdemo.dtos.UserDTO;

import com.userssecurity.autenticsecurityusersdemo.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserBuilder {

        @Builder.Default
        private Integer id = 1;

        @Builder.Default
        private String email = "mongret@gmail.com";

        @Builder.Default
        private String password = "hello0132";

        @Builder.Default
        private String name = "Ivã";

        @Builder.Default
        private String cpf = "77872339587";

        @Builder.Default
        private String rg = "05685298";

        @Builder.Default
        private String address = "Sky Road";

        @Builder.Default
        private List<String> roles = new ArrayList<>(Arrays.asList("USER"));

        public User toUserDTO(){
                return new User(id, email, password, name, cpf, rg, address, roles);
        }


}
