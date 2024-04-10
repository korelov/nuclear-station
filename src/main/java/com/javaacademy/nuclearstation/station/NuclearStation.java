package com.javaacademy.nuclearstation.station;

import com.javaacademy.nuclearstation.department.ReactorDepartment;
import com.javaacademy.nuclearstation.department.SecurityDepartment;
import com.javaacademy.nuclearstation.economic.EconomicDepartment;
import com.javaacademy.nuclearstation.station.Exceptions.NuclearFuelIsEmptyException;
import jakarta.annotation.PreDestroy;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.stream.IntStream;

@Slf4j
@RequiredArgsConstructor
@Component
public class NuclearStation {
    @NonNull
    private final EconomicDepartment economicDepartment;
    @NonNull
    private final ReactorDepartment reactorDepartment;
    @Autowired
    @Lazy
    private SecurityDepartment securityDepartment;
    private int accidentCountAllTime;
    private BigInteger amountOfEnergyGenerated = new BigInteger("0");

    @PreDestroy
    private void destroy() {
        log.info("Всего выработано {} киловатт/часов", amountOfEnergyGenerated);
    }

    private void startYear() {
        log.info("Атомная станция начала работу");
        BigInteger energyPerYear = new BigInteger("0");
        for (int i = 0; i < 365; i++) {
            try {
                energyPerYear = energyPerYear.add(reactorDepartment.run());
                reactorDepartment.stop();
            } catch (NuclearFuelIsEmptyException e) {
                log.warn("Внимание! Происходят работы на атомной станции! Электричества нет!");
                reactorDepartment.stop();
            }
        }
        amountOfEnergyGenerated = amountOfEnergyGenerated.add(energyPerYear);
        log.info("Количество инцидентов за год: {}", securityDepartment.getAccidentCountPeriod());
        log.info("Атомная станция закончила работу. За год Выработано {} киловатт/часов", energyPerYear);
        log.info("Доход за год составил {} {}", economicDepartment.computeYearIncomes(energyPerYear),
                economicDepartment.getCountryCurrency());
        securityDepartment.reset();
    }

    public void start(int year) {
        log.info("Действие происходит в стране: {}", economicDepartment.getCountryName());
        IntStream.range(0, year).forEach(i -> startYear());
        log.info("Количество инцидентов за всю работу станции: {}", accidentCountAllTime);
    }

    public void incrementAccident(int count) {
        accidentCountAllTime += count;
    }
}
