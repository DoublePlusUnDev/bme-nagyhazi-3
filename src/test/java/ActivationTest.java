import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import me.doubleplusundev.map.GameMap;
import me.doubleplusundev.map.GameMapHandler;
import me.doubleplusundev.map.worldobject.WorldObject;
import me.doubleplusundev.map.worldobject.component.ActivableComponent;
import me.doubleplusundev.map.worldobject.component.ActivationChannelComponent;
import me.doubleplusundev.map.worldobject.component.ActivatorComponent;

class ActivationTest {
    private List<ActivableComponent> activables;
    private List<ActivationChannelComponent> channels;
    private List<ActivatorComponent> activators;
    private GameMapHandler gameMapHandler;

    @Test
    void testDisconnected() {
        tickActivables();
        tickActivators();

        boolean isActive = gameMapHandler.getWorldObject(0, 0).getComponent(ActivableComponent.class).isActive();
        assertFalse(isActive);
    }

    @Test
    void testNextToActivator() {
        tickActivables();
        tickActivators();

        boolean isActive = gameMapHandler.getWorldObject(2, 1).getComponent(ActivableComponent.class).isActive();
        assertTrue(isActive);
    }

    @Test
    void testNextToConnected() {
        tickActivables();
        tickActivators();

        boolean isActive = gameMapHandler.getWorldObject(4, 1).getComponent(ActivableComponent.class).isActive();
        assertFalse(isActive);
    }

    @Test
    void testConnected() {
        tickActivables();
        tickActivators();

        boolean isActive = gameMapHandler.getWorldObject(4, 2).getComponent(ActivableComponent.class).isActive();
        assertTrue(isActive);
    }

    @Test 
    void testBetweenTicks() {
        tickActivables();
        assertFalse(gameMapHandler.getWorldObject(0, 0).getComponent(ActivableComponent.class).isActive());
        assertFalse(gameMapHandler.getWorldObject(2, 1).getComponent(ActivableComponent.class).isActive());
        assertFalse(gameMapHandler.getWorldObject(4, 1).getComponent(ActivableComponent.class).isActive());
        assertFalse(gameMapHandler.getWorldObject(4, 2).getComponent(ActivableComponent.class).isActive());

    }

    private WorldObject createActivable(int posX, int posY) {
        WorldObject worldObject = new WorldObject(posX, posY);
        worldObject.addComponent(new ActivableComponent());
        return worldObject;
    }

    private WorldObject createChannel(int posX, int posY) {
        WorldObject worldObject = new WorldObject(posX, posY);
        worldObject.addComponent(new ActivationChannelComponent());
        return worldObject;
    }

    private WorldObject createActivator(int posX, int posY, GameMapHandler gameMapHandler) {
        WorldObject worldObject = new WorldObject(posX, posY);
        worldObject.addComponent(new ActivatorComponent(gameMapHandler));
        return worldObject;
    }
    
    /*A - activable
      C - channel
      S - source
     * A    
     *   A A
     * CCS A
     * C   C
     * CCCCC
     */
    @BeforeEach 
    void setup() {
        gameMapHandler = new GameMapHandler();
        GameMap map = new GameMap(5, 5);

        int xPos, yPos;

        activables = new ArrayList<>();
        xPos = 0;
        yPos = 0;
        map.setWorldObject(xPos, yPos, createActivable(xPos, yPos));
        activables.add(map.getWorldObject(xPos, yPos).getComponent(ActivableComponent.class));

        xPos = 2;
        yPos = 1;
        map.setWorldObject(xPos, yPos, createActivable(xPos, yPos));
        activables.add(map.getWorldObject(xPos, yPos).getComponent(ActivableComponent.class));

        xPos = 4;
        yPos = 1;
        map.setWorldObject(xPos, yPos, createActivable(xPos, yPos));
        activables.add(map.getWorldObject(xPos, yPos).getComponent(ActivableComponent.class));

        xPos = 4;
        yPos = 2;
        map.setWorldObject(xPos, yPos, createActivable(xPos, yPos));
        activables.add(map.getWorldObject(xPos, yPos).getComponent(ActivableComponent.class));

        channels = new ArrayList<>();

        xPos = 0;
        yPos = 2;
        map.setWorldObject(xPos, yPos, createChannel(xPos, yPos));
        channels.add(map.getWorldObject(xPos, yPos).getComponent(ActivationChannelComponent.class));

        xPos = 1;
        yPos = 2;
        map.setWorldObject(xPos, yPos, createChannel(xPos, yPos));
        channels.add(map.getWorldObject(xPos, yPos).getComponent(ActivationChannelComponent.class));

        xPos = 0;
        yPos = 3;
        map.setWorldObject(xPos, yPos, createChannel(xPos, yPos));
        channels.add(map.getWorldObject(xPos, yPos).getComponent(ActivationChannelComponent.class));

        xPos = 4;
        yPos = 3;
        map.setWorldObject(xPos, yPos, createChannel(xPos, yPos));
        channels.add(map.getWorldObject(xPos, yPos).getComponent(ActivationChannelComponent.class));

        xPos = 0;
        yPos = 4;
        map.setWorldObject(xPos, yPos, createChannel(xPos, yPos));
        channels.add(map.getWorldObject(xPos, yPos).getComponent(ActivationChannelComponent.class));

        xPos = 1;
        yPos = 4;
        map.setWorldObject(xPos, yPos, createChannel(xPos, yPos));
        channels.add(map.getWorldObject(xPos, yPos).getComponent(ActivationChannelComponent.class));

        xPos = 2;
        yPos = 4;
        map.setWorldObject(xPos, yPos, createChannel(xPos, yPos));
        channels.add(map.getWorldObject(xPos, yPos).getComponent(ActivationChannelComponent.class));

        xPos = 3;
        yPos = 4;
        map.setWorldObject(xPos, yPos, createChannel(xPos, yPos));
        channels.add(map.getWorldObject(xPos, yPos).getComponent(ActivationChannelComponent.class));

        xPos = 4;
        yPos = 4;
        map.setWorldObject(xPos, yPos, createChannel(xPos, yPos));
        channels.add(map.getWorldObject(xPos, yPos).getComponent(ActivationChannelComponent.class));

        activators = new ArrayList<>();
        xPos = 2;
        yPos = 2;
        map.setWorldObject(xPos, yPos, createActivator(yPos, yPos, gameMapHandler));
        activators.add(map.getWorldObject(xPos, yPos).getComponent(ActivatorComponent.class));

        gameMapHandler.setMap(map);
    }    

    private void tickActivables() {
        for (ActivableComponent activable : activables) {
            activable.tick(0);
        }
    }

    private void tickActivators() {
        for (ActivatorComponent activator : activators) {
            activator.tick(0);
        }
    }
}
