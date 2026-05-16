package com.dragonblockinfinity.race;

public interface Race {
    String getName();
    double getBaseKi();
    double getBaseStamina();
    double getHealthRegen();
    boolean hasKiRecovery();
    boolean hasStaminaRecovery();

    default float getPowerMultiplier() { return 1.0f; }
    default float getKiRegen() { return 0.5f; }
    default float getStaminaRegen() { return 0.5f; }
    default boolean hasInfiniteKi() { return false; }
    default boolean hasInfiniteStamina() { return false; }
}
