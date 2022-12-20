package com.example.xCodeInterview.number;

import com.example.xCodeInterview.dto.SortRequest;
import com.example.xCodeInterview.dto.SortResponse;
import com.example.xCodeInterview.enums.SortOrder;
import com.example.xCodeInterview.service.NumberService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SortServiceTest {
    private static final List<Double> NUMBERS = List.of(1.0, 5.0, 2.0, 7.0, 3.5, -2.0, 2.0);

    NumberService numberService;

    @BeforeAll
    public void setUp() {
        numberService = new NumberService();
    }

    @Test
    public void givenNumbersWithASC_whenSort_thenReturnSorted() {
        SortRequest sortRequest = new SortRequest(NUMBERS, SortOrder.ASC);

        SortResponse sortResponse = numberService.sortNumbers(sortRequest);

        assertTrue(isSorted(sortResponse.getNumbers(), Comparator.naturalOrder()));
    }

    @Test
    public void givenNumbersWithDESC_whenSort_thenReturnSorted() {
        SortRequest sortRequest = new SortRequest(NUMBERS, SortOrder.DESC);

        SortResponse sortResponse = numberService.sortNumbers(sortRequest);

        assertTrue(isSorted(sortResponse.getNumbers(), Comparator.reverseOrder()));
    }

    @Test
    public void givenNull_whenSort_thenReturnNull() {
        SortRequest sortRequest = new SortRequest(null, SortOrder.DESC);

        SortResponse sortResponse = numberService.sortNumbers(sortRequest);

        assertNull(sortResponse.getNumbers());
    }

    private boolean isSorted(List<Double> numbers, Comparator<Double> comparator) {
        for (int i = 0; i < numbers.size() - 1; i++) {
            if (comparator.compare(numbers.get(i), numbers.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }

}
