package com.example.xCodeInterview.controller;

import com.example.xCodeInterview.dto.SortRequest;
import com.example.xCodeInterview.dto.SortResponse;
import com.example.xCodeInterview.service.NumberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/numbers")
public class NumberController {

    private final NumberService numberService;

    @PostMapping("/sort-command")
    public ResponseEntity<SortResponse> sortNumbers(@RequestBody @Valid SortRequest sortRequest) {
        return ResponseEntity.ok(numberService.sortNumbers(sortRequest));
    }

}
