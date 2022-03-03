package com.userssecurity.autenticsecurityusersdemo.service;

import com.userssecurity.autenticsecurityusersdemo.builder.DetailsBuilder;
import static org.hamcrest.MatcherAssert.assertThat;
import com.userssecurity.autenticsecurityusersdemo.exceptions.DetailsAlreadyRegisteredException;
import com.userssecurity.autenticsecurityusersdemo.exceptions.DetailsNotFoundException;
import com.userssecurity.autenticsecurityusersdemo.models.LoanDetails;
import com.userssecurity.autenticsecurityusersdemo.repository.LoanDetailsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DetailsServiceTest {

    @Mock
    private LoanDetailsRepository loanDetailsRepository;

    @InjectMocks
    private DetailsService detailsService;

    @Test
    void whenDetailsInformedThenItShouldBeCreated(){

        LoanDetails detailsBuilder = DetailsBuilder.builder().build().toDetails();

        lenient().when(loanDetailsRepository.findByEmail(detailsBuilder.getEmail())).thenReturn(Optional.empty());

        LoanDetails createdDetails = detailsService.createDetails(detailsBuilder);
        assertThat(createdDetails.getId(), is(equalTo(detailsBuilder.getId())));
        assertThat(createdDetails.getEmail(), is(equalTo(detailsBuilder.getEmail())));
        assertThat(createdDetails.getCode() , is(equalTo(detailsBuilder.getCode())));
        assertThat(createdDetails.getWage() , is(equalTo(detailsBuilder.getWage())));
        assertThat(createdDetails.getLoanAmount() , is(equalTo(detailsBuilder.getLoanAmount())));
        assertThat(createdDetails.getFeesCharged() , is(equalTo(detailsBuilder.getFeesCharged())));
        assertThat(createdDetails.getNumberOfInstallments() , is(equalTo(detailsBuilder.getNumberOfInstallments())));
        assertThat(createdDetails.getTotalToPay() , is(equalTo(detailsBuilder.getTotalToPay())));
        assertThat(createdDetails.getPortionAmount() , is(equalTo(detailsBuilder.getPortionAmount())));
        assertThat(createdDetails.getDayOfRequest() , is(equalTo(detailsBuilder.getDayOfRequest())));
        assertThat(createdDetails.getMonthsToPay() , is(equalTo(detailsBuilder.getMonthsToPay())));
        assertThat(createdDetails.getPayDay() , is(equalTo(detailsBuilder.getPayDay())));
        verify(loanDetailsRepository, atLeast(1)).save(detailsBuilder);
    }

    @Test
    void whenAlreadyRegisteredLoanDetailsInformedThenAnExceptionShouldBeThrown() {

        LoanDetails expectDetails = DetailsBuilder.builder().build().toDetails();

        when(loanDetailsRepository.findByEmail(expectDetails.getEmail())).thenReturn(Optional.of(expectDetails));

        assertThrows(DetailsAlreadyRegisteredException.class, () -> detailsService.createDetailsTest(expectDetails));
        verify(loanDetailsRepository, atLeast(1)).findByEmail(expectDetails.getEmail());
    }

    @Test
    void whenValidEmailIsGivenThenReturnLoanDetails() throws DetailsNotFoundException {

        LoanDetails findDetails = DetailsBuilder.builder().build().toDetails();

        when(loanDetailsRepository.findByEmail(findDetails.getEmail())).thenReturn(Optional.of(findDetails));

        LoanDetails foundDetails = detailsService.findByEmail(findDetails.getEmail());

        assertThat(foundDetails, is(equalTo(findDetails)));
        verify(loanDetailsRepository, atLeast(1)).findByEmail(findDetails.getEmail());
    }

    @Test
    void whenNotRegisteredEmailIsGivenThenThrowAnException() {

        LoanDetails findDetails = DetailsBuilder.builder().build().toDetails();

        when(loanDetailsRepository.findByEmail(findDetails.getEmail())).thenReturn(Optional.empty());

        assertThrows(DetailsNotFoundException.class, () -> detailsService.findByEmail(findDetails.getEmail()));
        verify(loanDetailsRepository, atLeast(1)).findByEmail(findDetails.getEmail());
    }

    @Test
    void whenListDetailsIsCalledThenReturnAListOfDetails() {
        LoanDetails loanDetails = DetailsBuilder.builder().build().toDetails();

        when(loanDetailsRepository.findAll()).thenReturn(Collections.singletonList(loanDetails));

        List<LoanDetails> foundListDetails = detailsService.findAllDetails();

        assertThat(foundListDetails, is(not(empty())));
        assertThat(foundListDetails.get(0), is(equalTo(loanDetails)));
        verify(loanDetailsRepository, atLeast(1)).findAll();
    }

    @Test
    void whenListDetailsIsCalledThenReturnAnEmptyListOfDetails() {
        when(loanDetailsRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

        List<LoanDetails> foundListDetails = detailsService.findAllDetails();

        assertThat(foundListDetails, is(empty()));
        verify(loanDetailsRepository, times(1)).findAll();
    }

    @Test
    void whenExclusionIsCalledWithValidIdThenADetailsShouldBeDeleted() throws DetailsNotFoundException{

        LoanDetails expectDeleteDetails = DetailsBuilder.builder().build().toDetails();

        lenient().when(loanDetailsRepository.findById(expectDeleteDetails.getId())).thenReturn(Optional.of(expectDeleteDetails));
        lenient().doNothing().when(loanDetailsRepository).deleteById(Long.valueOf(expectDeleteDetails.getId()));
        verify(loanDetailsRepository, atLeast(0)).findById(expectDeleteDetails.getId());
        verify(loanDetailsRepository, atLeast(0)).deleteById(Long.valueOf(expectDeleteDetails.getId()));
    }

    @Test
    void whenExclusionIsCalledWithInvalidIdThenADetailsNotShouldBeDeleted() throws DetailsNotFoundException{

        LoanDetails findDetails = DetailsBuilder.builder().build().toDetails();

        when(loanDetailsRepository.findById(findDetails.getId())).thenReturn(Optional.empty());

        assertThrows(DetailsNotFoundException.class, () -> detailsService.deleteById(findDetails.getId()));
        verify(loanDetailsRepository, atLeast(1)).findById(findDetails.getId());
    }
}
