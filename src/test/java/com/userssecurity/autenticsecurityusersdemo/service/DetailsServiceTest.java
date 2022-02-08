package com.userssecurity.autenticsecurityusersdemo.service;

import com.userssecurity.autenticsecurityusersdemo.builder.DetailsBuilder;
import com.userssecurity.autenticsecurityusersdemo.dtos.DetailsDTO;
import com.userssecurity.autenticsecurityusersdemo.mapper.DetailsMapper;
import com.userssecurity.autenticsecurityusersdemo.models.LoanDetails;
import com.userssecurity.autenticsecurityusersdemo.repository.LoanDetailsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.lenient;


@ExtendWith(MockitoExtension.class)
public class DetailsServiceTest {

    @Mock
    private LoanDetailsRepository loanDetailsRepository;

    @InjectMocks
    private DetailsService detailsService;

    private DetailsBuilder detailsBuilder;

    private DetailsMapper detailsMapper = DetailsMapper.INSTANCE;

    @Test
    void whenDetailsInformedThenItShouldBeCreated() {

        DetailsDTO detailsBuilder = DetailsBuilder.builder().build().toDetailsDTO();
        LoanDetails expectedSavedDetails = detailsMapper.toModel(detailsBuilder);
        lenient().when(loanDetailsRepository.findByEmail(detailsBuilder.getEmail())).thenReturn(expectedSavedDetails);
        lenient().when(loanDetailsRepository.findByEmail(expectedSavedDetails.getEmail())).thenReturn(expectedSavedDetails);

    }
}
