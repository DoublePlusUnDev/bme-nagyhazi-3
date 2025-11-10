package me.doubleplusundev.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

import javax.imageio.ImageIO;

import me.doubleplusundev.map.TileType;
import me.doubleplusundev.map.resourcenodes.ResourceNodeType;
import me.doubleplusundev.map.structures.StructureType;
import me.doubleplusundev.resource.ResourceType;
import me.doubleplusundev.ui.ExceptionUI;

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

    private static final Map<StructureType, String> structurePaths = Map.ofEntries(
        Map.entry(StructureType.ROAD, "/textures/road.png"),
        Map.entry(StructureType.CENTER, "/textures/center.png")
    );
    private static final Map<StructureType, BufferedImage> structureImages;

    private static final Map<ResourceNodeType, String> resourceNodePaths = Map.ofEntries(
        Map.entry(ResourceNodeType.TREE, "/textures/tree.png"),
        Map.entry(ResourceNodeType.BOULDER, "/textures/boulder.png")
    );
    private static final EnumMap<ResourceNodeType, BufferedImage> resourceNodeImages;

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
                ExceptionUI.showException(e);
            }
        }

        structureImages = new EnumMap<>(StructureType.class);
        for (Map.Entry<StructureType, String> structure : structurePaths.entrySet()) {
            try{
                structureImages.put(structure.getKey(), ImageIO.read(TextureManager.class.getResource(structure.getValue())));
            }
            catch (IOException e){
                ExceptionUI.showException(e);
            }
        }

        resourceNodeImages = new EnumMap<>(ResourceNodeType.class);
        for (Map.Entry<ResourceNodeType, String> resourceNode : resourceNodePaths.entrySet()) {
            try{
                resourceNodeImages.put(resourceNode.getKey(), ImageIO.read(TextureManager.class.getResource(resourceNode.getValue())));
            }
            catch (IOException e){
                ExceptionUI.showException(e);
            }
        }

        resourceImages = new EnumMap<>(ResourceType.class);
        for (Map.Entry<ResourceType, String> resource : resourcePaths.entrySet()) {
            try{
                resourceImages.put(resource.getKey(), ImageIO.read(TextureManager.class.getResource(resource.getValue())));
            }
            catch (IOException e){
                ExceptionUI.showException(e);
            }
        }
    }

    private TextureManager() {
        
    }
    
    public static BufferedImage getTile(TileType tile) {
        return tileImages.get(tile);
    }

    public static BufferedImage getStructure(StructureType structure) {
        return structureImages.get(structure);
    }

    public static BufferedImage getResourceNode(ResourceNodeType resourceNode) {
        return resourceNodeImages.get(resourceNode);
    }

    public static BufferedImage getResource(ResourceType resource) {
        return resourceImages.get(resource);
    }
}
