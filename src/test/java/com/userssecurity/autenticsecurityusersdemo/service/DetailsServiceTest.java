package com.userssecurity.autenticsecurityusersdemo.service;

import com.userssecurity.autenticsecurityusersdemo.builder.DetailsBuilder;
import static org.hamcrest.MatcherAssert.assertThat;

import com.userssecurity.autenticsecurityusersdemo.builder.LoanDetailsBuilder;
import com.userssecurity.autenticsecurityusersdemo.dtos.DetailsDTO;
import com.userssecurity.autenticsecurityusersdemo.exceptions.DetailsAlreadyRegisteredException;
import com.userssecurity.autenticsecurityusersdemo.exceptions.DetailsNotFoundException;
import com.userssecurity.autenticsecurityusersdemo.mapper.DetailsMapper;
import com.userssecurity.autenticsecurityusersdemo.models.LoanDetails;
import com.userssecurity.autenticsecurityusersdemo.repository.LoanDetailsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalMatchers.and;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@ExtendWith(MockitoExtension.class)
public class DetailsServiceTest {

    private DetailsMapper detailsMapper = DetailsMapper.INSTANCE;

    @Mock
    private LoanDetailsRepository loanDetailsRepository;

    @InjectMocks
    private DetailsService detailsService;

    @Test
    void whenDetailsInformedThenItShouldBeCreated() {

        LoanDetails detailsBuilder = DetailsBuilder.builder().build().toDetails();

        lenient().when(loanDetailsRepository.findByEmail(detailsBuilder.getEmail())).thenReturn(detailsBuilder);

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
    }

    @Test
    void whenAlreadyRegisteredLoanDetailsInformedThenAnExceptionShouldBeThrown() {

        LoanDetails expectDetails = DetailsBuilder.builder().build().toDetails();
        when(loanDetailsRepository.findByEmail(expectDetails.getEmail())).thenReturn(expectDetails);
        assertThrows(DetailsAlreadyRegisteredException.class, () -> detailsService.createLoanDetails(expectDetails));
    }
}
