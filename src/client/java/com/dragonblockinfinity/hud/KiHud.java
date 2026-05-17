package com.dragonblockinfinity.hud;

import com.dragonblockinfinity.stats.PlayerStatsProvider;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

public class KiHud implements HudRenderCallback {

    private static final int BAR_WIDTH = 120;
    private static final int BAR_HEIGHT = 10;

    @Override
    public void onHudRender(DrawContext context, float tickDelta) {

        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        var ki = PlayerStatsProvider.getClientKi();
        var stats = PlayerStatsProvider.getClientStats();

        int x = 10;   // canto ESQUERDO
        int y = 10;   // topo

        int current = ki.getClientKi();
        int max = ki.getClientMaxKi();

        // Fundo preto
        context.fill(x, y, x + BAR_WIDTH, y + BAR_HEIGHT, 0xFF000000);

        // Azul (barra atual)
        int filled = (int) ((current / (float) max) * BAR_WIDTH);
        context.fill(x, y, x + filled, y + BAR_HEIGHT, 0xFF1E90FF);

        // Texto "KI: atual/max"
        String text = "KI: " + current + " / " + max;
        context.drawText(client.textRenderer, text, x + 2, y - 10, 0xFFFFFFFF, true);

        // Mostrar BP logo abaixo
        int bp = stats.getClientBP();
        String bpText = "BP: " + bp;
        context.drawText(client.textRenderer, bpText, x + 2, y + BAR_HEIGHT + 3, 0xFFFFFFFF, true);
    }

    // Registrar HUD no client
    public static void register() {
        HudRenderCallback.EVENT.register(new KiHud());
    }
}
