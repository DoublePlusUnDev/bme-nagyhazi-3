package me.doubleplusundev.map.worldobject.component;

import me.doubleplusundev.resource.ResourceManager;
import me.doubleplusundev.resource.ResourceBank;

public class HarvestableComponent extends Component {
    private final ResourceBank resourceStore;
    private transient ResourceManager resourceManager;

    public HarvestableComponent(ResourceBank resourceStore, ResourceManager resourceManager) {
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
