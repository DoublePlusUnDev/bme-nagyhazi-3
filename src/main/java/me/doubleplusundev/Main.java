package me.doubleplusundev;

import me.doubleplusundev.game.UpdateManager;
import me.doubleplusundev.map.GameMapHandler;
import me.doubleplusundev.player.KeyInputManager;
import me.doubleplusundev.player.PlayerController;
import me.doubleplusundev.resource.ResourceManager;
import me.doubleplusundev.ui.UIHandler;

public class Main {
    public static void main(String[] args) {
        UpdateManager updateManager = new UpdateManager();

        ResourceManager resourceManager = new ResourceManager();

        GameMapHandler gameMapHandler = new GameMapHandler(updateManager, resourceManager);

        KeyInputManager keyInputManager = new KeyInputManager();

        PlayerController playerController = new PlayerController(updateManager, keyInputManager);
        updateManager.registerForUpdate(playerController);

        UIHandler uiHandler = new UIHandler(gameMapHandler, resourceManager, updateManager, playerController, keyInputManager);
        uiHandler.initialize();

        updateManager.start();
    }
} 