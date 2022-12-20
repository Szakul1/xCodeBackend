package com.example.xCodeInterview.dto;

import com.example.xCodeInterview.enums.SortOrder;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SortRequest {

    private List<Double> numbers;

    @NotNull
    private SortOrder order;

}
