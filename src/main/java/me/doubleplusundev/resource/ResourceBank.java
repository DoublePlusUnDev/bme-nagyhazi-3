package me.doubleplusundev.resource;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;

/**
 * Used for storing a collection of resourcetype amount pairs.
 * Can represent the available resources for the player, building costs,
 * resources gained from harvest, produced resources.
 * When no entry is found, value is assumed to be zero.
 */
public class ResourceBank implements Serializable {
    private Map<ResourceType, Double> resources; /** Resourcetype - amount map */

    public ResourceBank(Map<ResourceType, Double> initMap) {
        resources = new EnumMap<>(ResourceType.class);
        for (ResourceType resource : ResourceType.values()) {
            if (initMap.containsKey(resource))
                resources.put(resource, initMap.get(resource));
            else
                resources.put(resource, 0.0);
        }
    }

    public ResourceBank() {
        this(new EnumMap<>(ResourceType.class));
    }

    /**
     * Returns the amount of resources of the given type.
     * If no such resource is registered returns 0.
     * @param type Type of the resource.
     * @return The amount.
     */
    public double getResource(ResourceType type) {
        return resources.getOrDefault(type, 0.0);
    }

    /**
     * Sets the amount for the specified type of resource.
     * @param type The type of the resource.
     * @param amount The new amount.
     */
    public void setResource(ResourceType type, double amount) {
        resources.put(type, amount);
    }

    /**
     * Attempts to add up resources of the same type from the two banks.
     * If the sum is never less than zero the operation will be completed and returns true.
     * Otherwise the operation will be cancelled, resource amounts stay pre-merge and returns false.
     * @param other The added bank of resources.
     * @return Whether the operation could be completed.
     */ 
    public boolean tryMerge(ResourceBank other) {
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

    /**
     * Adds up resources of the same type from the two banks.
     * If the resulting amount of zero the operation will still go through.
     * @param other The added bank of resources.
     */
    public void forceMerge(ResourceBank other) {
        for (ResourceType resource : ResourceType.values()) {
            double result = resources.get(resource) + other.getResource(resource); 
            resources.put(resource, result);
        }
    }
}
