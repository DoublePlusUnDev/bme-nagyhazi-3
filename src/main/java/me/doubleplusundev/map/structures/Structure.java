package me.doubleplusundev.map.structures;

import me.doubleplusundev.map.WorldObject;

public abstract  class Structure extends WorldObject {
    protected StructureType type;

    protected Structure(int xPos, int yPos, StructureType type) {
        super(xPos, yPos);
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
