package me.doubleplusundev.savegame;

import me.doubleplusundev.map.GameMap;
import me.doubleplusundev.resource.ResourceBank;

public class SaveData {
    private final GameMap gameMap;
    private final ResourceBank resourceStore;
    
    public SaveData(GameMap gameMap, ResourceBank resourceStore) {
        this.gameMap = gameMap;
        this.resourceStore = resourceStore;
    }

    public GameMap getMap() {
        return gameMap;
    }   

    public ResourceBank getResources() {
        return resourceStore;
    }
}
