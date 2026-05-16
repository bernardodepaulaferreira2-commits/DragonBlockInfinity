package com.dragonblockinfinity.hud.menus.components;

import com.dragonblockinfinity.stats.PlayerStatsComponent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class TPCostDisplay {

    private final PlayerEntity player;
    private final int x, y;
    private final int tpPerPoint = 16;

    public TPCostDisplay(PlayerEntity player, int x, int y) {
        this.player = player; this.x = x; this.y = y;
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        var tr = MinecraftClient.getInstance().textRenderer;
        int lineY = y;
        context.drawText(tr, Text.literal("Custo de TP:"),                    x, lineY,      0xFFFFFF, false); lineY += 12;
        context.drawText(tr, Text.literal("• 1 ponto: "  + tpPerPoint),       x, lineY,      0xFFFF55, false); lineY += 12;
        context.drawText(tr, Text.literal("• 10 pontos: " + tpPerPoint * 10), x, lineY,      0xFFFF55, false);
    }
}
