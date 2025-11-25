package com.dragonblockinfinity.stats;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ClientPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;

public class PlayerStatsSync {

    public static final Identifier STATS_SYNC_ID =
            new Identifier("dragonblockinfinity", "sync_player_stats");

    // =========================
    // SERVER -> CLIENT
    // =========================
    public static void sendToClient(PlayerStatsComponent stats, net.minecraft.server.network.ServerPlayerEntity player) {
        PacketByteBuf buf = PacketByteBufs.create();

        buf.writeInt(stats.getStrength());
        buf.writeInt(stats.getDexterity());
        buf.writeInt(stats.getConstitution());
        buf.writeInt(stats.getWillpower());
        buf.writeInt(stats.getSpirit());
        buf.writeInt(stats.getMind());

        ServerPlayNetworking.send(player, STATS_SYNC_ID, buf);
    }

    // =========================
    // CLIENT RECEBE OS STATS
    // =========================
    public static void registerClientReceiver() {

        ClientPlayNetworking.registerGlobalReceiver(STATS_SYNC_ID, (client, handler, buf, responseSender) -> {

            int str = buf.readInt();
            int dex = buf.readInt();
            int con = buf.readInt();
            int will = buf.readInt();
            int spi = buf.readInt();
            int mnd = buf.readInt();

            client.execute(() -> {
                if (MinecraftClient.getInstance().player != null) {

                    PlayerStatsComponent stats =
                            PlayerStatsProvider.get(MinecraftClient.getInstance().player);

                    stats.setStrength(str);
                    stats.setDexterity(dex);
                    stats.setConstitution(con);
                    stats.setWillpower(will);
                    stats.setSpirit(spi);
                    stats.setMind(mnd);
                }
            });
        });
    }
}
