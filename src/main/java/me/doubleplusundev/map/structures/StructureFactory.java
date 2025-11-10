package me.doubleplusundev.map.structures;

import java.util.Map;

import me.doubleplusundev.map.GameMapHandler;

public class StructureFactory {
    @FunctionalInterface
    interface StructureCreator {
        Structure create(int x, int y, GameMapHandler gameMapHandler);
    }

    private static final Map<StructureType, StructureCreator> registry = Map.ofEntries(
        Map.entry(StructureType.ROAD, (x, y, gameMapHandler) -> new Road(x, y, gameMapHandler)),
        Map.entry(StructureType.CENTER, (x, y, gameMapHandler) -> new Center(x, y, gameMapHandler))
    );

    private StructureFactory() {

    }

    public static Structure create(int xPos, int yPos, StructureType type, GameMapHandler gameMapHandler) {
        StructureCreator structureCreator = registry.get(type);
        
        if (structureCreator == null) {
            throw new IllegalArgumentException("Unregistered structure type: " + type);
        }
        Structure structure = structureCreator.create(xPos, yPos, gameMapHandler);
        structure.create();
        return structure;
    }
}
