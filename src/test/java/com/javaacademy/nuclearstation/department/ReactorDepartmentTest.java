package com.javaacademy.nuclearstation.department;

import com.javaacademy.nuclearstation.station.Exceptions.ReactorWorkException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigInteger;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
public class ReactorDepartmentTest {
    @Autowired
    private ReactorDepartment reactorDepartment;
    private static final BigInteger ENERGY_PER_DAY = BigInteger.valueOf(10_000_000);

    @Test
    public void testRunReactorPositive() {
        Assertions.assertDoesNotThrow(() -> reactorDepartment.run());
    }

    @Test
    public void testRunReactorNegative() {
        reactorDepartment.run();
        Assertions.assertThrows(ReactorWorkException.class, () -> reactorDepartment.run());
    }

    @Test
    public void testStopReactorPositive() {
        reactorDepartment.run();
        Assertions.assertDoesNotThrow(() -> reactorDepartment.stop());
    }

    @Test
    public void testStopReactorNegative() {
        Assertions.assertThrows(ReactorWorkException.class, () -> reactorDepartment.stop());
    }

    @Test
    public void testReactorEnergyPerDay() {
        Assertions.assertEquals(ENERGY_PER_DAY, reactorDepartment.run());
    }
}
