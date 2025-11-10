package me.doubleplusundev.map.structures;

import java.util.Map;

import me.doubleplusundev.game.UpdateManager;
import me.doubleplusundev.map.GameMap;
import me.doubleplusundev.resource.ResourceManager;
import me.doubleplusundev.resource.ResourceStore;
import me.doubleplusundev.resource.ResourceType;

public class BlackSmith extends Structure {

    protected BlackSmith(int xPos, int yPos, GameMap gameMap, ResourceManager resourceManager, UpdateManager updateManager) {
        super(xPos, yPos, StructureType.BLACKSMITH, gameMap, resourceManager, updateManager);
        production = new ResourceStore(Map.of(ResourceType.WOOD , -0.3, ResourceType.STONE, -0.2, ResourceType.IRON, 0.1));
    }
    
}
