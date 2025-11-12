package me.doubleplusundev.map.worldobject.component;

import java.io.Serializable;

import me.doubleplusundev.map.worldobject.WorldObject;

public abstract class Component implements Serializable{
    protected transient WorldObject owner;

    public void setOwner(WorldObject owner){
        this.owner = owner;
    }

    public WorldObject getOwner() {
        return owner;
    }

    public void initialize() {
        
    }
}
