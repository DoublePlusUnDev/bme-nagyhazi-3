package me.doubleplusundev.map.worldobject;

import java.util.EnumMap;
import java.util.Map;

import me.doubleplusundev.game.UpdateManager;
import me.doubleplusundev.map.worldobject.component.BuildingComponent;
import me.doubleplusundev.map.worldobject.component.HarvestableComponent;
import me.doubleplusundev.map.worldobject.component.ProductionComponent;
import me.doubleplusundev.map.worldobject.component.TypeComponent;
import me.doubleplusundev.resource.ResourceManager;
import me.doubleplusundev.resource.ResourceStore;
import me.doubleplusundev.resource.ResourceType;

public class WorldObjectFactory {
    
    @FunctionalInterface
    interface WorldObjectAssembler {
        WorldObject create(int posX, int posY);
    }

    private final Map<WorldObjectType, WorldObjectAssembler> assemblerRegistry = new EnumMap<>(WorldObjectType.class);

    private final UpdateManager updateManager;
    private final ResourceManager resourceManager;
    
    public WorldObjectFactory(UpdateManager updateManager, ResourceManager resourceManager) {
        this.updateManager = updateManager;
        this.resourceManager = resourceManager;

        registerAssemblers();
    }

    public WorldObject create(WorldObjectType type, int xPos, int yPos) {
        WorldObjectAssembler assembler = assemblerRegistry.get(type);
        if (assembler == null) {
            throw new IllegalArgumentException("No assember for " + type.toString());
        }
        return assembler.create(xPos, yPos);
    }

    private void registerAssemblers() {
        assemblerRegistry.put(WorldObjectType.CENTER, (x, y) -> {
            WorldObject worldObject = new WorldObject(x, y);
            worldObject.addComponent(new BuildingComponent(new ResourceStore()));
            worldObject.addComponent(new TypeComponent(WorldObjectType.CENTER));
            return worldObject;
        });

        assemblerRegistry.put(WorldObjectType.ROAD, (x, y) -> {
            WorldObject worldObject = new WorldObject(x, y);
            worldObject.addComponent(new BuildingComponent(new ResourceStore(Map.of(ResourceType.STONE, 20.0))));
            worldObject.addComponent(new TypeComponent(WorldObjectType.ROAD));
            return worldObject;
        });

        assemblerRegistry.put(WorldObjectType.LUMBERHUT, (x, y) -> {
            WorldObject worldObject = new WorldObject(x, y);
            worldObject.addComponent(new BuildingComponent(new ResourceStore(Map.of(ResourceType.WOOD, 50.0))));
            worldObject.addComponent(new ProductionComponent(new ResourceStore(Map.of(ResourceType.WOOD, 0.3)), resourceManager));
            worldObject.addComponent(new TypeComponent(WorldObjectType.LUMBERHUT));
            updateManager.registerForTick(worldObject.getComponent(ProductionComponent.class));
            return worldObject;
        });

        assemblerRegistry.put(WorldObjectType.QUARRY, (x, y) -> {
            WorldObject worldObject = new WorldObject(x, y);
            worldObject.addComponent(new BuildingComponent(new ResourceStore(Map.of(ResourceType.WOOD, 50.0))));
            worldObject.addComponent(new ProductionComponent(new ResourceStore(Map.of(ResourceType.STONE, 0.2)), resourceManager));
            worldObject.addComponent(new TypeComponent(WorldObjectType.QUARRY));
            updateManager.registerForTick(worldObject.getComponent(ProductionComponent.class));
            return worldObject;
        });
        
        assemblerRegistry.put(WorldObjectType.BLACKSMITH, (x, y) -> {
            WorldObject worldObject = new WorldObject(x, y);
            worldObject.addComponent(new BuildingComponent(new ResourceStore(Map.of(ResourceType.STONE, 100.0, ResourceType.WOOD, 200.0))));
            worldObject.addComponent(new ProductionComponent(new ResourceStore(Map.of(ResourceType.STONE, -0.2, ResourceType.WOOD, -0.3, ResourceType.IRON, 0.2)), resourceManager));
            worldObject.addComponent(new TypeComponent(WorldObjectType.BLACKSMITH));
            updateManager.registerForTick(worldObject.getComponent(ProductionComponent.class));
            return worldObject;
        });

        assemblerRegistry.put(WorldObjectType.TREE, (x, y) -> {
            WorldObject worldObject = new WorldObject(x, y);
            worldObject.addComponent(new HarvestableComponent(new ResourceStore(Map.of(ResourceType.WOOD, 30.0)), resourceManager));
            worldObject.addComponent(new TypeComponent(WorldObjectType.TREE));
            return worldObject;
        });

        assemblerRegistry.put(WorldObjectType.BOULDER, (x, y) -> {
            WorldObject worldObject = new WorldObject(x, y);
            worldObject.addComponent(new HarvestableComponent(new ResourceStore(Map.of(ResourceType.STONE, 20.0)), resourceManager));
            worldObject.addComponent(new TypeComponent(WorldObjectType.BOULDER));
            return worldObject;
        });
    }
}
