package com.dragonblockinfinity.hud;

import com.dragonblockinfinity.stats.PlayerStatsComponent;
import com.dragonblockinfinity.stats.PlayerStatsProvider;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;

public class MainHudOverlay implements HudRenderCallback {

    @Override
    public void onHudRender(MatrixStack matrices, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        PlayerStatsComponent stats = PlayerStatsProvider.get(client.player);
        if (stats == null) return;

        // KI
        double ki = stats.getCurrentKi();
        double maxKi = stats.getMaxKi();

        int kiBarX = 10;
        int kiBarY = 10;
        int kiBarWidth = 180;
        int kiBarHeight = 14;

        // fundo
        fill(matrices, kiBarX, kiBarY, kiBarX + kiBarWidth, kiBarY + kiBarHeight, 0xAA000000);
        int kiFill = (int) ((ki / maxKi) * kiBarWidth);
        fill(matrices, kiBarX, kiBarY, kiBarX + kiFill, kiBarY + kiBarHeight, 0xFF008CFF);

        // texto em cima
        client.textRenderer.draw(matrices, (int)ki + " / " + (int)maxKi, kiBarX, kiBarY - 12, 0x00A2FF);

        // BP dentro da barra (calcule com stats)
        long bp = Math.round(stats.getBattlePower());
        client.textRenderer.draw(matrices, "BP: " + bp, kiBarX + 6, kiBarY + 3, 0x000000);

        // HP + Stamina no lado direito
        double hp = stats.getCurrentHealth();
        double maxHp = stats.getMaxHealth();
        double stamina = stats.getCurrentStamina();
        double maxSt = stats.getMaxStamina();

        int screenWidth = client.getWindow().getScaledWidth();
        int hpBarWidth = 140;
        int hpBarHeight = 12;
        int hpBarX = screenWidth - hpBarWidth - 10;
        int hpBarY = client.getWindow().getScaledHeight() / 2 - 10;

        // HP
        fill(matrices, hpBarX, hpBarY, hpBarX + hpBarWidth, hpBarY + hpBarHeight, 0xAA000000);
        int hpFill = (int) ((hp / maxHp) * hpBarWidth);
        fill(matrices, hpBarX, hpBarY, hpBarX + hpFill, hpBarY + hpBarHeight, 0xFFFF0000);
        client.textRenderer.draw(matrices, "HP: " + (int)hp + " / " + (int)maxHp, hpBarX, hpBarY - 10, 0xFF4444);

        // Stamina (abaixo)
        int stY = hpBarY + hpBarHeight + 6;
        fill(matrices, hpBarX, stY, hpBarX + hpBarWidth, stY + hpBarHeight, 0xAA000000);
        int stFill = (int) ((stamina / maxSt) * hpBarWidth);
        fill(matrices, hpBarX, stY, hpBarX + stFill, stY + hpBarHeight, 0xFFFFFF00);
        client.textRenderer.draw(matrices, "STM: " + (int)stamina + " / " + (int)maxSt, hpBarX, stY - 10, 0xFFFF55);
    }
}
