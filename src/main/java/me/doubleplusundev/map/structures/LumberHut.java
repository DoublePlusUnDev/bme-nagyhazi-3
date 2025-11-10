package me.doubleplusundev.map.structures;

import me.doubleplusundev.map.GameMap;
import me.doubleplusundev.resource.ResourceManager;

public class LumberHut extends Structure {

    protected LumberHut(int xPos, int yPos, GameMap gameMap, ResourceManager resourceManager) {
        super(xPos, yPos, StructureType.LUMBERHUT, gameMap, resourceManager);
        
    }
    
}
