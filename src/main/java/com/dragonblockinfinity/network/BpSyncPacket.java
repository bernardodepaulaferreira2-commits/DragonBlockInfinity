package com.dragonblockinfinity.network;

import com.dragonblockinfinity.stats.PlayerStatsProvider;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class BpSyncPacket {

    public static final Identifier ID = new Identifier("dbi", "bp_sync");

    public static void send(ServerPlayerEntity player, int bp) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(bp);
        ServerPlayNetworking.send(player, ID, buf);
    }

    public static void registerReceiver() {
        ClientPlayNetworking.registerGlobalReceiver(ID, (client, handler, buf, responseSender) -> {
            int bp = buf.readInt();
            client.execute(() -> {
                if (client.player != null)
                    PlayerStatsProvider.getClientStats().setClientBP(bp);
            });
        });
    }
}
