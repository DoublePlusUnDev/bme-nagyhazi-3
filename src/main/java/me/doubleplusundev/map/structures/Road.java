package me.doubleplusundev.map.structures;

import me.doubleplusundev.map.GameMapHandler;

public class Road extends Structure {
    public Road(int xPos, int yPos, GameMapHandler gameMapHandler) {
        super(xPos, yPos, StructureType.ROAD, gameMapHandler);
    }
}
