package com.dragonblockinfinity.transformations.saiyan;

import com.dragonblockinfinity.transformations.Transformation;

public class SuperSaiyan1 implements Transformation {

    @Override
    public String getName() {
        return "Super Saiyan";
    }

    @Override
    public double getPowerMultiplier() {
        return 50.0;
    }

    @Override
    public double getKiDrainPerSecond() {
        return 50.0;
    }

    @Override
    public double getStaminaDrainPerSecond() {
        return 8.0;
    }

    @Override
    public double getStrengthBonus() {
        return 25.0;
    }

    @Override
    public boolean isAvailableForRace(String race) {
        return race.equalsIgnoreCase("Saiyan");
    }
}
