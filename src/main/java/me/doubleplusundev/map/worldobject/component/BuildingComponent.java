package me.doubleplusundev.map.worldobject.component;

import java.util.Map;
import java.util.Set;

import me.doubleplusundev.map.TileType;
import me.doubleplusundev.map.worldobject.WorldObjectType;
import me.doubleplusundev.resource.ResourceBank;

/**
 * Player buildable structures.
 * A build limit may be set for certain types of buildings.
 * May have a building cost, only buildable if the necessary resources are provided.
 * Some buildings may only be built on certain types of tiles.
 */
public class BuildingComponent extends Component {
    private static final Map<WorldObjectType, Integer> maxInstances = Map.of(WorldObjectType.CENTER, 1);
    
    private final ResourceBank buildingCost; /** The amount of resourcs that will be merged into the resourcehandlers bank upon building.
                                                Building costs are negative in amount, resources gained upon building are positive. */
    private final Set<TileType> suitableTiles; /** The tiles on which the building can be built. */

    public BuildingComponent(ResourceBank buildingCost, Set<TileType> suitableTiles) {
        this.buildingCost = buildingCost;
        this.suitableTiles = suitableTiles;
    }

    /**
     * Checks whether the conditions are given for building and remove/add the resources.
     * If building is possible it will subtract the cost / add the gained resources to the resourcebank. 
     * @param availableResources The resoucestore which the cost/gained resources will be subtracted from/added to.  
     * @param baseTile The tile the building is attempted to be built upon.
     * @param instanceCount Number of instances of the building in existance.
     * @return Whether building is possible.
     */
    public boolean tryBuild(ResourceBank availableResources, TileType baseTile, int instanceCount) {
        if (instanceCount >= maxInstances.getOrDefault(getOwner().getComponent(TypeComponent.class).getType(), 10000))
            return false; 

        if (!suitableTiles.isEmpty() && !suitableTiles.contains(baseTile))
            return false;

        return availableResources.tryMerge(buildingCost);
    }
}
