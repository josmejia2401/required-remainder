package com.josmejia2401.requiredremainder.configuration;

import com.josmejia2401.requiredremainder.domain.port.RemainderPort;
import com.josmejia2401.requiredremainder.domain.service.RemainderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RemainderConfig {
    @Bean
    public RemainderPort remainderPort() {
        return new RemainderService();
    }
}

