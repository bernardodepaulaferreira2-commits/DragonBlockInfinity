package com.dragonblockinfinity.transformations.saiyan;

import com.dragonblockinfinity.transformations.Transformation;

public class HalfSuperSaiyan1 implements Transformation {

    @Override
    public String getName() {
        return "Super Saiyan (Hybrid)";
    }

    @Override
    public double getPowerMultiplier() {
        return 60.0; 
        // Híbrido = mais potencial no SSJ1
    }

    @Override
    public double getKiDrainPerSecond() {
        return 35.0; 
        // Mais eficiente que um Saiyan puro
    }

    @Override
    public double getStaminaDrainPerSecond() {
        return 6.0;
    }

    @Override
    public double getStrengthBonus() {
        return 35.0; 
        // Mais força que SSJ1 puro
    }

    @Override
    public boolean isAvailableForRace(String race) {
        return race.equalsIgnoreCase("Half Saiyan");
    }
}
