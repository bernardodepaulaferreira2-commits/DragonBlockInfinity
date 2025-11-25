package com.dragonblockinfinity.stats;

import net.minecraft.nbt.NbtCompound;

public class PlayerRaceComponent {

    public enum Race {
        HUMAN,
        SAIYAN,
        HYBRID_SAIYAN,
        NAMEKIAN,
        ARCOSIAN,
        MAJIN,
        ANDROID,
        BIO_ANDROID,
        KAIOSHIN
    }

    private Race race = Race.HUMAN;  // Raça padrão
    private boolean hasGodKi = false;
    private boolean isTransformed = false;

    public PlayerRaceComponent() {}

    // ==========================
    // GETTERS
    // ==========================
    public Race getRace() {
        return race;
    }

    public boolean hasGodKi() {
        return hasGodKi;
    }

    public boolean isTransformed() {
        return isTransformed;
    }

    // ==========================
    // SETTERS
    // ==========================

    public void setRace(Race r) {
        this.race = r;
    }

    public void setGodKi(boolean value) {
        this.hasGodKi = value;
    }

    public void setTransformed(boolean value) {
        this.isTransformed = value;
    }

    // ========================================================
    // SALVAR / CARREGAR (NBT)
    // ========================================================
    public void saveToNbt(NbtCompound nbt) {
        nbt.putString("race", race.name());
        nbt.putBoolean("godKi", hasGodKi);
        nbt.putBoolean("transformed", isTransformed);
    }

    public void loadFromNbt(NbtCompound nbt) {
        if (nbt.contains("race")) {
            this.race = Race.valueOf(nbt.getString("race"));
        }
        if (nbt.contains("godKi")) {
            this.hasGodKi = nbt.getBoolean("godKi");
        }
        if (nbt.contains("transformed")) {
            this.isTransformed = nbt.getBoolean("transformed");
        }
    }
}
