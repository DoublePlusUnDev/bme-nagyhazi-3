import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import me.doubleplusundev.map.GameMap;
import me.doubleplusundev.map.TileType;
import me.doubleplusundev.map.worldobject.WorldObject;
import me.doubleplusundev.map.worldobject.WorldObjectType;
import me.doubleplusundev.map.worldobject.component.TypeComponent;

class GameMapTest {
    private GameMap gameMap;

    @Test
    void testGetTile() {
        assertEquals(TileType.GRASS, gameMap.getTile(0, 0));
        assertEquals(TileType.SEA_DEEP, gameMap.getTile(1, 0));
        assertEquals(TileType.GRASS, gameMap.getTile(0, 1));
        assertEquals(TileType.GRASS, gameMap.getTile(1, 1));
    }

    @Test
    void testSetTile() {
        gameMap.setTile(0, 0, TileType.SAND);
        gameMap.setTile(0, 1, TileType.SNOW);

        assertEquals(TileType.SAND, gameMap.getTile(0, 0));
        assertEquals(TileType.SEA_DEEP, gameMap.getTile(1, 0));
        assertEquals(TileType.SNOW, gameMap.getTile(0, 1));
        assertEquals(TileType.GRASS, gameMap.getTile(1, 1));
    }

    @Test
    void testGetWorldObject() {
        assertNotNull(gameMap.getWorldObject(0, 0));
        assertNull(gameMap.getWorldObject(1, 0));
        assertNotNull(gameMap.getWorldObject(0, 1));
        assertNull(gameMap.getWorldObject(1, 1));

        assertEquals(WorldObjectType.TREE, gameMap.getWorldObject(0, 0).getComponent(TypeComponent.class).getType());
        assertEquals(WorldObjectType.BOULDER, gameMap.getWorldObject(0, 1).getComponent(TypeComponent.class).getType());
    }

    @Test
    void testSetWorldObject() {
        gameMap.setWorldObject(0, 0, null);
        WorldObject worldObject = new WorldObject(1, 1);
        worldObject.addComponent(new TypeComponent(WorldObjectType.BLACKSMITH));
        gameMap.setWorldObject(1, 1, worldObject);

        assertNull(gameMap.getWorldObject(0, 0));
        assertNull(gameMap.getWorldObject(1, 0));
        assertNotNull(gameMap.getWorldObject(0, 1));
        assertNotNull(gameMap.getWorldObject(1, 1));

        assertEquals(WorldObjectType.BLACKSMITH, gameMap.getWorldObject(1, 1).getComponent(TypeComponent.class).getType());
        assertEquals(WorldObjectType.BOULDER, gameMap.getWorldObject(0, 1).getComponent(TypeComponent.class).getType());
    }

    @Test 
    void reassignMap() {
        gameMap = new GameMap(3, 4);
        assertEquals(3, gameMap.getWidth());
        assertEquals(4, gameMap.getHeight());

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 4; y++) {
                assertNull(gameMap.getWorldObject(x, y));
            }
        }
    }

    @Test
    void testDimensions() {
        assertEquals(2, gameMap.getWidth());
        assertEquals(2, gameMap.getHeight());
    }

    @BeforeEach
    void setup() {
        gameMap = new GameMap(2, 2);
    
        gameMap.setTile(0, 0, TileType.GRASS);
        gameMap.setTile(1, 0, TileType.SEA_DEEP);
        gameMap.setTile(0, 1, TileType.GRASS);
        gameMap.setTile(1, 1, TileType.GRASS);

        WorldObject worldObject;
        int xPos, yPos;

        xPos = 0;
        yPos = 0;
        worldObject = new WorldObject(xPos, yPos);
        worldObject.addComponent(new TypeComponent(WorldObjectType.TREE));
        gameMap.setWorldObject(xPos, yPos, worldObject);

        xPos = 0;
        yPos = 1;
        worldObject = new WorldObject(xPos, yPos);
        worldObject.addComponent(new TypeComponent(WorldObjectType.BOULDER));
        gameMap.setWorldObject(xPos, yPos, worldObject);
    }   
}
