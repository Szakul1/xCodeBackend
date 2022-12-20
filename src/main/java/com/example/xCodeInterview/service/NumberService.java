package com.example.xCodeInterview.service;

import com.example.xCodeInterview.dto.SortRequest;
import com.example.xCodeInterview.dto.SortResponse;
import com.example.xCodeInterview.enums.SortOrder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NumberService {

    public SortResponse sortNumbers(SortRequest sortRequest) {
        if (sortRequest.getNumbers() == null) {
            return new SortResponse(null);
        }

        Comparator<Double> comparator = Comparator.naturalOrder();
        if (sortRequest.getOrder().equals(SortOrder.DESC)) {
            comparator = Comparator.reverseOrder();
        }

        List<Double> sorted = sortRequest.getNumbers().stream()
                .sorted(comparator)
                .collect(Collectors.toList());
        return new SortResponse(sorted);
    }

}
