package com.dragonblockinfinity.hud.menus.components;

import com.dragonblockinfinity.stats.PlayerTransformationComponent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class TransformationDisplay {

    private final PlayerEntity player;
    private final int x, y;
    private final int barWidth = 120, barHeight = 8;

    public TransformationDisplay(PlayerEntity player, int x, int y) {
        this.player = player; this.x = x; this.y = y;
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        var tr = MinecraftClient.getInstance().textRenderer;
        PlayerTransformationComponent comp = PlayerTransformationComponent.get(player);

        String formName  = comp.getCurrentFormName();
        int mastery      = comp.getMastery();
        int masteryMax   = comp.getMasteryMax();
        int filled       = (int)((mastery / (float) masteryMax) * barWidth);

        context.drawText(tr, Text.literal("Forma Atual: " + formName), x, y, 0x00B2FF, false);
        context.fill(x, y + 15, x + barWidth, y + 15 + barHeight, 0x55000000);
        context.fill(x, y + 15, x + filled,   y + 15 + barHeight, 0xFFA0A0A0);

        boolean hovering = mouseX >= x && mouseX <= x + barWidth
                        && mouseY >= y + 15 && mouseY <= y + 15 + barHeight;
        if (hovering) MasteryTooltip.renderTooltip(context, mouseX, mouseY, mastery, masteryMax);
    }
}
