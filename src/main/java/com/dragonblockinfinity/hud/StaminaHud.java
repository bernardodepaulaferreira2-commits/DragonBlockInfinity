package com.dragonblockinfinity.hud;

import com.dragonblockinfinity.stats.PlayerStatsProvider;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class StaminaHud implements HudRenderCallback {

    private static final int BAR_WIDTH = 120;
    private static final int BAR_HEIGHT = 10;

    @Override
    public void onHudRender(DrawContext context, float tickDelta) {

        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        var stamina = PlayerStatsProvider.getClientStamina();

        int screenWidth = client.getWindow().getScaledWidth();
        int screenHeight = client.getWindow().getScaledHeight();

        int x = screenWidth - BAR_WIDTH - 15; // lado direito
        int y = screenHeight / 2;             // meio da tela

        int current = stamina.getClientStamina();
        int max = stamina.getClientMaxStamina();

        // fundo preto
        context.fill(x, y, x + BAR_WIDTH, y + BAR_HEIGHT, 0xFF000000);

        // barra amarela
        int filled = (int)((current / (float)max) * BAR_WIDTH);
        context.fill(x, y, x + filled, y + BAR_HEIGHT, 0xFFFFFF00);

        // texto
        String text = "STA: " + current + " / " + max;
        context.drawText(client.textRenderer, text, x + 2, y - 10, 0xFFFFFFFF, true);
    }

    public static void register() {
        HudRenderCallback.EVENT.register(new StaminaHud());
    }
}
