package me.doubleplusundev;

import me.doubleplusundev.game.UpdateManager;
import me.doubleplusundev.map.GameMapHandler;
import me.doubleplusundev.map.worldgen.WorldGenerator;
import me.doubleplusundev.map.worldobject.WorldObjectFactory;
import me.doubleplusundev.player.GameInteractionManager;
import me.doubleplusundev.player.KeyInputManager;
import me.doubleplusundev.player.PlayerController;
import me.doubleplusundev.resource.ResourceManager;
import me.doubleplusundev.savegame.SaveGameManager;
import me.doubleplusundev.ui.MainMenuUI;
import me.doubleplusundev.ui.UIHandler;

public class Main {
    public static void main(String[] args) {
        UpdateManager updateManager = new UpdateManager();

        ResourceManager resourceManager = new ResourceManager();

        GameMapHandler gameMapHandler = new GameMapHandler();

        WorldObjectFactory worldObjectFactory = new WorldObjectFactory(updateManager, resourceManager, gameMapHandler);

        WorldGenerator worldGenerator = new WorldGenerator(worldObjectFactory);

        KeyInputManager keyInputManager = new KeyInputManager();

        SaveGameManager saveGameManager = new SaveGameManager(gameMapHandler, resourceManager, updateManager);

        PlayerController playerController = new PlayerController(updateManager, keyInputManager);
        updateManager.registerForUpdate(playerController);

        GameInteractionManager gameInteractionManager = new GameInteractionManager(gameMapHandler, playerController, resourceManager, worldObjectFactory);

        UIHandler uiHandler = new UIHandler(gameMapHandler, resourceManager, updateManager, saveGameManager, playerController, gameInteractionManager, keyInputManager);
        
        MainMenuUI mainMenuUI = new MainMenuUI(uiHandler, gameMapHandler, saveGameManager, worldGenerator);
        mainMenuUI.initialize();

        updateManager.start();
    }
} 