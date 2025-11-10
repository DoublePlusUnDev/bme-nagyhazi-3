package me.doubleplusundev.map.resourcenodes;

import me.doubleplusundev.map.WorldObject;

public abstract class ResourceNode extends WorldObject {
    ResourceNodeType type;

    protected ResourceNode (int xPos, int yPos, ResourceNodeType type) {
        super(xPos, yPos);
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
