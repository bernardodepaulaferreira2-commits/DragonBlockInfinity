package com.dragonblockinfinity.hud.menus;

import com.dragonblockinfinity.hud.menus.components.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class MainMenuScreen extends Screen {

    private RaceDisplay raceDisplay;
    private TransformationDisplay transformationDisplay;
    private StatsDisplay statsDisplay;
    private TPCostDisplay tpCostDisplay;

    public MainMenuScreen() {
        super(Text.literal("Dragon Block Infinity"));
    }

    @Override
    protected void init() {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;

        raceDisplay           = new RaceDisplay(player, 20, 30);
        transformationDisplay = new TransformationDisplay(player, 20, 50);
        statsDisplay          = new StatsDisplay(player, 20, 100);
        tpCostDisplay         = new TPCostDisplay(player, 200, 100);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        if (raceDisplay           != null) raceDisplay.render(context, mouseX, mouseY, delta);
        if (transformationDisplay != null) transformationDisplay.render(context, mouseX, mouseY, delta);
        if (statsDisplay          != null) statsDisplay.render(context, mouseX, mouseY, delta);
        if (tpCostDisplay         != null) tpCostDisplay.render(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() { return true; }
}
