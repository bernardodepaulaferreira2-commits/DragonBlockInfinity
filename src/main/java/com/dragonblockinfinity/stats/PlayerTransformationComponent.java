package com.dragonblockinfinity.stats;

import net.minecraft.nbt.NbtCompound;

public class PlayerTransformationComponent {

    public enum Transformation {

        // ==== SAIYANS ====
        BASE,
        SSJ1,
        SSJ2,
        SSJ3,
        GOD,
        BLUE,
        UI_OMEN,
        ULTRA_INSTINCT,

        // ==== ARCOSIAN ====
        ARCOSIAN_FORM1,
        ARCOSIAN_FORM2,
        ARCOSIAN_FORM3,
        ARCOSIAN_GOLDEN,

        // ==== MAJIN ====
        PURE_MAJIN,

        // ==== NAMEKIAN ====
        GIANT_NAMEK,

        // ==== HUMAN ====
        HIDDEN_POTENTIAL,

        // ==== BIO-ANDROID ====
        PERFECT_FORM,

        // ==== ANDROID ====
        OVERCLOCK
    }

    private Transformation current = Transformation.BASE;
    private int timer = 0;
    private boolean active = false;

    // Multiplicadores da forma
    private double strengthMultiplier = 1.0;
    private double dexMultiplier = 1.0;
    private double conMultiplier = 1.0;
    private double willMultiplier = 1.0;
    private double spiritMultiplier = 1.0;

    public PlayerTransformationComponent() {}

    // ========================================
    // ATIVAR TRANSFORMAÇÃO
    // ========================================
    public void activate(Transformation form) {
        this.current = form;
        this.active = true;
        this.timer = 0;
        applyMultipliers(form);
    }

    // ========================================
    // DESATIVAR TRANSFORMAÇÃO
    // ========================================
    public void deactivate() {
        this.current = Transformation.BASE;
        this.active = false;
        this.timer = 0;
        strengthMultiplier = 1.0;
        dexMultiplier = 1.0;
        conMultiplier = 1.0;
        willMultiplier = 1.0;
        spiritMultiplier = 1.0;
    }

    // ========================================
    // MULTIPLICADORES DE CADA FORMA
    // ========================================
    private void applyMultipliers(Transformation form) {
        switch (form) {

            // ==== SAIYANS ====
            case SSJ1 -> setMulti(1.5, 1.3, 1.2, 1.2, 1.3);
            case SSJ2 -> setMulti(2.0, 1.5, 1.3, 1.3, 1.4);
            case SSJ3 -> setMulti(4.0, 2.0, 1.6, 1.5, 2.0);
            case GOD -> setMulti(8.0, 4.0, 3.0, 4.0, 6.0);
            case BLUE -> setMulti(15.0, 8.0, 5.0, 7.0, 9.0);
            case UI_OMEN -> setMulti(40.0, 30.0, 12.0, 20.0, 25.0);
            case ULTRA_INSTINCT -> setMulti(80.0, 50.0, 20.0, 30.0, 40.0);

            // ==== ARCOSIAN ====
            case ARCOSIAN_FORM1 -> setMulti(1.8, 1.2, 1.1, 1.2, 1.1);
            case ARCOSIAN_FORM2 -> setMulti(3.5, 2.0, 1.6, 1.4, 1.3);
            case ARCOSIAN_FORM3 -> setMulti(6.0, 3.5, 2.0, 1.6, 1.5);
            case ARCOSIAN_GOLDEN -> setMulti(25.0, 12.0, 6.0, 10.0, 14.0);

            // ==== MAJIN ====
            case PURE_MAJIN -> setMulti(30.0, 10.0, 15.0, 12.0, 18.0);

            // ==== NAMEKIAN ====
            case GIANT_NAMEK -> setMulti(10.0, 5.0, 20.0, 5.0, 3.0);

            // ==== HUMAN ====
            case HIDDEN_POTENTIAL -> setMulti(5.0, 3.0, 3.0, 2.0, 2.5);

            // ==== BIO-ANDROID ====
            case PERFECT_FORM -> setMulti(20.0, 15.0, 10.0, 10.0, 12.0);

            // ==== ANDROID ====
            case OVERCLOCK -> setMulti(12.0, 8.0, 4.0, 6.0, 3.0);

            default -> setMulti(1.0, 1.0, 1.0, 1.0, 1.0);
        }
    }

    private void setMulti(double str, double dex, double con, double will, double spi) {
        strengthMultiplier = str;
        dexMultiplier = dex;
        conMultiplier = con;
        willMultiplier = will;
        spiritMultiplier = spi;
    }

    // ========================================
    // GETTERS
    // ========================================
    public boolean isActive() { return active; }
    public Transformation getCurrent() { return current; }
    public int getTimer() { return timer; }

    public double getStrengthMultiplier() { return strengthMultiplier; }
    public double getDexMultiplier() { return dexMultiplier; }
    public double getConMultiplier() { return conMultiplier; }
    public double getWillMultiplier() { return willMultiplier; }
    public double getSpiritMultiplier() { return spiritMultiplier; }

    // ========================================
    // TICK
    // ========================================
    public void tick() {
        if (active) timer++;
    }

    // ========================================
    // NBT SAVE/LOAD
    // ========================================
    public void saveToNbt(NbtCompound nbt) {
        nbt.putString("current_form", current.name());
        nbt.putBoolean("active", active);
        nbt.putInt("timer", timer);
    }

    public void loadFromNbt(NbtCompound nbt) {
        if (nbt.contains("current_form"))
            this.current = Transformation.valueOf(nbt.getString("current_form"));

        if (nbt.contains("active"))
            this.active = nbt.getBoolean("active");

        if (nbt.contains("timer"))
            this.timer = nbt.getInt("timer");

        applyMultipliers(current);
    }
}
