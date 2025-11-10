package me.doubleplusundev.resource;

public class ResourceManager {
    private ResourceStore resources;

    public ResourceManager() {
        resources = new ResourceStore();
        
        resources.setResource(ResourceType.WOOD, -500.0);
    }
    
    public void setResource(ResourceType type, double amount) {
        resources.setResource(type, amount);
    }

    public double getResource(ResourceType type) {
        return resources.getResource(type);
    }

    public ResourceStore getResources() {
        return resources;
    }

    public void setResources(ResourceStore resources) {
        this.resources = resources;
    }
}
