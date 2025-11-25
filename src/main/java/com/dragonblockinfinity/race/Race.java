package com.dragonblockinfinity.race;

public interface Race {

    // Nome da raça
    String getName();

    // Ki base da raça
    float getBaseKi();

    // Stamina base da raça
    float getBaseStamina();

    // Regeneração de Ki por tick
    float getKiRegen();

    // Regeneração de Stamina por tick
    float getStaminaRegen();

    // Multiplicador natural da raça
    float getPowerMultiplier();

    // Se a raça tem ki infinito (ex: Android, Bio-Android)
    default boolean hasInfiniteKi() {
        return false;
    }

    // Se a raça tem stamina infinita
    default boolean hasInfiniteStamina() {
        return false;
    }
}
