package me.doubleplusundev.map;

import me.doubleplusundev.map.resourcenodes.ResourceNodeFactory;
import me.doubleplusundev.map.resourcenodes.ResourceNodeType;
import me.doubleplusundev.map.structures.StructureFactory;
import me.doubleplusundev.map.structures.StructureType;
import me.doubleplusundev.map.worldgen.WorldGenerator;
import me.doubleplusundev.resource.ResourceManager;

public class GameMapHandler {
    private GameMap map;
    private final ResourceManager resourceManager;

    public GameMapHandler(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
        this.map = WorldGenerator.generateWorld(500, 500, 0, resourceManager);
    }

    public TileType getTile(int x, int y){
        if (0 <= x && x < map.getWidth() && 0 <= y && y < map.getHeight())
            return map.getTile(x, y);
        else
            return TileType.SEA_DEEP;
    }

    public WorldObject getWorldObject(int x, int y){
        if (0 <= x && x < map.getWidth() && 0 <= y && y < map.getHeight())
            return map.getWorldObject(x, y);
        else
            return null;
    }

    public void buildStructure(int x, int y, StructureType type) {
        if (0 <= x && x < map.getWidth() && 0 <= y && y < map.getHeight()) {
            StructureFactory.create(x, y, type, map, resourceManager);
        }
    }

     public void spawnResourceNode(int x, int y, ResourceNodeType type) {
        if (0 <= x && x < map.getWidth() && 0 <= y && y < map.getHeight()) {
            ResourceNodeFactory.create(x, y, type, map, resourceManager);
        }
    }

    public void destroyWorldObject(int x, int y) {
        if (0 <= x && x < map.getWidth() && 0 <= y && y < map.getHeight() && map.getWorldObject(x, y) != null) {
            map.getWorldObject(x, y).destroy();
        }
    }

    public void setWorldObject(int x, int y, WorldObject object) {
        if (0 <= x && x < map.getWidth() && 0 <= y && y < map.getHeight()) {
            map.setWorldObject(x, y, object);
        }
    }    

    public GameMap getMap() {
        return map;
    }

    public void setMap(GameMap map) {
        this.map = map;
    }
}
