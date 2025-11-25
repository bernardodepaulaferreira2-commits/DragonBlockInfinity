package com.dragonblockinfinity.stats;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.entity.player.PlayerEntity;

public class PlayerStatsProvider {

    // Registro do componente CCA
    public static final ComponentKey<PlayerStatsComponent> PLAYER_STATS =
            ComponentRegistry.getOrCreate(
                    new net.minecraft.util.Identifier("dragonblockinfinity", "player_stats"),
                    PlayerStatsComponent.class
            );

    // Obter stats de um player
    public static PlayerStatsComponent get(PlayerEntity player) {
        return PLAYER_STATS.get(player);
    }

    // Sincronização automática aos clientes
    public static class Synced implements AutoSyncedComponent {
        private final PlayerEntity player;
        private final PlayerStatsComponent stats;

        public Synced(PlayerEntity player, PlayerStatsComponent stats) {
            this.player = player;
            this.stats = stats;
        }

        @Override
        public void sync() {
            PLAYER_STATS.sync(this.player);
        }
    }
}
