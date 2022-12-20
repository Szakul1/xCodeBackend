package com.example.xCodeInterview.number;

import com.example.xCodeInterview.dto.SortRequest;
import com.example.xCodeInterview.dto.SortResponse;
import com.example.xCodeInterview.enums.SortOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SortIntegrationTest {
    private static final List<Double> NUMBERS = List.of(1.0, 5.0, 2.0, 7.0, 3.5, -2.0, 2.0);

    @Autowired
    MockMvc mvc;

    private static ObjectMapper objectMapper;

    @BeforeAll
    public static void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void givenCorrectRequest_whenSort_thenStatusOk() throws Exception {
        SortRequest sortRequest = new SortRequest(NUMBERS, SortOrder.ASC);
        String request = objectMapper.writeValueAsString(sortRequest);

        String response = mvc.perform(post("/numbers/sort-command")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        SortResponse sortResponse = objectMapper.readValue(response, SortResponse.class);

        assertNotNull(sortResponse.getNumbers());
    }

    @Test
    public void givenIncorrectRequest_whenSort_thenStatusBadRequest() throws Exception {
        SortRequest sortRequest = new SortRequest(NUMBERS, null);
        String request = objectMapper.writeValueAsString(sortRequest);

        mvc.perform(post("/numbers/sort-command")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
