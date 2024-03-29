package com.javaacademy.nuclearstation;

import com.javaacademy.nuclearstation.Exception.NuclearFuelIsEmptyException;
import com.javaacademy.nuclearstation.Exception.ReactorWorkException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class ReactorDepartment {
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
        if (numberOfDayLaunches % 100 == 0) {
            securityDepartment.addAccident();
            throw new NuclearFuelIsEmptyException("Топливо кончилось");
        }
        return new BigInteger("10000000");
    }

    public void stop() {
        if (!isRunning) {
            securityDepartment.addAccident();
            throw new ReactorWorkException("Реактор уже выключен");
        }
        isRunning = false;
    }
}
