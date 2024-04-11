package com.javaacademy.nuclearstation.station;

import com.javaacademy.nuclearstation.station.Exceptions.AccidentCountException;
import com.javaacademy.nuclearstation.station.Exceptions.YearCountException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
public class NuclearStationTest {
    @Autowired
    private NuclearStation nuclearStation;

    @Test
    public void testStartNuclearStation() {
        Assertions.assertDoesNotThrow(() -> nuclearStation.start(3));
    }

    @Test
    public void testIncrementAccidentException() {
        Assertions.assertThrows(AccidentCountException.class, () -> nuclearStation.incrementAccident(-1));
    }

    @Test
    public void testStartYearException() {
        Assertions.assertThrows(YearCountException.class, () -> nuclearStation.start(-1));
    }
}
