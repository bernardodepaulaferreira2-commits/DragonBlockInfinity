package com.dragonblockinfinity.events;

import com.dragonblockinfinity.stats.PlayerRaceComponent;
import com.dragonblockinfinity.stats.PlayerStatsComponent;
import com.dragonblockinfinity.stats.PlayerStatsProvider;
import com.dragonblockinfinity.stats.PlayerStatsSync;
import com.dragonblockinfinity.stats.PlayerTransformationComponent;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerTickEvent {

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(PlayerTickEvent::onServerTick);
    }

    private static void onServerTick(MinecraftServer server) {
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            try {
                tickPlayer(player);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void tickPlayer(ServerPlayerEntity player) {
        PlayerStatsComponent stats = PlayerStatsProvider.get(player);
        PlayerRaceComponent race = new PlayerRaceComponent(); // fallback
        try {
            // If you have a race provider, replace this with correct provider.
            // Example: PlayerRaceComponent race = PlayerRaceProvider.get(player);
        } catch (Exception ignored) {}

        if (stats == null) return;

        // Basic regen values (per tick) — tuned for 20 ticks = 1 second
        double baseKiRegen = 0.1;    // per tick baseline
        double baseStaminaRegen = 0.5;
        double baseHealthRegen = 0.0;

        // adjust regen by race (examples)
        // Android: no ki regen (unless Bio-Android)
        // Majin: big health regen
        // Bio-Android: high ki regen
        if (race != null) {
            switch (race.getRace()) {
                case ANDROID -> baseKiRegen = 0.0;
                case BIO_ANDROID -> baseKiRegen = 1.5;
                case MAJIN -> baseHealthRegen = 0.5;
                case SAIYAN, HYBRID_SAIYAN -> baseKiRegen = 0.2;
                default -> {}
            }
        }

        // Transformations drain ki/stamina: apply multipliers if active
        PlayerTransformationComponent transform = new PlayerTransformationComponent(); // fallback
        try {
            // Replace with your provider if implemented
            // transform = PlayerTransformationProvider.get(player);
        } catch (Exception ignored) {}

        if (transform != null && transform.isActive()) {
            // drain per tick = (kiDrainPerSecond / 20)
            // Here we use a small generic drain based on form multipliers
            double kiDrainPerSecond = transform.getSpiritMultiplier() * 0.5; // example
            double staminaDrainPerSecond = transform.getDexMultiplier() * 0.2;

            stats.consumeKi(kiDrainPerSecond / 20.0);
            stats.consumeStamina(staminaDrainPerSecond / 20.0);
        }

        // Regenerate
        stats.regenTick(baseKiRegen, baseStaminaRegen, baseHealthRegen);

        // If stamina is very low, apply movement penalty (to be implemented in movement handlers)

        // Sync to client periodically (every server tick is heavy; sync every 10 ticks)
        long ticks = player.server.getOverworld().getTime() % 20;
        if (ticks % 10 == 0) {
            PlayerStatsSync.sendToClient(stats, player);
        }
    }
}
