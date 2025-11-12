package me.doubleplusundev.map.worldobject.component;

import java.util.Map;
import java.util.Set;

import me.doubleplusundev.map.TileType;
import me.doubleplusundev.map.worldobject.WorldObjectType;
import me.doubleplusundev.resource.ResourceStore;

public class BuildingComponent extends Component {
    private static final Map<WorldObjectType, Integer> maxInstances = Map.of(WorldObjectType.CENTER, 1);
    
    private final ResourceStore buildingCost;
    private final Set<TileType> suitableTiles;

    public BuildingComponent(ResourceStore buildingCost, Set<TileType> suitableTiles) {
        this.buildingCost = buildingCost;
        this.suitableTiles = suitableTiles;
    }

    public boolean tryBuild(ResourceStore availableResources, TileType baseTile, int instanceCount) {
        if (instanceCount >= maxInstances.getOrDefault(getOwner().getComponent(TypeComponent.class).getType(), 10000))
            return false; 

        if (!suitableTiles.contains(baseTile))
            return false;

        return availableResources.tryMerge(buildingCost);
    }
}
