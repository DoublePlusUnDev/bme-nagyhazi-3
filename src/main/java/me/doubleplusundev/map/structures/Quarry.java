package me.doubleplusundev.map.structures;

import me.doubleplusundev.map.GameMap;
import me.doubleplusundev.resource.ResourceManager;

public class Quarry extends Structure {

    protected Quarry(int xPos, int yPos, GameMap gameMap, ResourceManager resourceManager) {
        super(xPos, yPos, StructureType.QUARRY, gameMap, resourceManager);
    }
}
