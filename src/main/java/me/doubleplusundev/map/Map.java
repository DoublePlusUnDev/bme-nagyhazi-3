package me.doubleplusundev.map;

public class Map {
    int width;
    int height;
    TileType[][] tiles;
    
    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new TileType[width][height];
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                tiles[x][y] = TileType.GRASS;
            }
        }
    }

    public TileType getTile(int x, int y){
        if (x < 0 || width <= x || y < 0 || height <= y){
            throw new IllegalArgumentException();
        }

        return tiles[x][y];
    }

    public void setTile(int x, int y, TileType tile){
        if (x < 0 ||  width <= x || y < 0 || height <= y){
            throw new IllegalArgumentException();
        }

        tiles[x][y] = tile;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }
}