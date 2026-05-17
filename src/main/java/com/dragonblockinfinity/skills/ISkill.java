package com.dragonblockinfinity.skills;

import net.minecraft.entity.player.PlayerEntity;
import com.dragonblockinfinity.stats.PlayerKiComponent;

public abstract class ISkill<T> {

    public String name;
    public int kiCost;
    public int cooldown;

    public ISkill(String name, int kiCost, int cooldown) {
        this.name = name;
        this.kiCost = kiCost;
        this.cooldown = cooldown;
    }

    public int getKiCost() { return kiCost; }

    public boolean canUse(PlayerEntity player) {
        PlayerKiComponent ki = PlayerKiComponent.get(player);
        return !ki.isExhausted() && ki.getCurrentKi() >= getKiCost();
    }

    public void use(PlayerEntity player) {
        PlayerKiComponent ki = PlayerKiComponent.get(player);
        ki.useKi(getKiCost());
    }
}
