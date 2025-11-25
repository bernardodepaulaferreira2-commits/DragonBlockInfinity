package com.dragonblockinfinity;

import com.dragonblockinfinity.hud.KiHud;
import com.dragonblockinfinity.hud.StaminaHud;
import com.dragonblockinfinity.hud.BpHud;
import com.dragonblockinfinity.network.KiSyncPacket;
import com.dragonblockinfinity.network.StaminaSyncPacket;
import com.dragonblockinfinity.network.BpSyncPacket;

import net.fabricmc.api.ClientModInitializer;

public class DragonBlockInfinityClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        // Registrar HUDs
        KiHud.register();
        StaminaHud.register();
        BpHud.register();

        // Registrar Recebimento de Packets
        KiSyncPacket.registerReceiver();
        StaminaSyncPacket.registerReceiver();
        BpSyncPacket.registerReceiver();

        // keybinding
        MenuKeybind.register();

        // botão na tela (HUD)
        HudRenderCallback.EVENT.register(new MenuButtonOverlay());

    }
}
