package com.dragonblockinfinity.race.saiyan;

import com.dragonblockinfinity.race.Race;

public class HalfSaiyanRace implements Race {

    @Override
    public String getName() {
        return "Half Saiyan";
    }

    @Override
    public double getBaseKi() {
        return 35000000;
        // Menos Ki base que um Saiyan, mas cresce mais rápido com treinamento
    }

    @Override
    public double getBaseStamina() {
        return 18000000;
        // Meio Saiyan tem mais stamina e resistência
    }

    @Override
    public double getHealthRegen() {
        return 2.5;
        /*
         * Regen comparação:
         * Saiyan = 1.0
         * Half Saiyan = 2.5   <- excelente regen
         * Arcosian = 2.0
         * Android = 4.0
         * Bio Android = 7.0
         * Majin = 10.0
         */
    }

    @Override
    public boolean hasKiRecovery() {
        return true; 
    }

    @Override
    public boolean hasStaminaRecovery() {
        return true; 
    }
}
