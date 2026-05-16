package com.dragonblockinfinity.stats;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class PlayerStatsSync {

    public static final Identifier STATS_SYNC_ID = new Identifier("dbi", "stats_sync");

    public static void sendToClient(PlayerStatsComponent stats, ServerPlayerEntity player) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt((int) stats.getCurrentKi());
        buf.writeInt((int) stats.getMaxKi());
        buf.writeInt((int) stats.getCurrentStamina());
        buf.writeInt((int) stats.getMaxStamina());
        buf.writeInt((int) stats.getBattlePower());
        ServerPlayNetworking.send(player, STATS_SYNC_ID, buf);
    }
}
