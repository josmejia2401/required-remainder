package com.josmejia2401.requiredremainder.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = DummyController.class)
class CorsConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenPreflightRequest_thenCorsHeadersPresent() throws Exception {
        mockMvc.perform(
                        options("/api/test")
                                .header("Origin", "http://example.com")
                                .header("Access-Control-Request-Method", "POST")
                                .header("Access-Control-Request-Headers", "X-Custom-Header") // Agregado
                )
                .andExpect(status().isOk())
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS"))
                .andExpect(header().string("Access-Control-Allow-Headers", "X-Custom-Header")); // Ahora responde el header pedido
    }

}
