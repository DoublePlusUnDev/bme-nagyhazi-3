package me.doubleplusundev.map;

public class MapHandler {
    private static MapHandler instance;

    Map map;

    private MapHandler() {
        this.map = new Map(5, 5);
    }

    public static MapHandler getInstance(){
        if (instance == null){
            instance = new MapHandler();
        }
    
        return instance;
    }

    public TileType getTile(int x, int y){
        if (0 <= x && x < map.getWidth() && 0 <= y && y < map.getHeight())
            return map.getTile(x, y);
        else
            return TileType.SEA_DEEP;
    }
}
