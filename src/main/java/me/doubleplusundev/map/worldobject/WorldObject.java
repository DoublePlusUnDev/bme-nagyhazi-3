package me.doubleplusundev.map.worldobject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import me.doubleplusundev.map.worldobject.component.Component;

public class WorldObject implements Serializable {
    private final int xPos;
    private final int yPos;
    private final List<Component> components = new ArrayList<>();
    public WorldObject(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void addComponent(Component component) {
        components.add(component);
        component.setOwner(this);
    }

    public List<Component> getComponents() {
        return components;
    }

    public <T extends Component> T getComponent(Class<T> type) {
        for (Component component : components) {
            if (type.isInstance(component)){
                return type.cast(component);
            }
        }
        return null;
    }

    public int getX() {
        return xPos;
    }

    public int getY() {
        return yPos;
    }
}
