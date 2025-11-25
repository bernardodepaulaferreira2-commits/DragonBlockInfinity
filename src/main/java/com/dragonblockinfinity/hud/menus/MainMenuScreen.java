package com.dragonblockinfinity.hud.menus;

import com.dragonblockinfinity.hud.menus.components.*;
import com.dragonblockinfinity.stats.PlayerStatsComponent;
import com.dragonblockinfinity.stats.PlayerRaceComponent;
import com.dragonblockinfinity.stats.PlayerTransformationComponent;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class MainMenuScreen extends Screen {

    private final PlayerEntity player;

    // Componentes visuais do menu
    private RaceDisplay raceDisplay;
    private TransformationDisplay transformationDisplay;
    private StatsDisplay statsDisplay;
    private TPCostDisplay tpCostDisplay;

    public MainMenuScreen(PlayerEntity player) {
        super(Text.literal("DragonBlock Infinity - Status"));
        this.player = player;
    }

    @Override
    protected void init() {

        int baseX = 20;
        int baseY = 20;

        // Criar componentes com posições organizadas
        raceDisplay = new RaceDisplay(player, baseX, baseY);
        transformationDisplay = new TransformationDisplay(player, baseX, baseY + 50);
        statsDisplay = new StatsDisplay(player, baseX, baseY + 120);
        tpCostDisplay = new TPCostDisplay(player, baseX, baseY + 220);

        super.init();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {

        // Fundo
        this.renderBackground(context);

        // Renderizar módulos
        raceDisplay.render(context, mouseX, mouseY, delta);
        transformationDisplay.render(context, mouseX, mouseY, delta);
        statsDisplay.render(context, mouseX, mouseY, delta);
        tpCostDisplay.render(context, mouseX, mouseY, delta);

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return true; // Pausar jogo ao abrir menu
    }
}
