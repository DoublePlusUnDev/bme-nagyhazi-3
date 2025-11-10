package me.doubleplusundev.map.resourcenodes;

import java.util.Map;

import me.doubleplusundev.game.UpdateManager;
import me.doubleplusundev.map.GameMap;
import me.doubleplusundev.resource.ResourceManager;
import me.doubleplusundev.resource.ResourceStore;
import me.doubleplusundev.resource.ResourceType;

public class Tree extends ResourceNode {
    public Tree(int xPos, int yPos, GameMap gameMap, ResourceManager resourceManager, UpdateManager updateManager) {
        super(xPos, yPos, ResourceNodeType.TREE, gameMap, resourceManager, updateManager);
        resourcesOnRemoval = new ResourceStore(Map.of(ResourceType.WOOD, 20.0));
    }
}
