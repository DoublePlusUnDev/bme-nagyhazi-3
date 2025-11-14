package me.doubleplusundev.map.worldobject;

/**
 * The type assigned to a worldobject through TypeComponent.
 * It's also used for creating prefabs in the factory. 
 */
public enum WorldObjectType {
    //buildings
    CENTER,
    ROAD,
    LUMBERHUT,
    QUARRY,
    BLACKSMITH,

    //resource nodes
    TREE,
    BOULDER
}
