package com.javaacademy.nuclearstation.economic;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

@Profile("france")
@Component
public class FranceEconomicDepartment extends EconomicDepartment {
    @Value("${currency.discount}")
    private BigDecimal discount;

    @Override
    public BigDecimal computeYearIncomes(BigInteger countElectricity) {
        BigDecimal totalIncome = new BigDecimal(BigInteger.ZERO);
        while (true) {
            if (countElectricity.compareTo(kwtLimit) < 0) {
                totalIncome = totalIncome.add(kilowattHourRate.multiply(new BigDecimal(countElectricity)));
                break;
            }
            BigDecimal kwtLimitCost = kilowattHourRate.multiply(new BigDecimal(kwtLimit));
            totalIncome = totalIncome.add(kwtLimitCost);
            countElectricity = countElectricity.subtract(kwtLimit);
            kilowattHourRate = kilowattHourRate.multiply(discount);
        }
        return totalIncome.setScale(2, RoundingMode.HALF_UP);
    }
}
