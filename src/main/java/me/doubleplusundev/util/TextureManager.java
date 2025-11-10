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

public class TextureManager {
    private static TextureManager instance;

    private Map<TileType, String> tilePaths = Map.ofEntries(
        Map.entry(TileType.GRASS, "/textures/grass.png"),
        Map.entry(TileType.LAKE, "/textures/lake.png"),
        Map.entry(TileType.ROCK, "/textures/rock.png"),
        Map.entry(TileType.SAND, "/textures/sand.png"),
        Map.entry(TileType.SEA_DEEP, "/textures/sea_deep.png"),
        Map.entry(TileType.SEA_SHORE, "/textures/sea_shore.png"),
        Map.entry(TileType.SNOW, "/textures/snow.png")
    );
    private EnumMap<TileType, BufferedImage> tileImages;

    private Map<StructureType, String> structurePaths = Map.ofEntries(
        Map.entry(StructureType.ROAD, "/textures/road.png"),
        Map.entry(StructureType.CENTER, "/textures/center.png")
    );
    private Map<StructureType, BufferedImage> structureImages;

    private Map<ResourceNodeType, String> resourceNodePaths = Map.ofEntries(
        Map.entry(ResourceNodeType.TREE, "/textures/tree.png"),
        Map.entry(ResourceNodeType.BOULDER, "/textures/boulder.png")
    );
    private EnumMap<ResourceNodeType, BufferedImage> resourceNodeImages;

    private Map<ResourceType, String> resourcePaths = Map.ofEntries(
        Map.entry(ResourceType.WOOD, "/textures/wood.png"),
        Map.entry(ResourceType.STONE, "/textures/stone.png"),
        Map.entry(ResourceType.IRON, "/textures/iron.png")
    );
    private EnumMap<ResourceType, BufferedImage> resourceImages;

    private TextureManager() {
        tileImages = new EnumMap<>(TileType.class);
        for (Map.Entry<TileType, String> tile : tilePaths.entrySet()) {
            try{
                tileImages.put(tile.getKey(), ImageIO.read(getClass().getResource(tile.getValue())));
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

        structureImages = new EnumMap<>(StructureType.class);
        for (Map.Entry<StructureType, String> structure : structurePaths.entrySet()) {
            try{
                structureImages.put(structure.getKey(), ImageIO.read(getClass().getResource(structure.getValue())));
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

        resourceNodeImages = new EnumMap<>(ResourceNodeType.class);
        for (Map.Entry<ResourceNodeType, String> resourceNode : resourceNodePaths.entrySet()) {
            try{
                resourceNodeImages.put(resourceNode.getKey(), ImageIO.read(getClass().getResource(resourceNode.getValue())));
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

        resourceImages = new EnumMap<>(ResourceType.class);
        for (Map.Entry<ResourceType, String> resource : resourcePaths.entrySet()) {
            try{
                resourceImages.put(resource.getKey(), ImageIO.read(getClass().getResource(resource.getValue())));
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    
    public static TextureManager getInstance() {
        if (instance == null) {
            instance = new TextureManager();
        }
        return instance;
    }
    
    public BufferedImage getTile(TileType tile) {
        return tileImages.get(tile);
    }

    public BufferedImage getStructure(StructureType structure) {
        return structureImages.get(structure);
    }

    public BufferedImage getResourceNode(ResourceNodeType resourceNode) {
        return resourceNodeImages.get(resourceNode);
    }

    public BufferedImage getResource(ResourceType resource) {
        return resourceImages.get(resource);
    }
}
