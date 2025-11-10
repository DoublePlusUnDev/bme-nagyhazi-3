package me.doubleplusundev.map.structures;

import java.util.Map;

import me.doubleplusundev.game.UpdateManager;
import me.doubleplusundev.map.GameMap;
import me.doubleplusundev.map.TileType;
import me.doubleplusundev.resource.ResourceManager;
import me.doubleplusundev.resource.ResourceStore;
import me.doubleplusundev.resource.ResourceType;

public class Quarry extends Structure {

    protected Quarry(int xPos, int yPos, GameMap gameMap, ResourceManager resourceManager, UpdateManager updateManager) {
        super(xPos, yPos, StructureType.QUARRY, gameMap, resourceManager, updateManager);
        production = new ResourceStore(Map.of(ResourceType.STONE, 0.1));
    }

    @Override
    public void create() {
        if (gameMap.getTile(xPos, yPos) != TileType.ROCK)
            return;

        if (gameMap.getWorldObject(xPos, yPos) != null)
            return;
    
        super.create();
    }

    
}
