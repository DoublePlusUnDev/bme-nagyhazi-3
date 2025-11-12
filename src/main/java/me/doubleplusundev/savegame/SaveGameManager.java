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
import me.doubleplusundev.ui.ExceptionUI;

public class SaveGameManager {
    Gson gson;
    private final GameMapHandler gameMapHandler;
    private final ResourceManager resourceManager;

    private String saveGameLocation;

    public SaveGameManager(GameMapHandler gameMapHandler, ResourceManager resourceManager, UpdateManager updateManager) {
        this.gameMapHandler = gameMapHandler;
        this.resourceManager = resourceManager;

        gson = new GsonBuilder().registerTypeAdapter(Component.class, new ComponentSaveAdapter(resourceManager, updateManager)).create();
    }


    public void save() {
        SaveData saveData = new SaveData(gameMapHandler.getMap(), resourceManager.getResources());
        String jsonString = gson.toJson(saveData);
        try (FileWriter fw = new FileWriter(saveGameLocation)){
            fw.write(jsonString);
        }
        catch (IOException exception) {
            ExceptionUI.showException(exception);
        }
    }

    public void load() {
        try {
            String jsonString = Files.readString(new File(saveGameLocation).toPath());
            SaveData saveData = gson.fromJson(jsonString, SaveData.class);

            for (int x = 0; x < saveData.getMap().getWidth(); x++) {
                for (int y = 0; y < saveData.getMap().getHeight(); y++) {
                    WorldObject worldObject = new WorldObject(x, y);
                    for (Component component : worldObject.getComponents()) {
                        component.setOwner(worldObject);
                    }
                }
            }

            gameMapHandler.setMap(saveData.getMap());
            resourceManager.setResources(saveData.getResources());
        }
        catch (IOException | JsonSyntaxException exception) {
            ExceptionUI.showException(exception);
        }
        
    }
    
    public void setLocation(String location) {
        this.saveGameLocation = location;
    }
    
}
