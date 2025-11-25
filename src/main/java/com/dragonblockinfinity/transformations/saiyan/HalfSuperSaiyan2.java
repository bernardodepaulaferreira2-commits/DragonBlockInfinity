package com.dragonblockinfinity.transformations.saiyan;

import com.dragonblockinfinity.transformations.Transformation;

public class HalfSuperSaiyan2 implements Transformation {

    @Override
    public String getName() {
        return "Super Saiyan 2 (Hybrid)";
    }

    @Override
    public double getPowerMultiplier() {
        return 120.0;
        // Híbrido domina SSJ2 muito melhor
    }

    @Override
    public double getKiDrainPerSecond() {
        return 55.0;
        // Consumo moderado
    }

    @Override
    public double getStaminaDrainPerSecond() {
        return 10.0;
        // SSJ2 exige mais energia
    }

    @Override
    public double getStrengthBonus() {
        return 60.0;
        // Gohan Teen SSJ2 é um monstro
    }

    @Override
    public boolean isAvailableForRace(String race) {
        return race.equalsIgnoreCase("Half Saiyan");
    }
}
