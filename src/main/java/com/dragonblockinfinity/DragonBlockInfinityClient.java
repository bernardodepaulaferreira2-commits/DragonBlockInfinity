package com.dragonblockinfinity;

import com.dragonblockinfinity.hud.KiHud;
import com.dragonblockinfinity.hud.StaminaHud;
import com.dragonblockinfinity.hud.BpHud;
import com.dragonblockinfinity.hud.MainHudOverlay;
import com.dragonblockinfinity.inputs.MenuKeybind;
import com.dragonblockinfinity.network.KiSyncPacket;
import com.dragonblockinfinity.network.StaminaSyncPacket;
import com.dragonblockinfinity.network.BpSyncPacket;
import net.fabricmc.api.ClientModInitializer;

public class DragonBlockInfinityClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        KiHud.register();
        StaminaHud.register();
        BpHud.register();
        MainHudOverlay.register();

        KiSyncPacket.registerReceiver();
        StaminaSyncPacket.registerReceiver();
        BpSyncPacket.registerReceiver();

        MenuKeybind.register();
    }
}
