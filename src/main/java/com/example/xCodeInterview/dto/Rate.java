package com.example.xCodeInterview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Rate {

    private String currency;

    private String code;

    private Double mid;

}
