package me.doubleplusundev.map.structures;

import me.doubleplusundev.game.UpdateManager;
import me.doubleplusundev.map.GameMap;
import me.doubleplusundev.resource.ResourceManager;

public class Road extends Structure {
    
    public Road(int xPos, int yPos, GameMap gameMap, ResourceManager resourceManager, UpdateManager updateManager) {
        super(xPos, yPos, StructureType.ROAD, gameMap, resourceManager, updateManager);
    }
}
