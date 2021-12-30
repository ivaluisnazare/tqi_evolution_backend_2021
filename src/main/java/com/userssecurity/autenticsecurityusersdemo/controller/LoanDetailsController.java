package com.userssecurity.autenticsecurityusersdemo.controller;

import com.userssecurity.autenticsecurityusersdemo.models.LoanDetails;
import com.userssecurity.autenticsecurityusersdemo.service.DetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/details")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LoanDetailsController {

    private DetailsService detailsService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postDetails(@RequestBody @Valid  LoanDetails loanDetails){
        detailsService.createLoanDetails(loanDetails);
    }

}
