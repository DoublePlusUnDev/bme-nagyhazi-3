package me.doubleplusundev.map.resourcenodes;

import me.doubleplusundev.map.WorldObject;

public abstract class ResourceNode extends WorldObject {
    ResourceNodeType type;

    public ResourceNodeType getType() {
        return type;
    }
}
