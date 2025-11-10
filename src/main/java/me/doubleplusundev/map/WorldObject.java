package me.doubleplusundev.map;

import java.io.Serializable;

import me.doubleplusundev.game.ITickable;
import me.doubleplusundev.game.IUpdatable;

public abstract class WorldObject implements IUpdatable, ITickable, Serializable {
    protected final transient GameMap gameMap;
    protected int xPos;
    protected int yPos;
    
    protected WorldObject(int xPos, int yPos, GameMap gameMap) {
        this.gameMap = gameMap;
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
