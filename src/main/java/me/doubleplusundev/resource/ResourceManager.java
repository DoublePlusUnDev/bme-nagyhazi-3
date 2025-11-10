package me.doubleplusundev.resource;

import java.util.HashMap;
import java.util.Map;

public class ResourceManager {
    private Map<ResourceType, Integer> resources;

    private static ResourceManager instance;

    private ResourceManager() {
        resources = new HashMap<ResourceType,Integer>();
        
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
    
    public int getResource(ResourceType type) {
        return resources.get(type);
    }
}
