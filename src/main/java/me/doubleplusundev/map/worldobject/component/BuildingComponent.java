package me.doubleplusundev.map.worldobject.component;

import java.util.Map;
import java.util.Set;

import me.doubleplusundev.map.TileType;
import me.doubleplusundev.map.worldobject.WorldObjectType;
import me.doubleplusundev.resource.ResourceBank;

public class BuildingComponent extends Component {
    private static final Map<WorldObjectType, Integer> maxInstances = Map.of(WorldObjectType.CENTER, 1);
    
    private final ResourceBank buildingCost;
    private final Set<TileType> suitableTiles;

    public BuildingComponent(ResourceBank buildingCost, Set<TileType> suitableTiles) {
        this.buildingCost = buildingCost;
        this.suitableTiles = suitableTiles;
    }

    public boolean tryBuild(ResourceBank availableResources, TileType baseTile, int instanceCount) {
        if (instanceCount >= maxInstances.getOrDefault(getOwner().getComponent(TypeComponent.class).getType(), 10000))
            return false; 

        if (!suitableTiles.contains(baseTile))
            return false;

        return availableResources.tryMerge(buildingCost);
    }
}
