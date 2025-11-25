package com.dragonblockinfinity.race.saiyan;

import com.dragonblockinfinity.race.Race;

public class SaiyanRace implements Race {

    @Override
    public String getName() {
        return "Saiyan";
    }

    @Override
    public double getBaseKi() {
        return 35000; 
        // Ki alto comparado a humanos e namekuseijins
    }

    @Override
    public double getBaseStamina() {
        return 5000;
        // Stamina média
    }

    @Override
    public double getHealthRegen() {
        return 1.0;
        // Baixa regeneração
    }

    @Override
    public boolean hasKiRecovery() {
        return true; 
        // Saiyajins recuperam Ki normalmente
    }

    @Override
    public boolean hasStaminaRecovery() {
        return true;
    }

    // Sistema Zenkai Boost
    public double applyZenkaiBoost(double currentHealth, double maxHealth, double powerLevel) {
        double percentage = currentHealth / maxHealth;

        if (percentage < 0.25) {
            return powerLevel * 1.5; // 50% boost
        } else if (percentage < 0.10) {
            return powerLevel * 2.0; // 100% boost
        }

        return powerLevel;
    }
}
