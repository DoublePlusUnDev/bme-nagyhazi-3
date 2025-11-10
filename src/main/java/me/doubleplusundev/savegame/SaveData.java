package me.doubleplusundev.savegame;

import me.doubleplusundev.map.GameMap;
import me.doubleplusundev.resource.ResourceStore;

public class SaveData {
    private final GameMap gameMap;
    private final ResourceStore resourceStore;
    
    public SaveData(GameMap gameMap, ResourceStore resourceStore) {
        this.gameMap = gameMap;
        this.resourceStore = resourceStore;
    }

    public GameMap getMap() {
        return gameMap;
    }   

    public ResourceStore getResources() {
        return resourceStore;
    }
}
