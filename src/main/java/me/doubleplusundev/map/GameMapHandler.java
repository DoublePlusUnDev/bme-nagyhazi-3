package me.doubleplusundev.map;

import me.doubleplusundev.map.structures.StructureFactory;
import me.doubleplusundev.map.structures.StructureType;
import me.doubleplusundev.map.worldgen.WorldGenerator;

public class GameMapHandler {
    private static GameMapHandler instance;

    GameMap map;

    private GameMapHandler() {
        this.map = WorldGenerator.generateWorld(500, 500);
    }

    public static GameMapHandler getInstance() {
        if (instance == null){
            instance = new GameMapHandler();
        }
    
        return instance;
    }

    public static void setInstance(GameMapHandler mock) {
        GameMapHandler.instance = mock;
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
            StructureFactory.create(x, y, type);
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
