package com.userssecurity.autenticsecurityusersdemo.service;

import java.util.List;

import com.userssecurity.autenticsecurityusersdemo.exceptions.DetailsNotFoundException;
import com.userssecurity.autenticsecurityusersdemo.models.LoanDetails;
import com.userssecurity.autenticsecurityusersdemo.repository.LoanDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@PersistenceContext
public class DetailsService {
    private LoanDetailsRepository repository;
    EntityManager entityManager;

    public LoanDetails createDetails(LoanDetails loanDetails){
        LocalDate localDate = LocalDate.now();
        loanDetails.setDayOfRequest(localDate);
        loanDetails.setPayDay(loanDetails.getDayOfRequest().plusMonths(loanDetails.getMonthsToPay()));
        loanDetails.setTotalToPay(loanDetails.getLoanAmount() * (Math.pow(1 + loanDetails.getFeesCharged(), loanDetails.getNumberOfInstallments())));
        loanDetails.setPortionAmount(loanDetails.getTotalToPay() / loanDetails.getNumberOfInstallments());
        repository.save(loanDetails);
        return loanDetails;
    }

    public List getQuery(String email){
        return entityManager.createQuery("SELECT e.code, e.loanAmount, e.numberOfInstallments FROM LoanDetails e  WHERE e.email = (:email)")
                .setParameter("email", email)
                .setMaxResults(10)
                .getResultList();
    }

    public List getCodeQuery(Integer id){
        return entityManager.createQuery("SELECT e.email, e.code, e.wage,  e.loanAmount, e.totalToPay, e.portionAmount, e.numberOfInstallments, e.payDay FROM LoanDetails e  WHERE e.id= (:id)")
                .setParameter("id", id)
                    .setMaxResults(1)
                .getResultList();
    }

        public ResponseEntity<LoanDetails> putRequest(Integer id, LoanDetails loanDetails){
        Optional<LoanDetails> loanOldDetailsById = repository.findById(id);
        if (loanOldDetailsById.isPresent()) {
            LoanDetails loanDetailsPut = loanOldDetailsById.get();
            LocalDate localDate = LocalDate.now();
            loanDetailsPut.setEmail(loanDetails.getEmail());
            loanDetailsPut.setCode(loanDetails.getCode());
            loanDetailsPut.setWage(loanDetails.getWage());
            loanDetailsPut.setLoanAmount(loanDetails.getLoanAmount());
            loanDetailsPut.setFeesCharged(loanDetails.getFeesCharged());
            loanDetailsPut.setNumberOfInstallments(loanDetails.getNumberOfInstallments());
            loanDetailsPut.setMonthsToPay(loanDetails.getMonthsToPay());
            loanDetailsPut.setDayOfRequest(localDate);
            loanDetailsPut.setPayDay(loanDetailsPut.getDayOfRequest().plusMonths(loanDetailsPut.getMonthsToPay()));
            loanDetailsPut.setTotalToPay(loanDetailsPut.getLoanAmount()*(Math.pow(1 + loanDetailsPut.getFeesCharged(), loanDetailsPut.getNumberOfInstallments())));
            loanDetailsPut.setPortionAmount(loanDetailsPut.getTotalToPay() / loanDetailsPut.getNumberOfInstallments());
            repository.save(loanDetailsPut);
            return new ResponseEntity<LoanDetails>(loanDetailsPut, HttpStatus.NO_CONTENT);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> DeleteLoanDetailsById(Integer id) {
        Optional<LoanDetails> loanDetails = repository.findById(id);
        if(loanDetails.isPresent()){
            repository.delete(loanDetails.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public LoanDetails findById(Integer id) throws DetailsNotFoundException {
        LoanDetails details = repository.findById(id)
                .orElseThrow(() -> new DetailsNotFoundException(id));
        return details;
    }

    public void deleteById(Integer id) throws DetailsNotFoundException{
        verifyIfExist(id);
        repository.deleteById(id.longValue());
    }

    private LoanDetails verifyIfExist(Integer id) throws DetailsNotFoundException{
        return repository.findById(id)
                .orElseThrow(() -> new DetailsNotFoundException(id));
    }

    }





