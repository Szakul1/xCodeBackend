package com.example.xCodeInterview.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PingTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void given_whenPing_thenPong() throws Exception {
        MockHttpServletResponse mvcResult = mvc.perform(get("/status/ping"))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        String response = mvcResult.getContentAsString();

        assertEquals("pong", response);
    }

}
