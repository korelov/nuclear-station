package com.javaacademy.nuclearstation;

import com.javaacademy.nuclearstation.Exception.NuclearFuelIsEmptyException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Component
public class NuclearStation {
    @NonNull
    private ReactorDepartment reactorDepartment;
    @Autowired
    @Lazy
    private SecurityDepartment securityDepartment;
    private int accidentCountAllTime = 0;
    private BigInteger amountOfEnergyGenerated = new BigInteger("0");

    private void startYear() {
        System.out.println("Атомная станция начала работу");
        BigInteger energyPerYear = new BigInteger("0");
        for (int i = 0; i < 365; i++) {
            try {
                energyPerYear = energyPerYear.add(reactorDepartment.run());
                reactorDepartment.stop();
            } catch (NuclearFuelIsEmptyException e) {
                System.out.println("Внимание! Происходят работы на атомной станции! Электричества нет!");
                reactorDepartment.stop();
            }
        }
        amountOfEnergyGenerated = amountOfEnergyGenerated.add(energyPerYear);
        System.out.printf("Количество инцидентов за год: %d\n", securityDepartment.getAccidentCountPeriod());
        System.out.printf("Атомная станция закончила работу. За год Выработано %s киловатт/часов\n", energyPerYear);
        securityDepartment.reset();
    }

    public void start(int year) {
        IntStream.range(0, year).forEach(i -> startYear());
        System.out.printf("Количество инцидентов за всю работу станции: %d\n", accidentCountAllTime);
    }

    public void incrementAccident(int count) {
        accidentCountAllTime += count;
    }
}
