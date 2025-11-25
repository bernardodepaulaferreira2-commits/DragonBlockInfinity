package com.dragonblockinfinity.hud.menus.components;

import com.dragonblockinfinity.stats.PlayerRaceComponent;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class RaceDisplay {

    private final PlayerEntity player;
    private final int x;
    private final int y;

    public RaceDisplay(PlayerEntity player, int x, int y) {
        this.player = player;
        this.x = x;
        this.y = y;
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        TextRenderer textRenderer = context.getTextRenderer();

        String raceName = PlayerRaceComponent.get(player).getRaceName();

        context.drawText(
                textRenderer,
                Text.literal("Raça: " + raceName),
                x,
                y,
                0xFFFFFF,
                false
        );
    }
}
