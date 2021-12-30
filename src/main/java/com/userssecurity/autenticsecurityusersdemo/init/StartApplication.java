//package com.userssecurity.autenticsecurityusersdemo.init;
//
//import com.userssecurity.autenticsecurityusersdemo.models.User;
//import com.userssecurity.autenticsecurityusersdemo.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//@Component
//public class StartApplication implements CommandLineRunner {
//    @Autowired
//    private UserRepository repository;
//    @Transactional
//    @Override
//    public void run(String... args) throws Exception {
//        User user = repository.findByEmail("admin");
//        if(user==null){
//            user = new User();
//            user.setName("ADMIN");
//            user.setUsername("admin");
//            user.setEmail("ilan@gmail");
//            user.setPassword("master123");
//            user.getRoles().add("MANAGERS");
//            repository.save(user);
//        }
//        user = repository.findByEmail("user");
//        if(user ==null){
//            user = new User();
//            user.setName("USER");
//            user.setUsername("user");
//            user.setEmail("camilo@gmail");
//            user.setPassword("user123");
//            user.getRoles().add("USERS");
//            repository.save(user);
//        }
//    }
//}
