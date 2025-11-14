package me.doubleplusundev.map;

import java.util.EnumMap;
import java.util.Map;

import me.doubleplusundev.map.worldobject.WorldObject;
import me.doubleplusundev.map.worldobject.WorldObjectType;
import me.doubleplusundev.map.worldobject.component.BuildingComponent;
import me.doubleplusundev.map.worldobject.component.HarvestableComponent;
import me.doubleplusundev.map.worldobject.component.TypeComponent;
import me.doubleplusundev.resource.ResourceBank;

/**
 * Wrapper for the gamemap.
 * 
 */
public class GameMapHandler {
    private Map<WorldObjectType, Integer> worldObjectCount = new EnumMap<>(WorldObjectType.class);

    private GameMap map;

    public GameMapHandler() {

        map = new GameMap(0, 0);
    }

    public TileType getTile(int x, int y){
        try {
            return map.getTile(x, y);
        }
        catch (IllegalArgumentException e) {
            return TileType.SEA_DEEP;
        }
    }

    public void setTile(int x, int y, TileType tile) {
        try {
            map.setTile(x, y, tile);
        }
        catch (IllegalArgumentException e) {
            // Intentionally ignored — invalid placement is not fatal
        }
    }

    public WorldObject getWorldObject(int x, int y){
        try {
            return map.getWorldObject(x, y);
        }
        catch (IllegalArgumentException e) {
            return null;
        }
    }

    public void setWorldObject(int x, int y, WorldObject worldObject) {
        try {
            addToCounter(map.getWorldObject(x, y), -1);
            
            map.setWorldObject(x, y, worldObject);
            
            addToCounter(worldObject, 1);
        }
        catch (IllegalArgumentException e) {
            // Intentionally ignored — invalid placement is not fatal
        }
    }   

    public boolean tryBuildStructure(int x, int y, WorldObject worldObject, ResourceBank resourceBank) {
        if (x < 0 || map.getWidth() <= x || y < 0 || map.getHeight() <= y)
            return false;
        
        if (getWorldObject(x, y) != null)
            return false;

        BuildingComponent buildingComponent = worldObject.getComponent(BuildingComponent.class);
        int instanceCount = getWorldObjectCount(worldObject.getComponent(TypeComponent.class).getType());
        if (buildingComponent != null && !buildingComponent.tryBuild(resourceBank, getTile(x, y), instanceCount))
            return false;
        
        
        setWorldObject(x, y, worldObject);
        return true;
    } 

    public void destroyWorldObject(int x, int y, ResourceBank resourceBank) {
        if (0 <= x && x < map.getWidth() && 0 <= y && y < map.getHeight() && map.getWorldObject(x, y) != null) {   
            HarvestableComponent harvestable = map.getWorldObject(x, y).getComponent(HarvestableComponent.class);
            if (harvestable != null) {
                boolean canHarvest = harvestable.tryHarvest(resourceBank);
                if (!canHarvest) {
                    return;
                }
            }

            setWorldObject(x, y, null);
        }
    }

    public GameMap getMap() {
        return map;
    }

    public final void setMap(GameMap map) {
        worldObjectCount = new EnumMap<>(WorldObjectType.class);

        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                addToCounter(map.getWorldObject(x, y), 1);
                
            }
        }

        this.map = map;
    }

    private int getWorldObjectCount(WorldObjectType worldObjectType) {
        return worldObjectCount.getOrDefault(worldObjectType,  0);
    }

    private void addToCounter(WorldObjectType worldObjectType, int amount) {
        if (worldObjectCount.containsKey(worldObjectType))
            worldObjectCount.merge(worldObjectType, amount, Integer::sum);
        else
            worldObjectCount.put(worldObjectType, amount);
    }

    private void addToCounter(WorldObject worldObject, int amount) {
        if (worldObject == null)
            return;
        
        TypeComponent typeComponent = worldObject.getComponent(TypeComponent.class);
        if (typeComponent == null)
            return;

        addToCounter(typeComponent.getType(), amount);
    }

    public int getWidth() {
        return map.getWidth();
    }

    public int getHeight() {
        return map.getHeight();
    }
}
