package me.doubleplusundev.map.resourcenodes;

import me.doubleplusundev.game.UpdateManager;
import me.doubleplusundev.map.GameMap;
import me.doubleplusundev.map.WorldObject;
import me.doubleplusundev.resource.ResourceManager;
import me.doubleplusundev.resource.ResourceStore;

public abstract class ResourceNode extends WorldObject {
    protected ResourceStore resourcesOnRemoval = new ResourceStore();
    
    protected ResourceNodeType type;

    protected ResourceNode (int xPos, int yPos, ResourceNodeType type, GameMap gameMap, ResourceManager resourceManager, UpdateManager updateManager) {
        super(xPos, yPos, gameMap, resourceManager, updateManager);
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
