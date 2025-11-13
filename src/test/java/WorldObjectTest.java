import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import me.doubleplusundev.map.worldobject.WorldObject;
import me.doubleplusundev.map.worldobject.WorldObjectType;
import me.doubleplusundev.map.worldobject.component.ActivableComponent;
import me.doubleplusundev.map.worldobject.component.TypeComponent;

class WorldObjectTest {
    @Test
    void testInit() {
        WorldObject worldObject = new WorldObject(6, 7);
        assertEquals(6, worldObject.getX());
        assertEquals(7, worldObject.getY());

        assertEquals(0, worldObject.getComponents().size());
        assertNull(worldObject.getComponent(TypeComponent.class));
    } 

    @Test
    void addComponent() {
        WorldObject worldObject = new WorldObject(6, 7);

        assertNull(worldObject.getComponent(TypeComponent.class));

        worldObject.addComponent(new TypeComponent(WorldObjectType.BLACKSMITH));
    
        assertNotNull(worldObject.getComponent(TypeComponent.class));
        assertEquals(WorldObjectType.BLACKSMITH, worldObject.getComponent(TypeComponent.class).getType());
    }

    @Test
    void listComponents() {
        WorldObject worldObject = new WorldObject(6, 7);

        worldObject.addComponent(new TypeComponent(WorldObjectType.BLACKSMITH));
        worldObject.addComponent(new ActivableComponent());
    
        assertNotNull(worldObject.getComponent(TypeComponent.class));
        assertEquals(2, worldObject.getComponents().size());
    }
}
