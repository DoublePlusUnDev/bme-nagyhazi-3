package me.doubleplusundev.map.resourcenodes;

import java.util.Map;

import me.doubleplusundev.map.GameMap;
import me.doubleplusundev.resource.ResourceManager;
import me.doubleplusundev.resource.ResourceStore;
import me.doubleplusundev.resource.ResourceType;

public class Boulder extends ResourceNode {
    public Boulder(int xPos, int yPos, GameMap gameMap, ResourceManager resourceManager) {
        super(xPos, yPos, ResourceNodeType.BOULDER, gameMap, resourceManager);
        resourcesOnRemoval = new ResourceStore(Map.of(ResourceType.STONE, 20.0));
    }
    
}
