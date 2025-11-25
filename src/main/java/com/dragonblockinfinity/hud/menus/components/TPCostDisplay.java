package com.dragonblockinfinity.hud.menus.components;

import com.dragonblockinfinity.stats.PlayerStatsComponent;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class TPCostDisplay {

    private final PlayerEntity player;
    private final int x;
    private final int y;

    // Valores fixos que podem ser alterados futuramente por raça, forma ou mastery
    private final int tpPerPoint = 16;

    public TPCostDisplay(PlayerEntity player, int x, int y) {
        this.player = player;
        this.x = x;
        this.y = y;
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        TextRenderer tr = context.getTextRenderer();
        PlayerStatsComponent stats = PlayerStatsComponent.get(player);

        int cost1 = tpPerPoint;
        int cost10 = tpPerPoint * 10;

        int lineY = y;

        context.drawText(tr, Text.literal("Custo de TP:"), x, lineY, 0xFFFFFF, false);
        lineY += 12;

        context.drawText(tr, Text.literal("• 1 ponto: " + cost1), x, lineY, 0xFFFF55, false);
        lineY += 12;

        context.drawText(tr, Text.literal("• 10 pontos: " + cost10), x, lineY, 0xFFFF55, false);
    }
}
