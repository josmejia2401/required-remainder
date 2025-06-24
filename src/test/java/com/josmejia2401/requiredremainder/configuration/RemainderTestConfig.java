package com.josmejia2401.requiredremainder.configuration;

import com.josmejia2401.requiredremainder.domain.port.RemainderPort;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class RemainderTestConfig {
    @Bean
    public RemainderPort remainderPort() {
        return Mockito.mock(RemainderPort.class);
    }
}

