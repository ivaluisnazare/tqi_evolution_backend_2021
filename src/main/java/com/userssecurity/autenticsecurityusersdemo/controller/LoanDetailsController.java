package com.userssecurity.autenticsecurityusersdemo.controller;

import com.userssecurity.autenticsecurityusersdemo.models.LoanDetails;
import com.userssecurity.autenticsecurityusersdemo.service.DetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/details")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LoanDetailsController {



    private DetailsService detailsService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postUser(@RequestBody LoanDetails loanDetails){
        detailsService.createDetails(loanDetails);
    }

    @GetMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)

    public List getQuery(@PathVariable @Valid String email){
        return detailsService.getQuery(email);
    }

    @GetMapping("/code/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List getCodeQuery(@PathVariable @Valid Integer id){
        return detailsService.getCodeQuery(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<LoanDetails> putRequest(@PathVariable @Valid Integer id, @RequestBody LoanDetails loanDetails){
        return detailsService.putRequest(id, loanDetails);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void DeleteLoanDetailsById(@PathVariable @Valid Integer id){
        detailsService.DeleteLoanDetailsById(id);
    }

    }




