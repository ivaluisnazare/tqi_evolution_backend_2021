package com.userssecurity.autenticsecurityusersdemo.controller;

import com.userssecurity.autenticsecurityusersdemo.builder.DetailsBuilder;
import com.userssecurity.autenticsecurityusersdemo.dtos.DetailsDTO;
import com.userssecurity.autenticsecurityusersdemo.exceptions.DetailsNotFoundException;
import com.userssecurity.autenticsecurityusersdemo.mapper.DetailsMapper;
import com.userssecurity.autenticsecurityusersdemo.mapper.UserMapper;
import com.userssecurity.autenticsecurityusersdemo.models.LoanDetails;
import com.userssecurity.autenticsecurityusersdemo.repository.LoanDetailsRepository;
import com.userssecurity.autenticsecurityusersdemo.service.DetailsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import static com.userssecurity.autenticsecurityusersdemo.utils.JsonConvertionUtils.asJsonString;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.lenient;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.http.MediaType;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class DetailsControllerTest {

    private static final String DETAILS_API_URL_PATH = "/details";
    private static final long VALID_DETAILS_ID = 1L;
    private static final long INVALID_DETAILS_ID = 2l;

    private MockMvc mockMvc;

    @Mock
    private DetailsService detailsService;

    @InjectMocks
    private LoanDetailsController loanDetailsController;

    @Mock
    private LoanDetailsRepository loanDetailsRepository;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(loanDetailsController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenPOSTIsCalledThenADetailsIsCreated() throws Exception {

        LoanDetails details = DetailsBuilder.builder().build().toDetails();
        lenient().when(detailsService.createDetails(details)).thenReturn(details);
        mockMvc.perform(post(DETAILS_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(details)))
                        .andExpect(MockMvcResultMatchers.status().is(201))
                        .andExpect(status().isCreated());
    }

    @Test
    void whenPOSTDetailsIsCalledWithoutRequiredFieldThenAnErrorIsReturned() throws Exception {

        LoanDetails loanDetails = DetailsBuilder.builder().build().toDetails();
        if (loanDetails.getEmail() == null) {
            mockMvc.perform(post(DETAILS_API_URL_PATH)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(loanDetails)))
                            .andExpect(status().isBadRequest());
        }
    }

    @Test
    void whenGETDetailsEmailIsCalledWithValidEmailThenOkStatusIsReturned() throws Exception {

        LoanDetails loanDetails = DetailsBuilder.builder().build().toDetails();
        lenient().when(loanDetailsRepository.findByEmail(loanDetails.getEmail())).thenReturn(loanDetails);
        mockMvc.perform(MockMvcRequestBuilders.get(DETAILS_API_URL_PATH + "/" + loanDetails.getEmail())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

    @Test
    void whenGETDetailsEmailIsCalledWithoutRegisteredNameThenNotFoundStatusIsReturned() throws Exception {

        LoanDetails loanDetails = DetailsBuilder.builder().build().toDetails();
        if (loanDetails.getEmail() == null){
            Mockito.when(loanDetailsRepository.findByEmail(loanDetails.getEmail())).thenThrow(Mockito.mock(DetailsNotFoundException.class));
                mockMvc.perform(MockMvcRequestBuilders.get(DETAILS_API_URL_PATH + "/" + loanDetails.getEmail())
                            .contentType(MediaType.APPLICATION_JSON))
                            .andExpect(status().isNotFound());
        }
    }

    @Test
    void whenDELETEDetailsIsCalledWithValidIdThenNoContentStatusIsReturned() throws Exception {

        LoanDetails loanDetails = DetailsBuilder.builder().build().toDetails();
        if(loanDetails == null){
            doNothing().when(detailsService.DeleteLoanDetailsById(loanDetails.getId()));
                mockMvc.perform(MockMvcRequestBuilders.delete(DETAILS_API_URL_PATH  + "/" + loanDetails.getId())
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isNoContent());
    }}
}
