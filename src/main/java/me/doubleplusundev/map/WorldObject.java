package me.doubleplusundev.map;

import java.io.Serializable;

import me.doubleplusundev.game.ITickable;
import me.doubleplusundev.game.IUpdatable;

public abstract class WorldObject implements IUpdatable, ITickable, Serializable {
    protected final transient GameMapHandler gameMapHandler;
    protected int xPos;
    protected int yPos;
    
    protected WorldObject(int xPos, int yPos, GameMapHandler gameMapHandler) {
        this.gameMapHandler = gameMapHandler;
        this.xPos = xPos;
        this.yPos = yPos;

    }

    public void create () {
        gameMapHandler.setWorldObject(xPos, yPos, this);
    }

    public void destroy() {
        gameMapHandler.setWorldObject(xPos, yPos, null);
    }
}
