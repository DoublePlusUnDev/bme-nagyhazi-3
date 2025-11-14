package me.doubleplusundev.map;

import java.io.Serializable;

import me.doubleplusundev.map.worldobject.WorldObject;

/**
 * The object storing the data for the gamemap.
 * Composed of two layers: Tiles and Worldobjects.
 * Tiles are an width * height matrix of enums.
 * Worldobject are entities occupying one gridspace on top of the tiles matrix.
 * Not everygridspace has a worldobject.
 */
public class GameMap implements Serializable {
    private final int width; /** Width of the matrix. */
    private final int height; /** Height of the matrix. */
    private final TileType[][] tiles; /** Tile matrix. */
    private final WorldObject[][] worldObjects; /** Worldobject matrix. */
    
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

    /**
     * Gets the tile from position x and y.
     * @param x X position.
     * @param y Y position.
     * @return The tile at the given place.
     * @throws IllegalArgumentException If the x and y are out of bounds of the map.
     */
    public TileType getTile(int x, int y) throws IllegalArgumentException {
        if (x < 0 || width <= x || y < 0 || height <= y){
            throw new IllegalArgumentException();
        }

        return tiles[x][y];
    }

    /**
     * Overwrites the tile at position x and y.
     * @param x X position.
     * @param y Y position.
     * @param tile The type of the new tile.
     * @throws IllegalArgumentException If the x and y are out of bounds of the map.
     */
    public void setTile(int x, int y, TileType tile) {
        if (x < 0 ||  width <= x || y < 0 || height <= y){
            throw new IllegalArgumentException();
        }

        tiles[x][y] = tile;
    }

    /**
     * Gets the world object from position x and y.
     * @param x X position.
     * @param y Y position.
     * @return The worldobject at the given position. May be null if empty.
     * @throws IllegalArgumentException If the x and y are out of bounds of the map.
     */
    public WorldObject getWorldObject(int x, int y) {
        if (x < 0 || width <= x || y < 0 || height <= y){
            throw new IllegalArgumentException();
        }

        return worldObjects[x][y];
    }

    /**
     * Overwrites the world object at position x and y.
     * @param x X position.
     * @param y Y position.
     * @param object The new worldobject.
     * @throws IllegalArgumentException If the x and y are out of bounds of the map.
     */
    public void setWorldObject(int x, int y, WorldObject object) {
        if (x < 0 || width <= x || y < 0 || height <= y){
            throw new IllegalArgumentException();
        }
        worldObjects[x][y] = object;
    }

    /**
     * Getter for the width of the map.
     * @return Width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Getter for the height of the map.
     * @return Height.
     */
    public int getHeight() {
        return height;
    }
}