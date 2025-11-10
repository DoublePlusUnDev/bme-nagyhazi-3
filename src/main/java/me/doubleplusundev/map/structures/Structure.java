package me.doubleplusundev.map.structures;

import me.doubleplusundev.game.UpdateManager;
import me.doubleplusundev.map.GameMap;
import me.doubleplusundev.map.WorldObject;
import me.doubleplusundev.resource.ResourceManager;
import me.doubleplusundev.resource.ResourceStore;

public abstract  class Structure extends WorldObject {
    protected ResourceStore production = new ResourceStore();
    
    protected StructureType type;

    protected Structure(int xPos, int yPos, StructureType type, GameMap gameMap, ResourceManager resourceManager, UpdateManager updateManager) {
        super(xPos, yPos, gameMap, resourceManager, updateManager);
        this.type = type;
    }

    public StructureType getType() {
        return type;
    }

    @Override
    public void update() {
        resourceManager.tryMergeResources(production);
    }

    @Override
    public void tick(int count) {
        
    }
}
