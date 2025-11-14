package me.doubleplusundev.map.worldobject.component;

import me.doubleplusundev.game.ITickable;
import me.doubleplusundev.resource.ResourceManager;
import me.doubleplusundev.resource.ResourceBank;

/**
 * Some worldobjects provide/consume a set amount of resources each game tick.
 * The resource provision is only possible, if there are enough from the consumed resource types.
 * If the worldobject has an activable component the production is only possible when it is active.
 * If no component is present production is unaffected from the activation status.
 */
public class ProductionComponent extends Component implements ITickable {
    private final ResourceBank resourceStore; /** The provided and consumed resources each tick. */
    private transient ResourceManager resourceManager;

    public ProductionComponent(ResourceBank resourceStore, ResourceManager resourceManager){
        this.resourceStore = resourceStore;
        this.resourceManager = resourceManager;    
    }

    /**
     * Attempts to produce the specified amount of resources, if the conditions are given.
     * If the world object has an activable, it must be active.
     * Requires all the resources from resourcemanager not to dip below zero, after merging the resources.
     */
    @Override
    public void tick(int count) {
        ActivableComponent activable = getOwner().getComponent(ActivableComponent.class);

        if (activable != null && !activable.isActive())
            return;

        resourceManager.tryMergeResources(resourceStore);
    }

    /**
     * Used at loading a worldfile, assigns the dependencies.
     * @param resourceManager
     */
    public void loadBack(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }
    
}
