package com.userssecurity.autenticsecurityusersdemo.controller;

import com.userssecurity.autenticsecurityusersdemo.builder.DetailsBuilder;
import com.userssecurity.autenticsecurityusersdemo.dtos.DetailsDTO;
import com.userssecurity.autenticsecurityusersdemo.models.LoanDetails;
import com.userssecurity.autenticsecurityusersdemo.service.DetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import static com.userssecurity.autenticsecurityusersdemo.utils.JsonConvertionUtils.asJsonString;
import static org.mockito.ArgumentMatchers.nullable;
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
    void whenPOSTIsCalledWithoutRequiredFieldThenAnErrorIsReturned() throws Exception {

        LoanDetails loanDetails = DetailsBuilder.builder().build().toDetails();
        if (loanDetails.getEmail() == null) {
            mockMvc.perform(post(DETAILS_API_URL_PATH)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(loanDetails)))
                            .andExpect(status().isBadRequest());

        }


    }
}
