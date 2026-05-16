package com.dragonblockinfinity.transformations;

public interface Transformation {
    String getName();
    double getPowerMultiplier();
    double getKiDrainPerSecond();
    double getStaminaDrainPerSecond();
    double getStrengthBonus();
    boolean isAvailableForRace(String race);
}
