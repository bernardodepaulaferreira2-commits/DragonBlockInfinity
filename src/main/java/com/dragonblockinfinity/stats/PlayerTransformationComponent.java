package com.dragonblockinfinity.stats;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerTransformationComponent {

    public enum Transformation {
        BASE, SSJ1, SSJ2, SSJ3, GOD, BLUE, UI_OMEN, ULTRA_INSTINCT,
        ARCOSIAN_FORM1, ARCOSIAN_FORM2, ARCOSIAN_FORM3, ARCOSIAN_GOLDEN,
        PURE_MAJIN, GIANT_NAMEK, HIDDEN_POTENTIAL, PERFECT_FORM, OVERCLOCK
    }

    private static final Map<UUID, PlayerTransformationComponent> map = new HashMap<>();

    private Transformation current = Transformation.BASE;
    private boolean active = false;
    private int timer = 0;
    private int mastery = 0;
    private int masteryMax = 100;

    private double strengthMultiplier = 1.0, dexMultiplier = 1.0;
    private double conMultiplier = 1.0, willMultiplier = 1.0, spiritMultiplier = 1.0;

    public PlayerTransformationComponent() {}

    public static PlayerTransformationComponent get(PlayerEntity player) {
        return map.computeIfAbsent(player.getUuid(), id -> new PlayerTransformationComponent());
    }

    public void activate(Transformation form) {
        this.current = form; this.active = true; this.timer = 0;
        applyMultipliers(form);
    }

    public void deactivate() {
        this.current = Transformation.BASE; this.active = false; this.timer = 0;
        strengthMultiplier = dexMultiplier = conMultiplier = willMultiplier = spiritMultiplier = 1.0;
    }

    private void applyMultipliers(Transformation form) {
        switch (form) {
            case SSJ1            -> setMulti(1.5,  1.3,  1.2, 1.2, 1.3);
            case SSJ2            -> setMulti(2.0,  1.5,  1.3, 1.3, 1.4);
            case SSJ3            -> setMulti(4.0,  2.0,  1.6, 1.5, 2.0);
            case GOD             -> setMulti(8.0,  4.0,  3.0, 4.0, 6.0);
            case BLUE            -> setMulti(15.0, 8.0,  5.0, 7.0, 9.0);
            case UI_OMEN         -> setMulti(40.0, 30.0, 12.0,20.0,25.0);
            case ULTRA_INSTINCT  -> setMulti(80.0, 50.0, 20.0,30.0,40.0);
            case ARCOSIAN_FORM1  -> setMulti(1.8,  1.2,  1.1, 1.2, 1.1);
            case ARCOSIAN_FORM2  -> setMulti(3.5,  2.0,  1.6, 1.4, 1.3);
            case ARCOSIAN_FORM3  -> setMulti(6.0,  3.5,  2.0, 1.6, 1.5);
            case ARCOSIAN_GOLDEN -> setMulti(25.0, 12.0, 6.0,10.0,14.0);
            case PURE_MAJIN      -> setMulti(30.0, 10.0, 15.0,12.0,18.0);
            case GIANT_NAMEK     -> setMulti(10.0, 5.0,  20.0,5.0, 3.0);
            case HIDDEN_POTENTIAL-> setMulti(5.0,  3.0,  3.0, 2.0, 2.5);
            case PERFECT_FORM    -> setMulti(20.0, 15.0, 10.0,10.0,12.0);
            case OVERCLOCK       -> setMulti(12.0, 8.0,  4.0, 6.0, 3.0);
            default              -> setMulti(1.0,  1.0,  1.0, 1.0, 1.0);
        }
    }

    private void setMulti(double str, double dex, double con, double will, double spi) {
        strengthMultiplier = str; dexMultiplier = dex; conMultiplier = con;
        willMultiplier = will; spiritMultiplier = spi;
    }

    public String getCurrentFormName() {
        return switch (current) {
            case BASE            -> "Base";
            case SSJ1            -> "Super Saiyan";
            case SSJ2            -> "Super Saiyan 2";
            case SSJ3            -> "Super Saiyan 3";
            case GOD             -> "Super Saiyan God";
            case BLUE            -> "Super Saiyan Blue";
            case UI_OMEN         -> "Ultra Instinct -Omen-";
            case ULTRA_INSTINCT  -> "Ultra Instinct";
            case ARCOSIAN_FORM1  -> "Suppression Form 1";
            case ARCOSIAN_FORM2  -> "Suppression Form 2";
            case ARCOSIAN_FORM3  -> "Suppression Form 3";
            case ARCOSIAN_GOLDEN -> "Golden Form";
            case PURE_MAJIN      -> "Pure Majin";
            case GIANT_NAMEK     -> "Giant Form";
            case HIDDEN_POTENTIAL-> "Hidden Potential";
            case PERFECT_FORM    -> "Perfect Form";
            case OVERCLOCK       -> "Overclock";
        };
    }

    public int getMastery()    { return mastery; }
    public int getMasteryMax() { return masteryMax; }
    public void addMastery(int v) { mastery = Math.min(mastery + v, masteryMax); }

    public boolean isActive()   { return active; }
    public Transformation getCurrent() { return current; }
    public int getTimer()       { return timer; }
    public void tick()          { if (active) timer++; }

    public double getStrengthMultiplier() { return strengthMultiplier; }
    public double getDexMultiplier()      { return dexMultiplier; }
    public double getConMultiplier()      { return conMultiplier; }
    public double getWillMultiplier()     { return willMultiplier; }
    public double getSpiritMultiplier()   { return spiritMultiplier; }

    public void saveToNbt(NbtCompound nbt) {
        nbt.putString("current_form", current.name());
        nbt.putBoolean("active", active);
        nbt.putInt("timer", timer);
        nbt.putInt("mastery", mastery);
    }

    public void loadFromNbt(NbtCompound nbt) {
        if (nbt.contains("current_form")) current = Transformation.valueOf(nbt.getString("current_form"));
        if (nbt.contains("active"))  active  = nbt.getBoolean("active");
        if (nbt.contains("timer"))   timer   = nbt.getInt("timer");
        if (nbt.contains("mastery")) mastery = nbt.getInt("mastery");
        applyMultipliers(current);
    }
}
