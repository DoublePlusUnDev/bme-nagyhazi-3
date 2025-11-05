package me.doubleplusundev.map;

public class GameMapHandler {
    private static GameMapHandler instance;

    GameMap map;

    private GameMapHandler() {
        this.map = new GameMap(5, 5);
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
}
