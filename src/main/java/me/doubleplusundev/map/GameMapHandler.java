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
 * The underlying gamemap can be switched out for a new map at any moement.
 * Keeps count how many of each type of worldobject exists on the map. This is useful for enforcing build limits.
 * Provides helper methods for working with build (tryBuild) and harvest (tryHarvest) componens.
 */
public class GameMapHandler {
     /**
      *  Counter for the different types of objects.
      */
    private Map<WorldObjectType, Integer> worldObjectCount = new EnumMap<>(WorldObjectType.class);

    private GameMap map; /** The internal map. */

    public GameMapHandler() {

        map = new GameMap(0, 0);
    }

    /**
     * Getter for the tile at position X and Y.
     * If tile is out of bounds, defaults to DEEP SEA.
     * @param x X position.
     * @param y Y position.
     * @return The type of the tile.
     */
    public TileType getTile(int x, int y){
        try {
            return map.getTile(x, y);
        }
        catch (IllegalArgumentException e) {
            return TileType.SEA_DEEP;
        }
    }

    /**
     * Setter for the tile at position X and Y.
     * If tile is out of bounds, it will be ignored.
     * @param x X position.
     * @param y Y position.
     * @param tile New tile type.
     */
    public void setTile(int x, int y, TileType tile) {
        try {
            map.setTile(x, y, tile);
        }
        catch (IllegalArgumentException e) {
            // Intentionally ignored — invalid placement is not fatal
        }
    }

    /**
     * Getter for the worldobject at position X and Y.
     * If there is no worldobject it will return null.
     * @param x X position.
     * @param y Y position. 
     * @return Worldobject at position.
     */
    public WorldObject getWorldObject(int x, int y){
        try {
            return map.getWorldObject(x, y);
        }
        catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * Sets worldobject at position X and Y.
     * @param x X position.
     * @param y Y position.
     * @param worldObject The new worldobject.
     */
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

    /**
     * Attempts to build a worldobject with a building component at position X and Y.
     * Checks whether the gridspace is empty.
     * Runs the checks of the buildingcomponent.
     * If neither fails it places the object.
     * @param x
     * @param y
     * @param buildingComponent
     * @param resourceBank
     * @return
     */
    public boolean tryBuild(int x, int y, BuildingComponent buildingComponent, ResourceBank resourceBank) {
        if (getWorldObject(x, y) != null)
            return false;

        int instanceCount = getWorldObjectCount(buildingComponent.getOwner());
        if (!buildingComponent.tryBuild(resourceBank, getTile(x, y), instanceCount))
            return false;
        
        
        setWorldObject(x, y, buildingComponent.getOwner());
        return true;
    } 

    /**
     * Attempts to harvest a worldobject, at position X and Y.
     * If a harvestable component is present, the removal will only be possible if the conditions are met.
     * @param x X position.
     * @param y Y position.
     * @param resourceBank The resource source for the harvestable component.
     */
    public void tryHarvest(int x, int y, ResourceBank resourceBank) {
        if(getWorldObject(x, y) == null) 
            return;

        HarvestableComponent harvestable = map.getWorldObject(x, y).getComponent(HarvestableComponent.class);
        if (harvestable == null)
            return;

        if (!harvestable.tryHarvest(resourceBank))
           return;

        setWorldObject(x, y, null);
    }

    /**
     * Getter for a map object.
     * You should use the map write only, as directly accessing the map won't update counters in maphandler.
     * Should really only be used for serialization.
     * @return The map object.
     */
    public GameMap getMap() {
        return map;
    }

    /**
     * Overwrites the map object.
     * Will rebuild worldobject counters.
     * Intended for deserialization and setting a completely new map.
     * @param map The new map object.
     */
    public final void setMap(GameMap map) {
        worldObjectCount = new EnumMap<>(WorldObjectType.class);

        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                addToCounter(map.getWorldObject(x, y), 1);
                
            }
        }

        this.map = map;
    }

    /**
     * Gets the number of the given type of worldobject in the map.
     * @param worldObjectType The type of worldobject.
     * @return The number of worldobjects of the given type present.
     */
    public int getWorldObjectCount(WorldObjectType worldObjectType) {
        return worldObjectCount.getOrDefault(worldObjectType,  0);
    }

    /**
     * Returns the number of worldobjects matching the type of the given world object.
     * If the worldobject has no type 0 will be returned.
     * @param worldObject The object whose type will be matched.
     * @return The number of worldobjects with matching type.
     */
    public int getWorldObjectCount(WorldObject worldObject) {
        TypeComponent typeComponent = worldObject.getComponent(TypeComponent.class);
        if (typeComponent == null)
            return 0;

        return worldObjectCount.getOrDefault(typeComponent.getType(),  0);
    }

    /**
     * Increments the counter for the selected type of worldobject by the given amount.
     * @param worldObjectType The worldobject type.
     * @param amount The amount.
     */
    private void addToCounter(WorldObjectType worldObjectType, int amount) {
        if (worldObjectCount.containsKey(worldObjectType))
            worldObjectCount.merge(worldObjectType, amount, Integer::sum);
        else
            worldObjectCount.put(worldObjectType, amount);
    }

    /**
     * Increments the counter for the type of the selected worldobject by the given amount.
     * @param worldObjectType The worldobject whose type will be used.
     * @param amount The amount.
     */
    private void addToCounter(WorldObject worldObject, int amount) {
        if (worldObject == null)
            return;
        
        TypeComponent typeComponent = worldObject.getComponent(TypeComponent.class);
        if (typeComponent == null)
            return;

        addToCounter(typeComponent.getType(), amount);
    }

    /**
     * Getter for the width of the map.
     * @return Width of the map.
     */
    public int getWidth() {
        return map.getWidth();
    }

    /**
     * Getter for the height of the map.
     * @return Height of the map.
     */
    public int getHeight() {
        return map.getHeight();
    }
}
