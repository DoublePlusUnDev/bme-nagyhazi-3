package me.doubleplusundev.resource;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;

public class ResourceStore implements Serializable {
    private Map<ResourceType, Double> resources;

    public ResourceStore(Map<ResourceType, Double> initMap) {
        resources = new EnumMap<>(ResourceType.class);
        for (ResourceType resource : ResourceType.values()) {
            if (initMap.containsKey(resource))
                resources.put(resource, initMap.get(resource));
            else
                resources.put(resource, 0.0);
        }
    }

    public ResourceStore() {
        this(new EnumMap<>(ResourceType.class));
    }

    public double getResource(ResourceType type) {
        return resources.getOrDefault(type, 0.0);
    }

    public void setResource(ResourceType type, double amount) {
        resources.put(type, amount);
    }

    public boolean tryMerge(ResourceStore other) {
        EnumMap<ResourceType, Double> result = new EnumMap<>(ResourceType.class);
        for (ResourceType resource : ResourceType.values()) {
            double newAmount = resources.get(resource) + other.getResource(resource); 
            if (newAmount < 0)
                return false;
            result.put(resource, newAmount);
        }
        resources.putAll(result);
        return true;
    }

    public void forceMerge(ResourceStore other) {
        for (ResourceType resource : ResourceType.values()) {
            double result = resources.get(resource) + other.getResource(resource); 
            resources.put(resource, result);
        }
    }
}
