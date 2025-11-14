package me.doubleplusundev.map.worldobject;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

import me.doubleplusundev.game.UpdateManager;
import me.doubleplusundev.map.GameMapHandler;
import me.doubleplusundev.map.TileType;
import me.doubleplusundev.map.worldobject.component.ActivableComponent;
import me.doubleplusundev.map.worldobject.component.ActivationChannelComponent;
import me.doubleplusundev.map.worldobject.component.ActivatonSourceComponent;
import me.doubleplusundev.map.worldobject.component.BuildingComponent;
import me.doubleplusundev.map.worldobject.component.HarvestableComponent;
import me.doubleplusundev.map.worldobject.component.ProductionComponent;
import me.doubleplusundev.map.worldobject.component.TypeComponent;
import me.doubleplusundev.resource.ResourceManager;
import me.doubleplusundev.resource.ResourceBank;
import me.doubleplusundev.resource.ResourceType;

public class WorldObjectFactory {
    
    @FunctionalInterface
    interface WorldObjectAssembler {
        WorldObject create(int posX, int posY);
    }

    private final Map<WorldObjectType, WorldObjectAssembler> assemblerRegistry = new EnumMap<>(WorldObjectType.class);

    private final UpdateManager updateManager;
    private final ResourceManager resourceManager;
    private final GameMapHandler gameMapHandler;
    
    private static final Set<TileType> LAND_TILES = Set.of(TileType.GRASS, TileType.SAND, TileType.ROCK, TileType.SNOW);

    public WorldObjectFactory(UpdateManager updateManager, ResourceManager resourceManager, GameMapHandler gameMapHandler) {
        this.updateManager = updateManager;
        this.resourceManager = resourceManager;
        this.gameMapHandler = gameMapHandler;

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
            worldObject.addComponent(new BuildingComponent(new ResourceBank(), LAND_TILES));
            worldObject.addComponent(new TypeComponent(WorldObjectType.CENTER));
            worldObject.addComponent(new ActivatonSourceComponent(gameMapHandler));
            updateManager.registerForTick(worldObject.getComponent(ActivatonSourceComponent.class));
            return worldObject;
        });

        assemblerRegistry.put(WorldObjectType.ROAD, (x, y) -> {
            WorldObject worldObject = new WorldObject(x, y);
            worldObject.addComponent(new BuildingComponent(new ResourceBank(Map.of(ResourceType.STONE, -20.0)), LAND_TILES));
            worldObject.addComponent(new TypeComponent(WorldObjectType.ROAD));
            worldObject.addComponent(new ActivationChannelComponent());
            return worldObject;
        });

        assemblerRegistry.put(WorldObjectType.LUMBERHUT, (x, y) -> {
            WorldObject worldObject = new WorldObject(x, y);
            worldObject.addComponent(new BuildingComponent(new ResourceBank(Map.of(ResourceType.WOOD, -50.0)), LAND_TILES));
            worldObject.addComponent(new ProductionComponent(new ResourceBank(Map.of(ResourceType.WOOD, 0.3)), resourceManager));
            worldObject.addComponent(new TypeComponent(WorldObjectType.LUMBERHUT));
            worldObject.addComponent(new ActivableComponent());
            updateManager.registerForTick(worldObject.getComponent(ProductionComponent.class));
            updateManager.registerForTick(worldObject.getComponent(ActivableComponent.class));
            return worldObject;
        });

        assemblerRegistry.put(WorldObjectType.QUARRY, (x, y) -> {
            WorldObject worldObject = new WorldObject(x, y);
            worldObject.addComponent(new BuildingComponent(new ResourceBank(Map.of(ResourceType.WOOD, -50.0)), LAND_TILES));
            worldObject.addComponent(new ProductionComponent(new ResourceBank(Map.of(ResourceType.STONE, 0.2)), resourceManager));
            worldObject.addComponent(new TypeComponent(WorldObjectType.QUARRY));
            worldObject.addComponent(new ActivableComponent());
            updateManager.registerForTick(worldObject.getComponent(ProductionComponent.class));
            updateManager.registerForTick(worldObject.getComponent(ActivableComponent.class));
            return worldObject;
        });
        
        assemblerRegistry.put(WorldObjectType.BLACKSMITH, (x, y) -> {
            WorldObject worldObject = new WorldObject(x, y);
            worldObject.addComponent(new BuildingComponent(new ResourceBank(Map.of(ResourceType.STONE, -100.0, ResourceType.WOOD, -200.0)), LAND_TILES));
            worldObject.addComponent(new ProductionComponent(new ResourceBank(Map.of(ResourceType.STONE, -0.2, ResourceType.WOOD, -0.3, ResourceType.IRON, 0.2)), resourceManager));
            worldObject.addComponent(new TypeComponent(WorldObjectType.BLACKSMITH));
            worldObject.addComponent(new ActivableComponent());
            updateManager.registerForTick(worldObject.getComponent(ProductionComponent.class));
            updateManager.registerForTick(worldObject.getComponent(ActivableComponent.class));
            return worldObject;
        });

        assemblerRegistry.put(WorldObjectType.TREE, (x, y) -> {
            WorldObject worldObject = new WorldObject(x, y);
            worldObject.addComponent(new HarvestableComponent(new ResourceBank(Map.of(ResourceType.WOOD, 30.0))));
            worldObject.addComponent(new TypeComponent(WorldObjectType.TREE));
            return worldObject;
        });

        assemblerRegistry.put(WorldObjectType.BOULDER, (x, y) -> {
            WorldObject worldObject = new WorldObject(x, y);
            worldObject.addComponent(new HarvestableComponent(new ResourceBank(Map.of(ResourceType.STONE, 20.0))));
            worldObject.addComponent(new TypeComponent(WorldObjectType.BOULDER));
            return worldObject;
        });
    }
}
