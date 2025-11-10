package me.doubleplusundev.map.structures;

import java.util.Map;

import me.doubleplusundev.map.GameMap;

public class StructureFactory {
    @FunctionalInterface
    interface StructureCreator {
        Structure create(int x, int y, GameMap gameMap);
    }

    private static final Map<StructureType, StructureCreator> registry = Map.ofEntries(
        Map.entry(StructureType.ROAD, Road::new),
        Map.entry(StructureType.CENTER, Center::new)
    );

    private StructureFactory() {

    }

    public static Structure create(int xPos, int yPos, StructureType type, GameMap gameMap) {
        StructureCreator structureCreator = registry.get(type);
        
        if (structureCreator == null) {
            throw new IllegalArgumentException("Unregistered structure type: " + type);
        }
        Structure structure = structureCreator.create(xPos, yPos, gameMap);
        structure.create();
        return structure;
    }
}
