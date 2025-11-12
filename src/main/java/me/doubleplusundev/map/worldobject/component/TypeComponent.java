package me.doubleplusundev.map.worldobject.component;

import me.doubleplusundev.map.worldobject.WorldObjectType;

public class TypeComponent extends Component {
    private final WorldObjectType type;

    public TypeComponent(WorldObjectType type){
        this.type = type;
    }

    public WorldObjectType getType() {
        return type;
    }
    
}