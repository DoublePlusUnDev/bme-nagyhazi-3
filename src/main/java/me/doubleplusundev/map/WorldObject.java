package me.doubleplusundev.map;

import java.io.Serializable;

import me.doubleplusundev.game.ITickable;
import me.doubleplusundev.game.IUpdatable;
import me.doubleplusundev.game.UpdateManager;
import me.doubleplusundev.resource.ResourceManager;

public abstract class WorldObject implements IUpdatable, ITickable, Serializable {
    protected final transient GameMap gameMap;
    protected final transient ResourceManager resourceManager;
    protected final transient UpdateManager updateManager;
    protected int xPos;
    protected int yPos;
    
    protected WorldObject(int xPos, int yPos, GameMap gameMap, ResourceManager resourceManager, UpdateManager updateManager) {
        this.gameMap = gameMap;
        this.resourceManager = resourceManager;
        this.updateManager = updateManager;
        this.xPos = xPos;
        this.yPos = yPos;

    }

    public void create () {
        gameMap.setWorldObject(xPos, yPos, this);
        updateManager.registerForUpdate(this);
        updateManager.registerForTick(this);
    }

    public void destroy() {
        gameMap.setWorldObject(xPos, yPos, null);
    }
}
