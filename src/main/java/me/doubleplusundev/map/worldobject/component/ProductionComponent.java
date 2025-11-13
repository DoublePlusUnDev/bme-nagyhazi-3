package me.doubleplusundev.map.worldobject.component;

import me.doubleplusundev.game.ITickable;
import me.doubleplusundev.resource.ResourceManager;
import me.doubleplusundev.resource.ResourceStore;

public class ProductionComponent extends Component implements ITickable {
    private final ResourceStore resourceStore;
    private transient ResourceManager resourceManager;

    public ProductionComponent(ResourceStore resourceStore, ResourceManager resourceManager){
        this.resourceStore = resourceStore;
        this.resourceManager = resourceManager;    
    }

    @Override
    public void tick(int count) {
        ActivableComponent activable = getOwner().getComponent(ActivableComponent.class);
        
        if (activable != null && !activable.isActive())
            return;

        resourceManager.tryMergeResources(resourceStore);
    }

    public void loadBack(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }
    
}
