package me.doubleplusundev.map.worldobject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import me.doubleplusundev.map.worldobject.component.Component;

/**
 * An object can be placed in the worldgrid.
 * Unlike tiles it has a state and not every gridspace requires a worldobject.
 * May have several component that allow for extra functionalites or behaviour.
 */
public class WorldObject implements Serializable {
    private final int xPos; /** X position on the map. */
    private final int yPos; /** Y position on the map. */
    private final List<Component> components = new ArrayList<>(); /** The component list. */
    
    public WorldObject(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    /**
     * Adds a new component to the worldobject.
     * May have several components of the same size.
     * @param component
     */
    public void addComponent(Component component) {
        components.add(component);
        component.setOwner(this);
    }

    /**
     * Returns the entires list of components.
     * @return
     */
    public List<Component> getComponents() {
        return components;
    }

    /**
     * Returns every component of the given type.
     * @param <T> Type of component.
     * @param type
     * @return
     */
    public <T extends Component> List<T> getComponents(Class<T> type) {
        List<T> foundComponents = new ArrayList<>();
        
        for (Component component : components) {
            if (type.isInstance(component)){
                foundComponents.add(type.cast(component));
            }
        }
        return foundComponents;
    }

    /**
     * Returns the first component of a certain type.
     * @param <T> Type of component.
     * @param type
     * @return
     */
    public <T extends Component> T getComponent(Class<T> type) {
        for (Component component : components) {
            if (type.isInstance(component)){
                return type.cast(component);
            }
        }
        return null;
    }

    /**
     * Getter the position on the X axis. 
     * @return
     */
    public int getX() {
        return xPos;
    }

    /**
     * Getter the position on the Y axis. 
     * @return
     */
    public int getY() {
        return yPos;
    }
}
