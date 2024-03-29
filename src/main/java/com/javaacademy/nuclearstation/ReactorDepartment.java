package com.javaacademy.nuclearstation;

import com.javaacademy.nuclearstation.Exception.NuclearFuelIsEmptyException;
import com.javaacademy.nuclearstation.Exception.ReactorWorkException;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class ReactorDepartment {
    private boolean isRunning = false;
    private int numberOfDayLaunches = 0;

    public BigInteger run() {
        if (isRunning) {
            throw new ReactorWorkException("Реактор уже работает");
        }
        isRunning = true;
        numberOfDayLaunches++;
        if (numberOfDayLaunches % 100 == 0) {
            throw new NuclearFuelIsEmptyException("Топливо кончилось");
        }
        return new BigInteger("10000000");
    }

    public void stop() {
        if (!isRunning) {
            throw new ReactorWorkException("Реактор уже выключен");
        }
        isRunning = false;
    }
}
