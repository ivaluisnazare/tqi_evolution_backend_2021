package com.userssecurity.autenticsecurityusersdemo.service;

import com.userssecurity.autenticsecurityusersdemo.models.LoanDetails;
import com.userssecurity.autenticsecurityusersdemo.models.User;
import com.userssecurity.autenticsecurityusersdemo.repository.LoanDetailsRepository;
import com.userssecurity.autenticsecurityusersdemo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
@PersistenceContext
public class UserService{

    private UserRepository repository;
    private LoanDetailsRepository loanDetailsRepository;
    private PasswordEncoder encoder;
    EntityManager entityManager;

    public void createUser(User user){
        String pass = user.getPassword();
        user.setPassword(encoder.encode(pass));
        repository.save(user);
    }

//
//    public void userRequest(String email, User user, LoanDetails loanDetails){
//        LocalDate localDate = LocalDate.now();
//
//        User userLast = repository.findByEmail(email);
//        LoanDetails loanDetails1 = new LoanDetails();
//        User userNewRequest = new User();
//
//        userNewRequest.setPassword(userLast.getPassword());
//        userNewRequest.setEmail(userLast.getEmail());
//        userNewRequest.setRoles(userLast.getRoles());
//        loanDetails1.setMinPay(0.2*loanDetails.getMeanPay());
//        userNewRequest.setLoanDetails(userLast.getLoanDetails());
//        userNewRequest.setCode(user.getCode());
//        userNewRequest.setWage(user.getWage());
//        userNewRequest.setLoanAmount(user.getLoanAmount());
//        userNewRequest.setFeesCharged(user.getFeesCharged());
//        userNewRequest.setNumberOfInstallments(user.getNumberOfInstallments());
//        userNewRequest.setMonthsToPay(user.getMonthsToPay());
//        userNewRequest.setDayOfRequest(localDate);
//        userNewRequest.setPayDay(userNewRequest.getDayOfRequest().plusMonths(userNewRequest.getMonthsToPay()));
//        userNewRequest.setTotalToPay(userNewRequest.getLoanAmount() * (Math.pow(1 + userNewRequest.getFeesCharged(), userNewRequest.getNumberOfInstallments())));
//        userNewRequest.setPortionAmount(userNewRequest.getTotalToPay() / userNewRequest.getNumberOfInstallments());
//
//        repository.save(userNewRequest);
//        loanDetailsRepository.save(loanDetails1);
//    }
//
//    public User findByEmail(String email){
//        User userView = repository.findByEmail(email);
//        return userView;
//    }
//
//    public ResponseEntity<User> newRequest(Integer id, User user){
//        Optional<User> userLastRequest = repository.findById(id);
//        if (userLastRequest.isPresent()) {
//            User userRequest = userLastRequest.get();
//            LocalDate localDate = LocalDate.now();
//
//            userRequest.setCode(user.getCode());
//            userRequest.setWage(user.getWage());
//            userRequest.setLoanAmount(user.getLoanAmount());
//            userRequest.setFeesCharged(user.getFeesCharged());
//            userRequest.setNumberOfInstallments(user.getNumberOfInstallments());
//            userRequest.setMonthsToPay(user.getMonthsToPay());
//            userRequest.setDayOfRequest(localDate);
//            userRequest.setPayDay(userRequest.getDayOfRequest().plusMonths(userRequest.getMonthsToPay()));
//            userRequest.setTotalToPay(userRequest.getLoanAmount() * (Math.pow(1 + userRequest.getFeesCharged(), userRequest.getNumberOfInstallments())));
//            userRequest.setPortionAmount(userRequest.getTotalToPay() / userRequest.getNumberOfInstallments());
//            return new ResponseEntity<User>(userRequest, HttpStatus.OK);
//        } else
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//    }
//
//    public List getQuery(String email){
//        return entityManager.createQuery("SELECT e.code, e.loanAmount, e.totalToPay, e.portionAmount, e.numberOfInstallments FROM User e  WHERE e.email= (:email)")
//                .setParameter("email", email)
//                .getResultList();
//    }
//
//    public Optional<User> findAllFromEmail(String email){
//     Optional<User> userAll = repository.findAllByEmail(email);
//     return userAll;
//    }



}




