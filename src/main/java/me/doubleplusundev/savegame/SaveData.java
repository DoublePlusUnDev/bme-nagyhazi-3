package me.doubleplusundev.savegame;

import me.doubleplusundev.map.GameMap;
import me.doubleplusundev.resource.ResourceBank;

/**
 * The collection of everything that ought to be saved.
 * 
 */
public class SaveData {
    private final GameMap gameMap;
    private final ResourceBank resourceStore;
    
    public SaveData(GameMap gameMap, ResourceBank resourceStore) {
        this.gameMap = gameMap;
        this.resourceStore = resourceStore;
    }

    /**
     * Getter for the map.
     * @return The map.
     */
    public GameMap getMap() {
        return gameMap;
    }   

    /**
     * Getter for the resources.
     * @return The resources.
     */
    public ResourceBank getResources() {
        return resourceStore;
    }
}
