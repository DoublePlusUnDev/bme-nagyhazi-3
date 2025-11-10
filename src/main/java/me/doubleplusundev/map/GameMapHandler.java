package me.doubleplusundev.map;

import me.doubleplusundev.map.structures.StructureFactory;
import me.doubleplusundev.map.structures.StructureType;
import me.doubleplusundev.map.worldgen.WorldGenerator;

public class GameMapHandler {
    GameMap map;

    public GameMapHandler() {
        this.map = WorldGenerator.generateWorld(500, 500);
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
            StructureFactory.create(x, y, type, this);
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
}
