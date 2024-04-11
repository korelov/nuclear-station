package com.javaacademy.nuclearstation.economic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.math.BigInteger;

@ActiveProfiles("morocco")
@SpringBootTest
public class MoroccoEconomicDepartmentTest {
    @Autowired
    private MoroccoEconomicDepartment moroccoEconomicDepartment;

    @Test
    public void testComputeYearIncomes() {
        BigDecimal bigDecimal = moroccoEconomicDepartment.computeYearIncomes(new BigInteger("3620000000"));
        Assertions.assertEquals(new BigDecimal("18100000000.00"), bigDecimal);
    }

    @Test
    public void testMoreKWTLimit() {
        BigInteger moreKWTLimit = new BigInteger("10620000000");
        BigDecimal actual = moroccoEconomicDepartment.computeYearIncomes(moreKWTLimit);
        Assertions.assertEquals(new BigDecimal("58720000000.00"), actual);
    }
}
