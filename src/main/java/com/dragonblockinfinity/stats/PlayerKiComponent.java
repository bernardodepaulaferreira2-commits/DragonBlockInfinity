package com.dragonblockinfinity.stats;

public class PlayerKiComponent {

    private int currentKi, maxKi;
    private boolean isExhausted = false;
    private float regenRate = 1.0f;
    private float drainRate = 1.0f;
    private float transformationMultiplier = 1.0f;

    // client-side
    private int clientKi = 0, clientMaxKi = 100;
    private boolean clientExhausted = false;

    public PlayerKiComponent() { this.maxKi = 100; this.currentKi = 100; }

    public void updateStats(int STR, int DEX, int SPI, int WILL) {
        int base = 50 + STR * 2 + SPI * 5 + (int)(DEX * 1.5) + WILL * 3;
        this.maxKi = (int)(base * transformationMultiplier);
        if (currentKi > maxKi) currentKi = maxKi;
        this.regenRate = 0.5f + (WILL * 0.05f) + (SPI * 0.03f);
        this.drainRate = 1.0f + (STR * 0.02f) + (DEX * 0.01f);
    }

    public void setTransformationMultiplier(float mult) {
        this.transformationMultiplier = mult;
        this.maxKi = (int)(this.maxKi * mult);
        if (currentKi > maxKi) currentKi = maxKi;
    }

    public void regenerateKi() {
        if (isExhausted) return;
        currentKi += regenRate;
        if (currentKi > maxKi) currentKi = maxKi;
    }

    public boolean useKi(float amount) {
        float real = amount * drainRate;
        if (currentKi - real <= 0) { currentKi = 0; isExhausted = true; return false; }
        currentKi -= real;
        return true;
    }

    public void recoverExhaustion() {
        if (isExhausted && currentKi > maxKi * 0.2f) isExhausted = false;
    }

    public int getCurrentKi()  { return currentKi; }
    public int getMaxKi()      { return maxKi; }
    public boolean isExhausted() { return isExhausted; }

    // Client-side
    public void setClientValues(int current, int max, boolean exhausted) {
        this.clientKi = current;
        this.clientMaxKi = max;
        this.clientExhausted = exhausted;
    }
    public int getClientKi()      { return clientKi; }
    public int getClientMaxKi()   { return clientMaxKi; }
    public boolean isClientExhausted() { return clientExhausted; }
}
