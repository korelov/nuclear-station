package com.javaacademy.nuclearstation.department;

import com.javaacademy.nuclearstation.station.NuclearStation;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class SecurityDepartment {
    @Getter
    private int accidentCountPeriod = 0;
    private NuclearStation nuclearStation;

    public SecurityDepartment(NuclearStation nuclearStation) {
        this.nuclearStation = nuclearStation;
    }

    public void addAccident() {
        accidentCountPeriod++;
    }

    public void reset() {
        nuclearStation.incrementAccident(accidentCountPeriod);
        accidentCountPeriod = 0;
    }
}
