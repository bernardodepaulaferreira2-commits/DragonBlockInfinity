package com.dragonblockinfinity.hud.inputs;

import com.dragonblockinfinity.hud.menus.MainMenuScreen;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class MenuButtonOverlay implements HudRenderCallback {

    @Override
    public void onHudRender(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.options.hudHidden) return;

        int x = 10;
        int y = client.getWindow().getScaledHeight() - 40;

        // fundo do botão
        context.fill(x, y, x + 80, y + 30, 0x80000000);

        // texto
        context.drawText(client.textRenderer, "MENU", x + 20, y + 10, 0xFFFFFF, false);

        // clicou?
        if (isClicked(x, y, 80, 30)) {
            client.setScreen(new MainMenuScreen());
        }
    }

    private boolean isClicked(int x, int y, int w, int h) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.mouse.wasLeftButtonClicked()) {
            double mx = client.mouse.getX() / client.getWindow().getScaleFactor();
            double my = client.mouse.getY() / client.getWindow().getScaleFactor();

            return mx >= x && mx <= x + w &&
                   my >= y && my <= y + h;
        }
        return false;
    }
}
