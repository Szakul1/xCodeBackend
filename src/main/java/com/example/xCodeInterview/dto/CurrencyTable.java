package com.example.xCodeInterview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class CurrencyTable {

    private String table;

    private String no;

    private LocalDate effectiveDate;

    private List<Rate> rates;

}
