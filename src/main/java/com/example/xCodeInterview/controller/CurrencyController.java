package com.example.xCodeInterview.controller;

import com.example.xCodeInterview.dto.CurrencyRequest;
import com.example.xCodeInterview.dto.CurrencyResponse;
import com.example.xCodeInterview.service.CurrencyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    @PostMapping("/get-current-currency-value-command")
    public ResponseEntity<CurrencyResponse> getCurrentCurrency(@RequestBody @Valid CurrencyRequest currencyRequest) {
        return ResponseEntity.ok(currencyService.getCurrentCurrency(currencyRequest));
    }

}
