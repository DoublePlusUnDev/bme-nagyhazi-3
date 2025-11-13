import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

import me.doubleplusundev.resource.ResourceBank;
import me.doubleplusundev.resource.ResourceType;

class ResourceBankTest {


    @Test
    void emptyInitTest() {
        ResourceBank resourceBank1 = new ResourceBank();

        assertEquals(0, resourceBank1.getResource(ResourceType.WOOD));
        assertEquals(0, resourceBank1.getResource(ResourceType.STONE));
        assertEquals(0, resourceBank1.getResource(ResourceType.IRON));
    }

    @Test
    void notEmptyInitTest() {
        ResourceBank resourceBank1 = new ResourceBank(Map.of(ResourceType.WOOD, 5.0, ResourceType.STONE, 2.0));
        assertEquals(5, resourceBank1.getResource(ResourceType.WOOD));
        assertEquals(2, resourceBank1.getResource(ResourceType.STONE));
        assertEquals(0, resourceBank1.getResource(ResourceType.IRON));
    }

    @Test 
    void setterTest() {
        ResourceBank resourceBank1 = new ResourceBank(Map.of(ResourceType.WOOD, 5.0, ResourceType.STONE, 2.0));
        resourceBank1.setResource(ResourceType.STONE, -5);
        resourceBank1.setResource(ResourceType.IRON, 2);

        assertEquals(5, resourceBank1.getResource(ResourceType.WOOD));
        assertEquals(-5, resourceBank1.getResource(ResourceType.STONE));
        assertEquals(2, resourceBank1.getResource(ResourceType.IRON));
    }

    @Test
    void positiveTryMergeTest() {
        ResourceBank resourceBank1 = new ResourceBank(Map.of(ResourceType.WOOD, 5.0, ResourceType.STONE, 2.0));
        ResourceBank resourceBank2 = new ResourceBank(Map.of(ResourceType.IRON, 5.0, ResourceType.STONE, 2.0));
    
        assertTrue(resourceBank1.tryMerge(resourceBank2));

        assertEquals(5, resourceBank1.getResource(ResourceType.WOOD));
        assertEquals(4, resourceBank1.getResource(ResourceType.STONE));
        assertEquals(5, resourceBank1.getResource(ResourceType.IRON));
    }
    
    @Test
    void tryMergeFail() {
        ResourceBank resourceBank1 = new ResourceBank(Map.of(ResourceType.WOOD, 5.0, ResourceType.STONE, 2.0));
        ResourceBank resourceBank2 = new ResourceBank(Map.of(ResourceType.WOOD, -6.0, ResourceType.IRON, -2.0));
    
        assertFalse(resourceBank1.tryMerge(resourceBank2));

        assertEquals(5, resourceBank1.getResource(ResourceType.WOOD));
        assertEquals(2, resourceBank1.getResource(ResourceType.STONE));
        assertEquals(0, resourceBank1.getResource(ResourceType.IRON));
    }

     @Test
    void negativeForceMerge() {
        ResourceBank resourceBank1 = new ResourceBank(Map.of(ResourceType.WOOD, 5.0, ResourceType.STONE, 2.0));
        ResourceBank resourceBank2 = new ResourceBank(Map.of(ResourceType.WOOD, -6.0, ResourceType.IRON, -2.0));
    
        resourceBank1.forceMerge(resourceBank2);

        assertEquals(-1, resourceBank1.getResource(ResourceType.WOOD));
        assertEquals(2, resourceBank1.getResource(ResourceType.STONE));
        assertEquals(-2, resourceBank1.getResource(ResourceType.IRON));
    }
}
