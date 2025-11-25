package com.dragonblockinfinity.hud;

import com.dragonblockinfinity.stats.PlayerStatsProvider;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class BpHud implements HudRenderCallback {

    @Override
    public void onHudRender(DrawContext context, float tickDelta) {

        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        var stats = PlayerStatsProvider.getClientStats();

        int screenWidth = client.getWindow().getScaledWidth();
        int screenHeight = client.getWindow().getScaledHeight();

        int bp = stats.getClientBP();

        String text = "BP: " + bp;

        context.drawText(
                client.textRenderer,
                text,
                screenWidth / 2 - 20,   // quase centro
                screenHeight - 55,      // logo acima do hotbar
                0xFFFFFFFF,
                true
        );
    }

    public static void register() {
        HudRenderCallback.EVENT.register(new BpHud());
    }
}
