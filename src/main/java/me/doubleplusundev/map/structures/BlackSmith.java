package me.doubleplusundev.map.structures;

import me.doubleplusundev.map.GameMap;
import me.doubleplusundev.resource.ResourceManager;

public class BlackSmith extends Structure {

    protected BlackSmith(int xPos, int yPos, GameMap gameMap, ResourceManager resourceManager) {
        super(xPos, yPos, StructureType.BLACKSMITH, gameMap, resourceManager);
    }
    
}
