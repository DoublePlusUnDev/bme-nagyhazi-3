package me.doubleplusundev.savegame;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import me.doubleplusundev.map.GameMap;
import me.doubleplusundev.map.GameMapHandler;

public class SaveGameManager {
    private final GameMapHandler gameMapHandler;

    private String saveGameLocation;

    public SaveGameManager(GameMapHandler gameMapHandler) {
        this.gameMapHandler = gameMapHandler;
    }


    public void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveGameLocation))){
            oos.writeObject(gameMapHandler.getMap());
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveGameLocation))) {
            gameMapHandler.setMap((GameMap)ois.readObject());
        }
        catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        
    }
    
    public void setLocation(String location) {
        this.saveGameLocation = location;
    }
    
}
