package com.dragonblockinfinity.network;

import com.dragonblockinfinity.stats.PlayerStatsProvider;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class StaminaSyncPacket {

    public static final Identifier ID = new Identifier("dbi", "stamina_sync");

    public static void send(ServerPlayerEntity player, int current, int max, boolean exhausted) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(current);
        buf.writeInt(max);
        buf.writeBoolean(exhausted);
        ServerPlayNetworking.send(player, ID, buf);
    }

    public static void registerReceiver() {
        ClientPlayNetworking.registerGlobalReceiver(ID, (client, handler, buf, responseSender) -> {
            int current   = buf.readInt();
            int max       = buf.readInt();
            boolean exhausted = buf.readBoolean();
            client.execute(() -> {
                if (client.player != null)
                    PlayerStatsProvider.getClientStamina().setClientValues(current, max, exhausted);
            });
        });
    }
}
