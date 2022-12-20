package com.example.xCodeInterview.currency;

import com.example.xCodeInterview.dto.CurrencyRequest;
import com.example.xCodeInterview.dto.CurrencyResponse;
import com.example.xCodeInterview.dto.CurrencyTable;
import com.example.xCodeInterview.dto.Rate;
import com.example.xCodeInterview.dto.SortRequest;
import com.example.xCodeInterview.dto.SortResponse;
import com.example.xCodeInterview.enums.SortOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CurrencyIntegrationTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    RestTemplate restTemplate;

    private ObjectMapper objectMapper;

    @BeforeAll
    public void setUp() {
        List<Rate> rates = List.of(new Rate("Euro", "EUR", 4.5),
                new Rate("Dollar", "USD", 3.4));
        CurrencyTable currencyTable = new CurrencyTable("table", "number", LocalDate.now(), rates);
        when(restTemplate.getForObject(Mockito.<String>any(), any())).thenReturn(new CurrencyTable[]{currencyTable});
        objectMapper = new ObjectMapper();
    }

    @Test
    public void givenCorrectRequest_whenGetValue_thenStatusOk() throws Exception {
        CurrencyRequest eur = new CurrencyRequest("EUR");
        String request = objectMapper.writeValueAsString(eur);

        String response = mvc.perform(post("/currencies/get-current-currency-value-command")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        CurrencyResponse currencyResponse = objectMapper.readValue(response, CurrencyResponse.class);

        assertNotNull(currencyResponse.getValue());
    }

    @Test
    public void givenIncorrectRequest_whenGetValue_thenStatusBadRequest() throws Exception {
        CurrencyRequest eur = new CurrencyRequest(null);
        String request = objectMapper.writeValueAsString(eur);

        mvc.perform(post("/currencies/get-current-currency-value-command")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
