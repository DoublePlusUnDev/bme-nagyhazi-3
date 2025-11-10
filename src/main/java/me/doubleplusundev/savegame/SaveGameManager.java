package me.doubleplusundev.savegame;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import me.doubleplusundev.map.GameMapHandler;
import me.doubleplusundev.map.WorldObject;
import me.doubleplusundev.resource.ResourceManager;
import me.doubleplusundev.resource.ResourceType;
import me.doubleplusundev.ui.ExceptionUI;

public class SaveGameManager {
    Gson gson;
    private final GameMapHandler gameMapHandler;
    private final ResourceManager resourceManager;

    private String saveGameLocation;

    public SaveGameManager(GameMapHandler gameMapHandler, ResourceManager resourceManager) {
        this.gameMapHandler = gameMapHandler;
        this.resourceManager = resourceManager;
        this.gson = new GsonBuilder().registerTypeAdapter(WorldObject.class, new WorldObjectAdapter()).create();
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
