package com.javaacademy.nuclearstation;

import com.javaacademy.nuclearstation.Exception.NuclearFuelIsEmptyException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@RequiredArgsConstructor
@Component
public class NuclearStation {
    @NonNull
    private ReactorDepartment reactorDepartment;
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
        System.out.printf("Атомная станция закончила работу. За год Выработано %s киловатт/часов\n", energyPerYear);
    }

    public void start(int year) {
        for (int i = 0; i < year; i++) {
            startYear();
        }
    }
}
