package com.josmejia2401.requiredremainder.configuration;

import com.josmejia2401.requiredremainder.domain.port.RemainderPort;
import com.josmejia2401.requiredremainder.domain.service.RemainderService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class RemainderConfigTest {

    @Test
    void remainderPortBeanIsLoadedAndIsCorrectType() {
        new ApplicationContextRunner()
                .withUserConfiguration(RemainderConfig.class)
                .run(context -> {
                    assertThat(context).hasSingleBean(RemainderPort.class);
                    RemainderPort port = context.getBean(RemainderPort.class);
                    assertThat(port).isInstanceOf(RemainderService.class);
                });
    }
}
