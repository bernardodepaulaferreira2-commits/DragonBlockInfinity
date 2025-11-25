package com.dragonblockinfinity.stats;

import net.minecraft.nbt.NbtCompound;

public class PlayerStatsComponent {

    // Base stats avançados
    private int strength = 10;   // Força bruta
    private int dexterity = 10;  // Velocidade, esquiva
    private int constitution = 10; // Vida, defesa bruta
    private int willpower = 10;    // Resistência mental, ki clash, bloqueio
    private int spirit = 10;      // Ki power, ki control, dano de técnicas
    private int mind = 10;        // Buffs, skills de aprendizado

    // Atributos derivados
    private double maxHealth;
    private double maxKi;
    private double maxStamina;
    private double battlePower;

    // Valores dinâmicos (são salvos)
    private double currentHealth;
    private double currentKi;
    private double currentStamina;

    // Flags
    private boolean initialized = false;

    public PlayerStatsComponent() {
        recalculateStats();
        // current values serão inicializados no load ou no primeiro tick
    }

    // Recalcular vida/ki/stamina/BP com sistema avançado
    public void recalculateStats() {

        this.maxHealth = (constitution * 20) + (willpower * 5);

        this.maxKi =
                (spirit * 35) +
                (willpower * 20) +
                (strength * 8) +
                (dexterity * 5);

        this.maxStamina =
                (constitution * 15) +
                (dexterity * 20) +
                (mind * 5);

        // Fórmula avançada BP estilo DBC + Xenoverse
        this.battlePower =
                (strength * 38) +
                (dexterity * 25) +
                (constitution * 40) +
                (willpower * 30) +
                (spirit * 45) +
                (mind * 15);

        // Se ainda não inicializou os current, inicializa agora
        if (!initialized) {
            this.currentHealth = this.maxHealth;
            this.currentKi = this.maxKi;
            this.currentStamina = this.maxStamina;
            initialized = true;
        } else {
            // clamp current values
            if (currentHealth > maxHealth) currentHealth = maxHealth;
            if (currentKi > maxKi) currentKi = maxKi;
            if (currentStamina > maxStamina) currentStamina = maxStamina;
        }
    }

    // ===========================
    // GETTERS
    // ===========================
    public int getStrength() { return strength; }
    public int getDexterity() { return dexterity; }
    public int getConstitution() { return constitution; }
    public int getWillpower() { return willpower; }
    public int getSpirit() { return spirit; }
    public int getMind() { return mind; }

    public double getMaxHealth() { return maxHealth; }
    public double getMaxKi() { return maxKi; }
    public double getMaxStamina() { return maxStamina; }
    public double getBattlePower() { return battlePower; }

    public double getCurrentHealth() { return currentHealth; }
    public double getCurrentKi() { return currentKi; }
    public double getCurrentStamina() { return currentStamina; }

    // ===========================
    // SETTERS
    // ===========================
    public void setStrength(int value) { this.strength = value; recalculateStats(); }
    public void setDexterity(int value) { this.dexterity = value; recalculateStats(); }
    public void setConstitution(int value) { this.constitution = value; recalculateStats(); }
    public void setWillpower(int value) { this.willpower = value; recalculateStats(); }
    public void setSpirit(int value) { this.spirit = value; recalculateStats(); }
    public void setMind(int value) { this.mind = value; recalculateStats(); }

    public void setCurrentHealth(double v) { this.currentHealth = clamp(v, 0, maxHealth); }
    public void setCurrentKi(double v) { this.currentKi = clamp(v, 0, maxKi); }
    public void setCurrentStamina(double v) { this.currentStamina = clamp(v, 0, maxStamina); }

    // ===========================
    // KI / STAMINA helpers
    // ===========================
    public boolean consumeKi(double amount) {
        if (amount <= 0) return true;
        if (currentKi >= amount) {
            currentKi -= amount;
            return true;
        } else {
            // not enough ki
            currentKi = 0;
            return false;
        }
    }

    public boolean consumeStamina(double amount) {
        if (amount <= 0) return true;
        if (currentStamina >= amount) {
            currentStamina -= amount;
            return true;
        } else {
            currentStamina = 0;
            return false;
        }
    }

    public void regenTick(double kiRegen, double staminaRegen, double healthRegen) {
        // add regen and clamp
        this.currentKi = clamp(this.currentKi + kiRegen, 0, this.maxKi);
        this.currentStamina = clamp(this.currentStamina + staminaRegen, 0, this.maxStamina);
        this.currentHealth = clamp(this.currentHealth + healthRegen, 0, this.maxHealth);
    }

    private double clamp(double val, double min, double max) {
        if (val < min) return min;
        if (val > max) return max;
        return val;
    }

    // ===========================
    // SAVE / LOAD
    // ===========================
    public void saveToNbt(NbtCompound nbt) {
        nbt.putInt("str", strength);
        nbt.putInt("dex", dexterity);
        nbt.putInt("con", constitution);
        nbt.putInt("will", willpower);
        nbt.putInt("spi", spirit);
        nbt.putInt("mnd", mind);

        nbt.putDouble("currentHealth", currentHealth);
        nbt.putDouble("currentKi", currentKi);
        nbt.putDouble("currentStamina", currentStamina);
        nbt.putBoolean("initialized", initialized);
    }

    public void loadFromNbt(NbtCompound nbt) {
        if (nbt.contains("str")) strength = nbt.getInt("str");
        if (nbt.contains("dex")) dexterity = nbt.getInt("dex");
        if (nbt.contains("con")) constitution = nbt.getInt("con");
        if (nbt.contains("will")) willpower = nbt.getInt("will");
        if (nbt.contains("spi")) spirit = nbt.getInt("spi");
        if (nbt.contains("mnd")) mind = nbt.getInt("mnd");

        if (nbt.contains("currentHealth")) currentHealth = nbt.getDouble("currentHealth");
        if (nbt.contains("currentKi")) currentKi = nbt.getDouble("currentKi");
        if (nbt.contains("currentStamina")) currentStamina = nbt.getDouble("currentStamina");
        if (nbt.contains("initialized")) initialized = nbt.getBoolean("initialized");

        recalculateStats();
    }
}
