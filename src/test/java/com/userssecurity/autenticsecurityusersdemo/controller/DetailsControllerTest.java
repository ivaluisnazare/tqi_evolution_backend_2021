package com.userssecurity.autenticsecurityusersdemo.controller;

import com.userssecurity.autenticsecurityusersdemo.builder.DetailsBuilder;
import com.userssecurity.autenticsecurityusersdemo.builder.LoanDetailsBuilder;
import com.userssecurity.autenticsecurityusersdemo.exceptions.DetailsNotFoundException;
import com.userssecurity.autenticsecurityusersdemo.models.LoanDetails;
import com.userssecurity.autenticsecurityusersdemo.repository.LoanDetailsRepository;
import com.userssecurity.autenticsecurityusersdemo.service.DetailsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import static com.userssecurity.autenticsecurityusersdemo.utils.JsonConvertionUtils.asJsonString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.http.MediaType;
import java.util.Optional;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@DisplayName("Controller's class test")
public class DetailsControllerTest {

    private static final String DETAILS_API_URL_PATH = "/details";
    private static final String DETAILS_FIND_BY_API_URL_PATH = "/details/findById";
    private static final String DETAILS_DELETE_ID_API_URL_PATH = "/details/delete";
    private static final String DETAILS_DELETE_BY_ID_API_URL_PATH = "/detailsBy/delete";
    private static final Integer INVALID_BEER_ID = 3;

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

        this.mockMvc.perform(post(DETAILS_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(details)))
                .andExpect(status().isCreated());
    }

    @Test
    void whenPOSTDetailsIsCalledWithoutRequiredFieldThenAnErrorIsReturned() throws Exception {

        LoanDetails loanDetails = DetailsBuilder.builder().build().toDetails();
        loanDetails.setEmail(null);

        mockMvc.perform(post(DETAILS_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(loanDetails.getEmail())))
                .andExpect(status().isBadRequest());
    }

        @Test
        void whenGETQUERYIsCalledWithValidEmailThenOkStatusIsReturned() throws Exception {

        LoanDetails loanDetails = DetailsBuilder.builder().build().toDetails();

        lenient().when(loanDetailsRepository.findByEmail(loanDetails.getEmail())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get(DETAILS_API_URL_PATH + "/" + loanDetails.getEmail())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void whenDetailsIsCalledWithoutValidIdThenNoFoundStatusIsReturned() throws Exception {

        LoanDetails loanDetails = LoanDetailsBuilder.builder().build().toDetails();

        lenient().when(detailsService.findById(loanDetails.getId())).thenThrow(DetailsNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.get(DETAILS_FIND_BY_API_URL_PATH + "/" + loanDetails.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenDELETEIsCalledWithValidIdThenNoContentStatusIsReturned() throws Exception {

        LoanDetails loanDetails = DetailsBuilder.builder().build().toDetails();

        lenient().doNothing().when(detailsService).deleteById(loanDetails.getId());

        mockMvc.perform(MockMvcRequestBuilders.delete(DETAILS_DELETE_ID_API_URL_PATH + "/" + loanDetails.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void whenDELETEIsCalledWithInvalidIdThenNotFoundStatusIsReturned() throws Exception {

        lenient().doThrow(DetailsNotFoundException.class).when(detailsService).deleteById(INVALID_BEER_ID);

        mockMvc.perform(MockMvcRequestBuilders.delete(DETAILS_DELETE_BY_ID_API_URL_PATH + "/" + INVALID_BEER_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}



