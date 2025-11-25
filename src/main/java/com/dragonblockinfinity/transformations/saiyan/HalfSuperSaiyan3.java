package com.dragonblockinfinity.transformations.saiyan;

import com.dragonblockinfinity.transformations.Transformation;

public class HalfSuperSaiyan3 implements Transformation {

    @Override
    public String getName() {
        return "Super Saiyan 3 (Hybrid)";
    }

    @Override
    public double getPowerMultiplier() {
        return 250.0;
        // Híbridos têm potencial bruto maior
    }

    @Override
    public double getKiDrainPerSecond() {
        return 150.0;
        // Forma extremamente instável — drenagem absurda
    }

    @Override
    public double getStaminaDrainPerSecond() {
        return 35.0;
        // Seu corpo não suporta essa forma
    }

    @Override
    public double getStrengthBonus() {
        return 90.0;
        // SSJ3 buff insano
    }

    @Override
    public boolean isAvailableForRace(String race) {
        return race.equalsIgnoreCase("Half Saiyan");
    }
}
