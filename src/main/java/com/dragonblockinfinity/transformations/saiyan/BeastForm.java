package com.dragonblockinfinity.transformations.saiyan;

import com.dragonblockinfinity.transformations.Transformation;

public class BeastForm implements Transformation {

    @Override
    public String getName() {
        return "Beast";
    }

    @Override
    public double getPowerMultiplier() {
        return 950.0;
        // Forma monstruosa — mais forte que SSJ4 e UI base
    }

    @Override
    public double getKiDrainPerSecond() {
        return 18.0;
        // Forma extremamente instável
    }

    @Override
    public double getStaminaDrainPerSecond() {
        return 15.0;
        // Cansa muito rápido
    }

    @Override
    public double getStrengthBonus() {
        return 280.0;
        // Fúria completa híbrida
    }

    @Override
    public boolean isAvailableForRace(String race) {
        return race.equalsIgnoreCase("Half Saiyan");
    }
}
