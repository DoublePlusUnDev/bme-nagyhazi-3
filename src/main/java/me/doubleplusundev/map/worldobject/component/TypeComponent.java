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

    /**
     * Getter for the type enum.
     * @return
     */
    public WorldObjectType getType() {
        return type;
    }
    
}