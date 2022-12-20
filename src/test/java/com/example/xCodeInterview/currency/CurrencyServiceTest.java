package com.example.xCodeInterview.currency;

import com.example.xCodeInterview.dto.CurrencyRequest;
import com.example.xCodeInterview.dto.CurrencyResponse;
import com.example.xCodeInterview.dto.CurrencyTable;
import com.example.xCodeInterview.dto.Rate;
import com.example.xCodeInterview.service.CurrencyService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CurrencyServiceTest {

    private CurrencyService currencyService;

    @BeforeAll
    public void setUp() {
        RestTemplate mock = mock(RestTemplate.class);
        List<Rate> rates = List.of(new Rate("Euro", "EUR", 4.5),
                new Rate("Dollar", "USD", 3.4));
        CurrencyTable currencyTable = new CurrencyTable("table", "number", LocalDate.now(), rates);
        when(mock.getForObject(Mockito.<String>any(), any())).thenReturn(new CurrencyTable[]{currencyTable});
        currencyService = new CurrencyService(mock);
    }

    @Test
    public void givenEur_whenGetValue_thenReturnValue() {
        CurrencyRequest eur = new CurrencyRequest("EUR");

        CurrencyResponse currencyResponse = currencyService.getCurrentCurrency(eur);

        assertEquals(4.5, currencyResponse.getValue());
    }

    @Test
    public void givenUsd_whenGetValue_thenReturnValue() {
        CurrencyRequest eur = new CurrencyRequest("USD");

        CurrencyResponse currencyResponse = currencyService.getCurrentCurrency(eur);

        assertEquals(3.4, currencyResponse.getValue());
    }

}
