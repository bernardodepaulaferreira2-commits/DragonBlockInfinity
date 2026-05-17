package com.dragonblockinfinity.stats;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerRaceComponent {

    public enum Race {
        HUMAN, SAIYAN, HYBRID_SAIYAN, NAMEKIAN,
        ARCOSIAN, MAJIN, ANDROID, BIO_ANDROID, KAIOSHIN
    }

    private static final Map<UUID, PlayerRaceComponent> raceMap = new HashMap<>();

    private Race race = Race.HUMAN;
    private boolean hasGodKi = false;
    private boolean isTransformed = false;

    public PlayerRaceComponent() {}

    public static PlayerRaceComponent get(PlayerEntity player) {
        return raceMap.computeIfAbsent(player.getUuid(), id -> new PlayerRaceComponent());
    }

    public Race getRace() { return race; }
    public boolean hasGodKi() { return hasGodKi; }
    public boolean isTransformed() { return isTransformed; }

    public String getRaceName() {
        return switch (race) {
            case HUMAN -> "Human";
            case SAIYAN -> "Saiyan";
            case HYBRID_SAIYAN -> "Half-Saiyan";
            case NAMEKIAN -> "Namekian";
            case ARCOSIAN -> "Arcosian";
            case MAJIN -> "Majin";
            case ANDROID -> "Android";
            case BIO_ANDROID -> "Bio-Android";
            case KAIOSHIN -> "Kaioshin";
        };
    }

    public void setRace(Race r) { this.race = r; }
    public void setGodKi(boolean value) { this.hasGodKi = value; }
    public void setTransformed(boolean v) { this.isTransformed = v; }

    public void saveToNbt(NbtCompound nbt) {
        nbt.putString("race", race.name());
        nbt.putBoolean("godKi", hasGodKi);
        nbt.putBoolean("transformed", isTransformed);
    }

    public void loadFromNbt(NbtCompound nbt) {
        if (nbt.contains("race")) this.race = Race.valueOf(nbt.getString("race"));
        if (nbt.contains("godKi")) this.hasGodKi = nbt.getBoolean("godKi");
        if (nbt.contains("transformed")) this.isTransformed = nbt.getBoolean("transformed");
    }
}
