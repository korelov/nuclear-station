package com.javaacademy.nuclearstation.department;

import com.javaacademy.nuclearstation.station.Exceptions.NuclearFuelIsEmptyException;
import com.javaacademy.nuclearstation.station.Exceptions.ReactorWorkException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class ReactorDepartment {
    private static final int FUEL_LIMIT = 100;
    private static final BigInteger ENERGY_PER_DAY = BigInteger.valueOf(10_000_000);
    private boolean isRunning = false;
    private int numberOfDayLaunches = 0;
    @Autowired
    @Lazy
    private SecurityDepartment securityDepartment;

    public BigInteger run() {
        if (isRunning) {
            securityDepartment.addAccident();
            throw new ReactorWorkException("Реактор уже работает");
        }
        isRunning = true;
        numberOfDayLaunches++;
        if (numberOfDayLaunches % FUEL_LIMIT == 0) {
            securityDepartment.addAccident();
            throw new NuclearFuelIsEmptyException("Топливо кончилось");
        }
        return ENERGY_PER_DAY;
    }

    public void stop() {
        if (!isRunning) {
            securityDepartment.addAccident();
            throw new ReactorWorkException("Реактор уже выключен");
        }
        isRunning = false;
    }
}
