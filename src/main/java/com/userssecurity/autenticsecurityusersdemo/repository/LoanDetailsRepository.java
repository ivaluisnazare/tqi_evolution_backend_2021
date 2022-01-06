package com.userssecurity.autenticsecurityusersdemo.repository;

import com.userssecurity.autenticsecurityusersdemo.models.LoanDetails;
import com.userssecurity.autenticsecurityusersdemo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LoanDetailsRepository extends JpaRepository<LoanDetails, Long> {


    @Query("SELECT e FROM User e JOIN FETCH e.roles WHERE e.email= (:email)")
    public User findByEmail(@Param("email") String email);
    public Optional<LoanDetails> findById(@Param("id") Integer id);
    boolean existsById(String email);



}
