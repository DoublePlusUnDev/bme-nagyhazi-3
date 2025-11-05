package me.doubleplusundev.map.structures;

import me.doubleplusundev.map.WorldObject;

public abstract  class Structure extends WorldObject {
    StructureType type;

    public StructureType getType() {
        return type;
    }
}
