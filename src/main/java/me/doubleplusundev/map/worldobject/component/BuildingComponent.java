package me.doubleplusundev.map.worldobject.component;

import me.doubleplusundev.resource.ResourceStore;

public class BuildingComponent extends Component {
    private final ResourceStore buildingCost;

    public BuildingComponent(ResourceStore buildingCost) {
        this.buildingCost = buildingCost;
    }

    public boolean tryBuild(ResourceStore availableResources) {
        return availableResources.tryMerge(buildingCost);
    }
}
