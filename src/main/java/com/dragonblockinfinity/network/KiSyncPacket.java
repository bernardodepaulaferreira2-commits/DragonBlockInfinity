package com.dragonblockinfinity.network;

import com.dragonblockinfinity.stats.PlayerStatsProvider;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class KiSyncPacket {

    public static final Identifier ID = new Identifier("dragonblockinfinity", "ki_sync");

    public static void send(ServerPlayerEntity player, int currentKi, int maxKi, boolean exhausted) {
        PacketByteBuf buf = new PacketByteBuf(net.minecraft.network.PacketByteBufs.create());
        buf.writeInt(currentKi);
        buf.writeInt(maxKi);
        buf.writeBoolean(exhausted);
        ServerPlayNetworking.send(player, ID, buf);
    }

    public static void registerReceiver() {
        ClientPlayNetworking.registerGlobalReceiver(ID, (client, handler, buf, responseSender) -> {

            int currentKi = buf.readInt();
            int maxKi = buf.readInt();
            boolean exhausted = buf.readBoolean();

            client.execute(() -> {
                if (client.player != null) {
                    PlayerStatsProvider.getClientKi().setClientValues(currentKi, maxKi, exhausted);
                }
            });
        });
    }
}
