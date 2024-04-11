package com.javaacademy.nuclearstation.economic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.math.BigInteger;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("france")
@SpringBootTest
public class FranceEconomicDepartmentTest {
    @Autowired
    private FranceEconomicDepartment franceEconomicDepartment;
    private static final BigInteger KWT_LIMIT = BigInteger.valueOf(1_000_000_000);

    @Test
    public void testLessKWTLimit() {
        BigInteger lessKWTLimit = BigInteger.valueOf(600_000_000);
        BigDecimal actual = franceEconomicDepartment.computeYearIncomes(lessKWTLimit);
        Assertions.assertEquals(new BigDecimal("300000000.00"), actual);
    }

    @Test
    public void testEquallyKWTLimit() {
        BigDecimal actual = franceEconomicDepartment.computeYearIncomes(KWT_LIMIT);
        Assertions.assertEquals(new BigDecimal("500000000.00"), actual);
    }

    @Test
    public void testMoreKWTLimit() {
        BigInteger moreKWTLimit = new BigInteger("3620000000");
        BigDecimal actual = franceEconomicDepartment.computeYearIncomes(moreKWTLimit);
        Assertions.assertEquals(new BigDecimal("1785842690.00"), actual);
    }
}
