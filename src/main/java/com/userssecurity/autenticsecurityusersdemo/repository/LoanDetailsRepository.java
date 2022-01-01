package com.userssecurity.autenticsecurityusersdemo.repository;

import com.userssecurity.autenticsecurityusersdemo.models.LoanDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LoanDetailsRepository extends JpaRepository<LoanDetails, Long> {

    @Query("SELECT e FROM User e JOIN FETCH e.roles WHERE e.id= (:id)")
    public Optional<LoanDetails> findById(@Param("id") Long id);
    boolean existsById(Long id);

}
