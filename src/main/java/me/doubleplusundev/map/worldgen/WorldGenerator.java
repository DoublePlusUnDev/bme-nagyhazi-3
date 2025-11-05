package me.doubleplusundev.map.worldgen;

import me.doubleplusundev.map.GameMap;
import me.doubleplusundev.map.TileType;

public class WorldGenerator {
    public static GameMap generateWorld(int xSize, int ySize){
        GameMap map = new GameMap(xSize, ySize);
        
        for (int x = 0; x < xSize; x++){
            for (int y = 0; y < ySize; y++) {

                double scale = 0.05;
                double noise = PerlinNoise.noise(x * scale, y * scale, scale);


                double distanceFromBorder = Math.min(Math.min(x - 0, xSize - x), Math.min(y - 0, ySize - y)) * 0.03;
                double mask = 1 - distanceFromBorder*distanceFromBorder / (1 + distanceFromBorder * distanceFromBorder); // 1- x^2/(1 -x^2) reverse sigmoid function for dropoff near edges

                double height = noise - mask;
                
                TileType tile;

                if (height > 0.35)
                    tile = TileType.SNOW;
                else if (height  > 0.15)
                    tile = TileType.ROCK;
                else if (height  > -0.1)
                    tile = TileType.GRASS;
                else if (height  > -0.2)
                    tile = TileType.SAND;
                else if (height  > -0.35)
                    tile = TileType.SEA_SHORE;
                else
                    tile = TileType.SEA_DEEP;

                map.setTile(x, y, tile);
            }
        }

        return map;
    } 
    
}
