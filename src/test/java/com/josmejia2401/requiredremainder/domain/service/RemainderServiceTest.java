package com.josmejia2401.requiredremainder.domain.service;

import com.josmejia2401.requiredremainder.domain.port.RemainderPort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RemainderServiceTest {

    private final RemainderPort service = new RemainderService();

    @Test
    void testSamples() {
        assertEquals(12339, service.compute(7, 5, 12345));
        assertEquals(0, service.compute(5, 0, 4));
        assertEquals(15, service.compute(10, 5, 15));
        assertEquals(54306, service.compute(17, 8, 54321));
        assertEquals(999999995, service.compute(499999993, 9, 1000000000));
        assertEquals(185, service.compute(10, 5, 187));
        assertEquals(999999998, service.compute(2, 0, 999999999));
    }
}

