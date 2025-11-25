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

        // EVENTO QUE RODA A CADA TICK DO SERVIDOR
        ServerTickEvents.END_PLAYER_TICK.register(player -> tick(player));
    }

    public static void tick(ServerPlayerEntity player) {

        // ===============================
        //      COMPONENTES DO PLAYER
        // ===============================
        PlayerKiComponent ki = PlayerStatsProvider.getKi(player);
        PlayerStaminaComponent stamina = PlayerStatsProvider.getStamina(player);
        var stats = PlayerStatsProvider.getStats(player);

        // ========================================
        //           ATUALIZAR STATS BASE
        // ========================================
        int STR = stats.getSTR();
        int DEX = stats.getDEX();
        int SPI = stats.getSPI();
        int WILL = stats.getWILL();

        // KI usa STR, SPI, WILL
        ki.updateStats(STR, DEX, SPI, WILL);

        // Stamina usa DEX, WILL
        stamina.updateStats(STR, DEX, SPI, WILL);

        // ========================================
        //           KI — REGEN & EXAUSTÃO
        // ========================================
        ki.regenerateKi();
        ki.recoverExhaustion();

        // Sincronizar KI para o cliente
        KiSyncPacket.send(
                player,
                ki.getCurrentKi(),
                ki.getMaxKi(),
                ki.isExhausted()
        );

        // ========================================
        //         STAMINA — REGEN & EXAUSTÃO
        // ========================================
        stamina.regenerate();
        stamina.recoverExhaustion();

        // Sincronizar STAMINA
        StaminaSyncPacket.send(
                player,
                stamina.getCurrentStamina(),
                stamina.getMaxStamina(),
                stamina.isExhausted()
        );

        // ========================================
        //                    BP
        // ========================================
        int bp = stats.getBP();

        BpSyncPacket.send(player, bp);
    }
}
