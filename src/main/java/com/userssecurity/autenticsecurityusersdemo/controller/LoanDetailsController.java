package com.userssecurity.autenticsecurityusersdemo.controller;

import com.userssecurity.autenticsecurityusersdemo.models.LoanDetails;
import com.userssecurity.autenticsecurityusersdemo.service.DetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public List getQuery(@PathVariable @Valid String email){
        return detailsService.getQuery(email);
    }
    @GetMapping("/code/{id}")
    public List getCodeQuery(@PathVariable @Valid Integer id){
        return detailsService.getCodeQuery(id);
    }

    @PutMapping("/update/{id}")
    public void putRequest(@PathVariable @Valid Integer id, @RequestBody LoanDetails loanDetails){
        detailsService.putRequest(id, loanDetails);
    }

    }




