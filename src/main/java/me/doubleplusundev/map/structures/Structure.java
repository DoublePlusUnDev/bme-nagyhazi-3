package me.doubleplusundev.map.structures;

import me.doubleplusundev.map.GameMapHandler;
import me.doubleplusundev.map.WorldObject;

public abstract  class Structure extends WorldObject {
    protected StructureType type;

    protected Structure(int xPos, int yPos, StructureType type, GameMapHandler gameMapHandler) {
        super(xPos, yPos, gameMapHandler);
        this.type = type;
    }

    public StructureType getType() {
        return type;
    }

    @Override
    public void update() {
        
    }

    @Override
    public void tick(int count) {
        
    }
}
