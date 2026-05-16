package com.dragonblockinfinity.stats;

import net.minecraft.entity.player.PlayerEntity;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerStatsProvider {

    private static final Map<UUID, PlayerStatsComponent> statsMap = new HashMap<>();
    private static final Map<UUID, PlayerKiComponent> kiMap = new HashMap<>();
    private static final Map<UUID, PlayerStaminaComponent> staminaMap = new HashMap<>();

    private static final PlayerKiComponent clientKi = new PlayerKiComponent();
    private static final PlayerStaminaComponent clientStamina = new PlayerStaminaComponent();
    private static final PlayerStatsComponent clientStats = new PlayerStatsComponent();

    public static PlayerStatsComponent get(PlayerEntity player) {
        return statsMap.computeIfAbsent(player.getUuid(), id -> new PlayerStatsComponent());
    }

    public static PlayerStatsComponent getStats(PlayerEntity player) {
        return get(player);
    }

    public static PlayerKiComponent getKi(PlayerEntity player) {
        return kiMap.computeIfAbsent(player.getUuid(), id -> new PlayerKiComponent());
    }

    public static PlayerStaminaComponent getStamina(PlayerEntity player) {
        return staminaMap.computeIfAbsent(player.getUuid(), id -> new PlayerStaminaComponent());
    }

    public static PlayerKiComponent getClientKi() {
        return clientKi;
    }

    public static PlayerStaminaComponent getClientStamina() {
        return clientStamina;
    }

    public static PlayerStatsComponent getClientStats() {
        return clientStats;
    }

    public static void remove(UUID uuid) {
        statsMap.remove(uuid);
        kiMap.remove(uuid);
        staminaMap.remove(uuid);
    }
}
