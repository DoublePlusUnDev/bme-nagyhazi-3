package me.doubleplusundev.map.worldobject.component;

import java.io.Serializable;

import me.doubleplusundev.map.worldobject.WorldObject;

/**
 * An abstract base component, when extended allows the worldobject to have extra optional functions.
 * A World object may have multiple components or even none at all.
 */
public abstract class Component implements Serializable{
    protected transient WorldObject owner; /** The linked gameobject. */

    /**
     * Assigns an owner worldobject.
     * Used when adding to a worldobject or when reloading from savefile.
     * @param owner The new worldobject.
     */
    public void setOwner(WorldObject owner){
        this.owner = owner;
    }

    /**
     * Getter for the linked worldobject.
     * @return
     */
    public WorldObject getOwner() {
        return owner;
    }
}
