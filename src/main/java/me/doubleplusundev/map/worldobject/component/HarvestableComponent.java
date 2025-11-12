package me.doubleplusundev.map.worldobject.component;

import me.doubleplusundev.resource.ResourceManager;
import me.doubleplusundev.resource.ResourceStore;

public class HarvestableComponent extends Component {
    private final ResourceStore resourceStore;
    private transient ResourceManager resourceManager;

    public HarvestableComponent(ResourceStore resourceStore, ResourceManager resourceManager) {
        this.resourceStore = resourceStore;
        this.resourceManager = resourceManager;
    }

    public boolean tryHarvest() {
        return  resourceManager.tryMergeResources(resourceStore);
    }

    public void loadBack(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }
}
