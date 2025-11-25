package com.dragonblockinfinity.transformations.saiyan;

import com.dragonblockinfinity.transformations.Transformation;

public class UltimateForm implements Transformation {

    @Override
    public String getName() {
        return "Ultimate (Mystic)";
    }

    @Override
    public double getPowerMultiplier() {
        return 350.0;
        // Mystic Gohan é mais forte que SSJ3 sem penalidades
    }

    @Override
    public double getKiDrainPerSecond() {
        return 0.0; 
        // Forma PERFEITA — sem drenagem
    }

    @Override
    public double getStaminaDrainPerSecond() {
        return 0.0;
        // Ideal para lutas longas
    }

    @Override
    public double getStrengthBonus() {
        return 120.0;
        // Potencial liberado
    }

    @Override
    public boolean isAvailableForRace(String race) {
        return race.equalsIgnoreCase("Half Saiyan");
    }
}
