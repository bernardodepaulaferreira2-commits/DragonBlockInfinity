package com.dragonblockinfinity.events;

import com.dragonblockinfinity.network.BpSyncPacket;
import com.dragonblockinfinity.network.KiSyncPacket;
import com.dragonblockinfinity.network.StaminaSyncPacket;
import com.dragonblockinfinity.stats.PlayerKiComponent;
import com.dragonblockinfinity.stats.PlayerStaminaComponent;
import com.dragonblockinfinity.stats.PlayerStatsProvider;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerEnergyTickEvent {

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                tick(player);
            }
        });
    }

    public static void tick(ServerPlayerEntity player) {
        PlayerKiComponent ki       = PlayerStatsProvider.getKi(player);
        PlayerStaminaComponent stamina = PlayerStatsProvider.getStamina(player);
        var stats = PlayerStatsProvider.getStats(player);

        int STR  = stats.getSTR();
        int DEX  = stats.getDEX();
        int SPI  = stats.getSPI();
        int WILL = stats.getWILL();

        ki.updateStats(STR, DEX, SPI, WILL);
        stamina.updateStats(STR, DEX, SPI, WILL);

        ki.regenerateKi();
        ki.recoverExhaustion();

        KiSyncPacket.send(player, ki.getCurrentKi(), ki.getMaxKi(), ki.isExhausted());

        stamina.regenerate();
        stamina.recoverExhaustion();

        StaminaSyncPacket.send(player, stamina.getCurrentStamina(), stamina.getMaxStamina(), stamina.isExhausted());

        BpSyncPacket.send(player, stats.getBP());
    }
}
