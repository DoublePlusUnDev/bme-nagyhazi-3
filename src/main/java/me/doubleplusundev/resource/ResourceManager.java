package me.doubleplusundev.resource;

import java.util.EnumMap;

public class ResourceManager {
    private final EnumMap<ResourceType, Integer> resources;

    private static ResourceManager instance;

    private ResourceManager() {
        resources = new EnumMap<>(ResourceType.class);
        
        for (ResourceType resource : ResourceType.values()) {
            resources.put(resource, 0);
        }
        resources.put(ResourceType.WOOD, 20);
        resources.put(ResourceType.STONE, 200);
    }

    public static ResourceManager getInstance() {
        if (instance == null) {
            instance = new ResourceManager();
        }
        return instance;
    }

    public static void setInstance(ResourceManager mock) {
        instance = mock;
    }
    
    public void setResource(ResourceType type, int amount) {
        resources.put(type, amount);
    }

    public int getResource(ResourceType type) {
        return resources.get(type);
    }
}
