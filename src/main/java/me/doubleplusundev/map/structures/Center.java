package me.doubleplusundev.map.structures;

import me.doubleplusundev.game.UpdateManager;
import me.doubleplusundev.map.GameMap;
import me.doubleplusundev.resource.ResourceManager;

public class Center extends Structure {
    
    public Center(int xPos, int yPos, GameMap gameMap, ResourceManager resourceManager, UpdateManager updateManager) {
        super(xPos, yPos, StructureType.CENTER, gameMap, resourceManager, updateManager);
    }
}
