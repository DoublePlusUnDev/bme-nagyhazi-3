package me.doubleplusundev.map;

import me.doubleplusundev.game.ITickable;
import me.doubleplusundev.game.IUpdatable;

public abstract class WorldObject implements IUpdatable, ITickable {
    protected int xPos;
    protected int yPos;
    
    public WorldObject(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;

    }

    public void create () {
        GameMapHandler.getInstance().setWorldObject(xPos, yPos, this);
    }

    public void destroy() {
        GameMapHandler.getInstance().setWorldObject(xPos, yPos, null);
    }
}
