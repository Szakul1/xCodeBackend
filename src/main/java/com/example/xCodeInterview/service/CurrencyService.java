package com.example.xCodeInterview.service;

import com.example.xCodeInterview.dto.CurrencyRequest;
import com.example.xCodeInterview.dto.CurrencyResponse;
import com.example.xCodeInterview.dto.CurrencyTable;
import com.example.xCodeInterview.dto.Rate;
import com.example.xCodeInterview.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    @Value("${currency-api-url}")
    private String currencyApiUrl;

    private final RestTemplate restTemplate;

    public CurrencyResponse getCurrentCurrency(CurrencyRequest currencyRequest) {
        CurrencyTable[] currencyTables = restTemplate.getForObject(currencyApiUrl, CurrencyTable[].class);
        if (currencyTables == null || currencyTables.length == 0) {
            throw new CustomException("Api returned empty value", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        CurrencyTable currencyTable = currencyTables[0];

        String currency = currencyRequest.getCurrency();
        Rate rate = currencyTable.getRates().stream()
                .filter(r -> r.getCode().equals(currency))
                .findFirst()
                .orElseThrow(() -> new CustomException("Currency: " + currency + " not found", HttpStatus.NOT_FOUND));

        return new CurrencyResponse(rate.getMid());
    }

}
