package me.doubleplusundev.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

import javax.imageio.ImageIO;

import me.doubleplusundev.map.tiles.TileType;
import me.doubleplusundev.map.worldobject.WorldObjectType;
import me.doubleplusundev.resource.ResourceType;

/**
 * Allows for access to cached images for each type of tile/worldobject/resource.
 */
public class TextureManager {
    private static final Map<TileType, String> tilePaths = Map.ofEntries(
        Map.entry(TileType.GRASS, "/textures/grass.png"),
        Map.entry(TileType.LAKE, "/textures/lake.png"),
        Map.entry(TileType.ROCK, "/textures/rock.png"),
        Map.entry(TileType.SAND, "/textures/sand.png"),
        Map.entry(TileType.SEA_DEEP, "/textures/sea_deep.png"),
        Map.entry(TileType.SEA_SHORE, "/textures/sea_shore.png"),
        Map.entry(TileType.SNOW, "/textures/snow.png")
    );
    private static final EnumMap<TileType, BufferedImage> tileImages;

    private static final Map<WorldObjectType, String> worldObjectPaths = Map.ofEntries(
        Map.entry(WorldObjectType.ROAD, "/textures/road.png"),
        Map.entry(WorldObjectType.CENTER, "/textures/center.png"),
        Map.entry(WorldObjectType.LUMBERHUT, "/textures/lumberhut.png"),
        Map.entry(WorldObjectType.QUARRY, "/textures/quarry.png"),
        Map.entry(WorldObjectType.BLACKSMITH, "/textures/blacksmith.png"),
        Map.entry(WorldObjectType.TREE, "/textures/tree.png"),
        Map.entry(WorldObjectType.BOULDER, "/textures/boulder.png")
    );
    private static final Map<WorldObjectType, BufferedImage> worldObjectImages;

    private static final Map<ResourceType, String> resourcePaths = Map.ofEntries(
        Map.entry(ResourceType.WOOD, "/textures/wood.png"),
        Map.entry(ResourceType.STONE, "/textures/stone.png"),
        Map.entry(ResourceType.IRON, "/textures/iron.png")
    );
    private static final EnumMap<ResourceType, BufferedImage> resourceImages;

    static {
        tileImages = new EnumMap<>(TileType.class);
        for (Map.Entry<TileType, String> tile : tilePaths.entrySet()) {
            try{
                tileImages.put(tile.getKey(), ImageIO.read(TextureManager.class.getResource(tile.getValue())));
            }
            catch (IOException e){
                UIUtils.showException(e);
            }
        }

        worldObjectImages = new EnumMap<>(WorldObjectType.class);
        for (Map.Entry<WorldObjectType, String> structure : worldObjectPaths.entrySet()) {
            try{
                worldObjectImages.put(structure.getKey(), ImageIO.read(TextureManager.class.getResource(structure.getValue())));
            }
            catch (IOException e){
                UIUtils.showException(e);
            }
        }

        resourceImages = new EnumMap<>(ResourceType.class);
        for (Map.Entry<ResourceType, String> resource : resourcePaths.entrySet()) {
            try{
                resourceImages.put(resource.getKey(), ImageIO.read(TextureManager.class.getResource(resource.getValue())));
            }
            catch (IOException e){
                UIUtils.showException(e);
            }
        }
    }

    private TextureManager() {
        
    }
    
    /**
     * Returns the image to the corresponding tile type.
     * @param tile
     * @return
     */
    public static BufferedImage getTile(TileType tile) {
        return tileImages.get(tile);
    }

    /**
     * Returns the image to the corresponding worldobject type.
     * @param tile
     * @return
     */
    public static BufferedImage getWorldObject(WorldObjectType worldObject) {
        return worldObjectImages.get(worldObject);
    }

    /**
     * Returns the image to the corresponding resource type.
     * @param tile
     * @return
     */
    public static BufferedImage getResource(ResourceType resourceType) {
        return resourceImages.get(resourceType);
    }
}
