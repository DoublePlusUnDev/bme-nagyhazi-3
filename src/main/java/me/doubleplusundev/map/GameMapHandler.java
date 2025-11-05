package me.doubleplusundev.map;

import me.doubleplusundev.map.structures.Center;
import me.doubleplusundev.map.structures.Structure;
import me.doubleplusundev.map.structures.StructureFactory;
import me.doubleplusundev.map.structures.StructureType;

public class GameMapHandler {
    private static GameMapHandler instance;

    GameMap map;

    private GameMapHandler() {
        this.map = WorldGenerator.generateWorld(500, 500);
    }

    public static GameMapHandler getInstance(){
        if (instance == null){
            instance = new GameMapHandler();
        }
    
        return instance;
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

    public void placeStructure(int x, int y, StructureType type) {
        if (0 <= x && x < map.getWidth() && 0 <= y && y < map.getHeight()) {
            map.setWorldObjet(x, y, StructureFactory.create(type));
        }
    }
}
