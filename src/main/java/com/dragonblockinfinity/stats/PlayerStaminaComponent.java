package com.dragonblockinfinity.stats;

public class PlayerStaminaComponent {

    private int currentStamina = 0;
    private int maxStamina = 100;
    private boolean exhausted = false;

    // multiplicadores por raça (Sayajin > Humano > Android > etc)
    private float raceMultiplier = 1.0f;

    // multiplicador de transformação
    private float formMultiplier = 1.0f;

    // regeneração base
    private float baseRegen = 1.2f;

    // exaustão
    private int exhaustionTimer = 0;

    public void updateStats(int STR, int DEX, int SPI, int WILL) {
        // WILL aumenta stamina máxima
        this.maxStamina = 50 + (WILL * 8) + (DEX * 4);

        // Regula para não ficar menor que atual
        if (currentStamina > maxStamina)
            currentStamina = maxStamina;
    }

    public void setRaceMultiplier(float mult) {
        this.raceMultiplier = mult;
    }

    public void setFormMultiplier(float mult) {
        this.formMultiplier = mult;
    }

    public int getCurrentStamina() {
        return currentStamina;
    }

    public int getMaxStamina() {
        return maxStamina;
    }

    public boolean isExhausted() {
        return exhausted;
    }

    public void consume(int amount) {
        if (exhausted) return;

        currentStamina -= amount;

        if (currentStamina <= 0) {
            currentStamina = 0;
            exhausted = true;
            exhaustionTimer = 80; // ~4 segundos de fadiga
        }
    }

    public void regenerate() {
        if (exhausted) return;

        float regen = baseRegen * raceMultiplier * formMultiplier;

        currentStamina += regen;

        if (currentStamina > maxStamina)
            currentStamina = maxStamina;
    }

    public void recoverExhaustion() {
        if (!exhausted) return;

        exhaustionTimer--;

        if (exhaustionTimer <= 0) {
            exhausted = false;
            currentStamina = Math.min(currentStamina + 5, maxStamina);
        }
    }

    // CLIENT-SIDE para HUD
    private int clientCurrentStamina = 0;
    private int clientMaxStamina = 0;
    private boolean clientExhausted = false;

    public void setClientValues(int current, int max, boolean exhausted) {
        this.clientCurrentStamina = current;
        this.clientMaxStamina = max;
        this.clientExhausted = exhausted;
    }

    public int getClientStamina() {
        return clientCurrentStamina;
    }

    public int getClientMaxStamina() {
        return clientMaxStamina;
    }

    public boolean getClientExhausted() {
        return clientExhausted;
    }
}
