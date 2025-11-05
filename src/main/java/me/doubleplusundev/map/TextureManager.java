package me.doubleplusundev.map;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import me.doubleplusundev.map.structures.StructureType;

public class TextureManager {
    private static TextureManager instance;

    Map<TileType, String> tilePaths = Map.ofEntries(
        Map.entry(TileType.GRASS, "/textures/grass.png"),
        Map.entry(TileType.LAKE, "/textures/lake.png"),
        Map.entry(TileType.SEA_DEEP, "/textures/sea_deep.png"),
        Map.entry(TileType.SEA_SHORE, "/textures/sea_shore.png")
        );
    Map<TileType, BufferedImage> tileImages;

    Map<StructureType, String> structurePaths = Map.ofEntries(
        Map.entry(StructureType.ROAD, "/textures/road.png")
        );
    Map<StructureType, BufferedImage> structureImages;

    Map<ResourceNodeType, String> resourceNodePaths = Map.ofEntries(
        Map.entry(ResourceNodeType.TREE, "/textures/tree.png"),
       Map.entry(ResourceNodeType.BOULDER, "/textures/boulder.png")
        );
    Map<ResourceNodeType, BufferedImage> resourceNodeImages;

    private TextureManager() {
        tileImages = new HashMap<TileType, BufferedImage>();
        for (Map.Entry<TileType, String> tile : tilePaths.entrySet()) {
            try{
                tileImages.put(tile.getKey(), ImageIO.read(getClass().getResource(tile.getValue())));
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        structureImages = new HashMap<StructureType, BufferedImage>();
        for (Map.Entry<StructureType, String> structure : structurePaths.entrySet()) {
            try{
                structureImages.put(structure.getKey(), ImageIO.read(getClass().getResource(structure.getValue())));
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        resourceNodeImages = new HashMap<ResourceNodeType, BufferedImage>();
        for (Map.Entry<ResourceNodeType, String> resourceNode : resourceNodePaths.entrySet()) {
            try{
                resourceNodeImages.put(resourceNode.getKey(), ImageIO.read(getClass().getResource(resourceNode.getValue())));
            }
            catch (Exception e){
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

    public BufferedImage getResourceNode(ResourceNodeType resource) {
        return resourceNodeImages.get(resource);
    }
}
