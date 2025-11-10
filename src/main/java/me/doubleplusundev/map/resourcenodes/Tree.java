package me.doubleplusundev.map.resourcenodes;

import me.doubleplusundev.map.GameMap;
import me.doubleplusundev.resource.ResourceManager;

public class Tree extends ResourceNode {
    public Tree(int xPos, int yPos, GameMap gameMap, ResourceManager resourceManager) {
        super(xPos, yPos, ResourceNodeType.TREE, gameMap, resourceManager);
    }
}
