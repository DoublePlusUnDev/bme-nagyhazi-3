package me.doubleplusundev.map.resourcenodes;

import me.doubleplusundev.map.GameMapHandler;
import me.doubleplusundev.map.WorldObject;

public abstract class ResourceNode extends WorldObject {
    ResourceNodeType type;

    protected ResourceNode (int xPos, int yPos, ResourceNodeType type, GameMapHandler gameMapHandler) {
        super(xPos, yPos, gameMapHandler);
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
