package me.doubleplusundev.savegame;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import me.doubleplusundev.map.GameMap;
import me.doubleplusundev.map.GameMapHandler;
import me.doubleplusundev.map.WorldObject;

public class SaveGameManager {
    Gson gson;
    private final GameMapHandler gameMapHandler;

    private String saveGameLocation;

    public SaveGameManager(GameMapHandler gameMapHandler) {
        this.gameMapHandler = gameMapHandler;
        this.gson = new GsonBuilder().registerTypeAdapter(WorldObject.class, new WorldObjectAdapter()).create();
    }


    public void save() {
        String jsonString = gson.toJson(gameMapHandler.getMap());
        try (FileWriter fw = new FileWriter(saveGameLocation)){
            fw.write(jsonString);
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void load() {
        try {
            String jsonString = Files.readString(new File(saveGameLocation).toPath());
            gameMapHandler.setMap(gson.fromJson(jsonString, GameMap.class));
        }
        catch (IOException | JsonSyntaxException exception) {
            exception.printStackTrace();
        }
        
    }
    
    public void setLocation(String location) {
        this.saveGameLocation = location;
    }
    
}
