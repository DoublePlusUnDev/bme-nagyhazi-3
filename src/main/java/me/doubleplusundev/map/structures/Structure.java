package me.doubleplusundev.map.structures;

import me.doubleplusundev.map.WorldObject;

public abstract  class Structure extends WorldObject {
    protected StructureType type;

    protected Structure(StructureType type) {
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
