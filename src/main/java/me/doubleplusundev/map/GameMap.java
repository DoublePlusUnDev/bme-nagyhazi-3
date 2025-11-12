package me.doubleplusundev.map;

import java.io.Serializable;

import me.doubleplusundev.map.worldobject.WorldObject;

public class GameMap implements Serializable{
    private static final long serialVersionUID = 1L;

    private final int width;
    private final int height;
    private final TileType[][] tiles;
    private final WorldObject[][] worldObjects;
    
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

    public void setWorldObject(int x, int y, WorldObject object) {
        if (x < 0 || width <= x || y < 0 || height <= y){
            throw new IllegalArgumentException();
        }
        worldObjects[x][y] = object;
    }
}