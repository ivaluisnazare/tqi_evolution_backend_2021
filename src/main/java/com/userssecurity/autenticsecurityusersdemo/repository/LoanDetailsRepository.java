package com.userssecurity.autenticsecurityusersdemo.repository;

import com.userssecurity.autenticsecurityusersdemo.models.LoanDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanDetailsRepository extends JpaRepository<LoanDetails, Long> {
}
