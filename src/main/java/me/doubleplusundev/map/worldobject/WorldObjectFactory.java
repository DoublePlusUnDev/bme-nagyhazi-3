package me.doubleplusundev.map.worldobject;

import java.util.Map;
import java.util.Set;

import me.doubleplusundev.game.UpdateManager;
import me.doubleplusundev.map.GameMapHandler;
import me.doubleplusundev.map.tiles.TileType;
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

/**
 * The class responsible for creating a world object given only it's type.
 */
public class WorldObjectFactory {
   
    private final UpdateManager updateManager;
    private final ResourceManager resourceManager;
    private final GameMapHandler gameMapHandler;
    
    /** A list if tiles that are counted as land tiles, most building will require this preset. */
    private static final Set<TileType> LAND_TILES = Set.of(TileType.GRASS, TileType.SAND, TileType.ROCK, TileType.SNOW); 

    public WorldObjectFactory(UpdateManager updateManager, ResourceManager resourceManager, GameMapHandler gameMapHandler) {
        this.updateManager = updateManager;
        this.resourceManager = resourceManager;
        this.gameMapHandler = gameMapHandler;
    }

    /**
     * Creates a world object of a given type at the specified position.
     * It also sets up it's components, dependencies and registers them.
     * @param type The worldtype, the basis for the preset.
     * @param xPos The X position of the created object.
     * @param yPos The Y position of the created object.
     * @return
     */
    public WorldObject create(WorldObjectType type) {
        switch (type) {
            case TREE -> {
                WorldObject worldObject = new WorldObject();
                worldObject.addComponent(new HarvestableComponent(new ResourceBank(Map.of(ResourceType.WOOD, 30.0))));
                worldObject.addComponent(new TypeComponent(WorldObjectType.TREE));
                return worldObject;
            }
            case BOULDER -> {
                WorldObject worldObject = new WorldObject();
                worldObject.addComponent(new HarvestableComponent(new ResourceBank(Map.of(ResourceType.STONE, 20.0))));
                worldObject.addComponent(new TypeComponent(WorldObjectType.BOULDER));
                return worldObject;
            }
            case CENTER -> {
                WorldObject worldObject = new WorldObject();
                worldObject.addComponent(new BuildingComponent(new ResourceBank(), LAND_TILES));
                worldObject.addComponent(new TypeComponent(WorldObjectType.CENTER));
                worldObject.addComponent(new ActivatonSourceComponent(gameMapHandler));
                updateManager.registerForTick(worldObject.getComponent(ActivatonSourceComponent.class));
                return worldObject;
            }
            case ROAD -> {
                WorldObject worldObject = new WorldObject();
                worldObject.addComponent(new BuildingComponent(new ResourceBank(Map.of(ResourceType.STONE, -20.0)), LAND_TILES));
                worldObject.addComponent(new TypeComponent(WorldObjectType.ROAD));
                worldObject.addComponent(new ActivationChannelComponent());
                return worldObject;
            }
            case LUMBERHUT -> {
                WorldObject worldObject = new WorldObject();
                worldObject.addComponent(new BuildingComponent(new ResourceBank(Map.of(ResourceType.WOOD, -50.0)), LAND_TILES));
                worldObject.addComponent(new ProductionComponent(new ResourceBank(Map.of(ResourceType.WOOD, 0.3)), resourceManager));
                worldObject.addComponent(new TypeComponent(WorldObjectType.LUMBERHUT));
                worldObject.addComponent(new ActivableComponent());
                updateManager.registerForTick(worldObject.getComponent(ProductionComponent.class));
                updateManager.registerForTick(worldObject.getComponent(ActivableComponent.class));
                return worldObject;
            }
            case QUARRY -> {
                WorldObject worldObject = new WorldObject();
                worldObject.addComponent(new BuildingComponent(new ResourceBank(Map.of(ResourceType.WOOD, -50.0)), LAND_TILES));
                worldObject.addComponent(new ProductionComponent(new ResourceBank(Map.of(ResourceType.STONE, 0.2)), resourceManager));
                worldObject.addComponent(new TypeComponent(WorldObjectType.QUARRY));
                worldObject.addComponent(new ActivableComponent());
                updateManager.registerForTick(worldObject.getComponent(ProductionComponent.class));
                updateManager.registerForTick(worldObject.getComponent(ActivableComponent.class));
                return worldObject;
            }
            case BLACKSMITH -> {
                WorldObject worldObject = new WorldObject();
                worldObject.addComponent(new BuildingComponent(new ResourceBank(Map.of(ResourceType.STONE, -100.0, ResourceType.WOOD, -200.0)), LAND_TILES));
                worldObject.addComponent(new ProductionComponent(new ResourceBank(Map.of(ResourceType.STONE, -0.2, ResourceType.WOOD, -0.3, ResourceType.IRON, 0.2)), resourceManager));
                worldObject.addComponent(new TypeComponent(WorldObjectType.BLACKSMITH));
                worldObject.addComponent(new ActivableComponent());
                updateManager.registerForTick(worldObject.getComponent(ProductionComponent.class));
                updateManager.registerForTick(worldObject.getComponent(ActivableComponent.class));
                return worldObject;
            }
            default -> throw new AssertionError();
        }
    }
}
