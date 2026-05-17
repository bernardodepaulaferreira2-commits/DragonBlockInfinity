package com.dragonblockinfinity.inputs;

import com.dragonblockinfinity.hud.menus.MainMenuScreen;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class MenuKeybind {

    private static KeyBinding openMenu;

    public static void register() {

        openMenu = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.dragonblockinfinity.open_menu",
                GLFW.GLFW_KEY_M, // tecla M
                "category.dragonblockinfinity.keys"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (openMenu.wasPressed()) {
                MinecraftClient.getInstance().setScreen(new MainMenuScreen());
            }
        });
    }
}
