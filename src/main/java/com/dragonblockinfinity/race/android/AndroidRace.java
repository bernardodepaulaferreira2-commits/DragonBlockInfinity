package com.dragonblockinfinity.race.android;

import com.dragonblockinfinity.race.Race;

public class AndroidRace implements Race {

    @Override
    public String getName() {
        return "Android";
    }

    @Override
    public double getBaseKi() {
        return Double.MAX_VALUE; // Ki infinito
    }

    @Override
    public double getBaseStamina() {
        return 9999999; // Stamina praticamente infinita
    }

    @Override
    public double getHealthRegen() {
        return 4.0; 
        /*
         * Regen média:
         * Saiyan = 1.0
         * Arcosian = 2.0
         * Android = 4.0  <- aqui
         * Bio Android = 7.0
         * Majin = 10.0
         */
    }

    @Override
    public boolean hasKiRecovery() {
        return false; // Não recupera ki naturalmente (como no DragonBlockC)
    }

    @Override
    public boolean hasStaminaRecovery() {
        return true; // Stamina infinita
    }
}
