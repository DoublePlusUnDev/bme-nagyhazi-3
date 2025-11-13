package me.doubleplusundev.resource;

public class ResourceManager {
    private ResourceBank resources;

    public ResourceManager() {
        resources = new ResourceBank();
    }
    
    public void setResource(ResourceType type, double amount) {
        resources.setResource(type, amount);
    }

    public double getResource(ResourceType type) {
        return resources.getResource(type);
    }

    public void mergeResources(ResourceBank other) {
        resources.forceMerge(other);
    }

    public boolean tryMergeResources(ResourceBank other) {
        return resources.tryMerge(other);
    }

    public ResourceBank getResources() {
        return resources;
    }

    public void setResources(ResourceBank resources) {
        this.resources = resources;
    }
}
