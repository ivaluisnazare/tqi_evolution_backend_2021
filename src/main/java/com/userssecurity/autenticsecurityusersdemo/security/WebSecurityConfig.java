package com.userssecurity.autenticsecurityusersdemo.security;

import com.userssecurity.autenticsecurityusersdemo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor(onConstructor = @__(@Autowired))


public class WebSecurityConfig extends WebSecurityConfigurerAdapter {




    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST,"/login").permitAll()
                .antMatchers(HttpMethod.POST,"/users").permitAll()
                .antMatchers(HttpMethod.POST,"/details").hasAnyRole("MANAGERS")
                .antMatchers(HttpMethod.PUT,"/details/update/{id}").hasAnyRole("MANAGERS")
                .antMatchers(HttpMethod.PUT,"/users/update/{id}").hasAnyRole("MANAGERS")
                .antMatchers(HttpMethod.GET,"/details/{email}").hasAnyRole("USERS")
                .antMatchers(HttpMethod.GET,"/details/code/{id}").hasAnyRole("USERS")
                .antMatchers(HttpMethod.DELETE,"/details/delete/{id}").hasAnyRole("MANAGERS")

                .antMatchers("/managers").hasAnyRole("MANAGERS")
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/users").hasAnyRole("USERS")
                .anyRequest().authenticated().and().httpBasic();
        http.csrf().disable();
        // Don't use that configuration in a production environment ...
        http.headers().frameOptions().disable();
    }
    }

