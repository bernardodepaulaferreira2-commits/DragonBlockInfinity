package com.dragonblockinfinity.hud.menus.components;

import com.dragonblockinfinity.stats.PlayerStatsComponent;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class StatsDisplay {

    private final PlayerEntity player;
    private final int x;
    private final int y;

    public StatsDisplay(PlayerEntity player, int x, int y) {
        this.player = player;
        this.x = x;
        this.y = y;
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        TextRenderer tr = context.getTextRenderer();
        PlayerStatsComponent stats = PlayerStatsComponent.get(player);

        int lineY = y;

        context.drawText(tr, Text.literal("Status:"), x, lineY, 0xFFFFFF, false);
        lineY += 12;

        context.drawText(tr, Text.literal("+ STR: " + stats.getSTR()), x, lineY, 0xFFAAAA, false);
        lineY += 12;

        context.drawText(tr, Text.literal("+ DEX: " + stats.getDEX()), x, lineY, 0xFFAAAA, false);
        lineY += 12;

        context.drawText(tr, Text.literal("+ CON: " + stats.getCON()), x, lineY, 0xFFAAAA, false);
        lineY += 12;

        context.drawText(tr, Text.literal("+ SPI: " + stats.getSPI()), x, lineY, 0xFFAAAA, false);
        lineY += 12;

        context.drawText(tr, Text.literal("+ WILL: " + stats.getWILL()), x, lineY, 0xFFAAAA, false);
        lineY += 12;

        context.drawText(tr, Text.literal("+ MND: " + stats.getMND()), x, lineY, 0xFFAAAA, false);
    }
}
