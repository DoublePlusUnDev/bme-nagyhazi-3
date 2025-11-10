package me.doubleplusundev.map.resourcenodes;

import me.doubleplusundev.map.GameMap;
import me.doubleplusundev.map.WorldObject;
import me.doubleplusundev.resource.ResourceManager;

public abstract class ResourceNode extends WorldObject {
    ResourceNodeType type;

    protected ResourceNode (int xPos, int yPos, ResourceNodeType type, GameMap gameMap, ResourceManager resourceManager) {
        super(xPos, yPos, gameMap, resourceManager);
        this.type = type;
    }

    public ResourceNodeType getType() {
        return type;
    }

    @Override
    public void update() {
        
    }

    @Override
    public void tick(int count) {
        
    }

    
}
