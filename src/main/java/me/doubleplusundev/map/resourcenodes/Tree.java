package me.doubleplusundev.map.resourcenodes;

import me.doubleplusundev.map.GameMapHandler;

public class Tree extends ResourceNode {
    public Tree(int xPos, int yPos, GameMapHandler gameMapHandler) {
        super(xPos, yPos, ResourceNodeType.TREE, gameMapHandler);
    }  
}
