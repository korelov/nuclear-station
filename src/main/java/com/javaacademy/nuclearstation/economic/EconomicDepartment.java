package com.javaacademy.nuclearstation.economic;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
public abstract class EconomicDepartment {
    @Value("${economic.country-name}")
    protected String countryName;
    @Value("${currency.name}")
    protected String countryCurrency;
    @Value("${currency.kilowatt-hour-rate}")
    protected BigDecimal kilowattHourRate;
    @Value("${currency.kwt-limit}")
    protected BigInteger kwtLimit;

    public abstract BigDecimal computeYearIncomes(BigInteger countElectricity);
}
