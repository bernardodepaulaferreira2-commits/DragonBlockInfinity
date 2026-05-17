package com.dragonblockinfinity.hud;

import com.dragonblockinfinity.stats.PlayerStatsComponent;
import com.dragonblockinfinity.stats.PlayerStatsProvider;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class MainHudOverlay implements HudRenderCallback {

    @Override
    public void onHudRender(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        PlayerStatsComponent stats = PlayerStatsProvider.get(client.player);
        if (stats == null) return;

        double ki    = stats.getCurrentKi();
        double maxKi = stats.getMaxKi();
        int kiBarX = 10, kiBarY = 10, kiBarW = 180, kiBarH = 14;

        context.fill(kiBarX, kiBarY, kiBarX + kiBarW, kiBarY + kiBarH, 0xAA000000);
        int kiFill = (int)((ki / maxKi) * kiBarW);
        context.fill(kiBarX, kiBarY, kiBarX + kiFill, kiBarY + kiBarH, 0xFF008CFF);
        context.drawText(client.textRenderer, (int)ki + " / " + (int)maxKi, kiBarX, kiBarY - 12, 0x00A2FF, true);
        context.drawText(client.textRenderer, "BP: " + stats.getBP(), kiBarX + 6, kiBarY + 3, 0x000000, false);

        double hp    = stats.getCurrentHealth();
        double maxHp = stats.getMaxHealth();
        double stamina = stats.getCurrentStamina();
        double maxSt = stats.getMaxStamina();

        int screenW  = client.getWindow().getScaledWidth();
        int hpBarW   = 140, hpBarH = 12;
        int hpBarX   = screenW - hpBarW - 10;
        int hpBarY   = client.getWindow().getScaledHeight() / 2 - 10;

        context.fill(hpBarX, hpBarY, hpBarX + hpBarW, hpBarY + hpBarH, 0xAA000000);
        int hpFill = (int)((hp / maxHp) * hpBarW);
        context.fill(hpBarX, hpBarY, hpBarX + hpFill, hpBarY + hpBarH, 0xFFFF0000);
        context.drawText(client.textRenderer, "HP: " + (int)hp + " / " + (int)maxHp, hpBarX, hpBarY - 10, 0xFF4444, true);

        int stY = hpBarY + hpBarH + 6;
        context.fill(hpBarX, stY, hpBarX + hpBarW, stY + hpBarH, 0xAA000000);
        int stFill = (int)((stamina / maxSt) * hpBarW);
        context.fill(hpBarX, stY, hpBarX + stFill, stY + hpBarH, 0xFFFFFF00);
        context.drawText(client.textRenderer, "STM: " + (int)stamina + " / " + (int)maxSt, hpBarX, stY - 10, 0xFFFF55, true);
    }

    public static void register() {
        HudRenderCallback.EVENT.register(new MainHudOverlay());
    }
}
