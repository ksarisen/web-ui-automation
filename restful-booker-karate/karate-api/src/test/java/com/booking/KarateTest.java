package com.booking;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KarateTest {

    @Test
    void runAllFeatures() {
        Results results = Runner.path("classpath:features").parallel(5);

        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }
}
