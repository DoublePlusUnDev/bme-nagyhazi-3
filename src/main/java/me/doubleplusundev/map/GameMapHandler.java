package me.doubleplusundev.map;

import me.doubleplusundev.map.worldgen.WorldGenerator;
import me.doubleplusundev.map.worldobject.WorldObject;
import me.doubleplusundev.map.worldobject.WorldObjectFactory;
import me.doubleplusundev.map.worldobject.WorldObjectType;
import me.doubleplusundev.map.worldobject.component.HarvestableComponent;

public class GameMapHandler {
    private GameMap map;
    private final WorldObjectFactory worldObjectFactory;

    public GameMapHandler(WorldObjectFactory worldObjectFactory) {
        this.worldObjectFactory = worldObjectFactory;
        this.map = WorldGenerator.generateWorld(500, 500, 0, worldObjectFactory);
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

    public void buildStructure(int x, int y, WorldObjectType type) {
        if (0 <= x && x < map.getWidth() && 0 <= y && y < map.getHeight()) {
            map.setWorldObject(x, y, worldObjectFactory.create(type, x, y));
        }
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

            if (canDestroy)
                map.setWorldObject(x, y, null);
            
        }
    }

    public void setWorldObject(int x, int y, WorldObject object) {
        if (0 <= x && x < map.getWidth() && 0 <= y && y < map.getHeight()) {
            map.setWorldObject(x, y, object);
        }
    }    

    public GameMap getMap() {
        return map;
    }

    public void setMap(GameMap map) {
        this.map = map;
    }
}
