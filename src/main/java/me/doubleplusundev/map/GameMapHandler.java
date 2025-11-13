package me.doubleplusundev.map;

import java.util.EnumMap;
import java.util.Map;

import me.doubleplusundev.map.worldobject.WorldObject;
import me.doubleplusundev.map.worldobject.WorldObjectType;
import me.doubleplusundev.map.worldobject.component.BuildingComponent;
import me.doubleplusundev.map.worldobject.component.HarvestableComponent;
import me.doubleplusundev.map.worldobject.component.TypeComponent;
import me.doubleplusundev.resource.ResourceManager;

public class GameMapHandler {
    private Map<WorldObjectType, Integer> worldObjectCount = new EnumMap<>(WorldObjectType.class);

    private GameMap map;

    public GameMapHandler() {

        map = new GameMap(0, 0);
    }

    public TileType getTile(int x, int y){
        if (0 <= x && x < map.getWidth() && 0 <= y && y < map.getHeight())
            return map.getTile(x, y);
        else
            return TileType.SEA_DEEP;
    }

    public WorldObject getWorldObject(int x, int y){
        if (0 <= x && x < map.getWidth() && 0 <= y && y < map.getHeight())
            return map.getWorldObject(x, y);
        else
            return null;
    }

    public boolean tryBuildStructure(int x, int y, WorldObject worldObject, ResourceManager resourceManager) {
        if (x < 0 || map.getWidth() <= x || y < 0 || map.getHeight() <= y)
            return false;
        
        if (getWorldObject(x, y) != null)
            return false;

        BuildingComponent buildingComponent = worldObject.getComponent(BuildingComponent.class);
        int instanceCount = getWorldObjectCount(worldObject.getComponent(TypeComponent.class).getType());
        if (buildingComponent != null && !buildingComponent.tryBuild(resourceManager.getResources(), getTile(x, y), instanceCount))
            return false;
        
        
        setWorldObject(x, y, worldObject);
        return true;
    }

    public void destroyWorldObject(int x, int y) {
        if (0 <= x && x < map.getWidth() && 0 <= y && y < map.getHeight() && map.getWorldObject(x, y) != null) {
            boolean canDestroy = true;
            
            HarvestableComponent harvestable = map.getWorldObject(x, y).getComponent(HarvestableComponent.class);
            if (harvestable != null) {
                boolean canHarvest = harvestable.tryHarvest();
                if (!canHarvest) {
                    canDestroy = false;
                }
            }

            if (canDestroy) {
                WorldObject worldObject = map.getWorldObject(x, y);
                map.setWorldObject(x, y, null);
    
                if (worldObject != null)
                    addWorldObjectToCounter(worldObject.getComponent(TypeComponent.class).getType(), -1);
            }
        }
    }

    public void setWorldObject(int x, int y, WorldObject worldObject) {
        if (0 <= x && x < map.getWidth() && 0 <= y && y < map.getHeight()) {
            map.setWorldObject(x, y, worldObject);
            addWorldObjectToCounter(worldObject.getComponent(TypeComponent.class).getType(), 1);
        }
    }    

    public GameMap getMap() {
        return map;
    }

    public final void setMap(GameMap map) {
        worldObjectCount = new EnumMap<>(WorldObjectType.class);

        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                WorldObject worldObject = map.getWorldObject(x, y);
                if (worldObject != null) {
                    addWorldObjectToCounter(worldObject.getComponent(TypeComponent.class).getType(), 1);
                }
            }
        }

        this.map = map;
    }

    private int getWorldObjectCount(WorldObjectType worldObjectType) {
        return worldObjectCount.getOrDefault(worldObjectType,  0);
    }

    private void addWorldObjectToCounter(WorldObjectType worldObjectType, int amount) {
        if (worldObjectCount.containsKey(worldObjectType))
            worldObjectCount.merge(worldObjectType, amount, Integer::sum);
        else
            worldObjectCount.put(worldObjectType, amount);
    }

    public int getWidth() {
        return map.getWidth();
    }

    public int getHeight() {
        return map.getHeight();
    }
}
