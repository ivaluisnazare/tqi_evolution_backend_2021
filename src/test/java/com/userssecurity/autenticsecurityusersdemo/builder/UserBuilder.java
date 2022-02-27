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
public class UserBuilder {

    @Builder.Default
    private Integer id = 1;

    @Builder.Default
    private String email =  "mongret@gmail.com";

    @Builder.Default
    private String password = "hello0132";

    @Builder.Default
    private String name = "Izabella";

    @Builder.Default
    private String cpf = "77872339587";

    @Builder.Default
    private String rg = "056978425";

    @Builder.Default
    private String address = "Street of God";

    @Builder.Default
    private List<String> roles = Arrays.asList("MANAGERS", "USERS");

    public User toUser(){
        return new User(id, email, password, name, cpf, rg, address, roles);
    }
}
