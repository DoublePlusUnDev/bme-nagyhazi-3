package me.doubleplusundev.savegame;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import me.doubleplusundev.game.UpdateManager;
import me.doubleplusundev.map.GameMapHandler;
import me.doubleplusundev.map.worldobject.WorldObject;
import me.doubleplusundev.map.worldobject.component.Component;
import me.doubleplusundev.resource.ResourceManager;
import me.doubleplusundev.util.UIUtils;

/**
 * Responsible for handling saving the game data and loading it back.
 * Used gson to handle json.
 */
public class SaveGameManager {
    private final Gson gson;
    private final GameMapHandler gameMapHandler;
    private final ResourceManager resourceManager;

    private String saveGameLocation; /** Where the game data will be saved and loaded back from. */

    public SaveGameManager(GameMapHandler gameMapHandler, ResourceManager resourceManager, UpdateManager updateManager) {
        this.gameMapHandler = gameMapHandler;
        this.resourceManager = resourceManager;

        gson = new GsonBuilder().registerTypeAdapter(Component.class, new ComponentSaveAdapter(resourceManager, updateManager, gameMapHandler)).create();
    }

    /**
     * Saves the current state of the game to the save location.
     */
    public void save() {
        SaveData saveData = new SaveData(gameMapHandler.getMap(), resourceManager.getResources());
        String jsonString = gson.toJson(saveData);
        try (FileWriter fw = new FileWriter(saveGameLocation)){
            fw.write(jsonString);
        }
        catch (IOException exception) {
            UIUtils.showException(exception);
        }
    }

    /**
     * Loads the state of the game from the save location.
     */
    public void load() {
        try {
            String jsonString = Files.readString(new File(saveGameLocation).toPath());
            SaveData saveData = gson.fromJson(jsonString, SaveData.class);

            for (int x = 0; x < saveData.getMap().getWidth(); x++) {
                for (int y = 0; y < saveData.getMap().getHeight(); y++) {
                    WorldObject worldObject = saveData.getMap().getWorldObject(x, y);

                    if (worldObject == null)
                        continue;

                    for (Component component : worldObject.getComponents()) {
                        component.setOwner(worldObject);
                    }
                }
            }

            gameMapHandler.setMap(saveData.getMap());
            resourceManager.setResources(saveData.getResources());
        }
        catch (IOException | JsonSyntaxException exception) {
            UIUtils.showException(exception);
        }
    }
    
    /**
     * Sets the specified save/load location.
     * @param location The new location.
     */
    public void setLocation(String location) {
        this.saveGameLocation = location;
    }
    
}
