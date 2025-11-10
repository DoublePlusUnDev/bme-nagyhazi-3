package me.doubleplusundev.resource;

public class ResourceManager {
    private ResourceStore resources;

    public ResourceManager() {
        resources = new ResourceStore();
    }
    
    public void setResource(ResourceType type, double amount) {
        resources.setResource(type, amount);
    }

    public double getResource(ResourceType type) {
        return resources.getResource(type);
    }

    public void mergeResources(ResourceStore other) {
        resources.forceMerge(other);
    }

    public ResourceStore getResources() {
        return resources;
    }

    public void setResources(ResourceStore resources) {
        this.resources = resources;
    }
}
