package com.dragonblockinfinity.stats;

public class PlayerKiComponent {

    private int currentKi;
    private int maxKi;

    private boolean isExhausted = false;
    private boolean isOverflow = false;

    private float regenRate = 1.0f;   // base regen
    private float drainRate = 1.0f;   // base drain

    private float transformationMultiplier = 1.0f;

    public PlayerKiComponent() {
        this.maxKi = 100;   // valor base
        this.currentKi = this.maxKi;
    }

    // -------------------------------------------
    // KI BASEADO NOS STATUS DO JOGADOR
    // STR = poder de ataque
    // DEX = velocidade e controle
    // SPI = espírito, importante para ki máximo
    // WILL = força mental, aumenta regen
    // -------------------------------------------

    public void updateStats(int STR, int DEX, int SPI, int WILL) {
        int baseKi = 50;
        int strBonus = STR * 2;
        int spiBonus = SPI * 5;
        int dexBonus = (int) (DEX * 1.5);
        int willBonus = WILL * 3;

        this.maxKi = (int) ((baseKi + strBonus + spiBonus + dexBonus + willBonus) * transformationMultiplier);

        if (currentKi > maxKi) currentKi = maxKi;

        this.regenRate = 0.5f + (WILL * 0.05f) + (SPI * 0.03f);
        this.drainRate = 1.0f + (STR * 0.02f) + (DEX * 0.01f);
    }

    // -------------------------------------------
    // TRANSFORMAÇÕES
    // -------------------------------------------

    public void setTransformationMultiplier(float mult) {
        this.transformationMultiplier = mult;
        this.maxKi = (int) (this.maxKi * mult);
        if (currentKi > maxKi) currentKi = maxKi;
    }

    // -------------------------------------------
    // REGENERAÇÃO DE KI
    // -------------------------------------------

    public void regenerateKi() {
        if (isExhausted) return;

        currentKi += regenRate;
        if (currentKi > maxKi) {
            currentKi = maxKi;
            isOverflow = true;
        }
    }

    // -------------------------------------------
    // GASTAR KI
    // -------------------------------------------

    public boolean useKi(float amount) {
        float realDrain = amount * drainRate;

        if (currentKi - realDrain <= 0) {
            currentKi = 0;
            isExhausted = true;
            return false; // falhou
        }

        currentKi -= realDrain;
        return true;
    }

    // -------------------------------------------
    // LIBERAR O ESTADO DE EXAUSTÃO
    // -------------------------------------------

    public void recoverExhaustion() {
        if (isExhausted && currentKi > maxKi * 0.2f) {
            isExhausted = false;
        }
    }

    // -------------------------------------------
    // GETTERS
    // -------------------------------------------

    public int getCurrentKi() {
        return currentKi;
    }

    public int getMaxKi() {
        return maxKi;
    }

    public boolean isExhausted() {
        return isExhausted;
    }

    public boolean isOverflow() {
        return isOverflow;
    }
}
