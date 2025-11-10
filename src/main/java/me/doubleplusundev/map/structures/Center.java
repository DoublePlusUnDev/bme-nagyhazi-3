package me.doubleplusundev.map.structures;

import me.doubleplusundev.game.UpdateManager;
import me.doubleplusundev.map.GameMap;
import me.doubleplusundev.map.TileType;
import me.doubleplusundev.resource.ResourceManager;

public class Center extends Structure {
    
    public Center(int xPos, int yPos, GameMap gameMap, ResourceManager resourceManager, UpdateManager updateManager) {
        super(xPos, yPos, StructureType.CENTER, gameMap, resourceManager, updateManager);
    }

    @Override
    public void create() {
        if (gameMap.getTile(xPos, yPos) == TileType.SEA_DEEP || gameMap.getTile(xPos, yPos) == TileType.SEA_SHORE)
            return;

        if (gameMap.getWorldObject(xPos, yPos) != null)
            return;

        super.create();
    }

    
}
