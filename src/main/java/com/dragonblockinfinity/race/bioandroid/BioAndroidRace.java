package com.dragonblockinfinity.race.bioandroid;

import com.dragonblockinfinity.race.Race;

public class BioAndroidRace implements Race {

    @Override
    public String getName() {
        return "Bio Android";
    }

    @Override
    public double getBaseKi() {
        return 500000000; 
        // Ki extremamente alto, mas não infinito
    }

    @Override
    public double getBaseStamina() {
        return 12000000; 
        // Bem acima do Android normal
    }

    @Override
    public double getHealthRegen() {
        return 7.0; 
        /*
         * Regen:
         * Saiyan = 1.0
         * Arcosian = 2.0
         * Android = 4.0
         * Bio Android = 7.0  <- aqui
         * Majin = 10.0
         */
    }

    @Override
    public boolean hasKiRecovery() {
        return true; // Diferente dos Androids, Bio Android RECUPERA Ki
    }

    @Override
    public boolean hasStaminaRecovery() {
        return true; // Recupera stamina normalmente
    }
}
