package me.doubleplusundev.map.worldgen;

import java.util.Random;

import me.doubleplusundev.map.GameMap;
import me.doubleplusundev.map.TileType;
import me.doubleplusundev.map.resourcenodes.Boulder;
import me.doubleplusundev.map.resourcenodes.Tree;

public class WorldGenerator {
    public static GameMap generateWorld(int xSize, int ySize, long seed){
        GameMap map = new GameMap(xSize, ySize);
        
        PerlinNoise.setSeed(seed);
        Random random = new Random(seed);

        for (int x = 0; x < xSize; x++){
            for (int y = 0; y < ySize; y++) {

                double scale = 0.05;
                double noise = PerlinNoise.noise(x * scale, y * scale, scale);


                double distanceFromBorder = Math.min(Math.min(x - 0, xSize - x), Math.min(y - 0, ySize - y)) * 0.03;
                double mask = 1 - distanceFromBorder*distanceFromBorder / (1 + distanceFromBorder * distanceFromBorder); // 1- x^2/(1 -x^2) reverse sigmoid function for dropoff near edges

                double height = noise - mask;
                
                TileType tile = generateTile(map, random, x, y, height);

                map.setTile(x, y, tile);
            }
        }

        return map;
    }

    private static TileType generateTile(GameMap map, Random random, int x, int y, double height) {
        TileType tile;
        
        if (height > 0.35){
            tile = TileType.SNOW;
            if (random.nextDouble() < 0.15) {
                map.setWorldObject(x, y, new Boulder(x, y, map));
            }
        }   
        else if (height  > 0.15) {
            tile = TileType.ROCK;
            if (random.nextDouble() < 0.3) {
                map.setWorldObject(x, y, new Boulder(x, y, map));
            }
        }
        else if (height  > -0.1) {
            tile = TileType.GRASS;
            if (random.nextDouble() < 0.1) {
                map.setWorldObject(x, y, new Tree(x, y, map));
            }
        }
        else if (height  > -0.2) {
            tile = TileType.SAND;
        }
        else if (height  > -0.35) {
            tile = TileType.SEA_SHORE;
        }
        else {
            tile = TileType.SEA_DEEP;
        }
        return tile;
    } 
    
    private WorldGenerator() {

    }
}
