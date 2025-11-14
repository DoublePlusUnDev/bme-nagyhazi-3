package me.doubleplusundev.map.worldobject.component;

import me.doubleplusundev.resource.ResourceBank;

/**
 * Some worldobject when removed by the player give resources or may require certain resources for the removal process.
 * The removal is only possible if the required resources are present.
 */
public class HarvestableComponent extends Component {
    private final ResourceBank resourceBack; /** The resources provided by/required for object removal.*/

    public HarvestableComponent(ResourceBank resourceBank) {
        this.resourceBack = resourceBank;
    }

    /**
     * Returns whether the harvest is possible with the provided resources and if so modifies the resources accordingly.
     * @param resources
     * @return Whether the removal is possible.
     */
    public boolean tryHarvest(ResourceBank resources) {
        return resources.tryMerge(resourceBack);
    }
}
