package com.dragonblockinfinity;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.dragonblockinfity.DragonBlockInfinityClient;

public class DragonBlockInfinityServer implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("DragonBlockInfinity");

    @Override
    public void onInitialize() {
        LOGGER.info("Inicializando DragonBlockInfinity Server...");

        ServerLifecycleEvents.SERVER_STARTED.register(this::onServerStart);
        ServerLifecycleEvents.SERVER_STOPPED.register(this::onServerStop);

        LOGGER.info("DragonBlockInfinity Server inicializado com sucesso!");
    }

    private void onServerStart(MinecraftServer server) {
        LOGGER.info("Servidor iniciado: {}", server.getServerMotd());
        
        try {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client != null) {
                LOGGER.info("Cliente Minecraft carregado: {}", client.getClass().getName());
            }
        } catch (Exception e) {
            LOGGER.warn("Não foi possível acessar o cliente Minecraft", e);
        }
    }

    private void onServerStop(MinecraftServer server) {
        LOGGER.info("Servidor parado!");
    }

}
