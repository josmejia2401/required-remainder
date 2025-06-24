package com.josmejia2401.requiredremainder.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.josmejia2401.requiredremainder.configuration.RemainderTestConfig;
import com.josmejia2401.requiredremainder.domain.model.Remainder;
import com.josmejia2401.requiredremainder.domain.port.RemainderPort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RemainderController.class)
@Import(RemainderTestConfig.class)
class RemainderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RemainderPort remainderPort;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testBatchEndpoint() throws Exception {
        List<Remainder> input = List.of(
                new Remainder(7, 5, 12345),
                new Remainder(5, 0, 4),
                new Remainder(10, 5, 15),
                new Remainder(17, 8, 54321),
                new Remainder(499999993, 9, 1000000000),
                new Remainder(10, 5, 187),
                new Remainder(2, 0, 999999999)
        );

        List<Long> output = List.of(12339L, 0L, 15L, 54306L, 999999995L, 185L, 999999998L);

        for (int i = 0; i < input.size(); i++) {
            Remainder req = input.get(i);
            when(remainderPort.compute(req.x(), req.y(), req.n()))
                    .thenReturn(output.get(i));
        }

        mockMvc.perform(post("/api/required-remainder/batch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(output)));
    }
}
