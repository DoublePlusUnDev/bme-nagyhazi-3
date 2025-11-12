package me.doubleplusundev;

import me.doubleplusundev.game.UpdateManager;
import me.doubleplusundev.map.GameMapHandler;
import me.doubleplusundev.map.worldobject.WorldObjectFactory;
import me.doubleplusundev.player.KeyInputManager;
import me.doubleplusundev.player.PlayerController;
import me.doubleplusundev.resource.ResourceManager;
import me.doubleplusundev.ui.UIHandler;

public class Main {
    public static void main(String[] args) {
        UpdateManager updateManager = new UpdateManager();

        ResourceManager resourceManager = new ResourceManager();

        WorldObjectFactory worldObjectFactory = new WorldObjectFactory(updateManager, resourceManager);

        GameMapHandler gameMapHandler = new GameMapHandler(worldObjectFactory);

        KeyInputManager keyInputManager = new KeyInputManager();

        PlayerController playerController = new PlayerController(updateManager, keyInputManager);
        updateManager.registerForUpdate(playerController);

        UIHandler uiHandler = new UIHandler(gameMapHandler, resourceManager, updateManager, playerController, keyInputManager);
        uiHandler.initialize();

        updateManager.start();
    }
} 