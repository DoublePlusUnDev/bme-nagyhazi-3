package me.doubleplusundev.map.structures;

import java.util.Map;

import me.doubleplusundev.map.GameMap;

public class StructureFactory {
    @FunctionalInterface
    interface StructureCreator {
        Structure create(int x, int y);
    }

    private static Map<StructureType, StructureCreator> registry = Map.ofEntries(
        Map.entry(StructureType.ROAD, (x, y) -> new Road(x, y)),
        Map.entry(StructureType.CENTER, (x, y) -> new Center(x, y))
    );
    
    public static Structure create(GameMap map, int xPos, int yPos, StructureType type) {
        StructureCreator structureCreator = registry.get(type);
        
        if (structureCreator == null) {
            throw new IllegalArgumentException("Unregistered structure type: " + type);
        }
        Structure structure = structureCreator.create(xPos, yPos);
        structure.create();
        return structure;
    }
}
