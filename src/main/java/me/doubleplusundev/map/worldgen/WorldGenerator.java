package me.doubleplusundev.map.worldgen;

import java.util.Random;

import me.doubleplusundev.map.GameMap;
import me.doubleplusundev.map.tiles.TileType;
import me.doubleplusundev.map.worldobject.WorldObjectFactory;
import me.doubleplusundev.map.worldobject.WorldObjectType;

/**
 * Class responsible for generating a GameMap with given parameters. 
 * Parameters are x and y dimensions of the map, and also seed.
 * Different seeds may result in different maps, the same seed will always generate the exact same map.
 */
public class WorldGenerator {
    private final WorldObjectFactory worldObjectFactory;

    public WorldGenerator(WorldObjectFactory worldObjectFactory) {
        this.worldObjectFactory = worldObjectFactory;
    }

    /**
     * Generates a GameMap objecet of the given size based on the given world seed
     * @param xSize Width
     * @param ySize Height
     * @param seed World seed
     * @return A GameMap object
     */
    public GameMap generateWorld(int xSize, int ySize, long seed){
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
                
                generateTile(map, random, x, y, height);
            }
        }

        return map;
    }

    /**
     * Generates a tile on the given position, based on the perlin height inputted.
     * Also attempts to generate a WorldObject when applicable.
     * @param map
     * @param random
     * @param x
     * @param y
     * @param height
     */
    private void generateTile(GameMap map, Random random, int x, int y, double height) {
        TileType tile;
        
        if (height > 0.35){
            tile = TileType.SNOW;
            if (random.nextDouble() < 0.15) {
                map.setWorldObject(x, y, worldObjectFactory.create(WorldObjectType.BOULDER));
            }
        }   
        else if (height  > 0.15) {
            tile = TileType.ROCK;
            if (random.nextDouble() < 0.3) {
                map.setWorldObject(x, y, worldObjectFactory.create(WorldObjectType.BOULDER));
            }
        }
        else if (height  > -0.1) {
            tile = TileType.GRASS;
            if (random.nextDouble() < 0.1) {
                map.setWorldObject(x, y, worldObjectFactory.create(WorldObjectType.TREE));
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
        
        map.setTile(x, y, tile);
    } 
}
