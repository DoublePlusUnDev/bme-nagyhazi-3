package me.doubleplusundev.map.resourcenodes;

import me.doubleplusundev.map.GameMap;
import me.doubleplusundev.resource.ResourceManager;

public class Boulder extends ResourceNode {
    
    public Boulder(int xPos, int yPos, GameMap gameMap, ResourceManager resourceManager) {
        super(xPos, yPos, ResourceNodeType.BOULDER, gameMap, resourceManager);
    }
    
}
