package me.doubleplusundev.map.structures;

import java.util.Map;

import me.doubleplusundev.map.GameMap;
import me.doubleplusundev.resource.ResourceManager;

public class StructureFactory {
    @FunctionalInterface
    interface StructureCreator {
        Structure create(int x, int y, GameMap gameMap, ResourceManager resourceManager);
    }

    private static final Map<StructureType, StructureCreator> registry = Map.ofEntries(
        Map.entry(StructureType.ROAD, Road::new),
        Map.entry(StructureType.CENTER, Center::new)
    );

    private StructureFactory() {

    }

    public static Structure create(int xPos, int yPos, StructureType type, GameMap gameMap, ResourceManager resourceManager) {
        StructureCreator structureCreator = registry.get(type);
        
        if (structureCreator == null) {
            throw new IllegalArgumentException("Unregistered structure type: " + type.toString());
        }
        Structure structure = structureCreator.create(xPos, yPos, gameMap, resourceManager);
        structure.create();
        return structure;
    }
}
