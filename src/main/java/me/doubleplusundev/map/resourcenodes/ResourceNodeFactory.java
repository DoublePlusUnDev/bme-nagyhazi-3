package me.doubleplusundev.map.resourcenodes;

import java.util.Map;

import me.doubleplusundev.map.GameMap;
import me.doubleplusundev.resource.ResourceManager;

public class ResourceNodeFactory {
    @FunctionalInterface
    interface ResourceNodeCreator {
        ResourceNode create(int x, int y, GameMap gameMap, ResourceManager resourceManager);
    }

    private static final Map<ResourceNodeType, ResourceNodeCreator> registry = Map.ofEntries(
        Map.entry(ResourceNodeType.TREE, Tree::new),
        Map.entry(ResourceNodeType.BOULDER, Boulder::new)
    );
    
    private ResourceNodeFactory() {

    }

    public static ResourceNode create(int xPos, int yPos, ResourceNodeType type, GameMap gameMap, ResourceManager resourceManager) {
        ResourceNodeCreator resourceNodeCreator = registry.get(type);

        if (resourceNodeCreator == null) {
            throw new IllegalArgumentException("Unregistered structure type: " + type.toString());
        }
        ResourceNode resourceNode = resourceNodeCreator.create(xPos, yPos, gameMap, resourceManager);
        resourceNode.create();
        return resourceNode;
    }
}
