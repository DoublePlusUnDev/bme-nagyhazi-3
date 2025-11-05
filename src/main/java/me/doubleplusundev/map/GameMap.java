package me.doubleplusundev.map;

import me.doubleplusundev.map.structures.Road;

public class GameMap {
    int width;
    int height;
    TileType[][] tiles;
    WorldObject[][] worldObjects;
    
    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
        
        tiles = new TileType[width][height];
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                tiles[x][y] = TileType.GRASS;
            }
        }

        worldObjects = new WorldObject[width][height];
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                worldObjects[x][y] = new Road();
            }
        }
    }

    public TileType getTile(int x, int y) {
        if (x < 0 || width <= x || y < 0 || height <= y){
            throw new IllegalArgumentException();
        }

        return tiles[x][y];
    }

    public void setTile(int x, int y, TileType tile) {
        if (x < 0 ||  width <= x || y < 0 || height <= y){
            throw new IllegalArgumentException();
        }

        tiles[x][y] = tile;
    }

    public WorldObject getWorldObject(int x, int y) {
        if (x < 0 || width <= x || y < 0 || height <= y){
            throw new IllegalArgumentException();
        }

        return worldObjects[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}