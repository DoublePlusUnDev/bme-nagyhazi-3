package me.doubleplusundev.map;

import java.io.Serializable;

import me.doubleplusundev.game.ITickable;
import me.doubleplusundev.game.IUpdatable;
import me.doubleplusundev.resource.ResourceManager;

public abstract class WorldObject implements IUpdatable, ITickable, Serializable {
    protected final transient GameMap gameMap;
    protected final transient ResourceManager resourceManager;
    protected int xPos;
    protected int yPos;
    
    protected WorldObject(int xPos, int yPos, GameMap gameMap, ResourceManager resourceManager) {
        this.gameMap = gameMap;
        this.resourceManager = resourceManager;
        this.xPos = xPos;
        this.yPos = yPos;

    }

    public void create () {
        gameMap.setWorldObject(xPos, yPos, this);
    }

    public void destroy() {
        gameMap.setWorldObject(xPos, yPos, null);
    }
}
