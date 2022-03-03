package com.userssecurity.autenticsecurityusersdemo.builder;

import com.userssecurity.autenticsecurityusersdemo.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import java.util.Arrays;
import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserNewBuilder {

    @Builder.Default
    private Integer id = 2;

    @Builder.Default
    private String email =  "helobella@gmail.com";

    @Builder.Default
    private String password = "hello0132";

    @Builder.Default
    private String name = "Hello";

    @Builder.Default
    private String cpf = "77872339587";

    @Builder.Default
    private String rg = "056978425";

    @Builder.Default
    private String address = "Street of Great God";

    @Builder.Default
    private List<String> roles = Arrays.asList("MANAGERS", "USERS");

    public User toNewUser(){
        return new User(id, email, password, name, cpf, rg, address, roles);
    }
}


