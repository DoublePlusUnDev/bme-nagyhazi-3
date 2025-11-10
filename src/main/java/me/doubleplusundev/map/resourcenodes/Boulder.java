package me.doubleplusundev.map.resourcenodes;

import me.doubleplusundev.map.GameMap;

public class Boulder extends ResourceNode {
    
    public Boulder(int xPos, int yPos, GameMap gameMap) {
        super(xPos, yPos, ResourceNodeType.BOULDER, gameMap);
    }
    
}
