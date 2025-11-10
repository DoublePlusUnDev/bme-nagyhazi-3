package me.doubleplusundev.map.resourcenodes;

import me.doubleplusundev.map.GameMap;

public class Tree extends ResourceNode {
    public Tree(int xPos, int yPos, GameMap gameMap) {
        super(xPos, yPos, ResourceNodeType.TREE, gameMap);
    }
}
