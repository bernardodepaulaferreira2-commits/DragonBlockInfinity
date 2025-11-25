package com.dragonblockinfinity.hud.menus.components;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.text.Text;

public class MasteryTooltip {

    public static void renderTooltip(DrawContext context, int mouseX, int mouseY, int mastery, int masteryMax) {
        TextRenderer tr = context.getTextRenderer();

        String line1 = "Mastery: " + mastery + " / " + masteryMax;
        String line2 = "Quanto maior a mastery, mais forte a forma fica";
        String line3 = "e menor o gasto de KI e Stamina.";

        int width = Math.max(
                tr.getWidth(line1),
                Math.max(tr.getWidth(line2), tr.getWidth(line3))
        ) + 10;

        int height = 36;

        int x = mouseX + 12;
        int y = mouseY + 12;

        // fundo preto com transparência
        context.fill(x, y, x + width, y + height, 0xAA000000);

        // texto
        context.drawText(tr, Text.literal(line1), x + 5, y + 5, 0xFFFFFF, false);
        context.drawText(tr, Text.literal(line2), x + 5, y + 15, 0x00B2FF, false);
        context.drawText(tr, Text.literal(line3), x + 5, y + 25, 0x00B2FF, false);
    }
}
