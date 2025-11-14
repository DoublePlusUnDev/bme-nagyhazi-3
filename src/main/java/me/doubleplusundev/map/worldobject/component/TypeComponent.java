package me.doubleplusundev.map.worldobject.component;

import me.doubleplusundev.map.worldobject.WorldObjectType;

/**
 * World objects can be part of bigger categories.
 * The category is specified by the WorldObjectType enum.
 */
public class TypeComponent extends Component {
    private final WorldObjectType type;

    public TypeComponent(WorldObjectType type){
        this.type = type;
    }

    public WorldObjectType getType() {
        return type;
    }
    
}