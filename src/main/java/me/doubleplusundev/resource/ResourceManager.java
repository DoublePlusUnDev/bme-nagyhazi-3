package me.doubleplusundev.resource;

import java.util.EnumMap;

public class ResourceManager {
    private final EnumMap<ResourceType, Integer> resources;

    public ResourceManager() {
        resources = new EnumMap<>(ResourceType.class);
        
        for (ResourceType resource : ResourceType.values()) {
            resources.put(resource, 0);
        }
        resources.put(ResourceType.WOOD, 20);
        resources.put(ResourceType.STONE, 200);
    }
    
    public void setResource(ResourceType type, int amount) {
        resources.put(type, amount);
    }

    public int getResource(ResourceType type) {
        return resources.get(type);
    }
}
