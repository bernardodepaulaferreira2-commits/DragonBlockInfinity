package com.dragonblockinfinity.stats;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;

public class PlayerStatsComponent {

    private int strength = 10;
    private int dexterity = 10;
    private int constitution = 10;
    private int willpower = 10;
    private int spirit = 10;
    private int mind = 10;

    private double maxHealth, maxKi, maxStamina, battlePower;
    private double currentHealth, currentKi, currentStamina;
    private boolean initialized = false;

    // client-side
    private int clientBP = 0;

    public PlayerStatsComponent() { recalculateStats(); }

    public static PlayerStatsComponent get(PlayerEntity player) {
        return PlayerStatsProvider.get(player);
    }

    public void recalculateStats() {
        this.maxHealth    = (constitution * 20) + (willpower * 5);
        this.maxKi        = (spirit * 35) + (willpower * 20) + (strength * 8) + (dexterity * 5);
        this.maxStamina   = (constitution * 15) + (dexterity * 20) + (mind * 5);
        this.battlePower  = (strength * 38) + (dexterity * 25) + (constitution * 40)
                          + (willpower * 30) + (spirit * 45) + (mind * 15);
        if (!initialized) {
            this.currentHealth  = this.maxHealth;
            this.currentKi      = this.maxKi;
            this.currentStamina = this.maxStamina;
            initialized = true;
        } else {
            if (currentHealth  > maxHealth)  currentHealth  = maxHealth;
            if (currentKi      > maxKi)      currentKi      = maxKi;
            if (currentStamina > maxStamina) currentStamina = maxStamina;
        }
    }

    // Atalhos curtos para uso interno
    public int getSTR()  { return strength; }
    public int getDEX()  { return dexterity; }
    public int getCON()  { return constitution; }
    public int getWILL() { return willpower; }
    public int getSPI()  { return spirit; }
    public int getMND()  { return mind; }
    public int getBP()   { return (int) battlePower; }

    // Getters completos
    public int getStrength()     { return strength; }
    public int getDexterity()    { return dexterity; }
    public int getConstitution() { return constitution; }
    public int getWillpower()    { return willpower; }
    public int getSpirit()       { return spirit; }
    public int getMind()         { return mind; }

    public double getMaxHealth()    { return maxHealth; }
    public double getMaxKi()        { return maxKi; }
    public double getMaxStamina()   { return maxStamina; }
    public double getBattlePower()  { return battlePower; }

    public double getCurrentHealth()  { return currentHealth; }
    public double getCurrentKi()      { return currentKi; }
    public double getCurrentStamina() { return currentStamina; }

    // Setters
    public void setStrength(int v)     { this.strength = v;     recalculateStats(); }
    public void setDexterity(int v)    { this.dexterity = v;    recalculateStats(); }
    public void setConstitution(int v) { this.constitution = v; recalculateStats(); }
    public void setWillpower(int v)    { this.willpower = v;    recalculateStats(); }
    public void setSpirit(int v)       { this.spirit = v;       recalculateStats(); }
    public void setMind(int v)         { this.mind = v;         recalculateStats(); }

    public void setCurrentHealth(double v)  { this.currentHealth  = clamp(v, 0, maxHealth); }
    public void setCurrentKi(double v)      { this.currentKi      = clamp(v, 0, maxKi); }
    public void setCurrentStamina(double v) { this.currentStamina = clamp(v, 0, maxStamina); }

    // Client BP
    public int  getClientBP()      { return clientBP; }
    public void setClientBP(int v) { this.clientBP = v; }

    public boolean consumeKi(double amount) {
        if (amount <= 0) return true;
        if (currentKi >= amount) { currentKi -= amount; return true; }
        currentKi = 0; return false;
    }

    public boolean consumeStamina(double amount) {
        if (amount <= 0) return true;
        if (currentStamina >= amount) { currentStamina -= amount; return true; }
        currentStamina = 0; return false;
    }

    public void regenTick(double ki, double stamina, double health) {
        this.currentKi      = clamp(this.currentKi + ki, 0, this.maxKi);
        this.currentStamina = clamp(this.currentStamina + stamina, 0, this.maxStamina);
        this.currentHealth  = clamp(this.currentHealth + health, 0, this.maxHealth);
    }

    private double clamp(double val, double min, double max) {
        return val < min ? min : val > max ? max : val;
    }

    public void saveToNbt(NbtCompound nbt) {
        nbt.putInt("str", strength); nbt.putInt("dex", dexterity);
        nbt.putInt("con", constitution); nbt.putInt("will", willpower);
        nbt.putInt("spi", spirit); nbt.putInt("mnd", mind);
        nbt.putDouble("currentHealth", currentHealth);
        nbt.putDouble("currentKi", currentKi);
        nbt.putDouble("currentStamina", currentStamina);
        nbt.putBoolean("initialized", initialized);
    }

    public void loadFromNbt(NbtCompound nbt) {
        if (nbt.contains("str"))  strength     = nbt.getInt("str");
        if (nbt.contains("dex"))  dexterity    = nbt.getInt("dex");
        if (nbt.contains("con"))  constitution = nbt.getInt("con");
        if (nbt.contains("will")) willpower    = nbt.getInt("will");
        if (nbt.contains("spi"))  spirit       = nbt.getInt("spi");
        if (nbt.contains("mnd"))  mind         = nbt.getInt("mnd");
        if (nbt.contains("currentHealth"))  currentHealth  = nbt.getDouble("currentHealth");
        if (nbt.contains("currentKi"))      currentKi      = nbt.getDouble("currentKi");
        if (nbt.contains("currentStamina")) currentStamina = nbt.getDouble("currentStamina");
        if (nbt.contains("initialized"))    initialized    = nbt.getBoolean("initialized");
        recalculateStats();
    }
}
