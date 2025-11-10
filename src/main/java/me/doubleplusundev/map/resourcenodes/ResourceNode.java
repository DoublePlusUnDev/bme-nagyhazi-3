package me.doubleplusundev.map.resourcenodes;

import me.doubleplusundev.map.GameMap;
import me.doubleplusundev.map.WorldObject;
import me.doubleplusundev.resource.ResourceManager;
import me.doubleplusundev.resource.ResourceStore;

public abstract class ResourceNode extends WorldObject {
    protected ResourceStore resourcesOnRemoval;
    
    protected ResourceNodeType type;

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

    @Override
    public void destroy() {
        super.destroy();
        resourceManager.mergeResources(resourcesOnRemoval);
        
    }
}
