package me.doubleplusundev;

import me.doubleplusundev.game.UpdateManager;
import me.doubleplusundev.map.GameMapHandler;
import me.doubleplusundev.player.PlayerController;
import me.doubleplusundev.resource.ResourceManager;
import me.doubleplusundev.ui.UIHandler;

public class Main {
    public static void main(String[] args) {
        GameMapHandler gameMapHandler = new GameMapHandler();

        UpdateManager updateManager = new UpdateManager();
        updateManager.start();

        PlayerController playerController = new PlayerController(updateManager);

        ResourceManager resourceManager = new ResourceManager();

        UIHandler uiHandler = new UIHandler(gameMapHandler, resourceManager, updateManager, playerController);
        uiHandler.initialize();
    
        
    }
} 